package web.dao.face;

import java.sql.Connection;
import java.util.List;

import util.Paging;
import web.dto.NoticeFile;
import web.dto.Notice;

public interface NoticeDao {
	
	public List<Notice> selectAllNoticeBoard(Connection conn, Paging paging);

	public int selectCntAllNoticeBoard(Connection conn);
	
	/**
	 * 게시글 클릭 시 해당 게시글의 조회수가 증가한다
	 * 
	 * @param conn - DB 연결 객체
	 * @param idx - 조회할 게시글의 idx를 가진 DTO 객체
	 * @return int - UPDATE쿼리 수행 결과
	 */
	public int updateHits(Connection conn, Notice idx);

	/**
	 * 지정된 idx의 게시글 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param idx - 조회할 게시글의 idx를 가진 DTO 객체
	 * @return Free - 조회된 게시글의 상세정보 DTO객체
	 */
	public Notice selectNoticeByIdx(Connection conn, Notice idx);

	/**
	 * 게시글 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param free - 삽입될 게시글 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertNotice(Connection conn, Notice notice);

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
	public int insertFile(Connection conn, NoticeFile noticeFile);

	/**
	 * 첨부 파일 정보 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param viewFreeBoard - 조회할 게시글 번호
	 * @return FreeFile - 첨부 파일 정보
	 */
	public NoticeFile selectFile(Connection conn, Notice viewNoticeBoard);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserNo(Connection conn, Notice viewNoticeBoard);

	/**
	 * 게시글 수정 
	 * 
	 * @param free - 수정할 내용을 담은 객체
	 */
	public int updateNotice(Connection conn, Notice notice);

	/**
	 * 게시글에 첨부된 파일 삭제
	 * 
	 * @param free - 삭제할 게시글 번호을 담은 객체
	 */
	public int deleteFile(Connection conn, Notice notice);

	/**
	 * 게시글 삭제 
	 * 
	 * @param free - 삭제할 내용을 담은 객체
	 */
	public int deleteNotice(Connection conn, Notice notice);
	
	/**
	 * 게시글에 첨부된 댓글 삭제
	 * 
	 * @param free - 삭제할 내용을 담은 객체
	 */
	public int deleteComm(Connection conn, Notice notice);

	/**
	 * 검색어로 검색 후 리스트 출력
	 * 
	 * @param conn - DB 연결 객체
	 * @param paging - 페이징 객체
	 * @return
	 */
	public List<Notice> searchTitleNoticeList(Connection conn, Paging paging);

	public List<Notice> searchContentNoticeList(Connection conn, Paging paging);

	public List<Notice> searchUsernickNoticeList(Connection conn, Paging paging);
	
	
	public int selectCntTitleSearching(Connection conn, String data);

	public int selectCntContentSearching(Connection conn, String data);

	public int selectCntUsernickSearching(Connection conn, String data);

	public List<Notice> selectAllList(Connection conn);

}
