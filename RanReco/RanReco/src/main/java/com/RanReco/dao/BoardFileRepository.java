package com.RanReco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.BoardFileVO;



/**
 * File 테이블 Repository
 *
 */
public interface BoardFileRepository extends JpaRepository<BoardFileVO, Integer> {
	
	// FileIdx 값으로 조회
	BoardFileVO findByFileIdx(int fileIdx);
	
	// Member Idx 값으로 조회
	BoardFileVO findByBoardIdx(int boardIdx);
	
	// FileIdx 값으로 삭제
	void deleteByFileIdx(int fileIdx);
	
	// Member Idx 값으로 삭제
	void deleteByBoardIdx(int boardIdx);
	
}