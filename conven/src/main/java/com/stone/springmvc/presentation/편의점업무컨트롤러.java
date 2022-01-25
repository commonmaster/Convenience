package com.stone.springmvc.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.common.자유게시글;
import com.stone.springmvc.common.자유게시판페이지구성정보;
import com.stone.springmvc.common.제품;
import com.stone.springmvc.common.제품페이지구성정보;
import com.stone.springmvc.service.편의점사이트관리서비스;

@Controller
public class 편의점업무컨트롤러 {

	@Autowired
	편의점사이트관리서비스 편의점사이트관리서비스;

	@RequestMapping("/test")
	public String 화면디자인테스트() {

		return "test";
	}

	@RequestMapping("/main")
	public ModelAndView 메인화면을준비하다() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");

		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView 홈화면을준비하다() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");

		return mav;
	}

	@RequestMapping("/boards")
	// 반드시 값이 오는 것이 아니므로 Integer로 받아 null을 받을 수 있게 함.
	public ModelAndView 자유게시판화면을준비하다(Integer pageNo, Integer searchType, String searchContent) {

		int requestPageNo = 1;
		if (pageNo != null) {
			requestPageNo = pageNo;
		}
		// 값을 못받으면 자동으로 null값 반환

		int s_type = 0; // 검색타입 0: 그냥 검색

		if (searchType != null) {
			s_type = searchType;
		}

		if (searchContent != null) {
			// 검색 내용이 빈칸이면 그냥 모두 검색으로
			if (searchContent.trim().equals("")) {
				s_type = 0;
			}
		}

		자유게시판페이지구성정보 pageInfo = 편의점사이트관리서비스.자유게시판리스트출력서비스(requestPageNo, s_type, searchContent);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("boards");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("searchType", s_type);
		mav.addObject("searchContent", searchContent);

		return mav;
	}

	@RequestMapping("/board_add")
	public ModelAndView 자유게시판등록화면을준비하다() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("board_add");

		return mav;
	}

	@PostMapping("/board_add")
	public ModelAndView 자유게시글을등록하다(자유게시글 board) {

		자유게시글 newBoard = board;
		편의점사이트관리서비스.자유게시판등록서비스(newBoard);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/board");

		return mav;
	}

	@GetMapping("/board")
	public ModelAndView 자유게시글상세화면을준비하다(Integer no) {

		ModelAndView mav = new ModelAndView();

		자유게시글 board = 편의점사이트관리서비스.자유게시판상세서비스(no);

		if (board != null) {
			mav.setViewName("board");
			mav.addObject("board", board);
		} else {
			mav.setViewName("error");
		}

		return mav;
	}

	@GetMapping("/board_modify")
	public ModelAndView 자유게시글수정화면을준비하다(Integer no) {

		자유게시글 board = 편의점사이트관리서비스.자유게시판상세서비스(no);

		ModelAndView mav = new ModelAndView();
		if (board != null) {
			mav.setViewName("board_modify");
			mav.addObject("board", board);
		} else {
			mav.setViewName("error");
		}

		return mav;
	}

	@PostMapping("/board_modify")
	public ModelAndView 자유게시글을수정하다(자유게시글 board, Integer pageNo) {

		편의점사이트관리서비스.자유게시판수정서비스(board);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board_detail?no=" + board.getNo() + "&pageNo=" + pageNo);

		return mav;
	}

	@RequestMapping("/board_delete")
	public ModelAndView 자유게시글을삭제하다(Integer no) {

		편의점사이트관리서비스.자유게시판삭제서비스(no);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/boards");

		return mav;

	}
	///////////////////////////////////////////////////////// 자유게시판 끝, 제품 시작

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

		제품페이지구성정보 pageInfo = 편의점사이트관리서비스.제품리스트출력서비스(requestPageNo, search_category);

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

		제품 product = 편의점사이트관리서비스.제품상세정보출력서비스(barcode);

		mav.setViewName("product");
		mav.addObject("product", product);

		return mav;
	}


}
