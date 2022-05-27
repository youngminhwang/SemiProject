package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.CheckMemberByLoginInfoDao;
import web.dao.face.GetDbDataByLoginSessionDao;
import web.dao.impl.CheckMemberByLoginInfoDaoImpl;
import web.dao.impl.GetDbDataByLoginSessionDaoImpl;
import web.dto.UserInfo;
import web.service.face.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private CheckMemberByLoginInfoDao checkMemberByLoginInfoDao = new CheckMemberByLoginInfoDaoImpl();
	
	GetDbDataByLoginSessionDao getDbDateByLoginSessionDao = new GetDbDataByLoginSessionDaoImpl();
	
	@Override
	public boolean login(HttpServletRequest req) {
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setUser_id(req.getParameter("user_id"));
		userInfo.setUser_pw(req.getParameter("user_pw"));
		
		//로그인 인증 시도
		if(checkMemberByLoginInfoDao.checkMemberByLoginInfo(JDBCTemplate.getConnection(), userInfo) > 0 ) {	// 로그인 성공 시,
			
			return true;
		
		}
		
		//로그인 인증 실패 시,
		return false;
		
		// 단순히 DB를 조회하는 것이기 때문에, commit이나 rollback이 필요없다.
		
	}

	@Override
	public UserInfo getLoginMember(HttpServletRequest req) {
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setUser_id(req.getParameter("user_id"));
		userInfo.setUser_pw(req.getParameter("user_pw"));
		
		return userInfo;
	}

	@Override
	public UserInfo info(UserInfo userInfo) {
		return getDbDateByLoginSessionDao.getUserInfoByUserId(JDBCTemplate.getConnection(), userInfo);
	}

}
