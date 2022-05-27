package web.dao.face;

import java.sql.Connection;

import web.dto.User;

public interface UserDao {

	/**
	 * user_id와 user_pw가 일치하는 회원의 수 조회
	 * @param conn - DB연결 객체
	 * @param user - 조회할 회원의 정보
	 * @return int - 0 : 존재하지 않는 회원, 1: 존재하는 회원
	 */
	public int selectCntUserByUseridUserpw(Connection conn, User user);

	
	/**
	 * user_id를 이용해 회원정보 조회하기
	 * @param conn - DB연결 객체
	 * @param user - 조회할 user_id를 가진 객체
	 * @return User - 조회된 회원 정보
	 */
	public User selectUserByUserid(Connection conn, User user);

}
