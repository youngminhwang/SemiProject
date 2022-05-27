package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.MemberJoinDao;
import web.dao.impl.MemberJoinDaoImpl;
import web.dto.UserInfo;
import web.service.face.GetDataForMemberJoinService;
import web.service.face.MemberJoinService;

public class MemberJoinServiceImpl implements MemberJoinService {

	private GetDataForMemberJoinService getDataForMemberJoinService = new GetDataForMemberJoinServiceImpl();
	
	private MemberJoinDao memberJoinDao = new MemberJoinDaoImpl();
	
	@Override
	public void join(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		UserInfo userInfo = new UserInfo();
		
		userInfo = getDataForMemberJoinService.getUserInfo(req);
		
		System.out.println("DB에 입력될 userInfo 정보 확인 : " + userInfo);
		
		//DB에 userInfo 저장 후, 성공 또는 실패 결과_data를 반환 받는다.
		int userInfoResult = memberJoinDao.insertUserInfo(conn, userInfo);
		
		if( userInfoResult > 0 ) {	//DB삽입 성공
			
			JDBCTemplate.commit(conn);
			System.out.println(" 회원정보 DB INSERT 성공");
			
		} else {	//DB삽입 실패
			
			JDBCTemplate.rollback(conn);
			
		}
	
	}
	
	
	@Override
	public boolean duplicateUserId(String user_id) {
	
		String pattern = "^[a-zA-Z0-9]*$";
		
		boolean check = true; 
		if (Pattern.matches(pattern, user_id)) {		// e-mail 사용 가능시
			check = false;
		}
		
		if( ( (memberJoinDao.selectDuplicateUserId(JDBCTemplate.getConnection(), user_id) ) > 0) || (user_id.length() < 3) || (user_id.length() > 10) || check ) {
			return true; //중복
		} else {
			return false; //안 중복
		}
	}
	
	@Override
	public boolean duplicateUserNick(String user_nick) {
		
		if( ( memberJoinDao.selectDuplicateUserNick(JDBCTemplate.getConnection(), user_nick) > 0 ) || (user_nick.length() < 3) || (user_nick.length() > 15) ) {
			return true; //중복
		} else {
			return false; //안 중복
		}
	}
	
	@Override
	public boolean duplicateUserEmail(String user_email) {
		 
		String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		
		boolean check = true; 
		if (Pattern.matches(pattern, user_email)) {		// e-mail 사용 가능시
			check = false;
		}

		if( ( memberJoinDao.selectDuplicateUserEmail(JDBCTemplate.getConnection(), user_email) > 0 ) || check ) {
			return true; //중복
		} else {
			return false; //안 중복
		}
	}
	
}





















