package com.RanReco.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.MemberVO;


public interface MemberRepository extends JpaRepository<MemberVO, Integer>{
	
	// 모든 데이터 조회
	Page<MemberVO> findAll(Pageable pageable);
	// 모든 데이터 조회 Idx로 내림차순 조회
	Page<MemberVO> findAllByOrderByIdxDesc(Pageable pageable);
	
	// Name으로 like 조회 
	Page<MemberVO> findByNameLike(Pageable pageable, String name);
	// Name 조회 Idx로 내림차순 조회
	Page<MemberVO> findByNameLikeOrderByIdxDesc(Pageable pageable, String name);
	
	// MemberIdx로  조회
	MemberVO findByIdx(int idx);
	
	// 회원가입여부를 확인하기 위한(로그인하기 위한) Id, Pw로  조회
	MemberVO findByIdAndPassword(String id, String password);
	
	// MemberIdx로 삭제
	void deleteByIdx(int idx);
	
	MemberVO findByPhoneNumber(String phoneNumber);
	
	MemberVO findById(String id);


}
