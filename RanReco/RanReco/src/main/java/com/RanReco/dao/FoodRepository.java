package com.RanReco.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.FoodVO;


public interface FoodRepository extends JpaRepository<FoodVO, Integer>{

	FoodVO save(FoodVO foodVO);
	
	// 모든 게시글 조회
	Page<FoodVO> findAll(Pageable pageable);
	// 모든 게시글 조회 Idx로 내림차순 조회
	Page<FoodVO> findAllByOrderByIdxDesc(Pageable pageable);
	
	// Title으로 like 검색 Idx 내림차순 조회
	Page<FoodVO> findByLocationLikeOrderByIdxDesc(Pageable pageable, String location);
	
	// Board Idx로  조회
	FoodVO findByIdx(int idx);
	
	// 본인이 쓴 글인지 확인 하려는 쿼리

	void deleteByIdx(int foodIdx);
}
