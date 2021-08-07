package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.ProductDAO;
import com.test.service.ProductService;
import com.test.vo.PagingVO;
import com.test.vo.ProductSVO;
import com.test.vo.ProductVO;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productMapper;
	
	
	public List<ProductVO> selectProductList(ProductVO vo) throws Exception {
		return productMapper.selectProductList(vo);
	}
	
	@Override
	public List<ProductVO> selectProductListPaging(PagingVO page) throws Exception {
		return productMapper.selectProductListPaging(page);
	}
	
	@Transactional
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		productMapper.insertProduct(vo);
		
	}
	
	@Transactional
	@Override
	public void deleteProduct(ProductSVO vo) throws Exception {
		productMapper.deleteProduct(vo);
		
	}

	
	
	
}
