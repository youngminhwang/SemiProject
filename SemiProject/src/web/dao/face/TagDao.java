package web.dao.face;

import java.sql.Connection;
import java.util.List;

import util.Paging;
import web.dto.Cafe;
import web.dto.Tag;
import web.dto.TagConn;

public interface TagDao {

	
	/**
	 * Tag 테이블 전체 조회
	 * -> 페이징 처리 추가
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Tag> - Tag 테이블 전체 조회 결과 목록
	 */
	public List<Tag> selectAll(Connection conn, Paging paging);

	
	/**
	 * 총 태그 수 조회
	 * @param conn - DB연결 객체
	 * @return int - Tag 테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 태그 삭제
	 * @param conn - 연결객체
	 * @param tag - 삭제할 태그 번호
	 * @return 
	 */
	public int deleteUser(Connection conn, Tag tag);

	
	/**
	 * 지정된 tag_no의 태그 조회하기
	 * 
	 * @param conn - 연결객체
	 * @param tag_no - 조회할 태그의 tag_no를 가진 DTO객체
	 * @return Tag - 조회된 태그의 상세정보 DTO객체
	 */
	public Tag selectTagByTag_no(Connection conn, Tag tag_no);

	/**
	 * 태그 입력
	 * @param conn - 연결객체
	 * @param tag - 삽입될 태그
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insert(Connection conn, Tag tag);

	
	/**
	 * 시퀀스를 이용하여 다음 태그 번호 조회
	 * @param conn - 연결객체
	 * @return int - 다음 태그 번호
	 */
	public int selectTag_no(Connection conn);

	/**
	 * 태그 테이블 전체 조회
	 * @param conn - 연결객체
	 * @return List<Tag> - Tag 테이블 전체 조회 결과 목록
	 */
	public List<Tag> selectAll(Connection conn);

	/**
	 * 
	 * @param conn
	 * @param tagConn
	 * @return
	 */
	public int insertTagConn(Connection conn, TagConn tagConn);

	/**
	 * 
	 * @param conn
	 * @param tagConn
	 * @return
	 */
	public int updateTagConn(Connection conn, TagConn tagConn);


	public List<Tag> searchTagList(Connection conn, Paging paging);


	public int selectCntTagSearch(Connection conn, String data);

	
	public int deleteTag(Cafe cafe,Connection conn);


}
