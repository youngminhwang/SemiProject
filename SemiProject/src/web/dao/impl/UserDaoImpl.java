package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dao.face.UserDao;
import web.dto.User;

public class UserDaoImpl implements UserDao{

	//SQL수행 객체
	private PreparedStatement ps = null;
		
	//SQL 조회 결과 객체
	private ResultSet rs = null;
	
	@Override
	public int selectCntUserByUseridUserpw(Connection conn, User user) {
	
		String sql = "";
		sql += "SELECT count(*) FROM user_info";
		sql += " WHERE user_id = ?";
		sql += " AND user_pw = ?";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getUser_pw());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				//음
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}
	
	
	@Override
	public User selectUserByUserid(Connection conn, User user) {
	
		String sql = "";
		sql += "SELECT user_id, user_pw, user_nick";
		sql += " FROM user_info";
		sql += " WHERE user_id = ? ";
		
		
		//조회 결과를 저장할 DTO
		User result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUser_id());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				result = new User();
				
				result.setUser_id(rs.getString("user_id"));
				result.setUser_pw(rs.getString("user_pw"));
				result.setUser_nick(rs.getString("user_nick"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
}
