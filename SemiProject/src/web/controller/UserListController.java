package web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.User;
import web.service.face.UserListService;
import web.service.impl.UserListServiceImpl;

@WebServlet("/board/userlist")
public class UserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private UserListService userListService = new UserListServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 로그인 되어있지 않으면 리다이렉트
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			
			return;
		}
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = userListService.getPaging(req);
		
		//회원 리스트 전체 조회
		//List <User> userList = userListService.getUserList();
		
		//회원 페이징 목록 조회 - UserService 이용
		List<User> userList = userListService.getList(paging);
		
		//조회결과 model값 전달
		req.setAttribute("userList", userList);
		
		//paging model값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/board/userlist.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		String searchID = req.getParameter("searchId");
		System.out.println("searchID");
		
	
	}
}
