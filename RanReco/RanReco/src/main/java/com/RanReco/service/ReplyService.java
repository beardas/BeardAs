package com.RanReco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.RanReco.vo.LikeVO;
import com.RanReco.vo.ReplyVO;


@Service
public interface ReplyService {
	
	public void writeReply(ReplyVO vo);
	
	public void deleteReply(ReplyVO vo);
	
	public ReplyVO selectReply(ReplyVO vo);
	
	public List<ReplyVO> replyList(ReplyVO vo);
	
	public List<LikeVO> reply_likeList(LikeVO vo);
	
	public void replyLike(LikeVO lvo);
	
	public void deleteBoardReply(int idx);
	
	public void boardLikeDelete(int idx);
	
	public void replyLikeDelete(int replyIdx);
	
	public void replyLikeCancle(LikeVO lvo);
	
	public void replyHate(LikeVO lvo);
	
	public void replyHateCancle(LikeVO lvo);
}

