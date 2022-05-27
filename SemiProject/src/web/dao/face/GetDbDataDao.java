package web.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface GetDbDataDao {
	
	public List<Map<String, Object>> getUserInfoListFromDb(Connection conn);
	
	public int getNextUserInfoSeq(Connection conn);
	
	public int getNextUserBusiSeq (Connection conn);
	
}
