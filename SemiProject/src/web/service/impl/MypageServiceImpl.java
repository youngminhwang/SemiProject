package web.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.MypageDao;
import web.dao.impl.MypageDaoImpl;
import web.dto.User;
import web.service.face.MypageService;

public class MypageServiceImpl implements MypageService{

	
	private MypageDao mypageDao = new MypageDaoImpl();
	
	
	@Override
	public User mypageview(User user) {

		Connection conn = JDBCTemplate.getConnection();
		
		//회원정보 조회
		User mypage = mypageDao.selectUser_id(conn, user);
		
		return mypage;
	}
	
	@Override
	public List<User> getMypageList(User user) {

		
		return mypageDao.selectAll(JDBCTemplate.getConnection(),user);
	}
	
	@Override
	public void mypageupdate(HttpServletRequest req) {
		
		User user = new User();
		
		user.setUser_pw(req.getParameter("user_pw") );
		user.setUser_nick(req.getParameter("user_nick") );
		user.setUser_email(req.getParameter("user_email") );
		user.setUser_phone(req.getParameter("user_phone") );

		//작성자id 처리
		user.setUser_id	( (String) req.getSession().getAttribute("user_id") );

		System.out.println("user출력 : " + user);
	
		Connection conn = JDBCTemplate.getConnection();
		if( mypageDao.updateMy(conn, user) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public void mypagedelete(User user) {

		Connection conn = JDBCTemplate.getConnection();
		
		if (mypageDao.deleteReviewFile(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("reviewFileDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("reviewFileDelete Fail!");
		}
		
		if (mypageDao.deleteReview(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("reviewDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("reviewDelete Fail");
		}
		
		if (mypageDao.deleteUserRcm(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("userRcmDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("userRcmDelete Fail");
		}
		
		if (mypageDao.deleteFreeComm(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("freeCommDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("freeCommDelete Fail");
		}
		
		if (mypageDao.deleteFreeFile(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("freeFileDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("freeFileDelete Fail");
		}
		
		if (mypageDao.deleteFree(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("freeDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("freeDelete Fail");
		}
		
		if (mypageDao.deleteNoticeComm(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("noticeCommDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("noticeCommDelete Fail");
		}
		
		if (mypageDao.deleteNoticeFile(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("noticeFileDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("noticeFileDelete Fail");
		}
		
		if (mypageDao.deleteNotice(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("noticeDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("noticeDelete Fail");
		}
		
		if (mypageDao.deleteInquireFile(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("InquireFileDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("InquireFileDelete Fail");
		}
		
		if (mypageDao.deleteInquire(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("InquireDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("InquireDelete Fail");
		}
		
		if (mypageDao.deleteUserInfo(conn, user) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("userInfoDelete Success!");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("userInfoDelete Fail");
			
		}
		
	}
	
	@Override
	public boolean duplicate(String user_nick) {
		if( mypageDao.selectDuplicateUser_nick(JDBCTemplate.getConnection(), user_nick) > 0  ) {
			return true; //중복
		} else {
			return false; //안 중복
		}
	}

	@Override
	public User getUserpw(String userid) {
	
		Connection conn = JDBCTemplate.getConnection();
		
		//회원정보 조회
		User mypage = mypageDao.getUserpwByUserid(conn, userid);
		
		return mypage;
	}
	
}
