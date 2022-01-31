package com.stone.springmvc.board.dataservice;

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

import com.stone.springmvc.board.common.자유게시글;

import config.DBConfig;
import config.DBUtil;

@Repository
public class 자유게시판DAOImpl implements 자유게시판DAO {

	@Override
	public List<자유게시글> get자유게시글리스트(int startRow, int showRecordCount, int s_type, String search_content) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<자유게시글> 자유게시글리스트 = new ArrayList<자유게시글>();

		// 검색 유형에 따라 sql 분리(날짜 제대로 뽑기 위해 *을 안 씀)
		String sql = "";
		if (s_type == 0) {
			sql = "select board.no, title, authorId, name, contents, DATE_ADD(board.regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board,member where board.authorId = member.id and isDeleted = 0  order by no desc limit ?,?";
		} else if (s_type == 1) {
			sql = "select board.no, title, authorId, name, contents, DATE_ADD(board.regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board,member  where board.authorId = member.id and isDeleted = 0 and title like ? order by no desc limit ?,?";
		} else if (s_type == 2) {
			sql = "select board.no, title, authorId, name, contents, DATE_ADD(board.regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board,member  where board.authorId = member.id and isDeleted = 0 and contents like ? order by no desc limit ?,?";
		} else {
			sql = "select board.no, title, authorId, name, contents, DATE_ADD(board.regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount from board,member where board.authorId = member.id and isDeleted = 0 order by no desc limit ?,?";
		}

		try {
			pstmt = conn.prepareStatement(sql);

			if (s_type != 0) {
				pstmt.setString(1, "%" + search_content + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, showRecordCount);
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, showRecordCount);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {

				자유게시글 board = convert자유게시글(rs);
				board.setName(rs.getString("name"));
				자유게시글리스트.add(board);
			}
			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 자유게시글리스트;
	}

	@Override
	public int get게시글갯수(int s_type, String search_content) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		if (s_type == 0) {
			sql = "select count(*) from board where isDeleted = 0";
		} else if (s_type == 1) {
			// System.out.println("타이틀 갯수 조건 검색");
			sql = "select count(*) from board where title like ? and isDeleted = 0";
		} else if (s_type == 2) {
			sql = "select count(*) from board where contents like ? and isDeleted = 0";
		} else {
			sql = "select count(*) from board where isDeleted = 0";
		}

		try {
			pstmt = conn.prepareStatement(sql);

			if (s_type != 0) {
				pstmt.setString(1, "%" + search_content + "%");
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// System.out.println("갯수: " + rs.getInt(1));
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

	@Override
	public void insert자유게시글(자유게시글 board) {

		Connection conn = DBUtil.getConnection();
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
	public 자유게시글 get자유게시글(int no) {

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select board.no, title, authorId, name, contents, DATE_ADD(board.regDate, INTERVAL 9 HOUR) as regDate, DATE_ADD(modifyDate, INTERVAL 9 HOUR) as modifyDate, readCount, isDeleted from board,member where board.authorId = member.id and board.no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			자유게시글 board = null;
			if (rs.next()) {
				board = convert자유게시글(rs);
				board.setName(rs.getString("name"));
				board.setIsDeleted(rs.getInt("isDeleted"));
				return board;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public int 자유게시글조회수증가(int no) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int success = -1;

		String sql = "update board set readCount = readCount+1 where no = ? and isDeleted = 0";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

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
	public void update자유게시글(자유게시글 board) {

		Connection conn = DBUtil.getConnection();
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
	public void delete자유게시글(int no) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update board set isDeleted=1 where no=?";

		try {

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

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
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		}

		return null;

	}

}
