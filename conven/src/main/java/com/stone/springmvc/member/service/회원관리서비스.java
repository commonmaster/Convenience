package com.stone.springmvc.member.service;

import com.stone.springmvc.member.common.Member;

public interface 회원관리서비스 {

	int 회원등록서비스(Member 새회원);

	boolean ID사용가능확인서비스(String id);

	Member 회원찾기서비스(String id);

	Boolean 비밀번호변경서비스(String id, String origin_password, String new_password);

	Boolean 회원정보변경서비스(String id, Member member);

	void 회원탈퇴서비스(String id);

}