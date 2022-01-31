package com.stone.springmvc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.board.common.자유게시글;
import com.stone.springmvc.board.common.자유게시판페이지구성정보;
import com.stone.springmvc.board.dataservice.자유게시판DAO;


@Service
public class 자유게시판관리서비스Impl implements 자유게시판관리서비스{
	
	@Autowired
	자유게시판DAO 자유게시판DAOImpl;
	
	@Override
	public 자유게시판페이지구성정보 자유게시판리스트서비스(int 페이지번호, int 검색타입, String 검색내용){
		
		int showRecordCount = 5; // 보여줄 레코드수 (최대 10)
		int startRow = (페이지번호 - 1) * showRecordCount;
		
		int totalRecordCount = 자유게시판DAOImpl.get게시글갯수(검색타입, 검색내용);
		List<자유게시글> contents = 자유게시판DAOImpl.get자유게시글리스트(startRow, showRecordCount, 검색타입, 검색내용);
		
		return new 자유게시판페이지구성정보(totalRecordCount, 페이지번호, contents, showRecordCount);
	}
	
	@Override
	public void 자유게시판등록서비스(자유게시글 작성된게시글) {
		
		if(작성된게시글.getTitle().trim().isEmpty()) {
			작성된게시글.setTitle("No Title");
		}
		자유게시판DAOImpl.insert자유게시글(작성된게시글);
	}
	
	@Override
	public 자유게시글 자유게시판상세서비스(int 게시글번호) {
				
		자유게시글 board = 자유게시판DAOImpl.get자유게시글(게시글번호);
		
		return board;
	}
	
	@Override
	public int 자유게시판조회수증가서비스(int 게시글번호) {
		
		return 자유게시판DAOImpl.자유게시글조회수증가(게시글번호);
	}
	
	@Override
	public void 자유게시판수정서비스(자유게시글 게시글) {
		
		자유게시판DAOImpl.update자유게시글(게시글);
	}
	
	
	@Override
	public boolean 자유게시판삭제서비스(String id, int 게시글번호) {
		자유게시글 board = 자유게시판DAOImpl.get자유게시글(게시글번호);
		if(id.equals(board.getAuthorId())) {
			// todo
		}
		else {
			
		}
		
		자유게시판DAOImpl.delete자유게시글(게시글번호);
		
		return true;
	}
	
	
	
}
