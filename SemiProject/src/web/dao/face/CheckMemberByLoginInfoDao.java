package web.dao.face;

import java.sql.Connection;

import web.dto.UserInfo;

public interface CheckMemberByLoginInfoDao {

	public int checkMemberByLoginInfo(Connection conn, UserInfo userInfo);

}
