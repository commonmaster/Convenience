package com.stone.springmvc.product.dataservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

		if (search_category == 0) {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product where isExcluded=0 order by name desc limit ?,?";
		} else if (search_category == 1) {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product where type='snack' and isExcluded=0 order by name desc limit ?,?";
		} else if (search_category == 2) {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product where type='drink' and isExcluded=0 order by name desc limit ?,?";
		} else if (search_category == 3) {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product where type='icecream' and isExcluded=0 order by name desc limit ?,?";
		} else {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product order by name desc limit ?,?";
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
		if (search_category == 0) {
			sql = "select count(*) from product where isExcluded=0";
		} else if (search_category == 1) {
			sql = "select count(*) from product where type=1 and isExcluded=0";
		} else if (search_category == 2) {
			sql = "select count(*) from product where type=2 and isExcluded=0";
		} else if (search_category == 3) {
			sql = "select count(*) from product where type=3 and isExcluded=0";
		} else {
			sql = "select count(*) from product where isExcluded=0";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
			if (rs.next()) {
				product = convert제품(rs);
				return product;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	private 제품 convert제품(ResultSet rs) throws SQLException {

		제품 제품객체 = new 제품();

		제품객체.setBarcode(rs.getInt("barcode"));
		제품객체.setName(rs.getString("name"));
		제품객체.setType(rs.getInt("type"));
		제품객체.setPrice(rs.getInt("price"));
		제품객체.setProvider(rs.getString("provider"));
		제품객체.setProductImg(rs.getBytes("productImg"));
		제품객체.setIntro(rs.getString("intro"));
		제품객체.setIsExcluded(rs.getInt("isExcluded"));

		return 제품객체;
	}

	//////////////////////////////////////////////////////////////////////////////
	public int insert제품(제품 제품) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into product(barcode,name,type,price,provider,productImg,intro) values(?,?,?,?,?,?,?)";

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 제품.getBarcode());
			pstmt.setString(2, 제품.getName());
			pstmt.setInt(3, 제품.getType());
			pstmt.setInt(4, 제품.getPrice());
			pstmt.setString(5, 제품.getProvider());
			pstmt.setBytes(6, 제품.getProductImg());
			pstmt.setString(7, 제품.getIntro());

			int success = pstmt.executeUpdate();
			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
				return success;
			}
			conn.close();

		} catch (SQLException e) {
			System.out.println("여기서 발생");
			System.out.println(e.getMessage());
		}
		return 1;
	}

	@Override
	public List<제품> get매니저제품리스트(int startRow, int showRecordCount, int isExcluded, int searchType,
			String searchContent) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<제품> 제품리스트 = new ArrayList<제품>();

		String sql = "";

		if (searchType == 0) {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product where isExcluded=? order by name desc limit ?,?";
		} else if (searchType == 1) {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product where isExcluded=? and name like ? order by name desc limit ?,?";
		} else {
			sql = "select barcode, name, type, price, provider, productImg, intro, isExcluded from product where isExcluded=? order by name desc limit ?,?";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, isExcluded);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, showRecordCount);
			if (searchType == 1) {
				pstmt.setString(2, "%" + searchContent + "%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, showRecordCount);
			}

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
	public int get매니저제품갯수(int isExcluded, int searchType, String searchContent) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		if (searchType == 0) {
			sql = "select count(*) from product where isExcluded=?";
		} else if (searchType == 1) {
			sql = "select count(*) from product where isExcluded=? and name like ?";
		} else {
			sql = "select count(*) from product where isExcluded=?";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, isExcluded);
			if (searchType == 1) {
				pstmt.setString(2, "%" + searchContent + "%");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int update제품(제품 수정할제품, boolean 이미지갱신) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "";
		if (이미지갱신) {
			sql = "update product set name=?, type=?, price=?, provider=?, isExcluded=?, intro=?, productImg=? where barcode=?";
		} else {
			sql = "update product set name=?, type=?, price=?, provider=?, isExcluded=?, intro=? where barcode=?";
		}

		try {

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 수정할제품.getName());
			pstmt.setInt(2, 수정할제품.getType());
			pstmt.setInt(3, 수정할제품.getPrice());
			pstmt.setString(4, 수정할제품.getProvider());
			pstmt.setInt(5, 수정할제품.getIsExcluded());
			pstmt.setString(6, 수정할제품.getIntro());
			pstmt.setInt(7, 수정할제품.getBarcode());
			if (이미지갱신) {
				pstmt.setBytes(7, 수정할제품.getProductImg());
				pstmt.setInt(8, 수정할제품.getBarcode());
			}

			int success = pstmt.executeUpdate();

			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
				return success;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 1;

	}

	public int delete제품(int barcode) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from product where barcode=?";

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, barcode);
			
			int success = pstmt.executeUpdate();

			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
				return success;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return 1;

	}

}
