package com.RanReco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.FashionFileVO;




/**
 * File 테이블 Repository
 *
 */
public interface FashionFileRepository extends JpaRepository<FashionFileVO, Integer> {
	
	// FileIdx 값으로 조회
	FashionFileVO findByFileIdx(int fileIdx);
	
	// fashionIdx 값으로 조회
	FashionFileVO findByFashionIdx(int fashionIdx);
	
	// FileIdx 값으로 삭제
	void deleteByFileIdx(int fileIdx);
	
	// fashionIdx 값으로 삭제
	void deleteByFashionIdx(int fashionIdx);
	
}