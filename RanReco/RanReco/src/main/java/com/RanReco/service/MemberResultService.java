package com.RanReco.service;


import org.springframework.stereotype.Service;
import com.RanReco.vo.MemberResultVO;

@Service
public interface MemberResultService {
	
	public void insertMemberResult( MemberResultVO memberResultVO) throws Exception;


	public void updateMemberResult(MemberResultVO memberResultVO)throws Exception;


	void deleteMemberResult(MemberResultVO memberResultVO) throws Exception;


	MemberResultVO selectMemberResult(MemberResultVO memberResultVO) throws Exception;

		
		
	
}
