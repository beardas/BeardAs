package com.test.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.service.ProductService;
import com.test.vo.PagingVO;
import com.test.vo.ProductSVO;
import com.test.vo.ProductVO;

@Controller
public class ProductController {
	
	@Autowired
	@Resource(name = "productService")
	private ProductService productService;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping(value = "/productList")
	public String productList(Model model, @ModelAttribute("product") ProductVO vo, PagingVO page) throws Exception {
		
		List<ProductVO> list = productService.selectProductListPaging(page);
		List<ProductVO> list_total = productService.selectProductList(vo);
		
		model.addAttribute("list", list);
		model.addAttribute("total", list_total.size());
		
		return "product/productList";
	}
	
	@RequestMapping(value = "/productReg")
	public String productReg(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		try {} catch(Exception e) {}
		
		return "product/productReg";
	}
	
	@RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
	public String insertProduct(@ModelAttribute("product") ProductVO product, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
			
			product.setUuid(product.getUuid() + String.valueOf(System.currentTimeMillis()).substring(7));
			// 상품코드 3자리 + 밀리세컨드 6자리
		try {
			
			productService.insertProduct(product);
			
			model.addAttribute("message", "상품이 등록되었습니다.");
			model.addAttribute("product", product);
			model.addAttribute("returnURL", "/productList");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "common/result";
	}
	
	@RequestMapping(value = "/productUpd")
	public String productUpd(Model model) throws Exception {
		
		
		
		return "product/productUpd";
	}
	
	@RequestMapping(value = "/productDel")
	public String productDel(@ModelAttribute("productSVO") ProductSVO product, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception {
		
		try {
			productService.deleteProduct(product);
			
			model.addAttribute("message", "상품 정보가 삭제되었습니다.");
			model.addAttribute("returnURL", "/productList");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "common/result";
	}
	
}
