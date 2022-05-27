package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.GetDbDataDao;
import web.dao.impl.GetDbDataDaoImpl;
import web.dto.UserInfo;
import web.service.face.GetDataForMemberJoinService;

public class GetDataForMemberJoinServiceImpl implements GetDataForMemberJoinService{
	
	GetDbDataDao GetDbDataDao = new GetDbDataDaoImpl();
	
	@Override
	public UserInfo getUserInfo(HttpServletRequest req) {
		
		UserInfo userInfo = new UserInfo();
		
		String checkReq = null;
		
		int checkSeq = 0;
		
		//-----------------------------------------------------------
		
		checkSeq = GetDbDataDao.getNextUserInfoSeq(JDBCTemplate.getConnection());
		
		if( checkSeq != 0 ) {
			
			userInfo.setUser_no(checkSeq);
			
		} else {
			
			System.out.println("[WARN] user_info_seq가 생성 되지 않았다.");
		
		}
		
		// 일반회원의 user_rank값은 0, 관리자회원 user_rank값은 1로 고정돼있으므로, user_rank값은 DAO단에서 DB에 자동으로 INSERT 된다.

		//-----------------------------------------------------------
		
		checkReq = req.getParameter("user_id");
		
		if( checkReq != null && !"".equals( checkReq ) ) {
			
			userInfo.setUser_id(checkReq);
			
		} else {
			
			System.out.println("[WARN] JSP의 request_내장객체에, user_id값이 null이거나 비어있다.");
		
		}
		
		//-----------------------------------------------------------
		
		checkReq = req.getParameter("user_pw");
		
		if( checkReq != null && !"".equals( checkReq ) ) {
			
			userInfo.setUser_pw(checkReq);
			
		} else {
			
			System.out.println("[WARN] JSP의 request_내장객체에, user_pw값이 null이거나 비어있다.");
		
		}
		
		//-----------------------------------------------------------
		
		checkReq = req.getParameter("user_nick");
		
		if( checkReq != null && !"".equals( checkReq ) ) {
			
			userInfo.setUser_nick(checkReq);
			
		} else {
			
			System.out.println("[WARN] JSP의 request_내장객체에, user_nick값이 null이거나 비어있다.");
		
		}
		
		//-----------------------------------------------------------
		
		// user_join_date값은, DB에 Insert될 때의 Date(= Default_Date)로, DB에서 자동으로 Insert된다.
		
		//-----------------------------------------------------------
		
		if(req.getParameter("user_gender_m") != null) { 
			checkReq = req.getParameter("user_gender_m");
		} else if( req.getParameter("user_gender_f") != null) {
			checkReq = req.getParameter("user_gender_f");
		}
		if( checkReq != null && !"".equals( checkReq ) ) {
			
			userInfo.setUser_gender(checkReq);
			
		} else {
			
			System.out.println("[WARN] JSP의 request_내장객체에, user_gender값이 null이거나 비어있다.");
		
		}
		
		//-----------------------------------------------------------
		
		checkReq = req.getParameter("user_email");
		
		if( checkReq != null && !"".equals( checkReq ) ) {
			
			userInfo.setUser_email(checkReq);
			
		} else {
			
			System.out.println("[WARN] JSP의 request_내장객체에, user_email값이 null이거나 비어있다.");
		
		}
		
		//-----------------------------------------------------------
		
		checkReq = req.getParameter("user_phone");
		
		if( checkReq != null && !"".equals( checkReq ) ) {
			
			userInfo.setUser_phone(checkReq);
			
		} else {
			
			System.out.println("[WARN] JSP의 request_내장객체에, user_phone값이 null이거나 비어있다.");
		
		}
		
		//-----------------------------------------------------------
		
		checkReq = req.getParameter("user_year");
		
		if( checkReq != null && !"".equals( checkReq ) ) {
			
			userInfo.setUser_year(checkReq);
			
		} else {
			
			System.out.println("[WARN] JSP의 request_내장객체에, user_year값이 null이거나 비어있다.");
		
		}
		
		//-----------------------------------------------------------
		
		checkReq = req.getParameter("user_month");
				
		if( checkReq != null && !"".equals( checkReq ) ) {
					
			userInfo.setUser_month(checkReq);
					
		} else {
					
			System.out.println("[WARN] JSP의 request_내장객체에, user_month값이 null이거나 비어있다.");
				
		}
		
		//-----------------------------------------------------------
				
		checkReq = req.getParameter("user_day");
				
		if( checkReq != null && !"".equals( checkReq ) ) {
					
			userInfo.setUser_day(checkReq);
					
		} else {
					
			System.out.println("[WARN] JSP의 request_내장객체에, user_day값이 null이거나 비어있다.");
				
		}
	
		return userInfo;
	}

}
