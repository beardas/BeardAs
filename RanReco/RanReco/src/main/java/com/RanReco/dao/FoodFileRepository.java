package com.RanReco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.FoodFileVO;




/**
 * File 테이블 Repository
 *
 */
public interface FoodFileRepository extends JpaRepository<FoodFileVO, Integer> {
	
	// FileIdx 값으로 조회
	FoodFileVO findByFileIdx(int fileIdx);
	
	// insert
	FoodFileVO save(FoodFileVO file);
	
	// FileIdx 값으로 삭제
	void deleteByFileIdx(int fileIdx);

	FoodFileVO findByFoodIdx(int idx);

	void deleteByFoodIdx(int idx);
	
}