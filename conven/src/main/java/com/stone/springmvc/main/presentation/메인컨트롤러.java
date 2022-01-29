package com.stone.springmvc.main.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.member.common.Member;

@Controller
public class 메인컨트롤러 {

	@Autowired
	com.stone.springmvc.member.service.회원관리서비스 회원관리서비스;
	
	@RequestMapping("/test")
	public String 화면디자인테스트() {

		return "test";
	}

	@RequestMapping("/main")
	public ModelAndView 메인화면을준비하다(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		String id = (String) session.getAttribute("conven_session_id");

		if (id != null) {
			Member 회원 = 회원관리서비스.회원찾기서비스(id);
			mav.addObject("name", 회원.getName());
		}
		mav.setViewName("door/main");

		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView 홈화면을준비하다() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("door/home");

		return mav;
	}
}
