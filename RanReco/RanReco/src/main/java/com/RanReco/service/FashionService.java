package com.RanReco.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.RanReco.vo.FashionVO;




@Service
public interface FashionService {
	/**
	 * 게시글 목록 조회
	 * @param board
	 * @param pageable
	 * @return Page<Board>
	 * @throws Exception
	 */
	List<FashionVO> selectFashionList(FashionVO fashion) throws Exception;
	
}

