package com.stone.springmvc.board.presentation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.board.common.자유게시글;
import com.stone.springmvc.board.common.자유게시판페이지구성정보;
import com.stone.springmvc.board.service.자유게시판관리서비스;
import com.stone.springmvc.member.common.Member;
import com.stone.springmvc.member.service.회원관리서비스;

@Controller
public class 자유게시판컨트롤러 {

	@Autowired
	자유게시판관리서비스 자유게시판관리서비스Impl;
	@Autowired
	회원관리서비스 회원관리서비스Impl;

	@RequestMapping("/boards")
	// 반드시 값이 오는 것이 아니므로 Integer로 받아 null을 받을 수 있게 함.
	public ModelAndView 자유게시판화면을준비하다(Integer pageNo, Integer searchType, String searchContent, HttpSession session) {

		int requestPageNo = 1;
		if (pageNo != null) {
			requestPageNo = pageNo;
		}
		// 값을 못받으면 자동으로 null값 반환

		int 검색타입 = 0; // 검색타입 0: 그냥 검색

		if (searchType != null) {
			검색타입 = searchType;
		}

		if (searchContent != null) {
			// 검색 내용이 빈칸이면 그냥 모두 검색으로
			if (searchContent.trim().equals("")) {
				검색타입 = 0;
			}
		} else {
			검색타입 = 0;
		}

		자유게시판페이지구성정보 pageInfo = 자유게시판관리서비스Impl.자유게시판리스트서비스(requestPageNo, 검색타입, searchContent);

		ModelAndView mav = new ModelAndView();

		String id = (String) session.getAttribute("conven_session_id");
		if (id != null) {

			Member 회원 = 회원관리서비스Impl.회원찾기서비스(id);
			mav.addObject("name", 회원.getName());
		}

		mav.setViewName("board/boards");
		mav.addObject("pageInfo", pageInfo);

		// 검색타입과 검색 내용을 넘겨서 페이지 이동시에도 보존
		mav.addObject("searchType", 검색타입);
		mav.addObject("searchContent", searchContent);

		return mav;
	}

	@GetMapping("/board_add")
	public ModelAndView 자유게시판등록화면을준비하다() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("board/board_add");

		return mav;
	}

	@PostMapping("/board_add")
	public ModelAndView 자유게시글을등록하다(자유게시글 board) {

		자유게시글 newBoard = board;
		자유게시판관리서비스Impl.자유게시판등록서비스(newBoard);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/boards");

		return mav;
	}

	@GetMapping("/board")
	public ModelAndView 자유게시글상세화면을준비하다(Integer no, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		ModelAndView mav = new ModelAndView();

		자유게시글 board = 자유게시판관리서비스Impl.자유게시판상세서비스(no);

		Cookie[] cookies = request.getCookies();
		String id = (String) session.getAttribute("conven_session_id");

		// 비교하기 위해 새로운 쿠키
		Cookie viewCookie = null;

		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				// Cookie의 name이 conven_board + no와 일치하는 쿠키를 viewCookie에 넣어줌
				if (cookies[i].getName().equals("conven_board" + no)) {
					System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
					viewCookie = cookies[i];
				}
			}
		}

		if (board != null) {
			// 로그인이 되어있고
			if (id != null) {
				// 쿠키가 없다면
				if (viewCookie == null) {
					System.out.println("cookie 없음");

					// 쿠키 생성(이름, 값)
					Cookie newCookie = new Cookie("conven_board" + no, "|" + no + "|");

					// 쿠키 추가
					response.addCookie(newCookie);

					// 쿠키를 추가 시키고 조회수 증가시킴
					int result = 자유게시판관리서비스Impl.자유게시판조회수증가서비스(no);

					if (result > 0) {
						System.out.println("조회수 증가");
						board.setReadCount(board.getReadCount() + 1);
					} else {
						System.out.println("조회수 증가 에러");
					}
				}
				// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
				else {
					System.out.println("cookie 있음");
					// 쿠키 값 받아옴.
					String value = viewCookie.getValue();
					System.out.println("cookie 값 : " + value);

				}

				Member 회원 = 회원관리서비스Impl.회원찾기서비스(id);
				mav.addObject("name", 회원.getName());
			}

			mav.setViewName("board/board");
			mav.addObject("board", board);

			// 보낼 board 객체가 null 이라면
		} else {
			mav.setViewName("error");
		}

		return mav;
	}

	@GetMapping("/board_modify")
	public ModelAndView 자유게시글수정화면을준비하다(Integer no, HttpSession session) {

		String id = (String) session.getAttribute("conven_session_id");
		ModelAndView mav = new ModelAndView();
		// 게시글 정보 받아오기
		자유게시글 board = 자유게시판관리서비스Impl.자유게시판상세서비스(no);

		if (board != null) {
			if (id.equals(board.getAuthorId())) {
				mav.setViewName("board/board_modify");
				mav.addObject("board", board);
			} else {
				mav.setViewName("error/not_same_writer_error");
			}

			mav.setViewName("board/board_modify");
			mav.addObject("board", board);
		} else {
			mav.setViewName("error");
		}

		return mav;
	}

	@PostMapping("/board_modify")
	public ModelAndView 자유게시글을수정하다(자유게시글 board, Integer pageNo, HttpSession session) {

		String id = (String) session.getAttribute("conven_session_id");

		자유게시판관리서비스Impl.자유게시판수정서비스(board);
		Member 회원 = 회원관리서비스Impl.회원찾기서비스(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board?no=" + board.getNo() + "&pageNo=" + pageNo);
		mav.addObject("name", 회원.getName());

		return mav;
	}

	@GetMapping("/board_delete")
	public ModelAndView 자유게시글을삭제하다(Integer no, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		
		String id = (String) session.getAttribute("conven_session_id");
		자유게시글 board = 자유게시판관리서비스Impl.자유게시판상세서비스(no);
		
		if (id.equals(board.getAuthorId())) {
			자유게시판관리서비스Impl.자유게시판삭제서비스(id, no);
			mav.setViewName("redirect:/boards");

		}
		else {
			mav.setViewName("error/not_same_writer_error");
		}		
		
		if (id != null) {
			Member 회원 = 회원관리서비스Impl.회원찾기서비스(id);
			mav.addObject("name", 회원.getName());
		}

		return mav;
	}
	
	

}
