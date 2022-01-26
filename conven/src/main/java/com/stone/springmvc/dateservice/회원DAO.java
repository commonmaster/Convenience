package com.stone.springmvc.dateservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.stone.springmvc.common.Member;

import config.DBConfig;

@Repository
public class 회원DAO {

	public int 회원등록(Member 새회원) {

		Connection conn = getConnection();
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

	public Member 회원찾기ByNo(int no) {

		Connection conn = getConnection();
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

	public Member 회원찾기ByID(String id) {

		Connection conn = getConnection();
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

	public boolean 아이디가있는가(String id) {

		Connection conn = getConnection();
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

	public Boolean 비밀번호일치조회ById(String id, String pwd) {

		Connection conn = getConnection();
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

	public void 비밀번호업데이트(String id, String pwd) {

		Connection conn = getConnection();
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
	
	public void 회원정보업데이트(String id, Member member) {
		
		Connection conn = getConnection();
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
	
	public void 회원탈퇴ById(String id) {
		
		Connection conn = getConnection();
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

	private Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(DBConfig.DBURL, DBConfig.ID, DBConfig.PASSWORD);

			return conn;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		}

		return null;

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
