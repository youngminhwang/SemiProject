package web.dao.face;

import java.sql.Connection;

import web.dto.UserInfo;

public interface MemberDao {

	/**
	 * userid와 userpw가 일치하는 회원의 수를 조회한다
	 * 	-> 로그인인증에 사용한다
	 * 
	 * @param conn - DB연결 객체
	 * @param UserInfo - 조회할 회원의 정보
	 * @return int - 0:존재하지 않는 회원, 1:존재하는 회원
	 */
	public int selectCntUserInfoByUseridUserpw(Connection conn, UserInfo userInfo);

	/**
	 * userno를 이용해 회원정보 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param UserInfo - 조회할 userid를 가진 객체
	 * @return UserInfo - 조회된 회원 정보
	 */
	public UserInfo selectUserInfoByUserid(Connection conn, UserInfo userInfo);

	/**
	 * 회원정보 삽입하기
	 * 
	 * @param conn - DB연결 객체
	 * @param UserInfo - 회원가입 정보 객체
	 * @return int - INSERT 수행 결과
	 */
	public int insert(Connection conn, UserInfo userInfo);

}
















