package web.dao.face;

import java.sql.Connection;
import java.util.List;

import util.Paging;
import web.dto.Cafe;
import web.dto.CafeFile;
import web.dto.CafeTag;

public interface CafeDao {

	/**
	 * Cafe 테이블 전체 조회
	 * @param conn - DB연결 객체
	 * @return List<Cafe> - Cafe테이블 전체 조회 결과 목록
	 */
	public List<Cafe> selectAll(Connection conn);

	/**
	 * Cafe 테이블 전체 조회 -> 페이징 처리 추가
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Cafe> - Cafe테이블 전체 조회 결과 목록
	 */
	public List<Cafe> selectAll(Connection conn, Paging paging);

	
	/**
	 * 총 등록된 카페 정보글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Cafe테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	
	/**
	 * 지정된 cafe_no의 게시글 조회하기
	 * @param conn - DB연결 객체
	 * @param cafe_no - 조회할 카페의 cafe_no를 가진  DTO객체
	 * @return Cafe - 조회된 카페의 상세정보 DTO객체
	 */
	public Cafe selectCafeByCafe_no(Connection conn, Cafe cafe_no);

	
	/**
	 * 시퀀스를 이용하여 다음 카페 번호를 조회한다
	 * @param conn - DB연결 객체
	 * @return int - 다음 카페 번호
	 */
	public int selectCafe_no(Connection conn);

	
	/**
	 * 카페 정보 입력
	 * @param conn - DB연결 객체
	 * @param cafe - 삽입될 카페 정보 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insert(Connection conn, Cafe cafe);

	
	/**
	 * 첨부파일 삽입
	 * @param conn - DB연결 객체
	 * @param cafeFile - 첨부파일 정보
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertFile(Connection conn, CafeFile cafeFile);

	/**
	 * 카페 첨부 파일 삭제
	 * @param conn - 연결객체
	 * @param cafe - 삭제 카페 번호 담긴 객체
	 * @return
	 */
	public int deleteCafeFile(Connection conn, Cafe cafe);

	
	/**
	 * 카페 삭제
	 * @param conn - 연결객체
	 * @param cafe - 삭제 카페 번호 담긴 객체
	 * @return
	 */
	public int deleteCafe(Connection conn, Cafe cafe);

	/**
	 * 첨부파일 정보 조회
	 * @param conn - 연결 객체
	 * @param viewCafe - 조회할 카페 번호
	 * @return CafeFile - 첨부파일 정보
	 */
	public CafeFile selectCafeFile(Connection conn, Cafe viewCafe);

	
	/**
	 * 지정된 cafe_no의 Tag_no조회하기
	 * @param conn - 연결객체
	 * @param cafe_no - 조회할 카페의 cafe_no를 가진 DTO
	 * @return CafeTag - 조회된 태그 번호 DTO
	 */
	public CafeTag selectTag_noByCafe_no(Connection conn, Cafe cafe);

	
	public int update(Connection conn, Cafe cafe);

	public List<Cafe> searchNameList(Connection conn, Paging paging);

	public int selectCntNameSearch(Connection conn, String data);

	
	public int updateFile(Connection conn, CafeFile cafeFile);
	
}
