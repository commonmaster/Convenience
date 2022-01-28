package com.stone.springmvc.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.product.common.제품;
import com.stone.springmvc.product.common.제품페이지구성정보;
import com.stone.springmvc.product.dataservice.제품DAO;

@Service
public class 제품관리서비스Impl implements 제품관리서비스 {

	@Autowired
	제품DAO 제품DAOImpl;
	
	@Override
	public 제품페이지구성정보 제품리스트출력서비스(int pageNo, int search_category) {

		int showRecordCount = 4; // 보여줄 레코드수(3의 배수)(최대 9)
		int startRow = (pageNo - 1) * showRecordCount;

		int totalRecordCount = 제품DAOImpl.get제품갯수(search_category);
		List<제품> contents = 제품DAOImpl.get제품리스트(startRow, showRecordCount, search_category);

		return new 제품페이지구성정보(totalRecordCount, pageNo, contents, showRecordCount);
	}

	@Override
	public 제품 제품상세정보출력서비스(int barcode) {

		제품 product = 제품DAOImpl.get제품(barcode);

		return product;
	}
}
