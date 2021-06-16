package com.RanReco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RanReco.vo.FoodVO;
import com.RanReco.vo.Paging;

@Service
public interface FoodService {
	
	public void insertFood(MultipartFile files, FoodVO foodVO) throws Exception;

	public Page<FoodVO> selectFoodList(FoodVO foodSVO, Pageable pageable)throws Exception;

	public Paging setPaging(int pageSize, int pageNumber) throws Exception;

	public FoodVO selectFood(FoodVO foodVO) throws Exception;

	public void updateFood(MultipartFile files,FoodVO foodVO)throws Exception;

		
		
	
}
