package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.FindUserIdService;
import web.service.impl.FindUserIdServiceImpl;

@WebServlet("/member/idFind")
public class FindUserIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FindUserIdService findUserIdService = new FindUserIdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/idFind.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userId = findUserIdService.findUserIdByEmail(req);
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("user_id", userId);
	
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/showId.jsp").forward(req, resp);
		
	}
	
}
