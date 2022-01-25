package com.stone.springmvc.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.common.Member;
import com.stone.springmvc.service.회원관리서비스;

@Controller
public class 회원관리컨트롤러 {

	@Autowired
	회원관리서비스 회원관리서비스;

	@GetMapping("/join")
	public String 회원가입화면을준비하다() {

		return "join";
	}

	@GetMapping("/duplication")
	public String ID중복체크화면을준비하다() {

		return "duplication";
	}

	@PostMapping("/duplication")
	public ModelAndView ID중복체크하다(String id) {

		boolean canUse = 회원관리서비스.ID사용가능확인서비스(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("duplication");
		mav.addObject("canUse", canUse);
		mav.addObject("id", id);

		return mav;

	}

	@PostMapping("/join")
	public ModelAndView 회원가입하다(Member member) {

		ModelAndView mav = new ModelAndView();

		int success = 회원관리서비스.회원등록서비스(member);
		
		if (success > 0) {
			mav.addObject("name", member.getName());
			mav.setViewName("join_result");
		
		} else {
			mav.addObject("id", "이미사용중인ID");
			mav.setViewName("redirect:/join");
		}

		return mav;

	}
	
	@GetMapping("/member")
	public ModelAndView 회원정보화면을준비하다(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		String id = (String)session.getAttribute("conven_session_id");
		System.out.println("id:" + id);
		if(id == null) {
			mav.setViewName("error");
		}
		else {
			Member member = 회원관리서비스.회원찾기서비스ByID(id);
			System.out.println("member: " + member.toString());
			mav.setViewName("member");
			mav.addObject("member", member);
		}
		
		
		return mav;
	}
	
	@GetMapping("pwd_change")
	public String 비밀번호변경화면을준비하다() {
		
		return "password_change";
	}

}
