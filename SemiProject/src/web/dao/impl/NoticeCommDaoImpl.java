package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import web.dao.face.NoticeCommDao;
import web.dto.NoticeComm;

public class NoticeCommDaoImpl implements NoticeCommDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int insert(Connection conn, NoticeComm noticeComm) {
		
		String sql = "";
		sql += "INSERT INTO noticecomm(idx_comm, idx, comm_content, user_no, user_nick)";
		sql += " VALUES (noticecomm_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, noticeComm.getIdx());
			ps.setString(2, noticeComm.getCommContent());
			ps.setInt(3, noticeComm.getUserno());
			ps.setString(4, noticeComm.getUsernick());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int delete(Connection conn, NoticeComm noticeComm) {
		
		String sql = "";
		sql += "DELETE noticeComm";
		sql += " WHERE idx = ?";
		sql += " AND idx_comm = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, noticeComm.getIdx());
			ps.setInt(2, noticeComm.getIdxComm());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int update(Connection conn, NoticeComm noticeComm) {
		
		String sql = "";
		sql += "UPDATE noticecomm";
		sql += " SET comm_content = ?";
		sql += " WHERE idx_comm = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, noticeComm.getCommContent());
			ps.setInt(2, noticeComm.getIdxComm());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public List<NoticeComm> selectNoticeCommByIdx(Connection conn, NoticeComm idx) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	idx_comm";
		sql += "	, idx";
		sql += "	, comm_date";
		sql += "	, comm_content";
		sql += "	, user_nick";
		sql += "	, user_no";
		sql += " FROM noticecomm";
		sql += " WHERE idx = ?";
		sql += " ORDER BY idx_comm ASC";
		
		List<NoticeComm> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, idx.getIdx());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				NoticeComm noticeComm = new NoticeComm();
				
				noticeComm.setIdx(rs.getInt("idx"));
				noticeComm.setIdxComm(rs.getInt("idx_comm"));
				noticeComm.setCommContent(rs.getString("comm_content"));
				noticeComm.setCommDate(rs.getDate("comm_date"));
				noticeComm.setUsernick(rs.getString("user_nick"));
				noticeComm.setUserno(rs.getInt("user_no"));
				
				list.add(noticeComm);
				
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
