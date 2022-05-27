package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.User;
import web.service.face.UserListService;
import web.service.impl.UserListServiceImpl;


@WebServlet("/board/userview")
public class UserListViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스객체
	private UserListService userListService = new UserListServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1. 로그인 되어있지 않으면 리다이렉트
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			
			return;
		}
		
		//1. 전달 파라미터를 User DTO에 저장
		User user_no = userListService.getUser_no(req);
		System.out.println(user_no);
		
		//2. 상세보기 결과 조회
		User viewUser = userListService.view(user_no);
		System.out.println("UserListController viewUser - " + viewUser);
		
		//3. 조회결과 MODEL 값 전달
		req.setAttribute("viewUser", viewUser);
		
		//4. view지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/board/userview.jsp").forward(req, resp);

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		
		
		
		
	
	}

		
}
