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


@WebServlet("/board/userdelete")
public class UserListDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private UserListService userListService = new UserListServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/board/userdelete[GET]");

		
		User user = userListService.getUser_no(req);
		System.out.println("지금 삭제할 회원 번호는! : " + user);
		
		
		userListService.delete(user);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/board/userlist");
		
	}
	
	
}
