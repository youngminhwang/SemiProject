package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.FreeComm;

public interface FreeCommService {
	
	/**
	 * 글 번호를 입력받아 해당 게시글 댓글의 조회
	 * 
	 * @param idx - 게시글 번호
	 * @return 게시글 번호에 해당하는 댓글 리스트
	 */
	public List<FreeComm> viewComm(FreeComm idx);
	
	/**
	 * 게시글 번호 조회
	 * 
	 * @param req - 요청 정보 객체
	 * @return idx - 게시글 번호
	 */
	public FreeComm getIdx(HttpServletRequest req);
	
	/**
	 * 댓글 삽입
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void insertComm(HttpServletRequest req);

	
	public void updateComm(HttpServletRequest req);

	
	public void deleteComm(FreeComm freeComm);

	
	public FreeComm getIdxIdxComm(HttpServletRequest req);

}
