package com.stone.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.common.Member;
import com.stone.springmvc.dateservice.회원DAO;

@Service
public class 회원관리서비스 {

	@Autowired
	회원DAO 회원DAO;	
	
	public void 회원등록서비스(Member 새회원) {
		
		회원DAO.회원등록(새회원);
	}
	
	public boolean ID사용가능확인서비스(String id) {
		
		return !회원DAO.아이디가있는가(id);
	}
}
