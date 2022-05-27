package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dto.Free;
import web.dto.FreeFile;

public interface FreeService {

	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<Free> viewFreeBoardList(Paging paging);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 페이징 객체
	 */
	public Paging getFreeBoardPaging(HttpServletRequest req);
	
	/**
	 * idx값을 얻어온다
	 * 
	 * @param req
	 * @return
	 */
	public Free getIdx(HttpServletRequest req);

	/**
	 * 전달된 idx를 이용하여 게시글을 조회한다
	 * 
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param idx - 조회할 idx를 가지고 있는 DTO객체
	 * @return Free - 조회된 게시글 정보
	 */
	public Free viewText(Free idx);

	/**
	 * 작성글을 게시글 DB에 저장
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void writeText(HttpServletRequest req);

	/**
	 * 첨부 파일 정보 조회
	 * 
	 * @param viewFreeBoard - 첨부파일과 연결된 게시글의 번호
	 * @return BoardFile - 첨부파일 정보 DTO객체
	 */
	public FreeFile viewFile(Free viewFreeBoard);

	/**
	 * 전달된 Board 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewFreeBoard - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getNick(Free viewFreeBoard);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void updateText(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void deleteText(Free free);

	/**
	 * title로 검색한 게시글 목록 보여주기 
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 반영된 게시글 검색 결과 목록
	 */
	
	public List<Free> viewListSearchingT(Paging paging);
	
	public List<Free> viewListSearchingC(Paging paging);
	
	public List<Free> viewListSearchingN(Paging paging);
	

	public Paging getSearchPagingT(HttpServletRequest req);

	public Paging getSearchPagingC(HttpServletRequest req);
	
	public Paging getSearchPagingN(HttpServletRequest req);

	public List<Free> freeBoardList();

}
