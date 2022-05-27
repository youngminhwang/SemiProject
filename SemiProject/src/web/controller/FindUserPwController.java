package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.FindUserPwByEmailAndIdService;
import web.service.impl.FindUserPwByEmailAndIdServiceImpl;

@WebServlet("/member/pwFind")
public class FindUserPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	FindUserPwByEmailAndIdService findUserPwByEmailAndIdService = new FindUserPwByEmailAndIdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/pwFind.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println((req.getParameter("user_id")));
		String userPw = findUserPwByEmailAndIdService.findUserPwByEmailAndIdService(req);
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("user_pw", userPw);
	
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/showPw.jsp").forward(req, resp);
		
	}
	
}
