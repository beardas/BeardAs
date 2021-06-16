package com.RanReco.service;


import org.springframework.stereotype.Service;

import com.RanReco.vo.MemberFashionVO;

@Service
public interface MemberFashionService {
	
	public void insertMemberFashion( MemberFashionVO memberFashionVO) throws Exception;


	public void updateMemberFashion(MemberFashionVO memberFashionVO)throws Exception;


	void deleteMemberFashion(MemberFashionVO memberFashionVO) throws Exception;


	MemberFashionVO selectMemberFashion(MemberFashionVO memberFashionVO) throws Exception;

		
		
	
}
