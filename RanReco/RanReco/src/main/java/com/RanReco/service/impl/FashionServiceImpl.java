package com.RanReco.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RanReco.dao.FashionFileRepository;
import com.RanReco.dao.FashionRepository;
import com.RanReco.service.FashionService;
import com.RanReco.vo.FashionFileVO;
import com.RanReco.vo.FashionVO;



@Service("fashionService")
public class FashionServiceImpl implements FashionService{

	@Autowired
	private FashionFileRepository fileDAO;
	@Autowired
	private FashionRepository fashionDAO;
	
	
	/**
	 * 게시글 목록 조회
	 * @param memberVO
	 * @param pageable
	 * @return Page<Member>
	 * @throws Exception
	 */
	@Transactional
	@Override
	public List<FashionVO> selectFashionList(FashionVO vo) throws Exception {
		List <FashionVO> resultFashionList = null;
		
		resultFashionList = fashionDAO.findByGenderByLookBySeason("%"+vo.getGender()+"%", "%"+vo.getLook()+"%", "%"+vo.getSeason()+"%");
		
		for(int i=0; i<resultFashionList.size(); i++) {
			int fashionIdx = resultFashionList.get(i).getIdx();
			FashionFileVO file = fileDAO.findByFileIdx(fashionIdx);
			if(file != null) {
				resultFashionList.get(i).setFashionFile(file.getFileName());
			}
		}
		        
		return resultFashionList;
	}
	
	
}
