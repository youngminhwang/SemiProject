package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dto.CafeInfo;

public interface SearchService {

	/**
	 * 카페명으로 검색
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 카페명으로 검색한 페이징이 반영된 카페 리스트
	 */
	public List<CafeInfo> viewListSearchingN(Paging paging);
	
	/**
	 * 지역으로 검색
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 지역으로 검색한 페이징이 반영된 카페 리스트
	 */
	public List<CafeInfo> viewListSearchingL(Paging paging);
	
	/**
	 * 태그로 검색
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 태그로 검색한 페이징이 반영된 카페 리스트
	 */
	public List<CafeInfo> viewListSearchingT(Paging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 페이징 객체
	 */
	public Paging getSearchPagingN(HttpServletRequest req);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 페이징 객체
	 */
	public Paging getSearchPagingL(HttpServletRequest req);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 페이징 객체
	 */
	public Paging getSearchPagingT(HttpServletRequest req);

	/**
	 * 좋아요순으로 카페 목록 조회
	 * 
	 * @return
	 */
	public List<CafeInfo> rcmCafeSelectList();
	
	/**
	 * 신규등록순으로 카페 목록 조회
	 * 
	 * @return
	 */
	public List<CafeInfo> newCafeSelectList();

	public List<CafeInfo> newCafeAllList(Paging paging);

	public Paging getcafeListPaging(HttpServletRequest req);

	public List<CafeInfo> rcmCafeAllList(Paging paging);

}
