package com.RanReco.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RanReco.dao.LikeRepository;
import com.RanReco.dao.ReplyRepository;
import com.RanReco.service.ReplyService;
import com.RanReco.vo.LikeVO;
import com.RanReco.vo.ReplyVO;



@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyDAO;
	@Autowired
	private LikeRepository likeDAO;
	
	// 댓글 작성
	@Transactional
	@Override
	public void writeReply(ReplyVO vo) {
		replyDAO.save(vo);
	}
	
	// 댓글 삭제
	@Transactional
	@Override
	public void deleteReply(ReplyVO vo) {
		replyDAO.delete(vo);
	}
	
	// 댓글 삭제(게시글 삭제되면 안에 있는 댓글들)
	@Transactional
	@Override
	public void deleteBoardReply(int idx) {
		replyDAO.deleteByBoardIdx(idx);
	}
	
	// 좋아요삭제(게시글 삭제되면 안에 있는 좋아요들)
	@Transactional
	@Override
	public void boardLikeDelete(int idx) {
		likeDAO.deleteByBoardIdx(idx);
		
	}
	
	@Override
	public ReplyVO selectReply(ReplyVO vo) {
		return replyDAO.findByIdx(vo.getIdx());
	}
	
	// 해당하는 게시글의 전체 댓글 리스트
	@Transactional
	@Override
	public List<ReplyVO> replyList(ReplyVO vo) {
		return replyDAO.findReplyByBoardIdxOrderByIdxAsc(vo.getBoardIdx());
	}
	
	// 해당 댓글 좋아요, 싫어요 리스트
	@Transactional
	@Override
	public List<LikeVO> reply_likeList(LikeVO vo) {
		return likeDAO.findLikesByBoardIdxByMemberIdx(vo.getBoardIdx(), vo.getMemberIdx());
	}
	
	@Transactional
	@Override
	public void replyLikeDelete(int replyIdx) {
		likeDAO.deleteByReplyIdx(replyIdx);
	}
	
	// 좋아요 추가
	@Transactional
	@Override
	public void replyLike(LikeVO lvo) {
		lvo.setLikes("T");
		likeDAO.save(lvo);
	}
	
	// 좋아요 취소
	@Transactional
	@Override
	public void replyLikeCancle(LikeVO lvo) {
			likeDAO.delete(lvo);
	}
	
	// 싫어요 추가
	@Transactional
	@Override
	public void replyHate(LikeVO lvo) {
			lvo.setLikes("F");
			likeDAO.save(lvo);
	}
	
	// 싫어요 취소
	@Transactional
	@Override
	public void replyHateCancle(LikeVO lvo) {
			likeDAO.delete(lvo);
	}
}
