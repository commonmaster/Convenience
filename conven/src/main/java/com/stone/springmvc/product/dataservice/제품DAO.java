package com.stone.springmvc.product.dataservice;

import java.util.List;

import com.stone.springmvc.product.common.제품;

public interface 제품DAO {

	List<제품> get제품리스트(int startRow, int showRecordCount, int search_category);

	int get제품갯수(int search_category);

	제품 get제품(int barcode);
	
	int insert제품(제품 등록제품);
	
	List<제품> get매니저제품리스트(int startRow, int showRecordCount, int 제외유무, int 검색타입, String 검색내용);
	
	int get매니저제품갯수(int 제외유무, int 검색타입, String 검색내용);
	
	int update제품(제품 수정할제품, boolean 이미지갱신);

	int delete제품(int barcode);
}