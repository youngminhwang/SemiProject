package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.FreeDao;
import web.dto.Free;
import web.dto.FreeFile;

public class FreeDaoImpl implements FreeDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Free> selectAllFreeBoard(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, F.* FROM (";
		sql += "	SELECT";
		sql += "		idx, free_title, user_nick";
		sql += "		, free_hits, create_date, user_no";
		sql += "	FROM free";
		sql += "	ORDER BY idx DESC";
		sql += " 	) F";
		sql += " ) FREE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Free> freeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Free free = new Free();
				
				free.setIdx(rs.getInt("idx"));
				free.setFreeTitle(rs.getString("free_title"));
				free.setUsernick(rs.getString("user_nick"));
				free.setFreeHits(rs.getInt("free_hits"));
				free.setCreateDate(rs.getDate("create_date"));
				free.setUserno(rs.getInt("user_no"));
				
				freeList.add(free);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return freeList;
	}

	
	@Override
	public int selectCntAllFreeBoard(Connection conn) {
	
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM free";
		
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
	public int updateHits(Connection conn, Free idx) {
		
		String sql = "";
		sql += "UPDATE free";
		sql += " SET free_hits = free_hits + 1";
		sql += " WHERE idx = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, idx.getIdx());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public Free selectFreeByIdx(Connection conn, Free idx) {
		
		String sql = "";
		sql += "	SELECT";
		sql += "		idx, free_title, user_nick";
		sql += "		, free_content, free_hits, create_date, user_no";
		sql += "	FROM free";
		sql += " WHERE idx = ?";
		
		Free free = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, idx.getIdx());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				free = new Free();
				
				free.setIdx(rs.getInt("idx"));
				free.setFreeTitle(rs.getString("free_title"));
				free.setUsernick(rs.getString("user_nick"));
				free.setFreeContent(rs.getString("free_content"));
				free.setFreeHits(rs.getInt("free_hits"));
				free.setCreateDate(rs.getDate("create_date"));
				free.setUserno(rs.getInt("user_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return free;
	}

	
	@Override
	public int insertFree(Connection conn, Free free) {
		
		String sql = "";
		sql += "INSERT INTO free(idx, free_title, user_nick, free_content,  user_no, free_hits)";
		sql += " VALUES (?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, free.getIdx());
			ps.setString(2, free.getFreeTitle());
			ps.setString(3, free.getUsernick());
			ps.setString(4, free.getFreeContent());
			ps.setInt(5, free.getUserno());			
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	
	@Override
	public int selectIdx(Connection conn) {
		
		String sql = "";
		sql += "SELECT free_seq.nextval FROM dual";
		
		//다음 시퀀스 번호
		int nextIdx = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				nextIdx = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextIdx;
	}


	@Override
	public int insertFile(Connection conn, FreeFile freeFile) {
		
		String sql = "";
		sql += "INSERT INTO freefile( file_no, idx, file_ori, file_sto, file_size )";
		sql += " VALUES (freefile_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1, freeFile.getIdx());
			ps.setString(2, freeFile.getFileOri());
			ps.setString(3, freeFile.getFileSto());
			ps.setInt(4, freeFile.getFileSize());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public FreeFile selectFile(Connection conn, Free viewFreeBoard) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	file_no";
		sql += "	, idx";
		sql += "	, file_ori";
		sql += "	, file_sto";
		sql += "	, file_size";
		sql += "	, create_date";
		sql += " FROM freefile";
		sql += " WHERE idx = ?";
		sql += " ORDER BY file_no DESC";
		
		// 결과 저장 DTO 객체
		FreeFile freeFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewFreeBoard.getIdx());
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				freeFile = new FreeFile();
				
				freeFile.setFileno( rs.getInt("file_no") );
				freeFile.setIdx( rs.getInt("idx") );
				freeFile.setFileOri( rs.getString("file_ori") );
				freeFile.setFileSto( rs.getString("file_sto") );
				freeFile.setFileSize( rs.getInt("file_size") );
				freeFile.setCreateDate( rs.getDate("create_date") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return freeFile;
	}


	@Override
	public String selectNickByUserNo(Connection conn, Free viewFreeBoard) {
		//SQL 작성
		String sql = "";
		sql += "SELECT user_nick FROM user_info";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
				
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
					
			ps.setInt(1, viewFreeBoard.getUserno()); //조회할 user_no 적용
					
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("user_nick");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		//최종 결과 반환
		return usernick;
	}


	@Override
	public int updateFree(Connection conn, Free free) {
		
		String sql = "";
		sql += "UPDATE free";
		sql += " SET free_title = ?";
		sql += "	, free_content = ?";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, free.getFreeTitle());
			ps.setString(2, free.getFreeContent());
			ps.setInt(3, free.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public int deleteFile(Connection conn, Free free) {
		
		String sql = "";
		sql += "DELETE freefile";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, free.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public int deleteFree(Connection conn, Free free) {
	
		String sql = "";
		sql += "DELETE FROM free";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, free.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteComm(Connection conn, Free free) {
	
		String sql = "";
		sql += "DELETE freecomm";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, free.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public List<Free> searchTitleFreeList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, F.* FROM (";
		sql += "	SELECT";
		sql += "		idx, free_title, user_nick";
		sql += "		, free_hits, create_date, user_no";
		sql += "	FROM free";
		sql += "	WHERE free_title LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) F";
		sql += " ) FREE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Free> freeSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Free free = new Free();
				
				free.setIdx(rs.getInt("idx"));
				free.setFreeTitle(rs.getString("free_title"));
				free.setUsernick(rs.getString("user_nick"));
				free.setFreeHits(rs.getInt("free_hits"));
				free.setCreateDate(rs.getDate("create_date"));
				free.setUserno(rs.getInt("user_no"));
				
				freeSearchList.add(free);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return freeSearchList;
	}
	
	@Override
	public List<Free> searchContentFreeList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, F.* FROM (";
		sql += "	SELECT";
		sql += "		idx, free_title, user_nick";
		sql += "		, free_hits, create_date, user_no";
		sql += "	FROM free";
		sql += "	WHERE free_content LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) F";
		sql += " ) FREE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Free> freeSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Free free = new Free();
				
				free.setIdx(rs.getInt("idx"));
				free.setFreeTitle(rs.getString("free_title"));
				free.setUsernick(rs.getString("user_nick"));
				free.setFreeHits(rs.getInt("free_hits"));
				free.setCreateDate(rs.getDate("create_date"));
				free.setUserno(rs.getInt("user_no"));
				
				freeSearchList.add(free);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return freeSearchList;
	}
	
	@Override
	public List<Free> searchUsernickFreeList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, F.* FROM (";
		sql += "	SELECT";
		sql += "		idx, free_title, user_nick";
		sql += "		, free_hits, create_date, user_no";
		sql += "	FROM free";
		sql += "	WHERE user_nick LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) F";
		sql += " ) FREE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Free> freeSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Free free = new Free();
				
				free.setIdx(rs.getInt("idx"));
				free.setFreeTitle(rs.getString("free_title"));
				free.setUsernick(rs.getString("user_nick"));
				free.setFreeHits(rs.getInt("free_hits"));
				free.setCreateDate(rs.getDate("create_date"));
				free.setUserno(rs.getInt("user_no"));
				
				freeSearchList.add(free);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return freeSearchList;
	}


	@Override
	public int selectCntTitleSearching(Connection conn, String data) {
		
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM free";
		sql += "	WHERE free_title LIKE ?";
		sql += "	ORDER BY idx DESC";
					
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
	public int selectCntContentSearching(Connection conn, String data) {
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM free";
		sql += "	WHERE free_content LIKE ?";
		sql += "	ORDER BY idx DESC";
				
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
	public int selectCntUsernickSearching(Connection conn, String data) {
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM free";
		sql += "	WHERE user_nick LIKE ?";
		sql += "	ORDER BY idx DESC";
				
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
	public List<Free> selectAllList(Connection conn) {
		
		String sql = "";
		sql += "SELECT * FROM free";
		sql += " ORDER BY free_hits DESC";
		
		List<Free> freeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Free free = new Free();
				
				free.setIdx(rs.getInt("idx"));
				free.setFreeTitle(rs.getString("free_title"));
				free.setUsernick(rs.getString("user_nick"));
				free.setFreeHits(rs.getInt("free_hits"));
				free.setCreateDate(rs.getDate("create_date"));
				free.setUserno(rs.getInt("user_no"));
				
				freeList.add(free);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return freeList;

	}
	
}

