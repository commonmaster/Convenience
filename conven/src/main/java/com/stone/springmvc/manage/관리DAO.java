package com.stone.springmvc.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import config.DBUtil;

@Repository
public class 관리DAO {

	public void insert제품(제품 제품) {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "insert into product2(productImg) values(?)";
		
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setBytes(1, 제품.getProductImg());
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
}
