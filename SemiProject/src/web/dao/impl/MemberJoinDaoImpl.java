package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dao.face.MemberJoinDao;
import web.dto.UserInfo;


public class MemberJoinDaoImpl implements MemberJoinDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null;
	
	@Override
	public int insertUserInfo(Connection conn, UserInfo userInfo) {
		
		String sql = "";
		
		sql += "INSERT INTO user_info(user_no, user_id, user_pw, user_nick, user_gender, user_email, user_phone";
		sql += " ,user_year, user_month, user_day)";
		sql += " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int result = 0; //INSERT 수행 결과 저장 변수
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userInfo.getUser_no());
			ps.setString(2, userInfo.getUser_id());
			ps.setString(3, userInfo.getUser_pw());
			ps.setString(4, userInfo.getUser_nick());
			ps.setString(5, userInfo.getUser_gender());
			ps.setString(6, userInfo.getUser_email());
			ps.setString(7, userInfo.getUser_phone());
			ps.setString(8, userInfo.getUser_year());
			ps.setString(9, userInfo.getUser_month());
			ps.setString(10, userInfo.getUser_day());
			
			//INSERT 수행
			
			result = ps.executeUpdate();	// 성공 시 1을 반환, 실패 시 0을 반환
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	
	@Override
	public int selectDuplicateUserId(Connection conn, String user_id) {
		String sql = "";
		
		sql += "SELECT count(*) cnt FROM user_info";
		sql += " WHERE user_id = ?";
		
		int res = 0;
		try {
			
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, user_id);
			
			rs = ps.executeQuery();	// 성공 시 1을 반환, 실패 시 0을 반환
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int selectDuplicateUserNick(Connection conn, String user_nick) {
		String sql = "";
		
		sql += "SELECT count(*) cnt FROM user_info";
		sql += " WHERE user_nick = ?";
		
		int res = 0;
		try {
			
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, user_nick);
			
			rs = ps.executeQuery();	// 성공 시 1을 반환, 실패 시 0을 반환
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int selectDuplicateUserEmail(Connection conn, String user_email) {
		String sql = "";
		
		sql += "SELECT count(*) cnt FROM user_info";
		sql += " WHERE user_email = ?";
		
		int res = 0;
		try {
			
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, user_email);
			
			rs = ps.executeQuery();	// 성공 시 1을 반환, 실패 시 0을 반환
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
}
