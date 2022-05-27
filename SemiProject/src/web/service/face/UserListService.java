package web.service.face;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import util.Paging;

import web.dto.User;

public interface UserListService {

	/**
	 * 회원목록 전체 조회
	 * @return List<Board> - 회원목록 전체 조회 결과 목록
	 */
	public List<User> getUserList();

	
	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<User> getList(Paging paging);


	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);


	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return User - 전달파라미터 user_no값을 포함한 DTO객체
	 */
	public User getUser_no(HttpServletRequest req);

	/**
	 * 회원 삭제
	 * @param user - 삭제할 회원 번호를 가진 객체
	 */
	public void delete(User user);

	/**
	 * 전달된 user_no를 이용하여 회원정보을 조회한다
	 * 
	 * @param user_no - 조회할 user_no를 가지고 있는 DTO객체 
	 * @return User - 조회된 회원 정보
	 */
	public User view(User user_no);

	/**
	 * 요청 파라미터 얻기
	 * @param req - 요청 정보 객체
	 * @return User - 전달 파라미터 user_rank값 포함한 DTO 
	 */
	public User getUserRank(HttpServletRequest req);


	/**
	 *전달된 user_rank, user이용해 회원등급 변경 
	 * @param user - 등급변경할 회원 번호
	 * @param userrank - 변경할 등급
	 */
	public void updateRank(User user, User userrank);

	





	public List<User> viewListSearchI(Paging paging);

	public List<User> viewListSearchN(Paging paging);


	public Paging getSearchPagingI(HttpServletRequest req);


	public Paging getSearchPagingN(HttpServletRequest req);




















	
	
	
	
}
