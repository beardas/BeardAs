package com.RanReco.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RanReco.service.MemberFoodService;
import com.RanReco.service.MemberResultService;
import com.RanReco.vo.MemberFoodVO;
import com.RanReco.vo.MemberResultVO;

@Controller
public class MemberFoodController {

	@Resource(name="memberFoodService")
	private MemberFoodService memberFoodService;
	@Resource(name="memberResultService")
	private MemberResultService memberResultService;
	
	@RequestMapping(value={"/selectMemberFood.do"})
	public String memberFood(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "memberFood";
	}
	

	@RequestMapping(value="/memberFoodPopup.do")
	public String memberFoodPopup( @ModelAttribute("memberFoodVO")MemberFoodVO memberFoodVO, @ModelAttribute("memberResultVO")MemberResultVO memberResultVO,HttpServletRequest request, 
	HttpSession session, Model model) throws Exception{
	
	try {
		int memberIdx = (int)session.getAttribute("userIdx");
		memberFoodVO.setMemberIdx(memberIdx);
		SimpleDateFormat r_date = new SimpleDateFormat("yyyyMMdd");
		Date rDate = new Date();
		String regDate = r_date.format(rDate);
		
		memberFoodService.insertMemberFood(memberFoodVO);
		MemberFoodVO resultVO = memberFoodService.selectMemberFood(memberFoodVO);
		System.out.println(resultVO.getMemberFood());
		System.out.println(resultVO.getMemberFoodFile());
		
		memberResultVO.setIdx(memberIdx+"/"+regDate);
  	    MemberResultVO resultVO2 = memberResultService.selectMemberResult(memberResultVO);
  	  if(resultVO2==null) {
  		  memberResultVO.setMemberFood(resultVO.getMemberFood());
  		  memberResultVO.setMemberFoodFile(resultVO.getMemberFoodFile());
  		  memberResultService.insertMemberResult(memberResultVO);
  	  } else {
  	  resultVO2.setMemberFood(resultVO.getMemberFood());
  	  resultVO2.setMemberFoodFile(resultVO.getMemberFoodFile());
  	  memberResultService.insertMemberResult(resultVO2);
  	  }
		
			model.addAttribute("message", "등록 되셨습니다");
			model.addAttribute("memberFoodVO", memberFoodVO);
			model.addAttribute("memberResultVO", resultVO2);
			model.addAttribute("returnURL", "/selectFoodList.do");
	}catch(Exception e) {
		e.printStackTrace();
	}
	session.setAttribute("memberFood", memberFoodVO.getMemberFood());
	return "resultBody";
}
}




