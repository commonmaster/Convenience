package com.stone.springmvc.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.member.common.Member;

@Service
public class 회원관리서비스Impl implements 회원관리서비스 {

	@Autowired
	com.stone.springmvc.member.dataservice.회원DAO 회원DAO;	
	
	@Override
	public int 회원등록서비스(Member 새회원) {
		
		return 회원DAO.insert회원(새회원);
	}
	
	@Override
	public boolean ID사용가능확인서비스(String id) {
		
		return !회원DAO.아이디가있는가(id);
	}
	
	@Override
	public Member 회원찾기서비스(String id) {
		
		return 회원DAO.get회원ByID(id);
	}
	
	@Override
	public Boolean 비밀번호변경서비스(String id, String origin_password, String new_password) {
		
		Boolean isInputedCorrect = 회원DAO.비밀번호일치조회ById(id, origin_password);
		
		if(isInputedCorrect != null &&  isInputedCorrect) {
			 회원DAO.update비밀번호(id, new_password);
		}
		
		return isInputedCorrect;
		
	}
	
	@Override
	public Boolean 회원정보변경서비스(String id, Member member) {
		
		// 회원정보 변경시 비밀번호가 일치해야 변경됨
		Boolean isInputedCorrect = 회원DAO.비밀번호일치조회ById(id, member.getPassword());
		
		if(isInputedCorrect != null &&  isInputedCorrect) {
			 회원DAO.update회원정보(id, member);
		}
		
		return isInputedCorrect;
	}
	
	@Override
	public void 회원탈퇴서비스(String id) {
		
		회원DAO.delete회원ById(id);
	}
}
