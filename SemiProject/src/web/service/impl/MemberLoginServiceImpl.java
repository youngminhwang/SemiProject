package web.service.impl;

import common.JDBCTemplate;

import web.dao.face.MemberLoginDao;
import web.dao.impl.MemberLoginDaoImpl;

import web.service.face.MemberLoginService;

public class MemberLoginServiceImpl implements MemberLoginService {
	
	private MemberLoginDao memberLoginDao = new MemberLoginDaoImpl();
	
	@Override
	public boolean loginCheck(String id, String pw) {
		boolean result;
		if(memberLoginDao.getLoginCheck(id,pw,JDBCTemplate.getConnection())>0) {
			result =true;
		}else {
			result =false;
		}
		
		return result;
	}
}



















