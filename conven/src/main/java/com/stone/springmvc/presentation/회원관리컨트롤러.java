package com.stone.springmvc.presentation;

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
	
	@PostMapping("/join")
	public ModelAndView 회원가입하다(Member member) {
		
		회원관리서비스.회원등록서비스(member);
		
		ModelAndView mav = new ModelAndView();
		
		return mav;
		
	}
	
}
