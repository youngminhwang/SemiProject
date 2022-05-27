package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.JDBCTemplate;
import web.dao.face.GetDbDataDao;
import web.dto.UserInfo;

public class GetDbDataDaoImpl implements GetDbDataDao {
	
	private PreparedStatement ps = null;
	
	private ResultSet rs = null;	
	
	@Override
	public List<Map<String, Object>> getUserInfoListFromDb(Connection conn) {
	
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	user_no";
		sql += "	,user_rank";
		sql += "	,user_id";
		sql += "	,user_pw";
		sql += "	,user_nick";
		sql += "	,user_join_date";
		sql += "	,user_gender";
		sql += "	,user_email";
		sql += "	,user_phone";
		sql += "	,user_year";
		sql += "	,user_month";
		sql += "	,user_day";
		sql += " FROM user_info";
		sql += " ORDER BY user_no DESC";
		
		List<Map<String, Object>> userInfoList = new ArrayList<>();
		
		try {

			ps = conn.prepareStatement(sql); 
			
			rs = ps.executeQuery(); 
			
			while(rs.next()) {
				
				Map<String, Object> userInfo = new HashMap<>();
				
				userInfo.put("user_info", new UserInfo());
	
				( (UserInfo)userInfo.get("user_info") ).setUser_no( rs.getInt("user_no") );
				
				( (UserInfo)userInfo.get("user_info") ).setUser_rank( rs.getInt("user_rank") );
				( (UserInfo)userInfo.get("user_info") ).setUser_id( rs.getString("user_id") );
				( (UserInfo)userInfo.get("user_info") ).setUser_pw( rs.getString("user_pw") );
				( (UserInfo)userInfo.get("user_info") ).setUser_nick( rs.getString("user_nick") );
				( (UserInfo)userInfo.get("user_info") ).setUser_join_date( rs.getDate("user_join_date") );
				( (UserInfo)userInfo.get("user_info") ).setUser_gender( rs.getString("user_gender") );
				( (UserInfo)userInfo.get("user_info") ).setUser_email( rs.getString("user_email") );
				( (UserInfo)userInfo.get("user_info") ).setUser_phone( rs.getString("user_phone") );
				( (UserInfo)userInfo.get("user_info") ).setUser_year( rs.getString("user_year") );
				( (UserInfo)userInfo.get("user_info") ).setUser_month( rs.getString("user_month") );
				( (UserInfo)userInfo.get("user_info") ).setUser_day( rs.getString("user_day") );
				
				userInfoList.add(userInfo);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userInfoList;
				
	}
	

	@Override
	public int getNextUserInfoSeq(Connection conn) {
		
		String sql = "";
		sql += "SELECT user_no_seq.nextval FROM dual";
		
		int getNextUserInfoSeq = 0; // 조회된, 다음 시퀀스 결과를 저장할 변수
		
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			
			getNextUserInfoSeq = rs.getInt("nextval");	// 조회된, 다음 시퀀스 결과를 저장할 변수에, 조회된, 다음 시퀀스 결과를 저장 
			
			System.out.println("getNextUserInfoSeq = " + getNextUserInfoSeq);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return getNextUserInfoSeq;	// 조회된, 다음 시퀀스 결과를 반환
	}
	
	@Override
	public int getNextUserBusiSeq(Connection conn) {
		
		String sql = "";
		sql += "SELECT user_busi_seq.nextval FROM dual";
		
		int getNextUserBusiSeq = 0; // 조회된, 다음 시퀀스 결과를 저장할 변수
		
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			
			getNextUserBusiSeq = rs.getInt("nextval");	// 조회된, 다음 시퀀스 결과를 저장할 변수에, 조회된, 다음 시퀀스 결과를 저장 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return getNextUserBusiSeq;	// 조회된, 다음 시퀀스 결과를 반환
	
	}

	
}
