package com.stone.springmvc.member.dataservice;

import com.stone.springmvc.member.common.Member;

public interface 회원DAO {

	int insert회원(Member 새회원);

	Member get회원ByNo(int no);

	Member get회원ByID(String id);

	boolean 아이디가있는가(String id);

	Boolean 비밀번호일치조회ById(String id, String pwd);

	void update비밀번호(String id, String pwd);

	void update회원정보(String id, Member member);

	void delete회원ById(String id);

}