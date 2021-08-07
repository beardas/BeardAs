package com.test.service;

import java.util.List;

import com.test.vo.PagingVO;
import com.test.vo.ProductSVO;
import com.test.vo.ProductVO;

public interface ProductService {
	
	List<ProductVO> selectProductList(ProductVO vo) throws Exception;
	
	List<ProductVO> selectProductListPaging(PagingVO page) throws Exception;
	
	void insertProduct(ProductVO vo) throws Exception;
	
	void deleteProduct(ProductSVO vo) throws Exception;
}
