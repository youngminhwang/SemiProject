package web.dao.face;

import java.sql.Connection;

public interface MemberLoginDao {

	public int getLoginCheck(String id, String pw,Connection conn);

}
