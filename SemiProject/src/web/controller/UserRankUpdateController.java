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


@WebServlet("/board/userrankupdate")
public class UserRankUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
		private UserListService userListService = new UserListServiceImpl();
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			System.out.println("/board/userrankupdate[GET]");

		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			int changeRank = Integer.parseInt(req.getParameter("userChangeRank"));
			System.out.println("바뀐 등급 번호는 : " + changeRank);
			
			User user_no = userListService.getUser_no(req);
			System.out.println("등급 바꿀 회원 번호는 : " + user_no);

			
			User userrank = userListService.getUserRank(req);
			
			User user = userListService.getUser_no(req);
			
			
			userListService.updateRank(user, userrank);
			
			resp.sendRedirect("/board/userlist");
			
			
		}
		
}
