	package com.stone.springmvc.member.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.member.common.Member;
import com.stone.springmvc.member.service.회원관리서비스;
@Controller
public class 회원관리컨트롤러 {

	@Autowired
	회원관리서비스 회원관리서비스Impl;

	@GetMapping("/join")
	public String 회원가입화면을준비하다() {

		return "member/join";
	}

	@GetMapping("/duplication")
	public String ID중복체크화면을준비하다() {

		return "member/duplication";
	}

	@PostMapping("/duplication")
	public ModelAndView ID중복체크하다(String id) {

		boolean canUse = 회원관리서비스Impl.ID사용가능확인서비스(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/duplication");
		mav.addObject("canUse", canUse);
		mav.addObject("id", id);

		return mav;

	}

	@PostMapping("/join")
	public ModelAndView 회원가입하다(Member member, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		int success = 회원관리서비스Impl.회원등록서비스(member);

		if (success > 0) {
			mav.addObject("name", member.getName());
			mav.setViewName("member/join_result");
			session.setAttribute("conven_session_id", member.getId());

		} else {
			mav.addObject("id", "이미사용중인ID");
			// 중복확인을 했으나 그 사이에 이미 아이디 등록됨
			mav.setViewName("redirect:/join");
		}

		return mav;

	}

	@GetMapping("/member")
	public ModelAndView 회원정보화면을준비하다(HttpSession session) {

		ModelAndView mav = new ModelAndView();

		String id = (String) session.getAttribute("conven_session_id");
		//System.out.println("id:" + id);
		if (id == null) {
			mav.setViewName("error");
		} else {
			Member member = 회원관리서비스Impl.회원찾기서비스(id);
			
			mav.setViewName("member/member");
			mav.addObject("member", member);
		}
		return mav;
	}

	@PostMapping("/member")
	public ModelAndView 회원정보변경하다(HttpSession session, Member member) {
		
		ModelAndView mav = new ModelAndView();

		String id = (String) session.getAttribute("conven_session_id");
		Boolean isSuccess = 회원관리서비스Impl.회원정보변경서비스(id, member);
		if (isSuccess == null) {

			mav.setViewName("error");
		} else {
			mav.setViewName("member/member_change_result");
			mav.addObject("isSuccess", isSuccess);			
		}

		return mav;
	}

	@GetMapping("/pwd_change")
	public String 비밀번호변경화면을준비하다() {

		return "password_change";
	}

	@PostMapping("pwd_change")
	public ModelAndView 비밀번호변경하다(HttpSession session, String origin_password, String password) {

		ModelAndView mav = new ModelAndView();

		String id = (String) session.getAttribute("conven_session_id");
		Boolean isSuccess = 회원관리서비스Impl.비밀번호변경서비스(id, origin_password, password);
		if (isSuccess == null) {

			mav.setViewName("error");
		} else {
			mav.setViewName("member/password_change_result");
			mav.addObject("isSuccess", isSuccess);
		}

		return mav;
	}
	
	@GetMapping("/withdraw")
	public String 회원탈퇴준비하다() {
		
		return "member/withdraw_confirm";
	}
	
	@PostMapping("/withdraw")
	public String 회원탈퇴하다(HttpSession session) {
		
		String id = (String) session.getAttribute("conven_session_id");
		회원관리서비스Impl.회원탈퇴서비스(id);
		session.invalidate();
		
		return "redirect:/main";
	}

}
