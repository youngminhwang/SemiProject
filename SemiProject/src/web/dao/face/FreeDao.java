package web.dao.face;

import java.sql.Connection;

import java.util.List;

import util.Paging;
import web.dto.Free;
import web.dto.FreeFile;

public interface FreeDao {

	/**
	 * 게시판 정보 데이터 전체 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return 테이블 전체 조회 결과 리스트
	 */
	public List<Free> selectAllFreeBoard(Connection conn, Paging paging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @return int - free테이블의 전체 행 수
	 */
	public int selectCntAllFreeBoard(Connection conn);

	//---------------------------------------------------------------------------
	
	/**
	 * 게시글 클릭 시 해당 게시글의 조회수가 증가한다
	 * 
	 * @param conn - DB 연결 객체
	 * @param idx - 조회할 게시글의 idx를 가진 DTO 객체
	 * @return int - UPDATE쿼리 수행 결과
	 */
	public int updateHits(Connection conn, Free idx);

	/**
	 * 지정된 idx의 게시글 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param idx - 조회할 게시글의 idx를 가진 DTO 객체
	 * @return Free - 조회된 게시글의 상세정보 DTO객체
	 */
	public Free selectFreeByIdx(Connection conn, Free idx);

	/**
	 * 게시글 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param free - 삽입될 게시글 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertFree(Connection conn, Free free);

	/**
	 * 시퀀스를 이용하여 다음 게시글 번호를 조회한다
	 * 
	 * @param conn - DB 연결 객체
	 * @return int - 다음 게시글 번호
	 */
	public int selectIdx(Connection conn);

	/**
	 * 
	 * 
	 * @param conn - DB 연결 객체
	 * @param freeFile - 첨부 파일 정보
	 * @return int - INSERT 수행 결과
	 */
	public int insertFile(Connection conn, FreeFile freeFile);

	/**
	 * 첨부 파일 정보 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param viewFreeBoard - 조회할 게시글 번호
	 * @return FreeFile - 첨부 파일 정보
	 */
	public FreeFile selectFile(Connection conn, Free viewFreeBoard);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserNo(Connection conn, Free viewFreeBoard);

	/**
	 * 게시글 수정 
	 * 
	 * @param free - 수정할 내용을 담은 객체
	 */
	public int updateFree(Connection conn, Free free);

	/**
	 * 게시글에 첨부된 파일 삭제
	 * 
	 * @param free - 삭제할 게시글 번호을 담은 객체
	 */
	public int deleteFile(Connection conn, Free free);

	/**
	 * 게시글 삭제 
	 * 
	 * @param free - 삭제할 내용을 담은 객체
	 */
	public int deleteFree(Connection conn, Free free);
	
	/**
	 * 게시글에 첨부된 댓글 삭제
	 * 
	 * @param free - 삭제할 내용을 담은 객체
	 */
	public int deleteComm(Connection conn, Free free);

	/**
	 * 검색어로 검색 후 리스트 출력
	 * 
	 * @param conn - DB 연결 객체
	 * @param paging - 페이징 객체
	 * @return
	 */
	public List<Free> searchTitleFreeList(Connection conn, Paging paging);

	public List<Free> searchContentFreeList(Connection conn, Paging paging);

	public List<Free> searchUsernickFreeList(Connection conn, Paging paging);
	
	
	public int selectCntTitleSearching(Connection conn, String data);

	public int selectCntContentSearching(Connection conn, String data);

	public int selectCntUsernickSearching(Connection conn, String data);

	public List<Free> selectAllList(Connection conn);

	
	


}
