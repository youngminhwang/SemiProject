package web.dao.face;

import java.sql.Connection;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dto.CafeReviewFile;
import web.dto.CafeTag;
import web.dto.CafeFile;
import web.dto.CafeInfo;
import web.dto.CafeReview;
import web.dto.UserInfo;

public interface CafeInfoDao {
	public CafeInfo cafeview(Connection conn, CafeInfo cafe);

	public int selectCntAll(Connection connection, CafeInfo cafeno);

	public List<CafeInfo> selectAll(Connection connection, Paging paging);

	public List<CafeReview> selectreview(Connection connection, Paging paging);

	
	/**
	 * 리뷰 등록 해주는 DAO
	 * 
	 * @param cafeno
	 * @return
	 */
	public int reviewInsert(Connection conn,CafeInfo cafeno,CafeReview review,UserInfo user);

	public int reviewfileno(Connection conn);

	public int insertFile(Connection conn, CafeReviewFile cafeFile);

	public int reviewno(Connection conn);

	public int reviewInsert(Connection conn, CafeReview review, UserInfo user);

	public List<CafeReviewFile> selectfile(Connection conn, CafeReviewFile cafeFile);

	public int reviewdelete(Connection conn, CafeReview cafereview);

	public int reviewfiledelete(Connection conn, CafeReview review);

	public CafeReview getrCafeno(Connection connection, HttpServletRequest req);

	public List<CafeFile> selectCafeFile(Connection conn, CafeInfo cafeno);

	public List<CafeTag> selectCafeTag(Connection connection, CafeInfo cafeno);
	
	public CafeReview getReviewInfo(Connection connection, CafeReview cafereview);

	public int reviewUpdate(Connection conn, CafeReview review);

	int updateFile(Connection conn, CafeReviewFile cafeFile);
	
	
	public UserInfo getuserInfo(UserInfo user, Connection conn);

	public int getRcm(UserInfo user, CafeInfo cafeinfo, Connection conn);

	public int deleteRcm(UserInfo user, CafeInfo cafeinfo, Connection connection);

	public int insertRcm(UserInfo user, CafeInfo cafeinfo, Connection conn);

	public int updateRcm(CafeInfo cafeinfo,Connection conn);

	public int getuserRcm(int userno, CafeInfo cafeno,Connection conn);

	
	public int selectCntAll(Connection connection);

	
	/**
	 * 카페 리뷰 정보 다 불러오기(페이징 x)
	 * @param connection
	 * @return
	 */
	public List<CafeReview> selectreview(Connection connection);
	/**
	 * 별점 평균 구하기
	 * @param connection DB연결
	 * @param cafereviewList 카페 별점 가져오는
	 * @param cafeno 카페 넘버 가져오는
	 * @return
	 */
	public float getStarAvg(Connection conn, CafeInfo cafeno);


	

}
