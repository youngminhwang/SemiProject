package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import web.dao.face.MypageDao;
import web.dto.User;

public class MypageDaoImpl implements MypageDao{

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	
	@Override
	public User selectUser_id(Connection conn, User user) {

		String sql = "";
		sql += "SELECT";
		sql += "	 user_id";
		sql += "	, user_nick";
		sql += "	, user_pw";
//		sql += "	, user_join_date";
		sql += "	, user_email";
		sql += "	, user_gender";
		sql += "	, user_phone";
		sql += "	, user_year";
		sql += "	, LPAD(user_month, 2, '0')user_month";
		sql += "	, LPAD(user_day, 2, '0')user_day";
		sql += " FROM user_info";
		sql += " WHERE user_id = ? ";
		sql += " ORDER BY user_no DESC";
		
		User mypage = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUser_id());
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				
				mypage = new User();
				
				//결과값 한 행 처리
				mypage.setUser_id(rs.getString("user_id"));
				mypage.setUser_pw(rs.getString("user_pw"));
				mypage.setUser_nick(rs.getString("user_nick"));
//				mypage.setUser_join_date(rs.getDate("user_join_date"));
				mypage.setUser_email(rs.getString("user_email"));
				mypage.setUser_gender(rs.getString("user_gender"));
				mypage.setUser_phone(rs.getString("user_phone"));
				mypage.setUser_year(rs.getInt("user_year"));
				mypage.setUser_month(rs.getInt("user_month"));
				mypage.setUser_day(rs.getInt("user_day"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기, 자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return mypage;
	}
	
	@Override
	public List<User> selectAll(Connection conn, User user) {

		String sql = "";
		sql += "SELECT";
		sql += "	 user_id";
		sql += "	, user_nick";
		sql += "	, user_join_date";
		sql += "	, user_email";
		sql += "	, user_phone";
		sql += "	, user_year";
		sql += "	, user_month";
		sql += "	, user_day";
		sql += " FROM user_info";
		sql += " WHERE user_id = ? ";
		sql += " ORDER BY user_no DESC";
		
		List<User> mypageList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + user.getUser_id() + "%");
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				
				User mypage = new User();
				
				//결과값 한 행 처리
				mypage.setUser_id(rs.getString("user_id"));
				mypage.setUser_nick(rs.getString("user_nick"));
				mypage.setUser_join_date(rs.getDate("user_join_date"));
				mypage.setUser_email(rs.getString("user_id"));
				mypage.setUser_phone(rs.getString("user_phone"));
				mypage.setUser_year(rs.getInt("user_year"));
				mypage.setUser_month(rs.getInt("user_month"));
				mypage.setUser_day(rs.getInt("user_day"));
				
				mypageList.add(mypage);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기, 자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return mypageList;
	}
	
	
	@Override
	public int updateMy(Connection conn, User user) {
		
		String sql = "";
		sql += "UPDATE user_info";
		sql += " SET ";
		sql += " user_pw = ?";
		sql += " , user_nick = ?";
		sql += " , user_email = ?";
		sql += " , user_phone = ?";
		sql += " WHERE user_id = ?";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUser_pw());
			ps.setString(2, user.getUser_nick());
			ps.setString(3, user.getUser_email());
			ps.setString(4, user.getUser_phone());
			ps.setString(5, user.getUser_id());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteUserInfo(Connection conn, User user) {

		String sql = "";
		sql += "DELETE user_info";
		sql += " WHERE user_no = ?";
		
		//왜 얘도 -1이지
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	
	@Override
	public int selectDuplicateUser_nick(Connection conn, String user_nick) {
		String sql = "";
		
		sql += "SELECT count(*) cnt FROM user_info";
		sql += " WHERE user_nick = ?";
		
		int res = 0;
		try {
			
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, user_nick);
			
			rs = ps.executeQuery();	// 성공 시 1을 반환, 실패 시 0을 반환
			
			
			System.out.println("값 확인 : " + res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public User selectUserpwFromUserid(Connection conn, String userid) {

		String sql = "";
		sql += "SELECT user_pw FROM user_info";
		sql += " WHERE user_id = ?";
		
		User user = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				user = new User();
				user.setUser_pw(rs.getString("user_pw"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return user;
	}

	@Override
	public User getUserpwByUserid(Connection conn, String userid) {
		
		String sql = "";
		sql += "SELECT";
		sql += "	user_pw";
		sql += " FROM user_info";
		sql += " WHERE user_id = ? ";
		
		User mypage = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userid);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				
				mypage = new User();
				
				//결과값 한 행 처리
				mypage.setUser_pw(rs.getString("user_pw"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기, 자원 해제
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return mypage;
		
	}

	@Override
	public int deleteReviewFile(Connection conn, User user) {

		String sql = "";
		sql += "DELETE";
	    sql += "  FROM";
	    sql += "  ( SELECT c.user_no FROM review_attachments r";
	    sql += "   INNER JOIN cafe_review c ON r.review_no = c.review_no)";
	    sql += "  WHERE user_no = ?";
		
	  //왜 얘도 -1이지
	  	int res = -1;
	  		
	  	try {
	  		ps = conn.prepareStatement(sql);
	  			
	  		ps.setInt(1, user.getUser_no());
	  			
	  		res = ps.executeUpdate();
	  			
	  	} catch (SQLException e) {
	  		e.printStackTrace();
	  	} finally {
	  		JDBCTemplate.close(ps);
	  	}
	  	return res;
	}

	@Override
	public int deleteReview(Connection conn, User user) {
		String sql = "";
		sql += "DELETE";
		sql += " FROM cafe_review";
		sql += " WHERE user_no = ?";
		
	  //왜 얘도 -1이지
	  	int res = -1;
	  		
	  	try {
	  		ps = conn.prepareStatement(sql);
	  			
	  		ps.setInt(1, user.getUser_no());
	  			
	  		res = ps.executeUpdate();
	  			
	  	} catch (SQLException e) {
	  		e.printStackTrace();
	  	} finally {
	  		JDBCTemplate.close(ps);
	  	}
	  	return res;
	}

	@Override
	public int deleteFreeComm(Connection conn, User user) {
		String sql = "";
		sql += "DELETE freecomm";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteFreeFile(Connection conn, User user) {
		String sql = "";
		sql += "DELETE";
		sql += " FROM";
		sql += " ( SELECT f.user_no FROM freefile ff";
		sql += "  INNER JOIN free f ON f.idx = ff.idx)";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteFree(Connection conn, User user) {
		String sql = "";
		sql += "DELETE free";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteNoticeComm(Connection conn, User user) {
		String sql = "";
		sql += "DELETE noticecomm";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteNoticeFile(Connection conn, User user) {
		String sql = "";
		sql += "DELETE";
		sql += " FROM ( SELECT n.user_no FROM noticefile nf";
		sql += " INNER JOIN notice n ON n.idx = nf.idx)";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteNotice(Connection conn, User user) {
		String sql = "";
		sql += "DELETE notice";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteInquireFile(Connection conn, User user) {
		String sql = "";
		sql += "DELETE";
		sql += " FROM ( SELECT i.user_no FROM inquirefile if";
		sql += " INNER JOIN inquire i ON i.idx = if.idx)";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteInquire(Connection conn, User user) {
		String sql = "";
		sql += "DELETE inquire";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteUserRcm(Connection conn, User user) {
		String sql = "";
		sql += "DELETE user_rcm";
		sql += " WHERE user_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
}




