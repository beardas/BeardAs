package com.RanReco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.MemberLocationVO;


public interface MemberLocationRepository extends JpaRepository<MemberLocationVO, Integer>{

	
	MemberLocationVO findByMemberIdx(int idx);
	
	// 본인이 쓴 글인지 확인 하려는 쿼리

	void deleteByMemberIdx(int idx);
}
