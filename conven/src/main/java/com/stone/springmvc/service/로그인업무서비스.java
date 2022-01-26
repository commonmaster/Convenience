package com.stone.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.common.Member;
import com.stone.springmvc.dateservice.회원DAO;

@Service
public class 로그인업무서비스 {

	@Autowired
	회원DAO 회원DAO;
	
	public int 로그인서비스(String id, String password) {
		
		Member 회원 = 회원DAO.회원찾기ByID(id);
		if(회원 == null) {
			return -1;
		}
		else {
			if(password.equals(회원.getPassword()))
				return 1;
			else {
				return 0;
			}
		}
	}
}
