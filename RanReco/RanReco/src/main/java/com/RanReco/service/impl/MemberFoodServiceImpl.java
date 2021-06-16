package com.RanReco.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RanReco.dao.MemberFoodRepository;
import com.RanReco.service.MemberFoodService;
import com.RanReco.vo.MemberFoodVO;

@Service("memberFoodService")
public class MemberFoodServiceImpl implements MemberFoodService{

	@Autowired
	private MemberFoodRepository memberFoodDAO;
	
	
	@Override
	public void insertMemberFood(MemberFoodVO MemberFoodVO)throws Exception {
		memberFoodDAO.save(MemberFoodVO);
	}
	
	@Transactional
	@Override
	public MemberFoodVO selectMemberFood(MemberFoodVO MemberFoodVO) throws Exception {
		MemberFoodVO resultVO = memberFoodDAO.findByMemberIdx(MemberFoodVO.getMemberIdx());
		
		return resultVO;
	}
	

	@Transactional
	@Override
	public void deleteMemberFood(MemberFoodVO MemberFoodVO) throws Exception {
		memberFoodDAO.deleteByMemberIdx(MemberFoodVO.getMemberIdx());
	}


	@Transactional
	@Override
	public void updateMemberFood(MemberFoodVO MemberFoodVO) throws Exception {
		Optional<MemberFoodVO> resultVO = Optional.of(memberFoodDAO.findByMemberIdx(MemberFoodVO.getMemberIdx()));
		 
		if (resultVO.isPresent()) { 
			resultVO.get().setMemberFood(MemberFoodVO.getMemberFood());
			memberFoodDAO.save(MemberFoodVO); 
		}
	}

	
}
