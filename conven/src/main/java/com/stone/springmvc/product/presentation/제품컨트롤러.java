package com.stone.springmvc.product.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.member.common.Member;
import com.stone.springmvc.member.service.회원관리서비스;
import com.stone.springmvc.product.common.제품;

import com.stone.springmvc.product.common.제품페이지구성정보;
import com.stone.springmvc.product.service.제품관리서비스;

@Controller
public class 제품컨트롤러 {

	@Autowired
	제품관리서비스 제품관리서비스Impl;
	@Autowired
	회원관리서비스 회원관리서비스Impl;

	@RequestMapping("/products")
	public ModelAndView 제품소개화면을준비하다(Integer pageNo, Integer category, HttpSession session) {

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

		String id = (String) session.getAttribute("conven_session_id");
		if (id != null) {

			Member 회원 = 회원관리서비스Impl.회원찾기서비스(id);
			mav.addObject("name", 회원.getName());
		}

		mav.setViewName("product/products");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("selectCategory", search_category);

		return mav;
	}

	@GetMapping("/product")
	public ModelAndView 제품상세화면을준비하다(Integer barcode) {

		// System.out.println("제품상세화면:" + barcode);
		ModelAndView mav = new ModelAndView();

		제품 product = 제품관리서비스Impl.제품상세정보출력서비스(barcode);

		mav.setViewName("product/product");
		mav.addObject("product", product);

		return mav;
	}

	/////////////////////////////////////////////////////////////////////////////

	@GetMapping("/productImg/{바코드}") // 예 /profile/1
	public ModelAndView 제품사진을주다(@PathVariable("바코드") int barcode) {
		ModelAndView mav = new ModelAndView();

		제품 찾은제품 = 제품관리서비스Impl.제품상세정보출력서비스(barcode);

		mav.setViewName("product/productImg");
		mav.addObject("productImg", 찾은제품.getProductImg());

		return mav;
	}

	@GetMapping("/manage_product_add")
	public String 제품등록화면을준비하다() {

		return "product/manage_product_add";
	}
	
	@PostMapping("/manage_product_add")
	public ModelAndView 제품을등록하다(제품 등록제품) {

		ModelAndView mav = new ModelAndView();

		제품관리서비스Impl.제품등록서비스(등록제품);
		mav.setViewName("redirect:/manage_product/"+등록제품.getBarcode());
		return mav;
	}

	@GetMapping("/manage_product/{바코드}")
	public ModelAndView 매니저제품상세화면을준비하다(@PathVariable("바코드") int barcode) {
		
		ModelAndView mav = new ModelAndView();		
		제품 찾은제품 = 제품관리서비스Impl.제품상세정보출력서비스(barcode);
		mav.setViewName("product/manage_product");
		mav.addObject("product", 찾은제품);
		
		return mav;
		
	}
	
	@GetMapping("/manage_product_modify/{바코드}")
	public ModelAndView 매니저제품수정화면을준비하다(@PathVariable("바코드") int barcode) {
		
		ModelAndView mav = new ModelAndView();
		제품 찾은제품 = 제품관리서비스Impl.제품상세정보출력서비스(barcode);
		mav.setViewName("product/manage_product_modify");
		mav.addObject("product", 찾은제품);
		
		return mav;
	
	}
	
	@GetMapping("/manage")
	String 물품관리화면을준비하다() {
		
		return "product/manage";
	}
}
