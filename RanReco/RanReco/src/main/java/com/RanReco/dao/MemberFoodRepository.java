package com.RanReco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RanReco.vo.MemberFoodVO;


public interface MemberFoodRepository extends JpaRepository<MemberFoodVO, Integer>{

	
	MemberFoodVO findByMemberIdx(int idx);
	
	// 본인이 쓴 글인지 확인 하려는 쿼리

	void deleteByMemberIdx(int idx);
}
