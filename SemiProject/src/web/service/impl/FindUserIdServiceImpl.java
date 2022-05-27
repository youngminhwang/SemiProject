package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.SearchLoginDao;
import web.dao.impl.SearchLoginDaoImpl;
import web.service.face.FindUserIdService;

public class FindUserIdServiceImpl implements FindUserIdService{

	SearchLoginDao searchLoginDao = new SearchLoginDaoImpl();
	
	@Override
	public String findUserIdByEmail(HttpServletRequest req) {
		
		String UserEmail = req.getParameter("user_email");
	
		return searchLoginDao.findUserIdByEmail(JDBCTemplate.getConnection(), UserEmail);
		 
	}

}
