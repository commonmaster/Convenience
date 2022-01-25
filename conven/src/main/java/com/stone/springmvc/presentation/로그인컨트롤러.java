package com.stone.springmvc.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class 로그인컨트롤러 {

		
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

			mav.setViewName("redirect:/main");
		} else {

			if (id != null && password != null) {
				if (id.equals("user1") && password.equals("1234") || id.equals("user2") && password.equals("1234")) {
					session.setAttribute("conven_session_id", id);
					mav.setViewName("redirect:/main");
				} else {
					mav.setViewName("error");
				}
			} else {
				mav.setViewName("redirect:/login_prepare");
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
