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

import com.RanReco.service.NoticeService;
import com.RanReco.vo.NoticeSVO;
import com.RanReco.vo.NoticeVO;
import com.RanReco.vo.Paging;


@Controller
public class NoticeController {
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	/**
	 * 게시글 목록 조회
	 * @param pageable
	 * @param noticeSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectNoticeList.do")
	public String selectNoticeList(Pageable pageable, @ModelAttribute("NoticeVO")NoticeVO noticeSVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			// 목록 조회
			Page<NoticeVO> resultList = noticeService.selectNoticeList(noticeSVO, pageable);
			// 해당 목록의 페이징
			Paging pagingVO = noticeService.setPaging(pageable.getPageSize(), pageable.getPageNumber());
			
			model.addAttribute("resultNotice", resultList);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("resultNoticeList", resultList.getContent());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "noticeList";
	}
	
	// 게시글 조회
	@RequestMapping(value="/viewNotice.do")
	public String viewNotice(@ModelAttribute("NoticeVO")NoticeVO notice, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			NoticeVO resultView = noticeService.selectNotice(notice);
			
			model.addAttribute("resultView", resultView);
		} catch(Exception e) {}
		
		return "noticeView";
	}
	
	/**
	 * 게시글 작성 화면 이동
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdNoticeReg.do")
	public String fwdNoticeReg(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {} catch(Exception e) {}
		
		return "noticeReg";
	}
	/**
	 * 게시글 등록
	 * @param files
	 * @param notice
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insertNotice.do")
	public String insertNotice(@ModelAttribute("NoticeVO")NoticeVO notice, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
			notice.setWriter((String)session.getAttribute("userName"));
		try {
			noticeService.insertNotice(notice);
			
			model.addAttribute("message", "공지가 등록 되었습니다");
			model.addAttribute("notice", notice);
			model.addAttribute("returnURL", "/viewNotice.do?idx="+notice.getIdx());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 수정 화면 이동
	 * @param notice
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdNoticeUpt.do")
	public String fwdNoticeUpt(@ModelAttribute("NoticeVO")NoticeVO notice, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
			notice.setWriter((String)session.getAttribute("userName"));
		try {
			NoticeVO resultVO = noticeService.selectNotice(notice);
			
			model.addAttribute("resultVO", resultVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "noticeUpt";
	}
	/**
	 * 게시글 수정
	 * @param files
	 * @param notice
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateNotice.do")
	public String updateNotice(@ModelAttribute("NoticeVO")NoticeVO notice, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			noticeService.updateNotice(notice);
			
			model.addAttribute("message", "공지가 수정되었습니다.");
			model.addAttribute("notice", notice);
			model.addAttribute("returnURL", "/viewNotice.do?idx="+notice.getIdx());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 삭제(관리자)
	 * @param noticeSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteNoticeByAdmin.do")
	public String deleteNoticeByAdmin(@ModelAttribute("NoticeSVO")NoticeSVO noticeSVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			noticeService.deleteNoticeByAdmin(noticeSVO);
			
			model.addAttribute("message", "공지가 삭제되었습니다.");
			model.addAttribute("returnURL", "/selectNoticeList.do?size=10");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	@RequestMapping(value="/deleteNotice.do")
	public String deleteNotice(@ModelAttribute("NoticeVO")NoticeVO notice, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			noticeService.deleteNotice(notice);
			
			model.addAttribute("message", "공지가 삭제되었습니다.");
			model.addAttribute("returnURL", "/selectNoticeList.do?size=10");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
}
