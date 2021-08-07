package com.test.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.vo.PagingVO;
import com.test.vo.ProductSVO;
import com.test.vo.ProductVO;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<ProductVO> selectProductList(ProductVO vo) throws Exception{
		return mybatis.selectList("ProductDAO.selectProductList", vo);
	}
	
	public List<ProductVO> selectProductListPaging(PagingVO page) throws Exception{
		return mybatis.selectList("ProductDAO.selectProductListPaging", page);
	}
	
	public void insertProduct(ProductVO vo) {
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	public void deleteProduct(ProductSVO vo) {
		mybatis.insert("ProductDAO.deleteProduct", vo);
	}
}
