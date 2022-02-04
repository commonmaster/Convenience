package com.stone.springmvc.member.dataservice;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.stone.springmvc.member.common.Member;

import config.DBUtil;

@Repository
public class 회원DAOImpl implements 회원DAO {

	@Override
	public int insert회원(Member 새회원) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into member(id, password, name, email) values(?,?,?,?)";

		int success = -1;

		try {

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 새회원.getId());
			pstmt.setString(2, 새회원.getPassword());
			pstmt.setString(3, 새회원.getName());
			pstmt.setString(4, 새회원.getEmail());

			success = pstmt.executeUpdate();

			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return success;
	}

	@Override
	public Member get회원ByNo(int no) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member 회원 = null;

		String sql = "select * from member where no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				회원 = toConvertMember(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 회원;
	}

	@Override
	public Member get회원ByID(String id) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member 회원 = null;

		String sql = "select * from member where id = ? and state=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				회원 = toConvertMember(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 회원;
	}

	@Override
	public boolean 아이디가있는가(String id) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select count(*) from member where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	@Override
	public Boolean 비밀번호일치조회ById(String id, String pwd) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select password from member where id = ? and state=1";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String password = rs.getString(1);
				if (pwd.equals(password)) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public void update비밀번호(String id, String pwd) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update member set password = ? where id = ?";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);

			int success = pstmt.executeUpdate();
			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update회원정보(String id, Member member) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update member set name=?, email=? where id=?";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, id);

			int success = pstmt.executeUpdate();
			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete회원ById(String id) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update member set state=0 where id=?";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int success = pstmt.executeUpdate();
			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Member toConvertMember(ResultSet rs) {
		Member 회원 = null;
		try {
			회원 = new Member(rs.getInt("no"), rs.getString("id"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"), rs.getInt("state"), rs.getDate("regDate"));

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 회원;
	}
}
