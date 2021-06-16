package com.RanReco.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.ReplyVO;



public interface ReplyRepository extends JpaRepository<ReplyVO, Integer>{

		// 해당하는 게시글의 댓글 리스트 불러오기 
		List<ReplyVO> findReplyByBoardIdxOrderByIdxAsc(int boardIdx);
		
		// 해당하는 댓글의 정보 불러오기
		ReplyVO findByIdx(int idx);
		
		int countByBoardIdx(int idx);
		
		void deleteByBoardIdx(int idx);
		
}
