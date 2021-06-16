package com.RanReco.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.RanReco.vo.LikeVO;



public interface LikeRepository extends JpaRepository<LikeVO, Integer>{

	int countByReplyIdx(LikeVO vo);
	
	// replyList.do
	@Query(value = "SELECT l.IDX, l.BOARD_IDX, l.REPLY_IDX, l.MEMBER_IDX, l.LIKES FROM Reply r, Likes l " + 
			"WHERE r.IDX = l.REPLY_IDX " + 
		    "AND l.BOARD_IDX = :boardIdx " + 
		    "AND l.MEMBER_IDX = :memberIdx", nativeQuery = true)
	List<LikeVO> findLikesByBoardIdxByMemberIdx(@Param("boardIdx") int boardIdx, @Param("memberIdx") int memberIdx);
	
	void deleteByReplyIdx(int replyIdx);
	void deleteByBoardIdx(int boardIdx);
	
}
