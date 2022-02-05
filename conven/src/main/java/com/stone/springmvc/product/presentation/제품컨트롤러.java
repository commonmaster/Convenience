package com.stone.springmvc.product.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.board.common.자유게시판페이지구성정보;
import com.stone.springmvc.member.common.Member;
import com.stone.springmvc.member.service.회원관리서비스;
import com.stone.springmvc.product.common.매니저제품페이지구성정보;
import com.stone.springmvc.product.common.제품;

import com.stone.springmvc.product.common.제품페이지구성정보;
import com.stone.springmvc.product.service.제품관리서비스;

@Controller
public class 제품컨트롤러 {

	@Autowired
	제품관리서비스 제품관리서비스Impl;
	@Autowired
	회원관리서비스 회원관리서비스Impl;

	@GetMapping("/products")
	public ModelAndView 고객용제품소개화면을준비하다(Integer pageNo, Integer category, HttpSession session) {

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
	public ModelAndView 고객용제품상세화면을준비하다(Integer barcode) {

		// System.out.println("제품상세화면:" + barcode);
		ModelAndView mav = new ModelAndView();

		제품 product = 제품관리서비스Impl.제품상세정보출력서비스(barcode);

		mav.setViewName("product/product");
		mav.addObject("product", product);

		return mav;
	}

	@GetMapping("/productImg/{바코드}") // 예 /profile/1
	public ModelAndView 제품사진을주다(@PathVariable("바코드") int barcode) {
		ModelAndView mav = new ModelAndView();

		제품 찾은제품 = 제품관리서비스Impl.제품상세정보출력서비스(barcode);

		mav.setViewName("product/productImg");
		mav.addObject("productImg", 찾은제품.getProductImg());

		return mav;
	}

	/////////////////////////////////////////////////////////////////////////////

	@GetMapping("/manage")
	String 매니저제품관리화면을준비하다(HttpSession session) {

		if (isAdministrator(session)) {
			return "product/manage";
		}
		return "error/no_administrator";
	}

	@GetMapping("/manage_product_add")
	public String 매니저제품등록화면을준비하다(HttpSession session) {

		if (isAdministrator(session)) {
			return "product/manage_product_add";
		}
		return "error/no_administrator";
	}

	@PostMapping("/manage_product_add")
	public ModelAndView 매니저제품을등록하다(제품 등록제품) {

		ModelAndView mav = new ModelAndView();

		int success = 제품관리서비스Impl.제품등록서비스(등록제품);
		if (success > 0) {
			mav.setViewName("redirect:/manage_product?barcode=" + 등록제품.getBarcode());
		} else {
			mav.setViewName("error/manage_insert_fail");
		}

		return mav;
	}

	@GetMapping("/manage_product")
	public ModelAndView 매니저제품상세화면을준비하다(int barcode, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		if (isAdministrator(session)) {
			제품 찾은제품 = 제품관리서비스Impl.제품상세정보출력서비스(barcode);
			mav.setViewName("product/manage_product");
			mav.addObject("product", 찾은제품);
		} else {
			mav.setViewName("error/no_administrator");
		}
		return mav;

	}

	@GetMapping("/manage_products")
	public ModelAndView 매니저판매제품화면을준비하다(Integer pageNo, String searchContent, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		if (isAdministrator(session)) {
			int requestPageNo = 1;
			if (pageNo != null) {
				requestPageNo = pageNo;
			}
			// 값을 못받으면 자동으로 null값 반환
			// 검색타입 0: 그냥 검색
			// 검색타입 1: 제목 검색

			int 검색타입 = 0;

			if (searchContent != null) {
				// 검색 내용이 빈칸이면 그냥 모두 검색으로
				if (searchContent.trim().equals("")) {
					검색타입 = 0;
				} else {
					검색타입 = 1;
				}
			} else {
				검색타입 = 0;
			}

			매니저제품페이지구성정보 pageInfo = 제품관리서비스Impl.매니저제품리스트출력서비스(requestPageNo, 0, 검색타입, searchContent); // 0:판매중

			mav.setViewName("product/manage_products");
			mav.addObject("pageInfo", pageInfo);

			// 검색타입과 검색 내용을 넘겨서 페이지 이동시에도 보존
			mav.addObject("searchType", 검색타입);
			mav.addObject("searchContent", searchContent);
		} else {
			mav.setViewName("error/no_administrator");
		}

		return mav;
	}

	@GetMapping("/manage_products_ex")
	public ModelAndView 매니저제외제품화면을준비하다(Integer pageNo, String searchContent, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		if (isAdministrator(session)) {

			int requestPageNo = 1;
			if (pageNo != null) {
				requestPageNo = pageNo;
			}
			// 값을 못받으면 자동으로 null값 반환
			// 검색타입 0: 그냥 검색
			// 검색타입 1: 제목 검색

			int 검색타입 = 0;

			if (searchContent != null) {
				// 검색 내용이 빈칸이면 그냥 모두 검색으로
				if (searchContent.trim().equals("")) {
					검색타입 = 0;
				} else {
					검색타입 = 1;
				}
			} else {
				검색타입 = 0;
			}

			매니저제품페이지구성정보 pageInfo = 제품관리서비스Impl.매니저제품리스트출력서비스(requestPageNo, 1, 검색타입, searchContent); // 1:판매제외

			mav.setViewName("product/manage_products_ex");
			mav.addObject("pageInfo", pageInfo);

			// 검색타입과 검색 내용을 넘겨서 페이지 이동시에도 보존
			mav.addObject("searchType", 검색타입);
			mav.addObject("searchContent", searchContent);
		} else {
			mav.setViewName("error/no_administrator");
		}

		return mav;
	}

	@GetMapping("/manage_product_modify")
	public ModelAndView 매니저제품수정화면을준비하다(int barcode, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		if (isAdministrator(session)) {

			제품 찾은제품 = 제품관리서비스Impl.제품상세정보출력서비스(barcode);

			mav.setViewName("product/manage_product_modify");
			mav.addObject("product", 찾은제품);
		} else {
			mav.setViewName("error/no_administrator");
		}
		return mav;
	}

	@PostMapping("/manage_product_modify")
	public ModelAndView 매니저제품수정하다(제품 수정할제품, Integer isImgChange) {

		ModelAndView mav = new ModelAndView();

		boolean imgChange = false;

		if (isImgChange != null) {
			if (isImgChange == 1) {
				imgChange = true;
			}
		}

		int success = 제품관리서비스Impl.제품수정서비스(수정할제품, imgChange);
		if (success > 0) {
			mav.setViewName("redirect:/manage_product?barcode=" + 수정할제품.getBarcode());
		} else {
			mav.setViewName("error/manage_update_fail");
		}

		return mav;
	}
	
	
	@GetMapping("/manage_product_delete")
	public String 매니저제품삭제하다(int barcode, int isExcluded, HttpSession session) {


		if (isAdministrator(session)) {
			int success = 제품관리서비스Impl.제품삭제서비스(barcode);
			if(success > 0) {
				if(isExcluded == 0) {
					return "redirect:/manage_products";
				}
				else if(isExcluded == 1){
					return "redirect:/manage_products_ex";
				}
				else {
					return "error/error";
				}
			}else {
				return "error/manage_delete_fail";
			}
			
		}
		return "error/no_administrator";
		
	}

	// 관리자인지 확인
	private boolean isAdministrator(HttpSession session) {

		boolean result = false;

		String id = (String) session.getAttribute("conven_session_id");
		if (id != null) {
			if (id.equals("admin")) {
				result = true;
			}
		}
		return result;
	}

}
