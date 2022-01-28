package com.stone.springmvc.board.dataservice;

import java.util.List;

import com.stone.springmvc.board.common.자유게시글;

public interface 자유게시판DAO {

	List<자유게시글> get자유게시글리스트(int startRow, int showRecordCount, int s_type, String search_content);

	int get게시글갯수(int s_type, String search_content);

	void insert자유게시글(자유게시글 board);

	자유게시글 get자유게시글(int no);

	int 자유게시글조회수증가(int no);

	void update자유게시글(자유게시글 board);

	void delete자유게시글(int no);

}