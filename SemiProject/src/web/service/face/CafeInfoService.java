package web.service.face;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dto.CafeReviewFile;
import web.dto.CafeTag;
import web.dto.CafeFile;
import web.dto.CafeInfo;
import web.dto.CafeReview;
import web.dto.UserInfo;

public interface CafeInfoService {

	public CafeReview write(HttpServletRequest req);
	
	public CafeInfo view(CafeInfo cafeno);

	public CafeInfo getCafeno(HttpServletRequest req);

	public Paging getPaging(HttpServletRequest req,CafeInfo cafeno);

	public List<CafeInfo> getList(Paging paging);

	public List<CafeReview> getreviewlist(Paging paging);

	
	/**
	 * 리뷰 번호 받아오기
	 * 	 * @param cafeno 
	 * @return
	 */
	public CafeReview getReviewno(HttpServletRequest req);
	/**
	 * 리뷰 작성
	 * @param cafeno 
	 * @return
	 */
	public void reviewjoin(CafeReview cafereview,CafeInfo cafeno);

	public List<CafeReviewFile> getFileno(HttpServletRequest req, CafeReviewFile cafeFile);

	public void reviewdelete(HttpServletRequest req);

	
	
	public CafeReview getrCafeno(HttpServletRequest req);

	
	/**
	 * 카페 사진 받아오기
	 * @param cafeno 카페 번호
	 * @return 카페 사진 정보 보내기
	 */
	public List<CafeFile> getcafeFile(CafeInfo cafeno);
	/**
	 * 카페 태그 받아오기
	 * @param cafeno 카페 번호
	 * @return 카페 태그 보내기
	 */
	public List<CafeTag> getTagList(CafeInfo cafeno);
	/**
	 * 카페 리뷰 수정
	 * @param req 카페 정보 
	 * @return
	 */
	public CafeReview reviewUpdate(HttpServletRequest req);
	
	/**
	 * 리뷰 작성 내용 받아오기
	 * @param req
	 * @return
	 */
	public CafeReview getReviewInfo(HttpServletRequest req);

	
	public UserInfo getUserInfoNo(Object object);

	public boolean getRcm(HttpServletRequest req, CafeInfo cafeinfo, UserInfo userno);
	/**
	 * 좋아요 눌러서 데이터 삭제
	 * @param req
	 * @param cafeinfo
	 * @param userno 
	 * @return
	 */
	public void deleteRcm( CafeInfo cafeinfo, UserInfo userno);
	/**
	 * 좋아요 눌러서 데이터 저장
	 * @param cafeinfo
	 * @param userno 
	 * @return
	 */
	public void insertRcm( CafeInfo cafeinfo, UserInfo userno);
	
	/**
	 * 좋아요 총 수 업데이트
	 * @param req
	 * @param cafeinfo
	 */
	public void updateRcm( CafeInfo cafeinfo);
	
	/**
	 * 아이디가 좋아요를 눌렀나 확인
	 * @param userno
	 * @return
	 */
	public int getUserRcm(int userno,CafeInfo cafeno);

	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 카페 리뷰 리스트 불러오기
	 * @return
	 */
	public List<CafeReview> getreviewlist();
	/**
	 * 카페 별점 평균 구해오기
	 * @param cafereviewList
	 * @param cafeno
	 * @return
	 */
	public float getstarAvg(List<CafeReview> cafereviewList, CafeInfo cafeno);


	
	
	

}
