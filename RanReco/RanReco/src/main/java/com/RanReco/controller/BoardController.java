package com.RanReco.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.RanReco.service.BoardService;
import com.RanReco.service.ReplyService;
import com.RanReco.vo.BoardLikeVO;
import com.RanReco.vo.BoardSVO;
import com.RanReco.vo.BoardVO;
import com.RanReco.vo.LikeVO;
import com.RanReco.vo.Paging;
import com.RanReco.vo.ReplyVO;



@Controller
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="replyService")
	private ReplyService replyService;
	
	/**
	 * 게시글 목록 조회
	 * @param pageable
	 * @param BoardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectBoardList.do")
	   public String selectBoardList(Pageable pageable, @ModelAttribute("board")BoardVO BoardSVO, 
	         HttpServletRequest request, HttpSession session, Model model) throws Exception{
	      try {
	         // 목록 조회
	         Page<BoardVO> resultList = boardService.selectBoardList(BoardSVO, pageable);
	         // 명예의 전당 TOP3
	         List<BoardVO> rankList = boardService.rankBoardLike(BoardSVO);
	         // 해당 목록의 페이징
	         Paging pagingVO = boardService.setPaging(pageable.getPageSize(), pageable.getPageNumber());
	         
	         model.addAttribute("resultBoard", resultList);
	         model.addAttribute("rankBoard", rankList);
	         model.addAttribute("pagingVO", pagingVO);
	         model.addAttribute("resultBoardList", resultList.getContent());
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return "boardList";
	   }
	
	
	// 게시글 조회
	@RequestMapping(value="/viewBoard.do")
	public String viewBoard(@ModelAttribute("board")BoardVO board, BoardLikeVO bvo, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		bvo.setMemberIdx((int)session.getAttribute("userIdx"));
		bvo.setBoardIdx(board.getIdx());
		try {
			BoardVO resultView = boardService.selectBoard(board);
			
			BoardLikeVO likeList = boardService.likeList(bvo);
			if(likeList != null) {
				if(resultView.getIdx() == likeList.getBoardIdx()) {
					resultView.setBoardLike(likeList.getLikes());
				}
			}
			
			model.addAttribute("resultView", resultView);
		} catch(Exception e) {}
		
		return "replyList.do?boardIdx="+board.getIdx(); // 해당 게시글에 대한 댓글리스트 메소드로 이동
	}
	
	// 오늘 뭐먹지로 이동
	@RequestMapping(value="/viewFoodFeed.do")
	public String viewFoodFeed(@ModelAttribute("board")BoardVO board, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		
		return "foodFeed";
	}
	
	// 오늘 어디가지로 이동
	@RequestMapping(value="/viewMarble.do")
	public String viewMarbleFeed(@ModelAttribute("board")BoardVO board, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		
		return "marble";
	}
	
	/**
	 * 게시글 작성 화면 이동
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdBoardReg.do")
	public String fwdBoardReg(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {} catch(Exception e) {}
		
		return "boardReg";
	}
	/**
	 * 게시글 등록
	 * @param files
	 * @param board
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(@RequestParam("locationImageAttachFile") MultipartFile lo_file, 
			@RequestParam("foodImageAttachFile") MultipartFile fo_file, @RequestParam("fashionImageAttachFile") MultipartFile fa_file,
			@ModelAttribute("board")BoardVO board, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
			board.setWriter((String)session.getAttribute("userName"));
		try {
			boardService.insertBoard(lo_file, fo_file, fa_file, board);
			
			model.addAttribute("message", "게시글이 등록 되었습니다");
			model.addAttribute("board", board);
			model.addAttribute("returnURL", "/viewBoard.do?idx="+board.getIdx());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 수정 화면 이동
	 * @param board
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdBoardUpt.do")
	public String fwdBoardUpt(@ModelAttribute("board")BoardVO board, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
			board.setWriter((String)session.getAttribute("userName"));
		try {
			BoardVO resultVO = boardService.selectBoard(board);
			
			model.addAttribute("resultVO", resultVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "boardUpt";
	}
	/**
	 * 게시글 수정
	 * @param files
	 * @param board
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateBoard.do")
	public String updateBoard(@ModelAttribute("board")BoardVO board, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			boardService.updateBoard(board);
			
			model.addAttribute("message", "게시글이 수정되었습니다.");
			model.addAttribute("board", board);
			model.addAttribute("returnURL", "/viewBoard.do?idx="+board.getIdx());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 삭제(관리자)
	 * @param boardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteBoardByAdmin.do")
	public String deleteBoardByAdmin(@ModelAttribute("boardSVO")BoardSVO boardSVO, ReplyVO vo, LikeVO lvo, BoardLikeVO blvo,
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			boardService.deleteBoardByAdmin(boardSVO); // 게시글 삭제하면
			
			model.addAttribute("message", "게시글이 삭제되었습니다.");
			model.addAttribute("returnURL", "/selectBoardList.do?size=10");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 삭제(사용자 / 자신의 글에만 권한)
	 * @param boardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(@ModelAttribute("board")BoardVO board, ReplyVO vo, LikeVO lvo, BoardLikeVO blvo, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			boardService.deleteBoard(board); // 게시글 삭제하면
			vo.setBoardIdx(board.getIdx());
			replyService.deleteBoardReply(vo.getBoardIdx()); // 게시글에 포함되어있는 댓글들도 삭제
			lvo.setBoardIdx(board.getIdx());
			replyService.boardLikeDelete(lvo.getBoardIdx()); // 댓글안에 포함되어있는 좋아요도 삭제
			blvo.setBoardIdx(board.getIdx());
			boardService.deleteBoardLike(blvo.getBoardIdx()); // 게시글에 포함되어있는 좋아요 삭제
			
			
			model.addAttribute("message", "게시글이 삭제되었습니다.");
			model.addAttribute("returnURL", "/selectBoardList.do?size=10");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	// 게시글 좋아요
	@RequestMapping(value = "/boardLike.do")
	@ResponseBody
	public String boardLike(HttpServletRequest request, HttpSession session) throws Exception {
		String mvo = (String)session.getAttribute("userName");
		if(mvo == null)
		{
			return "N";
		}
		
		BoardLikeVO lvo = new BoardLikeVO();
		BoardVO bvo = new BoardVO();
		lvo.setBoardIdx(Integer.parseInt(request.getParameter("board_idx")));
		lvo.setMemberIdx(Integer.parseInt(request.getParameter("member_idx")));
		lvo.setLikes(request.getParameter("boardLike"));
		lvo.setIdx(lvo.getBoardIdx()+"/"+lvo.getMemberIdx());
		bvo.setIdx(lvo.getBoardIdx());
		
		BoardVO boardVO = boardService.selectBoard(bvo);
		int likes_cnt = boardVO.getLikes();
		int hates_cnt = boardVO.getHates();
		
		String result_like = "";
		switch(lvo.getLikes()) {
		case "T":
			boardService.boardLikeCancle(lvo);	// 좋아요 상태에서 좋아요를 한번 더 누르면  -> 좋아요 삭제
			
			likes_cnt -= 1;	// 좋아요 카운트 -1
			boardVO.setLikes(likes_cnt);
			boardService.reBoard(boardVO);
			break;
		case "F":
			boardService.boardHateCancle(lvo);
			boardService.boardLike(lvo);		// 싫어요 상태에서 좋아요를 누르면  -> 싫어요 삭제 + 좋아요
			
			likes_cnt += 1; // 좋아요 카운트 +1
			hates_cnt -= 1;	// 싫어요 카운트 -1
			boardVO.setLikes(likes_cnt);
			boardVO.setHates(hates_cnt);
			boardService.reBoard(boardVO);
			result_like = "T";
			break;
		default:
			boardService.boardLike(lvo);	// 아무것도 없는 상태에서 좋아요를 누르면 -> 좋아요
			
			likes_cnt += 1; // 좋아요 카운트 +1
			boardVO.setLikes(likes_cnt);
			boardService.reBoard(boardVO);
			result_like = "T";
			break;
		}
		
		return result_like;
	}

	@RequestMapping(value = "/boardHate.do")
	@ResponseBody
	public String replyHate(HttpServletRequest request, HttpSession session) throws Exception {
		String mvo = (String)session.getAttribute("userName");
		if(mvo == null)
		{
			return "N";
		}
		
		BoardLikeVO lvo = new BoardLikeVO();
		BoardVO bvo = new BoardVO();
		lvo.setBoardIdx(Integer.parseInt(request.getParameter("board_idx")));
		lvo.setMemberIdx(Integer.parseInt(request.getParameter("member_idx")));
		lvo.setLikes(request.getParameter("boardLike"));
		lvo.setIdx(lvo.getBoardIdx()+"/"+lvo.getMemberIdx());
		bvo.setIdx(lvo.getBoardIdx());
		
		BoardVO boardVO = boardService.selectBoard(bvo);
		int likes_cnt = boardVO.getLikes();
		int hates_cnt = boardVO.getHates();
		
		String result_like = "";
		switch(lvo.getLikes()) {
		case "T": 
			boardService.boardLikeCancle(lvo);	// 좋아요 상태에서 싫어요를 누르면  -> 좋아요 삭제  + 싫어요
			boardService.boardHate(lvo);
			
			likes_cnt -= 1; // 좋아요 카운트 -1
			hates_cnt += 1;	// 싫어요 카운트 +1
			boardVO.setLikes(likes_cnt);
			boardVO.setHates(hates_cnt);
			boardService.reBoard(boardVO);
			result_like = "F";
			break;
		case "F": 
			boardService.boardHateCancle(lvo);	// 싫어요 상태에서 싫어요를 누르면  -> 싫어요 삭제
			hates_cnt -= 1;	// 싫어요 카운트 -1
			boardVO.setHates(hates_cnt);
			boardService.reBoard(boardVO);
			break;
		default : 
			boardService.boardHate(lvo);	// 아무것도 없는 상태에서 싫어요를 누르면 -> 싫어요
			hates_cnt += 1;	// 싫어요 카운트 +1
			boardVO.setHates(hates_cnt);
			boardService.reBoard(boardVO);
			result_like = "F";
			break; 
		}
		
		return result_like;
	}
}
