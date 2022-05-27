package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.NoticeDao;
import web.dto.Notice;
import web.dto.NoticeFile;

public class NoticeDaoImpl implements NoticeDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Notice> selectAllNoticeBoard(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, N.* FROM (";
		sql += "	SELECT";
		sql += "		idx, notice_title, user_nick";
		sql += "		, notice_hits, create_date, user_no";
		sql += "	FROM notice";
		sql += "	ORDER BY idx DESC";
		sql += " 	) N";
		sql += " ) NOTICE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Notice> noticeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartPostNo());
			ps.setInt(2, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Notice notice = new Notice();
				
				notice.setIdx(rs.getInt("idx"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setUsernick(rs.getString("user_nick"));
				notice.setNoticeHits(rs.getInt("notice_hits"));
				notice.setCreateDate(rs.getDate("create_date"));
				notice.setUserno(rs.getInt("user_no"));
				
				noticeList.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeList;
	}

	
	@Override
	public int selectCntAllNoticeBoard(Connection conn) {
	
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM notice";
		
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
	public int updateHits(Connection conn, Notice idx) {
		
		String sql = "";
		sql += "UPDATE notice";
		sql += " SET notice_hits = notice_hits + 1";
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
	public Notice selectNoticeByIdx(Connection conn, Notice idx) {
		
		String sql = "";
		sql += "SELECT";
		sql += "		idx, notice_title, user_nick";
		sql += "		, notice_content, notice_hits, create_date, user_no";
		sql += "	FROM notice";
		sql += " WHERE idx = ?";
		
		Notice notice = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, idx.getIdx());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				notice = new Notice();
				
				notice.setIdx(rs.getInt("idx"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setUsernick(rs.getString("user_nick"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeHits(rs.getInt("notice_hits"));
				notice.setCreateDate(rs.getDate("create_date"));
				notice.setUserno(rs.getInt("user_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return notice;
	}

	
	@Override
	public int insertNotice(Connection conn, Notice notice) {
		
		String sql = "";
		sql += "INSERT INTO notice(idx, notice_title, user_nick, notice_content,  user_no, notice_hits)";
		sql += " VALUES (?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, notice.getIdx());
			ps.setString(2, notice.getNoticeTitle());
			ps.setString(3, notice.getUsernick());
			ps.setString(4, notice.getNoticeContent());
			ps.setInt(5, notice.getUserno());			
			
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
		sql += "SELECT notice_seq.nextval FROM dual";
		
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
	public int insertFile(Connection conn, NoticeFile noticeFile) {
		
		String sql = "";
		sql += "INSERT INTO noticefile( file_no, idx, file_ori, file_sto, file_size )";
		sql += " VALUES (noticefile_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1, noticeFile.getIdx());
			ps.setString(2, noticeFile.getFileOri());
			ps.setString(3, noticeFile.getFileSto());
			ps.setInt(4, noticeFile.getFileSize());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public NoticeFile selectFile(Connection conn, Notice viewNoticeBoard) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	file_no";
		sql += "	, idx";
		sql += "	, file_ori";
		sql += "	, file_sto";
		sql += "	, file_size";
		sql += "	, create_date";
		sql += " FROM noticefile";
		sql += " WHERE idx = ?";
		sql += " ORDER BY file_no DESC";
		
		// 결과 저장 DTO 객체
		NoticeFile noticeFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewNoticeBoard.getIdx());
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				noticeFile = new NoticeFile();
				
				noticeFile.setFileno( rs.getInt("file_no") );
				noticeFile.setIdx( rs.getInt("idx") );
				noticeFile.setFileOri( rs.getString("file_ori") );
				noticeFile.setFileSto( rs.getString("file_sto") );
				noticeFile.setFileSize( rs.getInt("file_size") );
				noticeFile.setCreateDate( rs.getDate("create_date") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeFile;
	}


	@Override
	public String selectNickByUserNo(Connection conn, Notice viewNoticeBoard) {
		//SQL 작성
		String sql = "";
		sql += "SELECT user_nick FROM user_info";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
				
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
					
			ps.setInt(1, viewNoticeBoard.getUserno()); //조회할 user_no 적용
					
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
	public int updateNotice(Connection conn, Notice notice) {
		
		String sql = "";
		sql += "UPDATE notice";
		sql += " SET notice_title = ?";
		sql += "	, notice_content = ?";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, notice.getNoticeTitle());
			ps.setString(2, notice.getNoticeContent());
			ps.setInt(3, notice.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public int deleteFile(Connection conn, Notice notice) {
		
		String sql = "";
		sql += "DELETE noticefile";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, notice.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public int deleteNotice(Connection conn, Notice notice) {
	
		String sql = "";
		sql += "DELETE notice";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, notice.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteComm(Connection conn, Notice notice) {
	
		String sql = "";
		sql += "DELETE noticecomm";
		sql += " WHERE idx = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, notice.getIdx());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public List<Notice> searchTitleNoticeList(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, N.* FROM (";
		sql += "	SELECT";
		sql += "		idx, notice_title, user_nick";
		sql += "		, notice_hits, create_date, user_no";
		sql += "	FROM notice";
		sql += "	WHERE notice_title LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) N";
		sql += " ) NOTICE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Notice> noticeSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Notice notice = new Notice();
				
				notice.setIdx(rs.getInt("idx"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setUsernick(rs.getString("user_nick"));
				notice.setNoticeHits(rs.getInt("notice_hits"));
				notice.setCreateDate(rs.getDate("create_date"));
				notice.setUserno(rs.getInt("user_no"));
				
				noticeSearchList.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeSearchList;
	}
	
	@Override
	public List<Notice> searchContentNoticeList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, N.* FROM (";
		sql += "	SELECT";
		sql += "		idx, notice_title, user_nick";
		sql += "		, notice_hits, create_date, user_no";
		sql += "	FROM notice";
		sql += "	WHERE notice_content LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) N";
		sql += " ) NOTICE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Notice> noticeSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Notice notice = new Notice();
				
				notice.setIdx(rs.getInt("idx"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setUsernick(rs.getString("user_nick"));
				notice.setNoticeHits(rs.getInt("notice_hits"));
				notice.setCreateDate(rs.getDate("create_date"));
				notice.setUserno(rs.getInt("user_no"));
				
				noticeSearchList.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeSearchList;
	}
	
	@Override
	public List<Notice> searchUsernickNoticeList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, N.* FROM (";
		sql += "	SELECT";
		sql += "		idx, notice_title, user_nick";
		sql += "		, notice_hits, create_date, user_no";
		sql += "	FROM notice";
		sql += "	WHERE user_nick LIKE ?";	
		sql += "	ORDER BY idx DESC";
		sql += " 	) N";
		sql += " ) NOTICE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Notice> noticeSearchList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, "%" + paging.getSearch() + "%");
			ps.setInt(2, paging.getStartPostNo());
			ps.setInt(3, paging.getEndPostNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Notice notice = new Notice();
				
				notice.setIdx(rs.getInt("idx"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setUsernick(rs.getString("user_nick"));
				notice.setNoticeHits(rs.getInt("notice_hits"));
				notice.setCreateDate(rs.getDate("create_date"));
				notice.setUserno(rs.getInt("user_no"));
				
				noticeSearchList.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeSearchList;
	}


	@Override
	public int selectCntTitleSearching(Connection conn, String data) {
		
		//SQL 구문
		String sql = "";
		sql += "SELECT count(*) cnt FROM notice";
		sql += "	WHERE notice_title LIKE ?";
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
		sql += "SELECT count(*) cnt FROM notice";
		sql += "	WHERE notice_content LIKE ?";
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
		sql += "SELECT count(*) cnt FROM notice";
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
	public List<Notice> selectAllList(Connection conn) {
		
		String sql = "";
		sql += "SELECT * FROM notice";
		sql += " ORDER BY idx DESC";
		
		List<Notice> noticeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Notice notice = new Notice();
				
				notice.setIdx(rs.getInt("idx"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setUsernick(rs.getString("user_nick"));
				notice.setNoticeHits(rs.getInt("notice_hits"));
				notice.setCreateDate(rs.getDate("create_date"));
				notice.setUserno(rs.getInt("user_no"));
				
				noticeList.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeList;	
	}

}
