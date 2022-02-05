package com.stone.springmvc.product.service;

import com.stone.springmvc.product.common.매니저제품페이지구성정보;
import com.stone.springmvc.product.common.제품;

import com.stone.springmvc.product.common.제품페이지구성정보;

public interface 제품관리서비스 {

	제품페이지구성정보 제품리스트출력서비스(int pageNo, int search_category);

	제품 제품상세정보출력서비스(int barcode);
	
	int 제품등록서비스(제품 등록제품);
	
	매니저제품페이지구성정보 매니저제품리스트출력서비스(int 페이지번호, int 제외유무, int 검색타입, String 검색내용);	
	
	int 제품수정서비스(제품 수정할제품, boolean 이미지갱신);
	
	int 제품삭제서비스(int barcode);

}