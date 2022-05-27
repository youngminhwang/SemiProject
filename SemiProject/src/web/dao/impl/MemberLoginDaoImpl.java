package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dao.face.MemberLoginDao;


public class MemberLoginDaoImpl implements MemberLoginDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null;

	@Override
	public int getLoginCheck(String id, String pw,Connection conn) {
		String sql="";
		sql+="select count(*) cnt from user_info where user_id=? and user_pw=?";
		int res =0;
		try {
			System.out.println("id : "+id+", pw : "+pw);
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return res;
	}
}
