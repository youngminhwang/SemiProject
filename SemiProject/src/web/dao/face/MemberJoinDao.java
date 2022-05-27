package web.dao.face;

import java.sql.Connection;

import web.dto.UserInfo;

public interface MemberJoinDao {

	public int insertUserInfo(Connection conn, UserInfo userInfo);

	public int selectDuplicateUserId(Connection conn, String user_id);
	
	public int selectDuplicateUserNick(Connection conn, String user_nick);

	public int selectDuplicateUserEmail(Connection conn, String user_email);
}

















