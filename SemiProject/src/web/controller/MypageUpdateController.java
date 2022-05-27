package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.User;
import web.service.face.MypageService;
import web.service.impl.MypageServiceImpl;


@WebServlet("/mypage/mypageupdate")
public class MypageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MypageService MypageService = new MypageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    req.setCharacterEncoding("UTF-8");
		
	    HttpSession session = req.getSession();

		String userid = (String) session.getAttribute("user_id");
		
		User user = new User();
		
		user.setUser_id(userid);
		
		System.out.println("확인을 하자, 이걸로 진행하자 : " + user);
		
		User viewMypageUp = MypageService.mypageview(user);

		req.setAttribute("viewMypageUp", viewMypageUp);

		req.getRequestDispatcher("/WEB-INF/views/mypage/mypageupdate.jsp").forward(req, resp);

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MypageService.mypageupdate(req);
		
		resp.sendRedirect("/mypage/mypageview");
		
		
	}
}
