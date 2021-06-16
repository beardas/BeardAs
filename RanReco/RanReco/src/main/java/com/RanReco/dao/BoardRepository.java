package com.RanReco.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RanReco.vo.BoardVO;


public interface BoardRepository extends JpaRepository<BoardVO, Integer>{
	
	// 모든 게시글 조회
	Page<BoardVO> findAll(Pageable pageable);
	// 모든 게시글 조회 Idx로 내림차순 조회
	Page<BoardVO> findAllByOrderByIdxDesc(Pageable pageable);
	
	// Title으로 like 검색 Idx 내림차순 조회
	Page<BoardVO> findByTitleLikeOrderByIdxDesc(Pageable pageable, String title);
	// Content으로 like 검색 Idx 내림차순 조회
	Page<BoardVO> findByContentLikeOrderByIdxDesc(Pageable pageable, String content);
	
	// Board Idx로  조회
	BoardVO findByIdx(int idx);
	
	// 본인이 쓴 글인지 확인 하려는 쿼리
	BoardVO findByWriter(String writer);
	
	// MemberIdx로 삭제
	void deleteByIdx(int idx);

	@Query(value = "SELECT * FROM (SELECT * FROM BOARD ORDER BY (LIKES - HATES) DESC) WHERE ROWNUM <= 3", nativeQuery = true)
	   List<BoardVO> findRankByLikes(BoardVO vo);

}
