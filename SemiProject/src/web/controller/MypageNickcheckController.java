package web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.MypageService;
import web.service.impl.MypageServiceImpl;

@WebServlet("/mypage/mypagenickcheck")
public class MypageNickcheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MypageService MypageService = new MypageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user_nick = req.getParameter("user_nick");
		
		boolean dupl = MypageService.duplicate(user_nick);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().println("{\"dupl\": "+ dupl +"}");
	
	}

}
