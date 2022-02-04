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
		
		자유게시판페이지구성정보 info = new 자유게시판페이지구성정보(); // 보여줄게시물수를 뽑기 위해 일단 생성
		int 보여줄게시글수 = info.getSHOW_RECORD_COUNT();
		int startRow = (페이지번호 - 1) * 보여줄게시글수;		
		
		int totalRecordCount = 자유게시판DAOImpl.get게시글갯수(검색타입, 검색내용);
		List<자유게시글> contents = 자유게시판DAOImpl.get자유게시글리스트(startRow, 보여줄게시글수, 검색타입, 검색내용);
				
		info = new 자유게시판페이지구성정보(totalRecordCount, 페이지번호, contents); // DAO를 통해 데이터를 받아 적용할 페이지정보 생성
		
		return info;
	}
	
	@Override
	public int 자유게시판등록서비스(자유게시글 작성된게시글) {
		
		if(작성된게시글.getTitle().trim().isEmpty()) {
			작성된게시글.setTitle("No Title");
		}
		
		return 자유게시판DAOImpl.insert자유게시글(작성된게시글);
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
	public int 자유게시판수정서비스(자유게시글 게시글) {
		
		return 자유게시판DAOImpl.update자유게시글(게시글);
	}
	
	
	@Override
	public int 자유게시판삭제서비스(int 게시글번호) {
				
		return 자유게시판DAOImpl.delete자유게시글(게시글번호);
	}
	
	
	
}
