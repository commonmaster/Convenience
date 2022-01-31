package com.stone.springmvc.board.service;

import com.stone.springmvc.board.common.자유게시글;
import com.stone.springmvc.board.common.자유게시판페이지구성정보;

public interface 자유게시판관리서비스 {

	public 자유게시판페이지구성정보 자유게시판리스트서비스(int 페이지번호, int 검색타입, String 검색어);
	public void 자유게시판등록서비스(자유게시글 새개시글);
	public 자유게시글 자유게시판상세서비스(int 게시글번호);
	public int 자유게시판조회수증가서비스(int 게시글번호);
	public void 자유게시판수정서비스(자유게시글 게시글);
	public boolean 자유게시판삭제서비스(String 아이디, int 게시글번호);
}
