package com.RanReco.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.RanReco.service.ReplyService;
import com.RanReco.vo.LikeVO;
import com.RanReco.vo.ReplyVO;


@Controller
public class ReplyController {
	
	@Resource(name="replyService")
	private ReplyService replyService;

	@RequestMapping(value = "/writeReply.do")
	public String writeReply(ReplyVO vo, HttpSession session) {
		vo.setMemberIdx((int)session.getAttribute("userIdx"));
		replyService.writeReply(vo);
		return "redirect:viewBoard.do?idx=" + vo.getBoardIdx();
	}
	
	@RequestMapping(value = "/deleteReply.do")
	public String deleteReply(ReplyVO vo, LikeVO lvo, HttpServletRequest request, HttpSession session) {
		lvo.setReplyIdx(vo.getIdx());
		int boardIdx = vo.getBoardIdx();
		
		replyService.deleteReply(vo);
		replyService.replyLikeDelete(lvo.getReplyIdx());
		return "redirect:viewBoard.do?idx=" + boardIdx;
	}


	@RequestMapping(value = "/replyList.do")
	public String replyList(ReplyVO vo, LikeVO lvo, Model model, HttpSession session) {
		lvo.setMemberIdx((int)session.getAttribute("userIdx"));
		
		List<ReplyVO> replyList =replyService.replyList(vo); // 댓글리스트 호춣
		
		for (ReplyVO rvo : replyList) {
			for (LikeVO rlvo : replyService.reply_likeList(lvo)) { // 댓글에 대한 좋아요 리스트 호출
				if (rvo.getIdx() == rlvo.getReplyIdx()) { // 댓글 번호가 같으면
					rvo.setReply_like(rlvo.getLikes());	// 좋아요 정보를 댓글 정보에 넘겨줌
				}
			}
		}
		
		model.addAttribute("replyList", replyList);	// 불러온 댓글 정보를 뱉어냄
		return "boardView";		// boardView.jsp로 이동
	}

	@RequestMapping(value = "/replyLike.do")
	@ResponseBody
	public String replyLike(HttpServletRequest request, HttpSession session) throws Exception {
		String mvo = (String)session.getAttribute("userName");
		if(mvo == null)
		{
			return "N";
		}
		
		LikeVO lvo = new LikeVO();
		ReplyVO rvo = new ReplyVO();
		lvo.setReplyIdx(Integer.parseInt(request.getParameter("reply_idx")));
		lvo.setBoardIdx(Integer.parseInt(request.getParameter("board_idx")));
		lvo.setMemberIdx(Integer.parseInt(request.getParameter("member_idx")));
		lvo.setLikes(request.getParameter("reply_like"));
		lvo.setIdx(lvo.getReplyIdx()+"/"+lvo.getBoardIdx()+"/"+lvo.getMemberIdx());
		rvo.setIdx(lvo.getReplyIdx());
		
		ReplyVO replyVO = replyService.selectReply(rvo);
		int likes_cnt = replyVO.getLikesCnt();
		int hates_cnt = replyVO.getHatesCnt();
		
		String result_like = "";
		switch(lvo.getLikes()) {
		case "T":
			System.out.println("case T");
			replyService.replyLikeCancle(lvo);	// 좋아요 상태에서 좋아요를 한번 더 누르면  -> 좋아요 삭제
			
			likes_cnt -= 1;	// 좋아요 카운트 -1
			replyVO.setLikesCnt(likes_cnt);
			replyService.writeReply(replyVO);
			break;
		case "F":
			System.out.println("case F");
			replyService.replyHateCancle(lvo);
			replyService.replyLike(lvo);		// 싫어요 상태에서 좋아요를 누르면  -> 싫어요 삭제 + 좋아요
			
			likes_cnt += 1; // 좋아요 카운트 +1
			hates_cnt -= 1;	// 싫어요 카운트 -1
			replyVO.setLikesCnt(likes_cnt);
			replyVO.setHatesCnt(hates_cnt);
			replyService.writeReply(replyVO);
			result_like = "T";
			break;
		default:
			System.out.println("case Default");
			replyService.replyLike(lvo);	// 아무것도 없는 상태에서 좋아요를 누르면 -> 좋아요
			
			likes_cnt += 1; // 좋아요 카운트 +1
			replyVO.setLikesCnt(likes_cnt);
			replyService.writeReply(replyVO);
			result_like = "T";
			break;
		}
		
		return result_like;
	}

	@RequestMapping(value = "/replyHate.do")
	@ResponseBody
	public String replyHate(HttpServletRequest request, HttpSession session) {
		String mvo = (String)session.getAttribute("userName");
		if(mvo == null)
		{
			return "N";
		}
		LikeVO lvo = new LikeVO();
		ReplyVO rvo = new ReplyVO();
		lvo.setReplyIdx(Integer.parseInt(request.getParameter("reply_idx")));
		lvo.setBoardIdx(Integer.parseInt(request.getParameter("board_idx")));
		lvo.setMemberIdx(Integer.parseInt(request.getParameter("member_idx")));
		lvo.setLikes(request.getParameter("reply_like"));
		lvo.setIdx(lvo.getReplyIdx()+"/"+lvo.getBoardIdx()+"/"+lvo.getMemberIdx());
		rvo.setIdx(lvo.getReplyIdx());
		ReplyVO replyVO = replyService.selectReply(rvo);
		int likes_cnt = replyVO.getLikesCnt();
		int hates_cnt = replyVO.getHatesCnt();
		
		String result_like = "";
		switch(lvo.getLikes()) {
		case "T": 
			replyService.replyLikeCancle(lvo);	// 좋아요 상태에서 싫어요를 누르면  -> 좋아요 삭제  + 싫어요
			replyService.replyHate(lvo);
			
			likes_cnt -= 1; // 좋아요 카운트 -1
			hates_cnt += 1;	// 싫어요 카운트 +1
			replyVO.setLikesCnt(likes_cnt);
			replyVO.setHatesCnt(hates_cnt);
			replyService.writeReply(replyVO);
			result_like = "F";
			break;
		case "F": 
			replyService.replyHateCancle(lvo);	// 싫어요 상태에서 싫어요를 누르면  -> 싫어요 삭제
			hates_cnt -= 1;	// 싫어요 카운트 -1
			replyVO.setHatesCnt(hates_cnt);
			replyService.writeReply(replyVO);
			break;
		default : 
			replyService.replyHate(lvo);	// 아무것도 없는 상태에서 싫어요를 누르면 -> 싫어요
			hates_cnt += 1;	// 싫어요 카운트 +1
			replyVO.setHatesCnt(hates_cnt);
			replyService.writeReply(replyVO);
			result_like = "F";
			break; 
		}
		
		return result_like;
	}

}
