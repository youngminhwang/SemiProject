package web.dao.face;

import java.sql.Connection;
import java.util.List;

import util.Paging;
import web.dto.CafeInfo;

public interface SearchDao {
	
	public List<CafeInfo> searchCafeNameList(Connection conn, Paging paging);

	public List<CafeInfo> searchCafeLocList(Connection conn, Paging paging);

	public List<CafeInfo> searchTagConnList(Connection conn, Paging paging);
	
	
	public int selectCntSearchCafeName(Connection conn, String data);

	public int selectCntSearchCafeLoc(Connection conn, String data);

	public int selectCntSearchTagConn(Connection conn, String data);

	public List<CafeInfo> rcmCafeSelectList(Connection conn);

	public List<CafeInfo> newCafeSelectList(Connection conn);

	public int selectCntAllCafeInfo(Connection conn);

	public List<CafeInfo> newCafeAllListPaging(Connection conn, Paging paging);

	public List<CafeInfo> rcmCafeAllListPaging(Connection conn, Paging paging);


}
