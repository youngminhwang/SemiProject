package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import web.dao.face.FreeCommDao;
import web.dto.FreeComm;

public class FreeCommDaoImpl implements FreeCommDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int insert(Connection conn, FreeComm freeComm) {
		
		String sql = "";
		sql += "INSERT INTO freeComm(idx_comm, idx, comm_content, user_no, user_nick)";
		sql += " VALUES (freecomm_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, freeComm.getIdx());
			ps.setString(2, freeComm.getCommContent());
			ps.setInt(3, freeComm.getUserno());
			ps.setString(4, freeComm.getUsernick());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int delete(Connection conn, FreeComm freeComm) {
		
		String sql = "";
		sql += "DELETE freecomm";
		sql += " WHERE idx = ?";
		sql += " AND idx_comm = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, freeComm.getIdx());
			ps.setInt(2, freeComm.getIdxComm());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int update(Connection conn, FreeComm freeComm) {
		
		String sql = "";
		sql += "UPDATE freecomm";
		sql += " SET comm_content = ?";
		sql += " WHERE idx_comm = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, freeComm.getCommContent());
			ps.setInt(2, freeComm.getIdxComm());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public List<FreeComm> selectFreeCommByIdx(Connection conn, FreeComm idx) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	idx_comm";
		sql += "	, idx";
		sql += "	, comm_date";
		sql += "	, comm_content";
		sql += "	, user_nick";
		sql += "	, user_no";
		sql += " FROM freecomm";
		sql += " WHERE idx = ?";
		sql += " ORDER BY idx_comm ASC";
		
		List<FreeComm> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, idx.getIdx());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				FreeComm freeComm = new FreeComm();
				
				freeComm.setIdx(rs.getInt("idx"));
				freeComm.setIdxComm(rs.getInt("idx_comm"));
				freeComm.setCommContent(rs.getString("comm_content"));
				freeComm.setCommDate(rs.getDate("comm_date"));
				freeComm.setUsernick(rs.getString("user_nick"));
				freeComm.setUserno(rs.getInt("user_no"));
				
				list.add(freeComm);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}


}
