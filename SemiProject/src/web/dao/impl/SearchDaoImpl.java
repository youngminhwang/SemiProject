package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.SearchDao;
import web.dto.CafeInfo;

public class SearchDaoImpl implements SearchDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<CafeInfo> searchCafeNameList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, C.* FROM (";
		sql += "	SELECT";
		sql += "		ci.cafe_no, cafe_name, cafe_tel";
		sql += "		, cafe_time, cafe_park, cafe_loc";
		sql += "		, cafe_rcm, cp.cafe_cpy_file_name";
		sql += "	FROM cafe_info ci, cafe_photo cp";
		sql += "	WHERE ci.cafe_no = cp.cafe_no";	
		sql += "	AND cafe_name LIKE ?";	
		sql += "	ORDER BY cafe_rcm DESC";
		sql += " 	) C";
		sql += " ) CAFEINFO";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<CafeInfo> searchCafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo();
				
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				
				searchCafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		System.out.println(paging.getSearch());
		System.out.println(searchCafeList.size());
		
		return searchCafeList;
	}
	
	@Override
	public List<CafeInfo> searchCafeLocList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, C.* FROM (";
		sql += "	SELECT";
		sql += "		ci.cafe_no, cafe_name, cafe_tel";
		sql += "		, cafe_time, cafe_park, cafe_loc";
		sql += "		, cafe_rcm, cp.cafe_cpy_file_name";
		sql += "	FROM cafe_info ci, cafe_photo cp";
		sql += "	WHERE ci.cafe_no = cp.cafe_no";	
		sql += "	AND cafe_loc LIKE ?";	
		sql += "	ORDER BY cafe_rcm DESC";
		sql += " 	) C";
		sql += " ) CAFEINFO";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<CafeInfo> searchCafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo();
				
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				
				searchCafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return searchCafeList;
		
	}
	
	@Override
	public List<CafeInfo> searchTagConnList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, C.* FROM (";
		sql += "		SELECT";
		sql += "			ci.cafe_no, cafe_name, cafe_tel";
		sql += "			,cafe_time, cafe_park, cafe_loc, cafe_rcm";
		sql += "			, t.tag_no, tag_name, cp.cafe_cpy_file_name";
		sql += "		FROM cafe_info ci, tag_conn tc, tag t, cafe_photo cp";
		sql += "		WHERE ci.cafe_no = tc.cafe_no";
		sql += "		AND tc.tag_no = t.tag_no";
		sql += "		AND ci.cafe_no = cp.cafe_no";
		sql += "		AND t.tag_name LIKE ?";
		sql += "		ORDER BY cafe_rcm";
		sql += "	)C";
		sql += " )CAFEINFO";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<CafeInfo> searchCafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo();
				
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				
				searchCafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return searchCafeList;
	}


	@Override
	public int selectCntSearchCafeName(Connection conn, String data) {
		
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM cafe_info";
		sql += "	WHERE cafe_name LIKE ?";
					
		//총 게시글 수
		int count = 0;
					
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + data + "%");
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
			
		return count;

	}


	@Override
	public int selectCntSearchCafeLoc(Connection conn, String data) {
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM cafe_info";
		sql += "	WHERE cafe_loc LIKE ?";
				
		//총 게시글 수
		int count = 0;
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + data + "%");
			
			rs = ps.executeQuery();
					
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return count;
	}


	@Override
	public int selectCntSearchTagConn(Connection conn, String data) {
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM (";
		sql += "	SELECT * FROM tag_conn tc, tag t";
		sql += "	WHERE tc.tag_no=t.tag_no";
		sql += "	AND t.tag_name LIKE ?";
		sql += " )";
		
		//총 게시글 수
		int count = 0;
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + data + "%");

			rs = ps.executeQuery();
					
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return count;
	}

	@Override
	public List<CafeInfo> rcmCafeSelectList(Connection conn) {
		
		String sql = "";
		sql += "SELECT * FROM(";
		sql += "	SELECT rownum rnum, C.* FROM (";
		sql += "		SELECT";
		sql += "		ci.cafe_no, cafe_name, cafe_tel";
		sql += "		, cafe_time, cafe_park, cafe_loc";
		sql += "		, cafe_rcm, cp.cafe_cpy_file_name";
		sql += "		FROM cafe_info ci, cafe_photo cp";
		sql += "		WHERE ci.cafe_no = cp.cafe_no";
		sql += "		ORDER BY cafe_rcm DESC";
		sql += "	) C";
		sql += " ) CAFEINFO";
		sql += " WHERE rnum BETWEEN 1 AND 9";
		
		List<CafeInfo> allCafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo();
				
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				
				allCafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return allCafeList;
	}

	@Override
	public List<CafeInfo> newCafeSelectList(Connection conn) {

		String sql = "";
		sql += "SELECT * FROM(";
		sql += "	SELECT rownum rnum, C.* FROM (";
		sql += "		SELECT";
		sql += "		ci.cafe_no, cafe_name, cafe_tel";
		sql += "		, cafe_time, cafe_park, cafe_loc";
		sql += "		, cafe_rcm, cp.cafe_cpy_file_name";
		sql += "		FROM cafe_info ci, cafe_photo cp";
		sql += "		WHERE ci.cafe_no = cp.cafe_no";
		sql += "		ORDER BY cafe_no DESC";
		sql += "	) C";
		sql += " ) CAFEINFO";
		sql += " WHERE rnum BETWEEN 1 AND 9";
		
		List<CafeInfo> newCafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo();
				
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				
				newCafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return newCafeList;
	}

	@Override
	public int selectCntAllCafeInfo(Connection conn) {
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM cafe_info";
				
		//총 게시글 수
		int count = 0;
				
		try {
			ps = conn.prepareStatement(sql);
					
			rs = ps.executeQuery();
					
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
					
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return count;

	}

	@Override
	public List<CafeInfo> newCafeAllListPaging(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, C.* FROM (";
		sql += "	SELECT";
		sql += "		ci.cafe_no, cafe_name, cafe_tel";
		sql += "		, cafe_time, cafe_park, cafe_loc";
		sql += "		, cafe_rcm, cp.cafe_cpy_file_name";
		sql += "	FROM cafe_info ci, cafe_photo cp";
		sql += "	WHERE ci.cafe_no = cp.cafe_no";	
		sql += "	ORDER BY cafe_no DESC";
		sql += " 	) C";
		sql += " ) CAFEINFO";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<CafeInfo> searchCafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo();
				
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				
				searchCafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		System.out.println(paging.getSearch());
		System.out.println(searchCafeList.size());
		
		return searchCafeList;
	}

	@Override
	public List<CafeInfo> rcmCafeAllListPaging(Connection conn, Paging paging) {
	
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, C.* FROM (";
		sql += "	SELECT";
		sql += "		ci.cafe_no, cafe_name, cafe_tel";
		sql += "		, cafe_time, cafe_park, cafe_loc";
		sql += "		, cafe_rcm, cp.cafe_cpy_file_name";
		sql += "	FROM cafe_info ci, cafe_photo cp";
		sql += "	WHERE ci.cafe_no = cp.cafe_no";	
		sql += "	ORDER BY cafe_rcm DESC";
		sql += " 	) C";
		sql += " ) CAFEINFO";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<CafeInfo> searchCafeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				CafeInfo cafeInfo = new CafeInfo();
				
				cafeInfo.setCafe_no(rs.getInt("cafe_no"));
				cafeInfo.setCafe_name(rs.getString("cafe_name"));
				cafeInfo.setCafe_tel(rs.getString("cafe_tel"));
				cafeInfo.setCafe_time(rs.getString("cafe_time"));
				cafeInfo.setCafe_park(rs.getString("cafe_park"));
				cafeInfo.setCafe_loc(rs.getString("cafe_loc"));
				cafeInfo.setCafe_rcm(rs.getInt("cafe_rcm"));
				cafeInfo.setCafe_cpy_file_name(rs.getString("cafe_cpy_file_name"));
				
				searchCafeList.add(cafeInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		System.out.println(paging.getSearch());
		System.out.println(searchCafeList.size());
		
		return searchCafeList;
	}

	
}
