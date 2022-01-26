package com.stone.springmvc.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.stone.springmvc.service.로그인업무서비스;
import com.stone.springmvc.service.회원관리서비스;

@Controller
public class 로그인아웃컨트롤러 {

	@Autowired
	로그인업무서비스 로그인업무서비스;
	@Autowired
	회원관리서비스 회원관리서비스;
		
	@GetMapping("/login")
	public ModelAndView 로그인화면을준비하다(HttpSession session) {

		ModelAndView mav = new ModelAndView();		

		if (sessionExistCheck(session, "conven_session_id")) {

			mav.setViewName("redirect:/main");
		} else {
			mav.setViewName("login");
		}

		return mav;
	}

	@PostMapping("/login")
	public ModelAndView 로그인하다(String id, String password, HttpSession session) {

		
		ModelAndView mav = new ModelAndView();	

		if (sessionExistCheck(session, "conven_session_id")) {

			mav.setViewName("main");
		} else {

			if (id != null && password != null) {
				
				int result = 로그인업무서비스.로그인서비스(id, password);
								
				if (result == 1) {					
					
					session.setAttribute("conven_session_id", id);					
					mav.setViewName("redirect:/main");
				}				
				else {
					
					mav.addObject("result", result);
					mav.setViewName("login");
				}
			}else {
				mav.setViewName("error");
			}
			
		
		}
		return mav;
	}

	@RequestMapping("/logout")
	public ModelAndView 로그아웃하다(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		mav.setViewName("redirect:/main");

		return mav;
	}

	private boolean sessionExistCheck(HttpSession session, String name) {
		if (session.getAttribute(name) == null || session.getAttribute(name).toString().isEmpty()) {
			return false;
		}
		return true;
	}
}
