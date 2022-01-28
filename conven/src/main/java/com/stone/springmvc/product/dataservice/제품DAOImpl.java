package com.stone.springmvc.product.dataservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.stone.springmvc.product.common.제품;

import config.DBUtil;

@Repository
public class 제품DAOImpl implements 제품DAO {

	@Override
	public List<제품> get제품리스트(int startRow, int showRecordCount, int search_category) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<제품> 제품리스트 = new ArrayList<제품>();		
		
		String sql = "";
		
		if(search_category == 0){
			sql = "select barcode, name, type, price, provider, imgUrl, intro from product order by barcode desc limit ?,?";
		}
		else if(search_category == 1) {
			sql = "select barcode, name, type, price, provider, imgUrl, intro from product where type='snack' order by barcode desc limit ?,?";
		}
		else if(search_category == 2) {
			sql = "select barcode, name, type, price, provider, imgUrl, intro from product where type='drink' order by barcode desc limit ?,?";
		}
		else if(search_category == 3) {
			sql = "select barcode, name, type, price, provider, imgUrl, intro from product where type='icecream' order by barcode desc limit ?,?";
		}
		else {
			sql = "select barcode, name, type, price, provider, imgUrl, intro from product order by barcode desc limit ?,?";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, showRecordCount);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				제품리스트.add(convert제품(rs));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 제품리스트;
	}
	
	@Override
	public int get제품갯수(int search_category) {
		
		 Connection conn = DBUtil.getConnection();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 String sql = "";
		 if(search_category == 0) {
			 sql = "select count(*) from product";
		 }
		 else if(search_category ==1) {
			 sql = "select count(*) from product where type='snack'";
		 }
		 else if(search_category == 2) {
			 sql = "select count(*) from product where type='drink'";
		 }
		 else if(search_category == 3) {
			 sql = "select count(*) from product where type='icecream'";
		 }
		 else {
			 sql = "select count(*) from product";
		 }
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		 return 0; 
		 
		 
	}
	
	@Override
	public 제품 get제품(int barcode) {
		
		Connection conn = DBUtil.getConnection();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select * from product where barcode=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, barcode);
			
			rs = pstmt.executeQuery();
			제품 product = null;
			if(rs.next()) {
				product = convert제품(rs);
				return product;
			}
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	private 제품 convert제품(ResultSet rs) throws SQLException {

		제품 제품객체 = new 제품();

		제품객체.setBarcode(rs.getInt("barcode"));
		제품객체.setName(rs.getString("name"));
		제품객체.setType(rs.getString("type"));
		제품객체.setPrice(rs.getInt("price"));
		제품객체.setProvider(rs.getString("provider"));
		제품객체.setImgUrl(rs.getString("imgUrl"));	
		제품객체.setIntro(rs.getString("intro"));
		

		return 제품객체;
	}
}
