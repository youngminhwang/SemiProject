package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.UserDao;
import web.dao.impl.UserDaoImpl;
import web.dto.User;
import web.service.face.UserService;

public class UserServiceImpl implements UserService {

	//Dao 객체 생성
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public User getLoginUser(HttpServletRequest req) {
		
		
		//DTO객체 생성
		User user = new User();
		
		//user에 요청 파라미터로 넣은 user_id, user_pw를 집어넣는다
		user.setUser_id(req.getParameter("user_id"));
		user.setUser_pw(req.getParameter("user_pw"));
		
		//user를 반환한다
		return user;
		
	}
 
	@Override
	public boolean login(User user) {
		
		if( userDao.selectCntUserByUseridUserpw(JDBCTemplate.getConnection(), user) > 0) {
			return true;
		} else { 
		//로그인 인증 실패
		return false;
		}
	}
	
	@Override
	public User info(User user) {
		return userDao.selectUserByUserid(JDBCTemplate.getConnection(), user);
	}



}//end
