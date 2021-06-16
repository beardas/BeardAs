package com.RanReco.dao;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.RanReco.vo.BoardLikeVO;



public interface BoardLikeRepository extends JpaRepository<BoardLikeVO, Integer>{

	
	// replyList.do
	@Query(value = "SELECT l.IDX, l.BOARD_IDX, l.MEMBER_IDX, l.LIKES FROM BOARD b, BOARD_LIKES l " + 
			"WHERE b.IDX = l.BOARD_IDX " + 
		    "AND l.BOARD_IDX = :boardIdx " + 
		    "AND l.MEMBER_IDX = :memberIdx", nativeQuery = true)
	BoardLikeVO findBoardLikesByBoardIdxByMemberIdx(@Param("boardIdx") int boardIdx, @Param("memberIdx") int memberIdx);
	
	void deleteByBoardIdx(int boardIdx);
	
}
