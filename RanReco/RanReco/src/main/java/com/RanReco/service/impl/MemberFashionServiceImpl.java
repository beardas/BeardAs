package com.RanReco.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RanReco.dao.MemberFashionRepository;
import com.RanReco.service.MemberFashionService;
import com.RanReco.vo.MemberFashionVO;

@Service("memberFashionService")
public class MemberFashionServiceImpl implements MemberFashionService{

	@Autowired
	private MemberFashionRepository memberFashionDAO;
	
	
	@Override
	public void insertMemberFashion(MemberFashionVO memberFashionVO)throws Exception {
		memberFashionDAO.save(memberFashionVO);
	}
	
	@Transactional
	@Override
	public MemberFashionVO selectMemberFashion(MemberFashionVO memberFashionVO) throws Exception {
		MemberFashionVO resultVO = memberFashionDAO.findByMemberIdx(memberFashionVO.getMemberIdx());
		
		return resultVO;
	}
	

	@Transactional
	@Override
	public void deleteMemberFashion(MemberFashionVO memberFashionVO) throws Exception {
		memberFashionDAO.deleteByMemberIdx(memberFashionVO.getMemberIdx());
	}


	@Transactional
	@Override
	public void updateMemberFashion(MemberFashionVO memberFashionVO) throws Exception {
		Optional<MemberFashionVO> resultVO = Optional.of(memberFashionDAO.findByMemberIdx(memberFashionVO.getMemberIdx()));
		if (resultVO.isPresent()) { 
			resultVO.get().setLook(memberFashionVO.getLook());
			resultVO.get().setSeason(memberFashionVO.getSeason());
			resultVO.get().setGender(memberFashionVO.getGender());
			resultVO.get().setMemberFashionFile(memberFashionVO.getMemberFashionFile()); 
			memberFashionDAO.save(memberFashionVO); 
		}
		
	}
	

}
