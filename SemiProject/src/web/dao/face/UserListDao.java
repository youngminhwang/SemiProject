package web.dao.face;

import java.sql.Connection;

import java.util.List;

import util.Paging;
import web.dto.User;

public interface UserListDao {

	/**
	 * USER_INFO 테이블 조회
	 * @param conn - DB연결 객체
	 * @return List<User> - User_info테이블 조회 결과 목록
	 */
	public List<User> selectAll(Connection conn);
	
	
	/**
	 * Board테이블 전체 조회
	 * 	-> 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - Board테이블 전체 조회 결과 목록
	 */
	public List<User> selectAll(Connection conn, Paging paging);

	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Board테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	
	/**
	 * 회원정보 삭제
	 * @param conn - DB연결 객체
	 * @param user - 삭제할 회원 번호를 담은 객체
	 * @return
	 */
	public int deleteUser(Connection conn, User user);

	/**
	 * 지정된 user_no의 회원정보 조회하기
	 * 
	 * @param user_no - 조회할 회원의 user_no를 가진 DTO객체
	 * @return User - 조회된 회원의 상세정보 DTO객체
	 */
	public User selectUserByUser_no(Connection conn, User user_no);
	
	

	/**
	 * 회원 등급 수정
	 * @param conn - 연결객체
	 * @param user - 변경할 회원 번호 가진 객체
	 * @param userrank - 변경할 회원등급 가진 객체
	 * @return int 1 : 일반회원, 0 : 관리자
	 */
	public int updateUserRank(Connection conn, User user, User userrank);




	public List<User> searchIdList(Connection conn, Paging paging);


	public List<User> searchNickList(Connection conn, Paging paging);


	public int selectCntIdSearch(Connection conn, String data);


	public int selectCntNickSearch(Connection conn, String data);




	



	
	






}
