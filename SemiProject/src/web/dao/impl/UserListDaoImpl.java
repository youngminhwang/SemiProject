package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.UserListDao;
import web.dto.User;

public class UserListDaoImpl implements UserListDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<User> selectAll(Connection conn) {

		String sql = "";
		sql += "SELECT";
		sql += "	user_no";
		sql += "	, user_id";
		sql += "	, user_nick";
		sql += "	, user_rank";
		sql += "	, user_join_date";
		sql += "	, user_email";
		sql += "	, user_phone";
		sql += "	, user_year";
		sql += "	, user_month";
		sql += "	, user_day";
		sql += " FROM user_info";
		sql += " ORDER BY user_no DESC";
		
		//--조회 결과 저장할 List
		List<User> userList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				User u = new User(); //결과값 저장 객체
				
				//결과값 한 행 처리
				u.setUser_no(rs.getInt("user_no"));
				u.setUser_id(rs.getString("user_id"));
				u.setUser_nick(rs.getString("user_nick"));
				u.setUser_rank(rs.getInt("user_rank"));
				u.setUser_join_date(rs.getDate("user_join_date"));
				u.setUser_email(rs.getString("user_email"));
				u.setUser_phone(rs.getString("user_phone"));
				u.setUser_year(rs.getInt("user_year"));
				u.setUser_month(rs.getInt("user_month"));
				u.setUser_day(rs.getInt("user_day"));
				
				
				//리스트객체에 조회한 행 객체 저장
				userList.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기, 자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return userList;
	}
	
	@Override
	public List<User> selectAll(Connection conn, Paging paging) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			user_no, user_rank, user_id, user_pw, user_nick, user_join_date";
		sql += "			, user_gender, user_email, user_phone, user_year, user_month, user_day";
		sql += "		FROM user_info";
		sql += "		ORDER BY user_no DESC";
		sql += " 	) B";
		sql += " ) user_info";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<User> userList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				User u = new User(); //결과값 저장 객체
				
				//결과값 한 행 처리
				u.setUser_no(rs.getInt("user_no"));
				u.setUser_id(rs.getString("user_id"));
				u.setUser_nick(rs.getString("user_nick"));
				u.setUser_rank(rs.getInt("user_rank"));
				u.setUser_join_date(rs.getDate("user_join_date"));
				u.setUser_email(rs.getString("user_email"));
				u.setUser_phone(rs.getString("user_phone"));
				u.setUser_year(rs.getInt("user_year"));
				u.setUser_month(rs.getInt("user_month"));
				u.setUser_day(rs.getInt("user_day"));
				
				//리스트객체에 조회한 행 객체 저장
				userList.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return userList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM user_info";
		
		//총 게시글 수
		int count = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
	}

	
	@Override
	public int deleteUser(Connection conn, User user) {
		
		String sql = "";
		sql += "DELETE user_info";
		sql += " WHERE user_no = ?";
		
		//왜 얘도 -1이지
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	@Override
	public User selectUserByUser_no(Connection conn, User user_no) {

		String sql = "";
		sql += "SELECT";
		sql += " 		user_no";
		sql += " 		, user_id";
		sql += " 		, user_nick";
		sql += " 		, user_rank";
		sql += " FROM user_info";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 DTO객체
		User user = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user_no.getUser_no());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				//결과값 저장 객체
				//ArrayList 대신 Board로 반환. (게시글 하나만 조회하므로)
				user = new User();
				
				//결과값 행 처리
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_nick(rs.getString("user_nick"));
				user.setUser_rank(rs.getInt("user_rank"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return user;
	}
	
	@Override
	public int updateUserRank(Connection conn, User user, User userrank) {

		
		String sql = "";
		sql += " UPDATE user_info";
		sql += " SET user_rank = ? ";
		sql += " WHERE user_no = ?";
		
		//왜 얘도 -1이지
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userrank.getUser_rank());
			ps.setInt(2, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}

	
	@Override
	public List<User> searchIdList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM ";
		sql += "		(SELECT";
		sql += "			user_no, user_rank, user_id, user_nick, user_join_date";
		sql += "			, user_gender, user_email, user_phone, user_year, user_month, user_day";
		sql += "		FROM user_info";
		sql += "      	WHERE user_id LIKE ? ";
		sql += "		ORDER BY user_no DESC)B";
		sql += "	) user_info ";
		sql += " WHERE rnum BETWEEN ? AND ?";
		 
		List<User> userSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				User user= new User();
			
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_rank(rs.getInt("user_rank"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_nick(rs.getString("user_nick"));
				user.setUser_join_date(rs.getDate("user_join_date"));
				user.setUser_gender(rs.getString("user_gender"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_year(rs.getInt("user_year"));
				user.setUser_month(rs.getInt("user_month"));
				user.setUser_day(rs.getInt("user_day"));
				
				userSearchList.add(user);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userSearchList;
	}
	
	@Override
	public List<User> searchNickList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM ";
		sql += "		(SELECT";
		sql += "			user_no, user_rank, user_id, user_nick, user_join_date";
		sql += "			, user_gender, user_email, user_phone, user_year, user_month, user_day";
		sql += "		FROM user_info";
		sql += "      	WHERE user_nick LIKE ?";
		sql += "		ORDER BY user_no DESC";
		sql += "		)B";
		sql += "	) user_info ";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<User> userSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				User user= new User();
			
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_rank(rs.getInt("user_rank"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_nick(rs.getString("user_nick"));
				user.setUser_join_date(rs.getDate("user_join_date"));
				user.setUser_gender(rs.getString("user_gender"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_year(rs.getInt("user_year"));
				user.setUser_month(rs.getInt("user_month"));
				user.setUser_day(rs.getInt("user_day"));
				
				userSearchList.add(user);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userSearchList;
	}
	
	@Override
	public int selectCntIdSearch(Connection conn, String data) {

		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM user_info";
		sql += "	WHERE user_id LIKE ?";
		sql += "	ORDER BY user_no DESC";
					
		//총 게시글 수
		int count = 0;
					
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + data + "%");
			System.out.println("아이디 데이터: " +data);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			System.out.println("아이디카운트 : " + count );
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
			
		return count;

	}
	
	@Override
	public int selectCntNickSearch(Connection conn, String data) {

		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM user_info";
		sql += "	WHERE user_nick LIKE ?";
		sql += "	ORDER BY user_no DESC";
				
		//총 게시글 수
		int count = 0;
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + data + "%");
			
			rs = ps.executeQuery();
					
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			System.out.println("닉카운트 : " + count );
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return count;
	}
	
}
