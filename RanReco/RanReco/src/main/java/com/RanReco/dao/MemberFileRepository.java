package com.RanReco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.MemberFileVO;



/**
 * File 테이블 Repository
 *
 */
public interface MemberFileRepository extends JpaRepository<MemberFileVO, Integer> {
	
	// FileIdx 값으로 조회
	MemberFileVO findByFileIdx(int fileIdx);
	
	// Member Idx 값으로 조회
	MemberFileVO findByMemberIdx(int memberIdx);
	
	
	// FileIdx 값으로 삭제
	void deleteByFileIdx(int fileIdx);
	
	// Member Idx 값으로 삭제
	void deleteByMemberIdx(int memberIdx);
	
}