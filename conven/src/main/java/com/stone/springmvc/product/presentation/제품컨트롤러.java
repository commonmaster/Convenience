package com.stone.springmvc.product.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.product.common.제품;
import com.stone.springmvc.product.common.제품페이지구성정보;
import com.stone.springmvc.product.service.제품관리서비스;

@Controller
public class 제품컨트롤러 {

	@Autowired
	제품관리서비스 제품관리서비스Impl;
	
	@RequestMapping("/products")
	public ModelAndView 제품소개화면을준비하다(Integer pageNo, Integer category) {

		int requestPageNo = 1;
		if (pageNo != null) {
			requestPageNo = pageNo;
		}

		int search_category = 0;
		if (category != null) {
			search_category = category;
		}

		제품페이지구성정보 pageInfo = 제품관리서비스Impl.제품리스트출력서비스(requestPageNo, search_category);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("products");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("selectCategory", search_category);

		return mav;
	}

	@GetMapping("/product")
	public ModelAndView 제품상세화면을준비하다(Integer barcode) {

		// System.out.println("제품상세화면:" + barcode);
		ModelAndView mav = new ModelAndView();

		제품 product = 제품관리서비스Impl.제품상세정보출력서비스(barcode);

		mav.setViewName("product");
		mav.addObject("product", product);

		return mav;
	}
}
