package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dto.Inquire;
import web.dto.InquireFile;

public interface InquireService {

	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<Inquire> viewInquireBoardList(Paging paging);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 페이징 객체
	 */
	public Paging getInquireBoardPaging(HttpServletRequest req);

	
	/**
	 * 작성글을 게시글 DB에 저장
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void writeText(HttpServletRequest req);
	
	/**
	 * 작성글을 게시글 DB에 저장
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void writeAnswerText(HttpServletRequest req);
	
	/**
	 * idx값을 얻어온다
	 * 
	 * @param req
	 * @return
	 */
	public Inquire getIdx(HttpServletRequest req);

	/**
	 * 전달된 idx를 이용하여 게시글을 조회한다
	 * 
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param idx - 조회할 idx를 가지고 있는 DTO객체
	 * @return Free - 조회된 게시글 정보
	 */
	public Inquire viewText(Inquire idx);
	
	/**
	 * 첨부 파일 정보 조회
	 * 
	 * @param viewFreeBoard - 첨부파일과 연결된 게시글의 번호
	 * @return BoardFile - 첨부파일 정보 DTO객체
	 */
	public InquireFile viewFile(Inquire viewInquireBoard);
	
	
	/**
	 * 전달된 Board 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewFreeBoard - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getNick(Inquire viewInquireBoard);

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
	public void deleteText(Inquire inquire);
	
	public List<Inquire> viewListSearchingT(Paging paging);
	
	public List<Inquire> viewListSearchingC(Paging paging);
	
	public List<Inquire> viewListSearchingN(Paging paging);
	

	public Paging getSearchPagingT(HttpServletRequest req);

	public Paging getSearchPagingC(HttpServletRequest req);
	
	public Paging getSearchPagingN(HttpServletRequest req);

	public List<Inquire> inquireBoardList();

	
}
