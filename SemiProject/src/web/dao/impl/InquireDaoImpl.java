package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.InquireDao;
import web.dto.Inquire;
import web.dto.InquireFile;

public class InquireDaoImpl implements InquireDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Inquire> selectAllInquireBoard(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, I.* FROM (";
		sql += "	SELECT";
		sql += "		idx, inquire_title, user_nick";
		sql += "		, inquire_hits, create_date, user_no";
		sql += "	FROM inquire";
		sql += "	ORDER BY idx DESC";
		sql += " 	) I";
		sql += " ) INQUIRE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Inquire> inquireList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Inquire inquire = new Inquire();
				
				inquire.setIdx(rs.getInt("idx"));
				inquire.setInquireTitle(rs.getString("inquire_title"));
				inquire.setUsernick(rs.getString("user_nick"));
				inquire.setInquireHits(rs.getInt("inquire_hits"));
				inquire.setCreateDate(rs.getDate("create_date"));
				inquire.setUserno(rs.getInt("user_no"));
				
				inquireList.add(inquire);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquireList;
	}

	
	@Override
	public int selectCntAllInquireBoard(Connection conn) {
	
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM inquire";
		
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
	public int insertInquire(Connection conn, Inquire inquire) {
		
		String sql = "";
		sql += "INSERT INTO inquire(idx, inquire_title, user_nick, inquire_content,  user_no, inquire_hits)";
		sql += " VALUES ( ?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, inquire.getIdx());			
			ps.setString(2, inquire.getInquireTitle());
			ps.setString(3, inquire.getUsernick());
			ps.setString(4, inquire.getInquireContent());
			ps.setInt(5, inquire.getUserno());			
			
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
		sql += "SELECT inquire_seq.nextval FROM dual";
		
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
	public int selectBoardIdx(Connection conn) {
		
		String sql = "";
		sql += "SELECT inquireboard_seq.nextval FROM dual";
		
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
	public int selectAnswerIdx(Connection conn) {
		
		String sql = "";
		sql += "SELECT inquireanswer_seq.nextval FROM dual";
		
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
	public int insertFile(Connection conn, InquireFile inquireFile) {
		
		String sql = "";
		sql += "INSERT INTO inquirefile( file_no, idx, file_ori, file_sto, file_size )";
		sql += " VALUES (inquirefile_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1, inquireFile.getIdx());
			ps.setString(2, inquireFile.getFileOri());
			ps.setString(3, inquireFile.getFileSto());
			ps.setInt(4, inquireFile.getFileSize());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int updateHits(Connection conn, Inquire idx) {
		
		String sql = "";
		sql += "UPDATE inquire";
		sql += " SET inquire_hits = inquire_hits + 1";
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
	public Inquire selectInquireByIdx(Connection conn, Inquire idx) {
		
		String sql = "";
		sql += "SELECT";
		sql += "		idx, inquire_title, user_nick";
		sql += "		, inquire_content, inquire_hits, create_date, user_no";
		sql += "	FROM inquire";
		sql += " WHERE idx = ?";
		
		Inquire inquire = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, idx.getIdx());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				inquire = new Inquire();
				
				inquire.setIdx(rs.getInt("idx"));
				inquire.setInquireTitle(rs.getString("inquire_title"));
				inquire.setUsernick(rs.getString("user_nick"));
				inquire.setInquireContent(rs.getString("inquire_content"));
				inquire.setInquireHits(rs.getInt("inquire_hits"));
				inquire.setCreateDate(rs.getDate("create_date"));
				inquire.setUserno(rs.getInt("user_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquire;
	}
	
	@Override
	public InquireFile selectFile(Connection conn, Inquire viewInquireBoard) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	file_no";
		sql += "	, idx";
		sql += "	, file_ori";
		sql += "	, file_sto";
		sql += "	, file_size";
		sql += "	, create_date";
		sql += " FROM inquirefile";
		sql += " WHERE idx = ?";
		sql += " ORDER BY file_no DESC";
		
		// 결과 저장 DTO 객체
		InquireFile inquireFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewInquireBoard.getIdx());
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				inquireFile = new InquireFile();
				
				inquireFile.setFileno( rs.getInt("file_no") );
				inquireFile.setIdx( rs.getInt("idx") );
				inquireFile.setFileOri( rs.getString("file_ori") );
				inquireFile.setFileSto( rs.getString("file_sto") );
				inquireFile.setFileSize( rs.getInt("file_size") );
				inquireFile.setCreateDate( rs.getDate("create_date") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquireFile;
	}
	
	
	@Override
	public int updateInquire(Connection conn, Inquire inquire) {
		
		String sql = "";
		sql += "UPDATE inquire";
		sql += " SET inquire_title = ?";
		sql += "	, inquire_content = ?";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, inquire.getInquireTitle());
			ps.setString(2, inquire.getInquireContent());
			ps.setInt(3, inquire.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public String selectNickByUserNo(Connection conn, Inquire viewInquireBoard) {
		//SQL 작성
		String sql = "";
		sql += "SELECT user_nick FROM user_info";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
				
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
					
			ps.setInt(1, viewInquireBoard.getUserno()); //조회할 user_no 적용
					
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
	public int deleteFile(Connection conn, Inquire inquire) {
		
		String sql = "";
		sql += "DELETE inquirefile";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, inquire.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	
	@Override
	public int deleteComm(Connection conn, Inquire inquire) {
		
		String sql = "";
		sql += "DELETE inquirecomm";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, inquire.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	

	@Override
	public int deleteInquire(Connection conn, Inquire inquire) {
	
		String sql = "";
		sql += "UPDATE inquire";
		sql += " SET inquire_title='(삭제된 게시물입니다)'";
		sql += " 	, user_nick='-'";
		sql += " 	, inquire_content='(삭제된 게시물입니다)'";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, inquire.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public List<Inquire> searchTitleInquireList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, I.* FROM (";
		sql += "	SELECT";
		sql += "		idx, inquire_title, user_nick";
		sql += "		, inquire_hits, create_date, user_no";
		sql += "	FROM inquire";
		sql += "	WHERE inquire_title LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) I";
		sql += " ) INQUIRE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Inquire> inquireSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Inquire inquire = new Inquire();
				
				inquire.setIdx(rs.getInt("idx"));
				inquire.setInquireTitle(rs.getString("inquire_title"));
				inquire.setUsernick(rs.getString("user_nick"));
				inquire.setInquireHits(rs.getInt("inquire_hits"));
				inquire.setCreateDate(rs.getDate("create_date"));
				inquire.setUserno(rs.getInt("user_no"));
				
				inquireSearchList.add(inquire);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquireSearchList;
	}
	
	@Override
	public List<Inquire> searchContentInquireList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, I.* FROM (";
		sql += "	SELECT";
		sql += "		idx, inquire_title, user_nick";
		sql += "		, inquire_hits, create_date, user_no";
		sql += "	FROM inquire";
		sql += "	WHERE inquire_content LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) I";
		sql += " ) INQUIRE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Inquire> inquireSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Inquire inquire = new Inquire();
				
				inquire.setIdx(rs.getInt("idx"));
				inquire.setInquireTitle(rs.getString("inquire_title"));
				inquire.setUsernick(rs.getString("user_nick"));
				inquire.setInquireHits(rs.getInt("inquire_hits"));
				inquire.setCreateDate(rs.getDate("create_date"));
				inquire.setUserno(rs.getInt("user_no"));
				
				inquireSearchList.add(inquire);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquireSearchList;
	}
	
	@Override
	public List<Inquire> searchUsernickInquireList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, I.* FROM (";
		sql += "	SELECT";
		sql += "		idx, inquire_title, user_nick";
		sql += "		, inquire_hits, create_date, user_no";
		sql += "	FROM inquire";
		sql += "	WHERE user_nick LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) I";
		sql += " ) INQUIRE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Inquire> inquireSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Inquire inquire = new Inquire();
				
				inquire.setIdx(rs.getInt("idx"));
				inquire.setInquireTitle(rs.getString("inquire_title"));
				inquire.setUsernick(rs.getString("user_nick"));
				inquire.setInquireHits(rs.getInt("inquire_hits"));
				inquire.setCreateDate(rs.getDate("create_date"));
				inquire.setUserno(rs.getInt("user_no"));
				
				inquireSearchList.add(inquire);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquireSearchList;
	}


	@Override
	public int selectCntTitleSearching(Connection conn, String data) {
		
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM inquire";
		sql += "	WHERE inquire_title LIKE ?";
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
		sql += "SELECT count(*) cnt FROM inquire";
		sql += "	WHERE inquire_content LIKE ?";
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
		sql += "SELECT count(*) cnt FROM inquire";
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
	public List<Inquire> selectAllList(Connection conn) {
		
		String sql = "";
		sql += "SELECT * FROM inquire";
		sql += " WHERE inquire_title NOT LIKE '%(답변)%'";
		sql += " ORDER BY idx DESC";
		
		List<Inquire> inquireList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Inquire inquire = new Inquire();
				
				inquire.setIdx(rs.getInt("idx"));
				inquire.setInquireTitle(rs.getString("inquire_title"));
				inquire.setUsernick(rs.getString("user_nick"));
				inquire.setInquireHits(rs.getInt("inquire_hits"));
				inquire.setCreateDate(rs.getDate("create_date"));
				inquire.setUserno(rs.getInt("user_no"));
				
				inquireList.add(inquire);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return inquireList;
	}


}
