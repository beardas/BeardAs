package com.RanReco.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.RanReco.service.MemberLocationService;
import com.RanReco.service.MemberResultService;
import com.RanReco.service.MemberService;
import com.RanReco.util.kakao.KakaoLoginVO;
import com.RanReco.util.naver.NaverJsonParser;
import com.RanReco.util.naver.NaverLoginVO;
import com.RanReco.vo.MemberResultVO;
import com.RanReco.vo.MemberSVO;
import com.RanReco.vo.MemberVO;
import com.RanReco.vo.Paging;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;



@Controller
public class MemberController {
   @Resource(name="memberService")
   private MemberService memberService;
   @Resource(name="memberLocationService")
   private MemberLocationService memberLocationService;
   @Resource(name="memberResultService")
   private MemberResultService memberResultService;
   
   private NaverLoginVO naverLoginVO;
   private KakaoLoginVO kakaoLoginVO;
   
   @Autowired
   private void setKakaoLoginVO(KakaoLoginVO kakaoLoginVO) {
      this.kakaoLoginVO = kakaoLoginVO;
   }
   
   
    @Autowired
    private void setNaverLoginVO(NaverLoginVO naverLoginVO) {
        this.naverLoginVO = naverLoginVO;
    }
   
   /**
    * 로그인 페이지
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value={"/changPw.do"})
   public String changePw(HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         
      }catch(Exception e) {
         
      }
      return "changePassword";
   }
   
   @RequestMapping(value={"/logout.do"})
   public String logout(HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         session.invalidate();
      }catch(Exception e) {
         
      }
      return "index.do";
   }
   
   
   @RequestMapping(value={"/login.do"})
   public String login(HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {} catch(Exception e) {}
      return "login";
   }
   
   // 네이버 아이디로 로그인하기
   @RequestMapping(value="/naverLogin.do", method = RequestMethod.GET)
   public String naverLogin(Model model, HttpSession session) {
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginVO.getAuthorizationUrl(session);
        
        //네이버 
        model.addAttribute("naverUrl", naverAuthUrl);

        /* 생성한 인증 URL을 View로 전달 */
        return "naverLogin";
   }
   
   
   @RequestMapping(value = "/callback", method = {RequestMethod.GET, RequestMethod.POST})
   public String callback(@RequestParam String code, @RequestParam String state, HttpSession session, Model model, MemberVO member) throws Exception {
      /* 네아로 인증이 성공적으로 완료되면 code 파라미터가 전달되며 이를 통해 access token을 발급 */
      NaverJsonParser json = new NaverJsonParser();
      
      OAuth2AccessToken oauthToken = naverLoginVO.getAccessToken(session, code, state);
      String apiResult = naverLoginVO.getUserProfile(oauthToken);
      member.setProvider("Naver");
      member = json.changeJson(apiResult); // vo에 userEmail, userGender, userNaver 저장
      int resultIdx = 0;
      
      if (memberService.selectNaver(member) > 0) { // 세션만들기
         session.setAttribute("userName", member.getName());
         session.setAttribute("userIdx", member.getIdx());
         session.setAttribute("userProvider", member.getProvider());
      } else {
         memberService.insertNaver(member);
         session.setAttribute("userName", member.getName());
         session.setAttribute("userIdx", member.getIdx());
         session.setAttribute("userProvider", member.getProvider());
      }
      
      MemberVO resultVO = memberService.selectMemberByIdByPw(member);
      if(resultVO != null) {
         resultIdx = resultVO.getIdx();
      }
      session.setAttribute("userIdx", resultIdx);
      
      return "naverCallback";
   }
   
   // 카카오 아이디로 로그인하기
   @RequestMapping(value= {"/kakaoLogin.do"}, method = RequestMethod.GET)
   public String kakaoLogin(Model model, HttpSession session) {
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String kakaoUrl = kakaoLoginVO.getAuthorizationUrl(session);
        
        /* 생성한 인증 URL을 View로 전달 */
        model.addAttribute("kakaoUrl", kakaoUrl);

        /* 생성한 인증 URL을 View로 전달 */
        return "kakaoLogin";
      
   }
   
   @RequestMapping(value={"/kakaoLogout.do"})
   public String kakaoLogout(Model model, HttpSession session) throws Exception{
         String kLogout = kakaoLoginVO.Logout(session);
         model.addAttribute("kLogout", kLogout);
      return "kakaoLogout";
   }
   
   @RequestMapping(value={"/kakaoLogout"})
   public String kakaoLogoutCallback(Model model, HttpSession session) throws Exception{
      session.invalidate();
      return "index.do";
   }
   
   
   @RequestMapping(value = "/oauth", produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
   public ModelAndView getKakaoSignIn(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, HttpSession session, MemberVO member) throws Exception {
      ModelAndView mav = new ModelAndView(); // 결과값을 node에 담아줌
      JsonNode node = KakaoLoginVO.getAccessToken(code); // accessToken에 사용자의 로그인한 모든 정보가 들어있음
      JsonNode accessToken = node.get("access_token"); // 사용자의 정보
      JsonNode userInfo = KakaoLoginVO.getKakaoUserInfo(accessToken);
      String kemail, kname, kimage = null;
      int resultIdx = 0;

      // 유저정보 카카오에서 가져오기 Get properties
      JsonNode properties = userInfo.path("properties");
      JsonNode kakao_account = userInfo.path("kakao_account");
      
      System.out.println(properties);
      
      kemail = kakao_account.path("email").asText();
      kname = properties.path("nickname").asText();
      kimage = properties.path("profile_image").asText();
      
      member.setFileName(kimage);
      member.setId(kemail);
      member.setEmail(kemail);
      member.setName(kname);
      member.setProvider("Kakao");
      
      if (memberService.selectNaver(member) > 0) { // 세션만들기
         session.setAttribute("userName", member.getName());
         session.setAttribute("userProvider", member.getProvider());
      } else {
         memberService.insertNaver(member);
         session.setAttribute("userName", member.getName());
         session.setAttribute("userProvider", member.getProvider());
      }
      
      MemberVO resultVO = memberService.selectMemberByIdByPw(member);
      if(resultVO != null) {
         resultIdx = resultVO.getIdx();
      }
      session.setAttribute("userIdx", resultIdx);
      
      
      mav.setViewName("kakaoCallback");
      
      return mav;
   }

      


   
   @RequestMapping(value={"","/index.do"})
   public String index(MemberResultVO vo, HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
    	  int memberIdx = (int)session.getAttribute("userIdx");
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		  Calendar c1 = Calendar.getInstance();
    	  vo.setIdx(memberIdx+"/"+sdf.format(c1.getTime()));
    	  MemberResultVO resultVO = memberResultService.selectMemberResult(vo);
    	  
    	  model.addAttribute("resultVO", resultVO);
    	  session.setAttribute("today", sdf.format(c1.getTime()));
    	  
      }catch(Exception e) {
         
      }
      
      if(session.getAttribute("userIdx") == null) {
         session.setAttribute("userIdx", 0);
      }
      
      return "weatherView.do";
   }
   
   /**
    * 로그인 
    * @param memberVO
    * @param request
    * @param response
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/loginAction.do")
   public @ResponseBody String loginAction(@ModelAttribute("memberVO")MemberVO member, 
         HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{
      String result = "N";
      int resultIdx = 0;
      String resultAdmin = "";
      String resultProvider = "";
      
      try {
         MemberVO resultVO = memberService.selectMemberByIdByPw(member);
         
         // 멤버의 정보가 있다면, 멤버의 이름을 리턴
         if(resultVO != null) {
            result = resultVO.getName();
            resultIdx = resultVO.getIdx();
            resultAdmin = resultVO.getAdmin();
            resultProvider = resultVO.getProvider();
         }

      }catch(Exception e) {
         e.printStackTrace();
      }
      SimpleDateFormat r_date = new SimpleDateFormat("yyyyMMdd");
      Date rDate = new Date();
      String date = r_date.format(rDate);
      
      session.setAttribute("today", date);
      session.setAttribute("userName", result);
      session.setAttribute("userIdx", resultIdx);
      session.setAttribute("userProvider", resultProvider);
      session.setAttribute("userAdmin", resultAdmin);
      return result;
   }
   
   
   @RequestMapping(value={"/findMember.do"})
   public String findMember(HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         
      }catch(Exception e) {
         
      }
      return "findId";
   }
   
   @RequestMapping(value="/findId.do")
   public @ResponseBody String findMember(@ModelAttribute("memberVO")MemberVO member, 
         HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{
      String result = "N";
      
      try {
         MemberVO resultVO = memberService.selectMemberByphoneNumber(member);
         
         // 멤버의 정보가 있다면, 멤버의 이름을 리턴
         if(resultVO != null) {
            result = resultVO.getId();
         }
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return result;
   }
   
   @RequestMapping(value={"/findMemberPw.do"})
   public String findMemberPw(HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         
      }catch(Exception e) {
         
      }
      return "findPw";
   }
   
   @RequestMapping(value="/findPw.do")
   public @ResponseBody String findPw(@ModelAttribute("memberVO")MemberVO member, 
         HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{
      String result = "N";
      
      try {
         MemberVO resultVO = memberService.selectMemberById(member);
         
         // 멤버의 정보가 있다면, 멤버의 이름을 리턴
         if(resultVO != null) {
            result = resultVO.getPassword();
         }
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return result;
   }
   
   
   /**
    * 멤버 목록 조회
    * @param pageable
    * @param memberSVO
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/selectMemberList.do")
   public String selectMemberList(Pageable pageable, @ModelAttribute("memberSVO")MemberVO memberSVO, 
         HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         // 목록 조회
         Page<MemberVO> resultList = memberService.selectMemberList(memberSVO, pageable);
         // 해당 목록의 페이징
         Paging pagingVO = memberService.setPaging(pageable.getPageSize(), pageable.getPageNumber());
         
         model.addAttribute("result", resultList);
         model.addAttribute("pagingVO", pagingVO);
         model.addAttribute("resultList", resultList.getContent());
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return "memberList";
   }
   
   /**
    * 멤버 등록화면 이동
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/fwdMemberReg.do")
   public String fwdMemberReg(HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         
      }catch(Exception e) {
         
      }
      
      return "memberReg";
   }
   /**
    * 멤버 등록
    * @param files
    * @param memberVO
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/insertMember.do")
   public String insertMember(@RequestParam("userImageAttachFile") MultipartFile files, 
         @ModelAttribute("memberVO")MemberVO memberVO, HttpServletRequest request, 
         HttpSession session, Model model) throws Exception{
      try {
         memberVO.setProvider("RanReco");
         memberService.insertMember(files, memberVO);
         
         model.addAttribute("message", "회원가입 되셨습니다");
         model.addAttribute("memberVO", memberVO);
         model.addAttribute("returnURL", "/login.do");
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return "resultBody";
   }
   
   /**
    * 멤버 수정화면 이동(사용자)
    * @param memberVO
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/fwdMemberUpt.do")
   public String fwdMemberUpt(@ModelAttribute("memberVO")MemberVO memberVO, 
         HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         MemberVO resultVO = memberService.selectMember(memberVO);
         
         model.addAttribute("resultVO", resultVO);
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return "memberUpt";
   }
   /**
    * 멤버 수정(사용자)
    * @param files
    * @param memberVO
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/updateMember.do")
   public String updateMember(@RequestParam("userImageAttachFile") MultipartFile files,
         @ModelAttribute("MemberVO")MemberVO memberVO, HttpServletRequest request, 
         HttpSession session, Model model) throws Exception{
      try {
         memberService.updateMember(files, memberVO);
         
         model.addAttribute("message", "수정되었습니다. 다시 로그인 하세요.");
         model.addAttribute("memberVO", memberVO);
         model.addAttribute("returnURL", "/index.do");
         session.invalidate();
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return "resultBody";
   }
   /**
    * 멤버 삭제(관리자)
    * @param boardSVO
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/deleteMemberByAdmin.do")
   public String deleteMemberByAdmin(@ModelAttribute("memberSVO")MemberSVO memberSVO, 
         HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         memberService.deleteMemberByAdmin(memberSVO);
         
         model.addAttribute("message", "회원 삭제되었습니다.");
         model.addAttribute("returnURL", "/selectMemberList.do?size=5");
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return "resultBody";
   }
   
   /**
    * 멤버 삭제
    * @param memberSVO
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/deleteMember.do")
   public String deleteMember(@ModelAttribute("MemberVO")MemberVO member, 
         HttpServletRequest request, HttpSession session, Model model) throws Exception{
      try {
         memberService.deleteMember(member);
         
         model.addAttribute("message", "회원 탈퇴 하셨습니다.");
         model.addAttribute("returnURL", "/index.do");
         session.invalidate();
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return "resultBody";
   }
   
   
   /**
    * 주소 검색 팝업페이지 이동
    * @param request
    * @param session
    * @param model
    * @throws Exception
    */
   @RequestMapping(value="/fwdSearchAddressPopup.do")
   public String fwdSearchAddressPopup(HttpServletRequest request, 
         HttpSession session, Model model) throws Exception{
      try {
         
      }catch(Exception e) {
         
      }
      
      return "addressAPIPopup";
   }

   @RequestMapping(value="/selectMyResult.do")
	public String selectMyResult(@ModelAttribute("memberResultVO")MemberResultVO memberResultVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			MemberResultVO resultVO = memberResultService.selectMemberResult(memberResultVO);
			
			model.addAttribute("resultVO", resultVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "myResult";
	}

   
}