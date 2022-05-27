package web.dao.face;

import java.sql.Connection;

import web.dto.UserInfo;

public interface SearchLoginDao {
	
	public String findUserIdByEmail(Connection conn, String email);
	
	public String findUserPwByIdAndEmail(Connection conn, UserInfo userInfo);
	
}
