package com.RanReco.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RanReco.dao.MemberResultRepository;
import com.RanReco.service.MemberResultService;
import com.RanReco.vo.MemberResultVO;

@Service("memberResultService")
public class MemberResultServiceImpl implements MemberResultService{

	@Autowired
	private MemberResultRepository memberResultDAO;
	
	
	@Override
	public void insertMemberResult(MemberResultVO memberResultVO)throws Exception {
		memberResultDAO.save(memberResultVO);
	}
	
	@Transactional
	@Override
	public MemberResultVO selectMemberResult(MemberResultVO memberResultVO) throws Exception {
		MemberResultVO resultVO = memberResultDAO.findByIdx(memberResultVO.getIdx());
		
		return resultVO;
	}
	

	@Transactional
	@Override
	public void deleteMemberResult(MemberResultVO memberResultVO) throws Exception {
		memberResultDAO.deleteByMemberIdx(memberResultVO.getMemberIdx());
	}


	@Transactional
	@Override
	public void updateMemberResult(MemberResultVO memberResultVO) throws Exception {
		Optional<MemberResultVO> resultVO = Optional.of(memberResultDAO.findByMemberIdx(memberResultVO.getMemberIdx()));
		 
		if (resultVO.isPresent()) { 
			resultVO.get().setMemberFood(memberResultVO.getMemberFood());
			resultVO.get().setMemberFoodFile(memberResultVO.getMemberFoodFile());
			resultVO.get().setMemberLocation(memberResultVO.getMemberLocation());
			resultVO.get().setMemberLocationFile(memberResultVO.getMemberLocationFile());
			resultVO.get().setLook(memberResultVO.getLook());
			resultVO.get().setMemberFashionFile(memberResultVO.getMemberFashionFile());
					
			memberResultDAO.save(memberResultVO); 
		}
	}

	
}
