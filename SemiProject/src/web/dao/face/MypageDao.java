package web.dao.face;

import java.sql.Connection;
import java.util.List;

import web.dto.User;

public interface MypageDao {

	
	
	public User selectUser_id(Connection conn, User user);

	
	
	public List<User> selectAll(Connection conn, User user);



	public int updateMy(Connection conn, User user);



	public int deleteUserInfo(Connection conn, User user);



	public int selectDuplicateUser_nick(Connection conn, String user_nick);



	User selectUserpwFromUserid(Connection conn, String userid);



	public User getUserpwByUserid(Connection conn, String userid);



	public int deleteReviewFile(Connection conn, User user);



	public int deleteReview(Connection conn, User user);



	public int deleteFreeComm(Connection conn, User user);



	public int deleteFreeFile(Connection conn, User user);



	public int deleteFree(Connection conn, User user);



	public int deleteNoticeComm(Connection conn, User user);



	public int deleteNoticeFile(Connection conn, User user);



	public int deleteNotice(Connection conn, User user);



	public int deleteInquireFile(Connection conn, User user);



	public int deleteInquire(Connection conn, User user);



	public int deleteUserRcm(Connection conn, User user);


	
}
