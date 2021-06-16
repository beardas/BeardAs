package com.RanReco.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RanReco.service.FashionService;
import com.RanReco.service.MemberFashionService;
import com.RanReco.service.MemberResultService;
import com.RanReco.vo.FashionVO;
import com.RanReco.vo.MemberFashionVO;
import com.RanReco.vo.MemberResultVO;




@Controller
public class FashionController {
	@Resource(name="fashionService")
	private FashionService fashionService;
	@Resource(name="memberFashionService")
	private MemberFashionService memberFashionService;
	@Resource(name="memberResultService")
	private MemberResultService memberResultService;
	
	
	/**
	 * 게시글 목록 조회
	 * @param pageable
	 * @param BoardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectFashionList.do")
	public String selectfashionList(@ModelAttribute("fashion")FashionVO vo, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "fashionList";
	}
	
	@RequestMapping(value="/selectLookList.do")
	public String selectLookList(@ModelAttribute("fashion")FashionVO vo, @ModelAttribute("memberFashion")MemberFashionVO memberFashionVO,@ModelAttribute("memberResultVO")MemberResultVO memberResultVO,
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			// 목록 조회
			List<FashionVO> resultFashionList = fashionService.selectFashionList(vo);
			memberFashionService.insertMemberFashion(memberFashionVO); 
			MemberFashionVO resultMemberFashion = memberFashionService.selectMemberFashion(memberFashionVO);
			
	      	  
			model.addAttribute("resultFashion", resultFashionList);
			model.addAttribute("resultMemberFashion", resultMemberFashion); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "fashionList";
	}
	
	
	@RequestMapping(value="/noselectLookList.do")
	public String selectLookList(@ModelAttribute("fashion")FashionVO vo,
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			// 목록 조회
			List<FashionVO> resultFashionList = fashionService.selectFashionList(vo);
		
			model.addAttribute("resultFashion", resultFashionList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "fashionList";
	}
	
	@RequestMapping(value="/memberFashionPopup.do")
	public String updateMemberFashion(@ModelAttribute("memberFashionVO")MemberFashionVO memberFashionVO, @ModelAttribute("memberResultVO")MemberResultVO memberResultVO,HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			int memberIdx = (int)session.getAttribute("userIdx");
			memberFashionVO.setMemberIdx(memberIdx);
			SimpleDateFormat r_date = new SimpleDateFormat("yyyyMMdd");
			Date rDate = new Date();
			String regDate = r_date.format(rDate);
			
			MemberFashionVO resultVO = memberFashionService.selectMemberFashion(memberFashionVO);
			resultVO.setMemberFashionFile(memberFashionVO.getMemberFashionFile());
			
			memberFashionService.updateMemberFashion(resultVO);
			
			memberResultVO.setIdx(memberIdx+"/"+regDate);
	    	  MemberResultVO resultVO2 = memberResultService.selectMemberResult(memberResultVO);
	    	  if(resultVO2==null) {
	    		  memberResultVO.setLook(resultVO.getLook());
	    		  memberResultVO.setMemberFashionFile(resultVO.getMemberFashionFile());
	    		  memberResultService.insertMemberResult(memberResultVO);
	    	  } else {
	    	  resultVO2.setLook(resultVO.getLook());
	      	  resultVO2.setMemberFashionFile(resultVO.getMemberFashionFile());
	      	  memberResultService.insertMemberResult(resultVO2);
	    	  }
			
			
			model.addAttribute("message", "등록되셨습니다");
			model.addAttribute("memberResultVO", resultVO2);
			model.addAttribute("returnURL", "/index.do");
			
			
			session.setAttribute("userLook", resultVO.getLook());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	
}
