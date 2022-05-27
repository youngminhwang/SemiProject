package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.SearchLoginDao;
import web.dao.impl.SearchLoginDaoImpl;
import web.dto.UserInfo;
import web.service.face.FindUserPwByEmailAndIdService;

public class FindUserPwByEmailAndIdServiceImpl implements FindUserPwByEmailAndIdService{

	SearchLoginDao searchLoginDao = new SearchLoginDaoImpl();
	
	@Override
	public String findUserPwByEmailAndIdService(HttpServletRequest req) {
		
		UserInfo userInfo = new UserInfo(); 
		
		userInfo.setUser_id(req.getParameter("user_id"));
		
		userInfo.setUser_email(req.getParameter("user_email"));
		
		return searchLoginDao.findUserPwByIdAndEmail(JDBCTemplate.getConnection(), userInfo);
		
	}

}
