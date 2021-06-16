package com.RanReco.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.RanReco.vo.FashionVO;


public interface FashionRepository extends JpaRepository<FashionVO, Integer>{
	
	
	// 계절, 룩 like 검색 Idx 내림차순 조회
	@Query(value = "SELECT * FROM FASHION WHERE GENDER LIKE :gender" +
	" AND LOOK LIKE :look"+ " AND SEASON LIKE :season" , nativeQuery = true)
	List<FashionVO> findByGenderByLookBySeason(@Param("gender") String gender,  @Param("look") String look,  @Param("season") String season);
	
	FashionVO findByIdx(int idx);
	
	// MemberIdx로 삭제
	void deleteByIdx(int idx);

}
