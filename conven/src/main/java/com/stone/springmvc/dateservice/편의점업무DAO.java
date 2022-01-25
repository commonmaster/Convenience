package com.stone.springmvc.dateservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.stone.springmvc.common.자유게시글;
import com.stone.springmvc.common.제품;

import config.DBConfig;

@Repository
public class 편의점업무DAO {	

	public List<자유게시글> 자유게시글리스트얻기(int startRow, int showRecordCount, int s_type, String search_content) {

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<자유게시글> 자유게시글리스트 = new ArrayList<자유게시글>();		
		
		String sql = "";
		if(s_type == 0){ 
			sql = "select no, title, authorId, contents, DATE_ADD(regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board order by no desc limit ?,?";
		}
		else if(s_type == 1) {
			sql = "select no, title, authorId, contents, DATE_ADD(regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board where title like ? order by no desc limit ?,?";
		}
		else if(s_type == 2) {
			sql = "select no, title, authorId, contents, DATE_ADD(regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board where contents like ? order by no desc limit ?,?";
		}
		else {
			sql = "select no, title, authorId, contents, DATE_ADD(regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board order by no desc limit ?,?";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s_type != 0) {
				pstmt.setString(1, "%"+search_content+"%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, showRecordCount);
			}
			else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, showRecordCount);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {

				자유게시글리스트.add(convert자유게시글(rs));
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 자유게시글리스트;
	}
	
	public int 게시글갯수검색(int s_type, String search_content) {
		
		 Connection conn = getConnection();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 String sql = "";
		 if(s_type == 0) {
			 sql = "select count(*) from board";
		 }
		 else if(s_type == 1) {
			 //System.out.println("타이틀 갯수 조건 검색");
			 sql = "select count(*) from board where title like ?";
		 }
		 else if(s_type == 2) {
			 sql = "select count(*) from board where contents like ?";
		 }
		 else {
			 sql = "select count(*) from board";
		 }
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			if(s_type != 0) {
				 pstmt.setString(1, "%"+search_content+"%");
			 }		 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//System.out.println("갯수: " + rs.getInt(1));
				return rs.getInt(1);
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("갯수 오류");
			System.out.println(e.getMessage());
		}
		 return 0; 
		 
		 
	}

	private 자유게시글 convert자유게시글(ResultSet rs) throws SQLException {

		자유게시글 자유게시글객체 = new 자유게시글();

		자유게시글객체.setNo(rs.getInt("no"));
		자유게시글객체.setTitle(rs.getString("title"));
		자유게시글객체.setAuthorId(rs.getString("authorId"));
		자유게시글객체.setContents(rs.getString("contents"));
		자유게시글객체.setRegDate(rs.getString("regDate"));
		자유게시글객체.setModifyDate(rs.getString("modifyDate"));
		자유게시글객체.setReadCount(rs.getInt("readCount"));

		return 자유게시글객체;

	}
	
	public void 자유게시글등록(자유게시글 board) {
		
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		Date now = new Date();
		
		String sql = "insert into board(title, authorId, contents, regDate, modifyDate) values(?,?,?,?,?)";
		
		try {
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getAuthorId());
			pstmt.setString(3, board.getContents());
			pstmt.setTimestamp(4, toTimestamp(now));
			pstmt.setTimestamp(5, toTimestamp(now));
			
			int success = pstmt.executeUpdate();
			
			if(success > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public 자유게시글 자유게시글을찾다(int no) {
		
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select no, title, authorId, contents, DATE_ADD(regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			자유게시글 board = null;
			if(rs.next()) {
				board = convert자유게시글(rs);
				return board;
			}
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public void 자유게시글조회수증가(int no) {
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		
		String sql = "update board set readCount = readCount+1 where no = ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int success = pstmt.executeUpdate();
			
			if(success > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void 자유게시글수정(자유게시글 board) {
		
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		
		String sql = "update board set title=?, contents=?, modifyDate=? where no=?";
		
		try {
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setTimestamp(3, toTimestamp(new Date()));
			pstmt.setLong(4, board.getNo());
			
			int success = pstmt.executeUpdate();
			
			if(success > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void 자유게시글삭제(int no) {
		
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		
		String sql = "delete from board where no=?";
		
		try {
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setLong(1, no);
			
			int success = pstmt.executeUpdate();
			
			if(success > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		
		return new Timestamp(date.getTime());
	}
	
	
	
	private Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(DBConfig.DBURL, DBConfig.ID, DBConfig.PASSWORD);
		
				return conn;
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		}
		
		return null;
		
	}
	
	////////////////////////////////////////////////////////////////////////////////
	public List<제품> 제품리스트얻기(int startRow, int showRecordCount, int search_category) {

		Connection conn = getConnection();
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
	
	public int 제품갯수검색(int search_category) {
		
		 Connection conn = getConnection();
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
	
	public 제품 제품을찾다(int barcode) {
		
		Connection conn = getConnection();		
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
