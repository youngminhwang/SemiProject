package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dto.Cafe;
import web.dto.CafeFile;
import web.dto.CafeTag;

public interface CafeService {

	/**
	 * 카페 전체 조회
	 * @return List<Cafe> - 카페 전체 조회 결과 목록
	 */
	public List<Cafe> getList();

	/**
	 * 카페 페이징 목록 조회
	 * @param paging - 페이징 정보 객체
	 * @return List<Cafe> - 페이징이 반영된 카페 조회 결과 목록
	 */
	public List<Cafe> getList(Paging paging);

	/**
	 * 페이징 객체 생성
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);

	
	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Board - 전달파라미터 cafe_no값을 포함한 DTO객체
	 */
	public Cafe getCafe_no(HttpServletRequest req);

	
	/**
	 * 전달된 cafe_no를 이용하여 게시글을 조회한다
	 * @param cafe_no - 조회할 cafe_no를 가지고 있는 DTO객체
	 * @return Cafe - 조회된 게시글 정보
	 */
	public Cafe view(Cafe cafe_no);

	/**
	 * 카페 정보 작성
	 * @param req - 요청정보 객체 (게시글 내용 + 첨부파일)
	 */
	public void cafewrite(HttpServletRequest req);

	/**
	 * 카페 삭제
	 * @param cafe - 삭제할 카페번호를 가진 객체
	 */
	public void delete(Cafe cafe);

	/**
	 * 첨부파일 정보조회
	 * @param viewCafe - 첨부파일과 연결된 게시글 번호
	 * @return CafeFile - 첨부파일 정보 DTO객체
	 */
	public CafeFile viewcafeFile(Cafe viewCafe);
	
	/**
	 * 전달된 cafe_no를 이용하여 태그 번호조회
	 * @param cafe_no - 조회할 cafe_no를 가지고 있는 객체
	 * @return CafeTag - 조회된 카페 태그 번호를 포함한 DTO객체
	 */
	public CafeTag tagno(Cafe cafe_no);

	/**
	 * 카페 정보 수정
	 * @param req - 요청 정보 객체
	 */
	public Cafe update(HttpServletRequest req);


	public List<Cafe> viewListSearchN(Paging paging);


	public Paging getSearchPagingN(HttpServletRequest req);


	
}
