package com.stone.springmvc.product.dataservice;

import java.util.List;

import com.stone.springmvc.product.common.제품;

public interface 제품DAO {

	List<제품> get제품리스트(int startRow, int showRecordCount, int search_category);

	int get제품갯수(int search_category);

	제품 get제품(int barcode);

}