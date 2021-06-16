package com.RanReco.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.RanReco.service.FoodService;
import com.RanReco.vo.FoodVO;
import com.RanReco.vo.Paging;

@Controller
public class FoodController {
	


	@Resource(name="foodService")
	private FoodService foodService;
	
	
	
	@RequestMapping(value={"/kakaomap.do"})
	public String kakaoMap(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "kakaomap";
	}
	
	
	
	@RequestMapping(value={"/randomStoreInfo.do"})
	public String randomStoreInfo(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
		}catch(Exception e) {
			
		}
		return "randomStoreInfo";
	}
	
	
	@RequestMapping(value="/foodInsert.do")
	public String insertFood(@RequestParam("userImageAttachFile") MultipartFile files, 
	@ModelAttribute("foodVO")FoodVO foodVO, HttpServletRequest request, 
	HttpSession session, Model model) throws Exception{
	
	try {
		foodService.insertFood(files, foodVO);
		
		model.addAttribute("message", "등록 되셨습니다");
		model.addAttribute("foodVO", foodVO);
		model.addAttribute("returnURL", "/selectFoodList.do?size=10");
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return "resultBody";
}
	
	
	@RequestMapping(value="/selectFoodList.do")
	public String selectFoodList(Pageable pageable, @ModelAttribute("foodVO")FoodVO FoodSVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			// 목록 조회
			Page<FoodVO> resulFoodtList = foodService.selectFoodList(FoodSVO, pageable);
			// 해당 목록의 페이징
			Paging pagingVO = foodService.setPaging(pageable.getPageSize(), pageable.getPageNumber());
			
			model.addAttribute("resultFood", resulFoodtList);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("resultFoodList", resulFoodtList.getContent());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "foodList";
	}
	
	// 게시글 조회
	@RequestMapping(value="/viewFood.do")
	public String viewBoard(@ModelAttribute("foodVO")FoodVO foodVO, HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			FoodVO resultView = foodService.selectFood(foodVO);
			
			model.addAttribute("resultView", resultView);
		} catch(Exception e) {
		}
		return "foodView";
	}
	
	
	@RequestMapping(value="/FoodUpt.do")
	public String FoodUpt(@ModelAttribute("foodVO")FoodVO foodVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			FoodVO resultVO = foodService.selectFood(foodVO);
			
			model.addAttribute("resultVO", resultVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "foodUpt";
	}

	
	@RequestMapping(value="/updateFood.do")
	public String updateFood(@RequestParam("userImageAttachFile") MultipartFile files, @ModelAttribute("foodVO")FoodVO foodVO, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			foodService.updateFood(files, foodVO);
			
			model.addAttribute("message", "게시글이 수정되었습니다.");
			model.addAttribute("foodVO", foodVO);
			model.addAttribute("returnURL", "/selectFoodList.do?size=10");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	

}




