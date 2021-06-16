package com.RanReco.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.RanReco.vo.MemberFoodVO;

@Service
public interface MemberFoodService {
	
	public void insertMemberFood( MemberFoodVO memberFoodVO) throws Exception;


	public void updateMemberFood(MemberFoodVO memberFoodVO)throws Exception;


	void deleteMemberFood(MemberFoodVO memberFoodVO) throws Exception;


	MemberFoodVO selectMemberFood(MemberFoodVO memberFoodVO) throws Exception;

		
		
	
}
