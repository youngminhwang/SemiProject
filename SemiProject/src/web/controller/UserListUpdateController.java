package web.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/board/userupdate")
public class UserListUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
//	private UserListService userListService = new UserListServiceImpl();
//	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.전달파라미터 얻기
//		User user = userListService.getUser_no(req);
		
		

		
		//목록으로 리다이렉트
		resp.sendRedirect("/board/userlist");
		
	}
	
	
}
