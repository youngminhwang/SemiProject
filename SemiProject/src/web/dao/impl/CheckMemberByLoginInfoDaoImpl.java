package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dao.face.CheckMemberByLoginInfoDao;
import web.dto.UserInfo;

public class CheckMemberByLoginInfoDaoImpl implements CheckMemberByLoginInfoDao {

	private PreparedStatement ps = null;
	
	private ResultSet rs = null;	
	
	@Override
	public int checkMemberByLoginInfo(Connection conn, UserInfo userInfo) {
		
		String sql = "";
		sql += "SELECT count(*) FROM user_info";
		sql += " WHERE user_id = ?";
		sql += " AND user_pw = ?";
		
		int checkcnt = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userInfo.getUser_id());
			ps.setString(2, userInfo.getUser_pw());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				checkcnt = rs.getInt(1);	// 입력된 Login_정보와 일치하는 회원을 조회한 후, 조회한 회원_레코드 중, 가장 첫 번째 회원_레코드의 Index를 반환한다.
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		// (checkcnt != 0) 이면, 입력된 Login_정보와 일치하는 회원이 존재한다는 것을 명시(= 의미)한다.
		return checkcnt;

	}

}
