package com.RanReco.controller;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RanReco.service.MemberLocationService;
import com.RanReco.service.MemberResultService;
import com.RanReco.util.weather.Coord;
import com.RanReco.util.weather.LocationCodeFetcher;
import com.RanReco.util.weather.WeatherFetcher;
import com.RanReco.util.weather.WeatherSet;
import com.RanReco.vo.MemberLocationVO;
import com.RanReco.vo.MemberResultVO;

@Controller
public class MemberLocationController {

	@Resource(name="memberLocationService")
	private MemberLocationService memberLocationService;
	@Resource(name="memberResultService")
	private MemberResultService memberResultService;
	
	
	@RequestMapping(value={"/selectLocation.do"})
	public String kakaoMap(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "location";
	}
	

	@RequestMapping(value="/locationPopup.do")
	public String locationPopup( @ModelAttribute("memberLocationVO")MemberLocationVO memberLocationVO, @ModelAttribute("memberResultVO")MemberResultVO memberResultVO,HttpServletRequest request, 
	HttpSession session, Model model) throws Exception{
	
	try {
		memberLocationService.insertLocation(memberLocationVO);
		
		memberResultVO.setIdx(memberResultVO.getMemberIdx()+"/"+memberResultVO.getRegDate());
  	  MemberResultVO resultVO2 = memberResultService.selectMemberResult(memberResultVO);
  	  if(resultVO2==null) {
  		  memberResultService.insertMemberResult(memberResultVO);
  	  } else {
  		  resultVO2.setMemberLocation(memberResultVO.getMemberLocation());
  		  resultVO2.setMemberLocationFile(memberResultVO.getMemberLocationFile());
  		  memberResultService.insertMemberResult(resultVO2);
  	  }
		
			model.addAttribute("message", "?????? ???????????????");
			model.addAttribute("locationVO", memberLocationVO);
			model.addAttribute("memberResultVO", resultVO2);
			model.addAttribute("returnURL", "/index.do");
	}catch(Exception e) {
		e.printStackTrace();
	}
	session.setAttribute("userLoc", memberLocationVO.getMemberLocation());
	return "resultBody";
}
	
	
	@RequestMapping(value="/weatherView.do")
	   public String weatherView(MemberLocationVO vo, 
	         HttpServletRequest request, HttpSession session, Model model) throws Exception {
	    int memberIdx = (int)session.getAttribute("userIdx");
	    String[] location;
	    String loc = "";
	    MemberLocationVO resultVO = memberLocationService.selectLocation(memberIdx);
	    if(resultVO != null ) {
	       location = new String[] { resultVO.getSido(), resultVO.getSigungu(), resultVO.getDong() };
	       loc = resultVO.getMemberLocation();
	    } else {
	        location = new String[] { "???????????????", "??????", "??????" };
	     }
	    String locationStr = "";
	    for(int i = 0; i < location.length; i++) {
	       locationStr += (" " + location[i]);  
	    }
	     Coord coLocationCode = null;
	     WeatherSet weather = null;
	     LocationCodeFetcher lcf = new LocationCodeFetcher();
	     WeatherFetcher wf = new WeatherFetcher();
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy??? MM??? dd??? hh???");
	     
	     
	     coLocationCode = lcf.fetchLocationCode(location);
	     weather = wf.fetchWeather(coLocationCode.getSx(), coLocationCode.getSy());
	     System.out.println(locationStr + "??? ?????? ????????? " + weather.getT3h()+ "???, ??????????????? " + weather.getPop() + "%, ??? ????????? " + weather.getPty() + ", ????????? " + weather.getSky() + "?????????.");
	     
	     session.setAttribute("resultLoc", locationStr); // ??????
	     session.setAttribute("resultLand", loc); // ??????
	     session.setAttribute("resultTime", sdf.format(weather.getBaseDate())); // ????????????
	     session.setAttribute("resultTem", weather.getT3h()); // ??????
	     session.setAttribute("resultPer", weather.getPop()); // ?????? ??????
	     session.setAttribute("resultRain", weather.getPty()); // ??? ??????
	     session.setAttribute("resultSky", weather.getSky()); // ??????
	     
	     
	     return "index";
	   }
	
	
}




