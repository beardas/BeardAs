package com.RanReco.service;


import org.springframework.stereotype.Service;
import com.RanReco.vo.MemberLocationVO;

@Service
public interface MemberLocationService {
	
	public void insertLocation(MemberLocationVO memberLocationVO) throws Exception;


	public void updateLocation(MemberLocationVO memberLocationVO)throws Exception;


	void deleteLocation(MemberLocationVO memberLocationVO) throws Exception;


	MemberLocationVO selectLocation(int memberIdx) throws Exception;

		
		
	
}
