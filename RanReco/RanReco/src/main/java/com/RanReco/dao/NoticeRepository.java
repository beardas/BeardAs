package com.RanReco.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.NoticeVO;



public interface NoticeRepository extends JpaRepository<NoticeVO, Integer>{
	
	// 모든 공지사항 조회
	Page<NoticeVO> findAll(Pageable pageable);
	// 모든 공지사항 조회 Idx로 내림차순 조회
	Page<NoticeVO> findAllByOrderByIdxDesc(Pageable pageable);
	
	// Title으로 like 검색 Idx 내림차순 조회
	Page<NoticeVO> findByTitleLikeOrderByIdxDesc(Pageable pageable, String title);
	// Content으로 like 검색 Idx 내림차순 조회
	Page<NoticeVO> findByContentLikeOrderByIdxDesc(Pageable pageable, String content);
	
	// Notice Idx로  조회
	NoticeVO findByIdx(int idx);
	
		
	// NoticeIdx로 삭제
	void deleteByIdx(int idx);


}
