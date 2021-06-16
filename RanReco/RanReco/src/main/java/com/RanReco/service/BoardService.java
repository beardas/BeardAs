package com.RanReco.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RanReco.vo.BoardLikeVO;
import com.RanReco.vo.BoardSVO;
import com.RanReco.vo.BoardVO;
import com.RanReco.vo.Paging;




@Service
public interface BoardService {
	/**
	 * 게시글 목록 조회
	 * @param board
	 * @param pageable
	 * @return Page<Board>
	 * @throws Exception
	 */
	Page<BoardVO> selectBoardList(BoardVO board, Pageable pageable) throws Exception;
	List<BoardVO> rankBoardLike(BoardVO vo);
	/**
	 * 페이징 처리를 위한 메소드
	 * @param pageSize
	 * @param number
	 * @return PagingVO
	 * @throws Exception
	 */
	public Paging setPaging(int pageSize, int number) throws Exception;
	
	/**
	 * 게시글 조회
	 * @param board
	 * @return Board
	 * @throws Exception
	 */
	BoardVO selectBoard(BoardVO board) throws Exception;
	
	
	/**
	 * 게시글 등록
	 * @param files
	 * @param board
	 * @throws Exception
	 */
	void insertBoard(MultipartFile lo, MultipartFile fo, MultipartFile fa, BoardVO board) throws Exception;
	
	/**
	 * 게시글 수정
	 * @param files
	 * @param board
	 * @throws Exception
	 */
	void updateBoard(BoardVO board) throws Exception;
	
	/**
	 * 게시글 삭제(관리자)
	 * @param boardSVO
	 * @throws Exception
	 */
	void deleteBoardByAdmin(BoardSVO boardSVO) throws Exception;
	
	
	/**
	 * 게시글 삭제
	 * @param board
	 * @throws Exception
	 */
	void deleteBoard(BoardVO board) throws Exception;
	
	void boardLike(BoardLikeVO bvo);
	void boardLikeCancle(BoardLikeVO bvo);
	void boardHate(BoardLikeVO bvo);
	void boardHateCancle(BoardLikeVO bvo);
	void deleteBoardLike(int boardIdx);
	public BoardLikeVO likeList(BoardLikeVO bvo);
	
	void reBoard(BoardVO vo);
}

