package web.service.impl;

import java.io.UnsupportedEncodingException;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.UserListDao;
import web.dao.impl.UserListDaoImpl;
import web.dto.User;
import web.service.face.UserListService;

public class UserListServiceImpl implements UserListService {

	private UserListDao userListDao = new UserListDaoImpl();
	
	@Override
	public List<User> getUserList() {
		return userListDao.selectAll (JDBCTemplate.getConnection()) ;
	}
	
	@Override
	public List<User> getList(Paging paging) {
		
		//페이징 적용해서 조회 결과 반환
		return userListDao.selectAll( JDBCTemplate.getConnection(), paging );
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] UserListService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = userListDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	
	@Override
	public User getUser_no(HttpServletRequest req) {
		//전달 파라미터 user_no를 저장할 DTO객체 생성
		User user_no = new User();
		
		//전달 파라미터를 String 타입에 저장
		String param = req.getParameter("user_no");

		//전달받은 param이 null값이 아니거나 비어있지 않다면 param을 int로 파싱해서 boardno에 넣기!
		//전달받은 param이 null값이거나 비어있다면 경고메시지 출력!
		if(param != null && !"".equals(param)) {
			user_no.setUser_no(Integer.parseInt(param));
		} else {
			System.out.println("[WARRING] UserListService getUser_no() - user_no값이 null이거나 비어있음");
		}
		
		//DTO 반환
		return user_no;
	}
	
	@Override
	public void delete(User user) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if (userListDao.deleteUser(conn, user) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
			
		}
		
	}
	
	
	@Override
	public User view(User user_no) {
		
		Connection conn = JDBCTemplate.getConnection();

		
		//회원정보 조회
		User user = userListDao.selectUserByUser_no(conn, user_no);
		
		
		return user;
	}
	

	@Override
	public User getUserRank(HttpServletRequest req) {

		//전달 파라미터 user_rank를 저장할 DTO 객체 생성
		User user_rank = new User();
		
		//전달 파라미터를 String타입에 저장
		String param = req.getParameter("userChangeRank");
		
		System.out.println("파라미터 등급 : " +param);
		
		//널, 빈값 아니면 int파싱
		if(param != null && !"".equals(param)) {
			user_rank.setUser_rank(Integer.parseInt(param));
		} else {
			System.out.println("경고경고 UserListService getUser_rank() - user_rank값이 null 또는 비어있음");
		}
		return user_rank;
	}
	
	
	@Override
	public void updateRank(User user, User userrank) {
		
		Connection conn = JDBCTemplate.getConnection();

		if (userListDao.updateUserRank(conn, user, userrank) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
			
		}
		
		
	}
	



	@Override
	public List<User> viewListSearchI(Paging paging) {
		return userListDao.searchIdList(JDBCTemplate.getConnection(), paging);
	}
	
	@Override
	public List<User> viewListSearchN(Paging paging) {
		return userListDao.searchNickList(JDBCTemplate.getConnection(), paging);
	}
	
	
	@Override
	public Paging getSearchPagingI(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//전달 파라미터 
		String data = req.getParameter("data");
		System.out.println("서비스임플에서 아이디 데이터 확인하기 : " + data);
		
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] freeService getSearchPaging - curPage값이 null이거나 공백");
		}
		
		//총 게시글 수 조회하기
		int totalCount = userListDao.selectCntIdSearch(JDBCTemplate.getConnection(), data);
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
		return searchPaging;
		
	}
	
	@Override
	public Paging getSearchPagingN(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//전달 파라미터 seach 추출하기
		String data = req.getParameter("data");
		
		//전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] freeService getSearchPaging - curPage값이 null이거나 공백");
		}
		
		//총 게시글 수 조회하기
		int totalCount = userListDao.selectCntNickSearch(JDBCTemplate.getConnection(), data);
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
				
		return searchPaging;
	}

}
