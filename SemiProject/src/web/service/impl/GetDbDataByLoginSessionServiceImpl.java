package web.service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.GetDbDataByLoginSessionDao;
import web.dao.impl.GetDbDataByLoginSessionDaoImpl;
import web.service.face.GetDbDataByLoginSessionService;

public class GetDbDataByLoginSessionServiceImpl implements GetDbDataByLoginSessionService {

	private GetDbDataByLoginSessionDao getDbDataByLoginSessionDao = new GetDbDataByLoginSessionDaoImpl();
	
	@Override
	public String getUserIdFromLoginSession(HttpServletRequest req) {
		
		return (String) req.getSession().getAttribute("user_id");
			
	}
	
	@Override
	public int getUserNoByUserId(Connection conn, HttpServletRequest req) {
		
		return getDbDataByLoginSessionDao.getUserNoByUserId(JDBCTemplate.getConnection(), (String) req.getSession().getAttribute("user_id"));
	}

	@Override
	public String getUserNickByUserId(Connection conn, HttpServletRequest req) {

		return getDbDataByLoginSessionDao.getUserNickByUserId(JDBCTemplate.getConnection(), (String) req.getSession().getAttribute("user_id"));
	
	}

}
