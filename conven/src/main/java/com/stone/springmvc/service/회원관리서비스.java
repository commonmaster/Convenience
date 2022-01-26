package com.stone.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.common.Member;
import com.stone.springmvc.dateservice.회원DAO;

@Service
public class 회원관리서비스 {

	@Autowired
	회원DAO 회원DAO;	
	
	public int 회원등록서비스(Member 새회원) {
		
		return 회원DAO.회원등록(새회원);
	}
	
	public boolean ID사용가능확인서비스(String id) {
		
		return !회원DAO.아이디가있는가(id);
	}
	
	public Member 회원찾기서비스(String id) {
		
		return 회원DAO.회원찾기ByID(id);
	}
	
	public Boolean 비밀번호변경서비스(String id, String origin_password, String new_password) {
		
		Boolean isInputedCorrect = 회원DAO.비밀번호일치조회ById(id, origin_password);
		
		if(isInputedCorrect != null &&  isInputedCorrect) {
			 회원DAO.비밀번호업데이트(id, new_password);
		}
		
		return isInputedCorrect;
		
	}
	
	public Boolean 회원정보변경서비스(String id, Member member) {
		
		Boolean isInputedCorrect = 회원DAO.비밀번호일치조회ById(id, member.getPassword());
		
		if(isInputedCorrect != null &&  isInputedCorrect) {
			 회원DAO.회원정보업데이트(id, member);
		}
		
		return isInputedCorrect;
	}
}
