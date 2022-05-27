package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import common.JDBCTemplate;
import web.dao.face.GetDbDataByLoginSessionDao;
import web.dto.UserInfo;

public class GetDbDataByLoginSessionDaoImpl implements GetDbDataByLoginSessionDao {
	
	private PreparedStatement ps = null;
	
	private ResultSet rs = null;
	
	@Override
	public UserInfo getUserInfoByUserId(Connection conn, UserInfo userInfo) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	user_no";
		sql += "	,user_rank";
		sql += "	,user_id";
		sql += "	,user_pw";
		sql += "	,user_nick";
		sql += "	,user_gender";
		sql += "	,user_email";
		sql += "	,user_phone";
		sql += " FROM user_info";
		sql += " WHERE user_id = ?";
		
		UserInfo result = null;
		
		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, userInfo.getUser_id());
			
			rs = ps.executeQuery(); 
			
			while( rs.next() ) {
				result = new UserInfo();
				
				result.setUser_no(rs.getInt("user_no"));
				result.setUser_rank(rs.getInt("user_rank"));
				result.setUser_id(rs.getString("user_id"));
				result.setUser_pw(rs.getString("user_pw"));
				result.setUser_nick(rs.getString("user_nick"));
				result.setUser_gender(rs.getString("user_gender"));
				result.setUser_email(rs.getString("user_email"));
				result.setUser_phone(rs.getString("user_phone"));
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
	public int getUserNoByUserId(Connection conn, String userId) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT user_no FROM user_info";
		sql += " WHERE user_id = ?";
		
		int userNo = 0;
		
		try {
			
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, userId); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				
				userNo = rs.getInt("user_no");
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return userNo;
	
	}
	
	@Override
	public String getUserNickByUserId(Connection conn, String userId) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT user_nick FROM user_info";
		sql += " WHERE user_id = ?";
		
		//결과 저장할 String 변수
		String userNick = null;
		
		try {
			
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, userId); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				
				userNick = rs.getString("user_nick");
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return userNick;
		
	}
	
}
