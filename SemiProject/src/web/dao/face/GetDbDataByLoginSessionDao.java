package web.dao.face;

import java.sql.Connection;


import web.dto.UserInfo;

public interface GetDbDataByLoginSessionDao {

	public UserInfo getUserInfoByUserId(Connection conn, UserInfo userInfo);

	public int getUserNoByUserId(Connection conn, String userId);
	
	public String getUserNickByUserId(Connection conn, String userId);

}
