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



@WebServlet("/mypage/mypagedelete")
public class MypageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MypageService mypageService = new MypageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String userid = (String) session.getAttribute("user_id");
		
		System.out.println("아이디 : " + userid);
		
		// view 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/mypage/mypagedelete.jsp").forward(req, resp);


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String userid = (String) session.getAttribute("user_id");
		int userno = Integer.parseInt (String.valueOf( session.getAttribute("user_no")));
		
		System.out.println("아이디d : " + userid);
		System.out.println("유저번호d : " + userno);
		
		User userpw = mypageService.getUserpw(userid);
		
		String user_pw = userpw.getUser_pw();
		System.out.println(user_pw);
		
		String user__pw = req.getParameter("user_pw");
		System.out.println("비밀번호ddd : " + user_pw);
		
		if (user__pw.equals(user_pw)) {
		
			User user = new User();
			
			user.setUser_no(userno);
		
			mypageService.mypagedelete(user);
		
			req.getSession().invalidate();
		
			resp.sendRedirect("/");
		} else {
			
			System.out.println("확인1 : " + userpw);
			System.out.println("확인_2 : " + user_pw);
			
			resp.sendRedirect("/mypage/mypageview");
		}
		
	
	}
	
	
}
