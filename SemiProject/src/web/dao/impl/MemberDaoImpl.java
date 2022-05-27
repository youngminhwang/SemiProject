package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dao.face.MemberDao;
import web.dto.UserInfo;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntUserInfoByUseridUserpw(Connection conn, UserInfo userInfo) {
		
		String sql = "";
		sql += "SELECT count(*) FROM user_info";
		sql += " WHERE user_id = ?";
		sql += "	AND user_pw = ?";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userInfo.getUser_id());
			ps.setString(2, userInfo.getUser_pw());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
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
	public UserInfo selectUserInfoByUserid(Connection conn, UserInfo userInfo) {

		String sql = "";
		sql += "SELECT ";
		sql += "	user_id, user_pw, user_nick, user_no, user_rank";
		sql += "	, user_phone, user_join_date, user_email";
		sql += " FROM user_info";
		sql += " WHERE user_id = ?";
		
		//조회 결과를 저장할 DTO
		UserInfo result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userInfo.getUser_id());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result = new UserInfo();
				
				result.setUser_id( rs.getString("user_id") );
				result.setUser_pw( rs.getString("user_pw") );
				result.setUser_nick( rs.getString("user_nick") );
				result.setUser_email(rs.getString("user_email"));
				result.setUser_rank(rs.getInt("user_rank"));
				result.setUser_no(rs.getInt("user_no"));
				result.setUser_phone(rs.getString("user_phone"));
				result.setUser_join_date(rs.getDate("user_join_date"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;

	}
	
	@Override
	public int insert(Connection conn, UserInfo userInfo) {
		
		String sql = "";
		sql += "INSERT INTO user_info ( ";
		sql += "	user_no, user_id, user_pw, user_nick";
		sql += "	, user_gender, user_email, user_phone)";
		sql += " VALUES (user_no_seq.nextval, ?, ?, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userInfo.getUser_id());
			ps.setString(2, userInfo.getUser_pw());
			ps.setString(3, userInfo.getUser_nick());
			ps.setString(4, userInfo.getUser_gender());
			ps.setString(5, userInfo.getUser_email());
			ps.setString(6, userInfo.getUser_phone());	
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
}

