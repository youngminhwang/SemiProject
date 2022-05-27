package web.dao.face;

import java.sql.Connection;
import java.util.List;

import web.dto.FreeComm;

public interface FreeCommDao {

	/**
	 * 게시물 번호에 해당하는 댓글 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param idx - 게시글 번호
	 * @return List - 게시글 번호에 해당하는 댓글 리스트
	 */
	public List<FreeComm> selectFreeCommByIdx(Connection conn, FreeComm idx);
	
	/**
	 * 게시글에 댓글 추가하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param freeComm - 전달할 게시글 
	 * @return 댓글 삽입
	 */
	public int insert(Connection conn, FreeComm freeComm);

	
	public int delete(Connection conn, FreeComm freeComm);
	
	
	public int update(Connection conn, FreeComm freeComm);
	
	
}
