package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dao.face.SearchLoginDao;
import web.dto.UserInfo;

public class SearchLoginDaoImpl implements SearchLoginDao {
	
	private PreparedStatement ps = null;
	
	private ResultSet rs = null;
	
	@Override
	public String findUserIdByEmail(Connection conn, String email) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	user_id";
		sql += "	From user_info";
		sql += "	WHERE user_email = ?";
		
		String userEmail = null;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, email);
				
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				userEmail = rs.getString("user_id");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	
		return userEmail;
	}

	@Override
	public String findUserPwByIdAndEmail(Connection conn, UserInfo userInfo) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	user_pw";
		sql += "	From user_info";
		sql += "	WHERE user_id = ? AND user_email = ?";
		
		String userPw = null;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, userInfo.getUser_id());
			ps.setString(2, userInfo.getUser_email());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				userPw = rs.getString("user_pw");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	
		return userPw;
		
	}
	
}
