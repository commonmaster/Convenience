package com.stone.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.common.자유게시글;
import com.stone.springmvc.common.자유게시판페이지구성정보;
import com.stone.springmvc.common.제품;
import com.stone.springmvc.common.제품페이지구성정보;
import com.stone.springmvc.dateservice.편의점업무DAO;

@Service
public class 편의점사이트관리서비스 {
	
	@Autowired
	편의점업무DAO dao;
	

	public 자유게시판페이지구성정보 자유게시판리스트출력서비스(int pageNo, int s_type, String search_content){
		
		int showRecordCount = 5; // 보여줄 레코드수 (최대 10)
		int startRow = (pageNo - 1) * showRecordCount;
		
		int totalRecordCount = dao.게시글갯수검색(s_type, search_content);
		List<자유게시글> contents = dao.자유게시글리스트얻기(startRow, showRecordCount, s_type, search_content);
		
		return new 자유게시판페이지구성정보(totalRecordCount, pageNo, contents, showRecordCount);
	}
	
	public void 자유게시판등록서비스(자유게시글 board) {
		
		if(board.getTitle().trim().isEmpty()) {
			board.setTitle("No Title");
		}
		dao.자유게시글등록(board);
	}
	
	public 자유게시글 자유게시판상세서비스(int no) {
				
		자유게시글 board = dao.자유게시글을찾다(no);
		
		return board;
	}
	
	public int 자유게시글조회수증가서비스(int no) {
		
		return dao.자유게시글조회수증가(no);
	}
	
	public void 자유게시판수정서비스(자유게시글 board) {
		
		dao.자유게시글수정(board);
	}
	
	public void 자유게시판삭제서비스(int no) {
	
		dao.자유게시글삭제(no);
	}
	
	/////////////////////////////////////////////////////////////
	
	public 제품페이지구성정보 제품리스트출력서비스(int pageNo, int search_category){
		
		int showRecordCount = 4; // 보여줄 레코드수(3의 배수)(최대 9)
		int startRow = (pageNo - 1) * showRecordCount;
		
		int totalRecordCount = dao.제품갯수검색(search_category);
		List<제품> contents = dao.제품리스트얻기(startRow, showRecordCount, search_category);
		
		return new 제품페이지구성정보(totalRecordCount, pageNo, contents, showRecordCount);
	}
	
	public 제품 제품상세정보출력서비스(int barcode) {
		
		제품 product = dao.제품을찾다(barcode);
		
		return product;
	}
	
	
}
