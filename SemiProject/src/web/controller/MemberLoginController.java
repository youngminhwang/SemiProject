package web.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.UserInfo;
import web.service.face.LoginService;
import web.service.impl.LoginServiceImpl;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginService loginService = new LoginServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 데이터의 한글 UTF-8 처리 설정
		req.setCharacterEncoding("UTF-8");
		
		//전달 파라미터 얻기 - 로그인 정보(user_id, user_pw)
		UserInfo userInfo = loginService.getLoginMember(req);
		
		System.out.println(req.getParameter("user_id"));
		System.out.println(req.getParameter("user_pw"));
		
		
		// req_data로 로그인 인증
		boolean isLogin = loginService.login(req);
	
		if(isLogin) {	//req_data로 로그인 인증 성공 시,
			
			userInfo = loginService.info(userInfo);
			
			HttpSession session = req.getSession();
			
			session.setAttribute("login", isLogin);
			session.setAttribute("user_id", userInfo.getUser_id());
			session.setAttribute("user_nick", userInfo.getUser_nick());
			session.setAttribute("user_no", userInfo.getUser_no());
			session.setAttribute("user_rank", userInfo.getUser_rank());
			session.setAttribute("user_gender", userInfo.getUser_gender());
			session.setAttribute("user_email", userInfo.getUser_email());
			session.setAttribute("user_phone", userInfo.getUser_phone());
		
			System.out.println(session.getAttribute("login"));
			System.out.println(session.getAttribute("user_id"));
			System.out.println(session.getAttribute("user_nick"));
			System.out.println(session.getAttribute("user_no"));
			System.out.println(session.getAttribute("user_rank"));
			System.out.println(session.getAttribute("user_gender"));
			System.out.println(session.getAttribute("user_email"));
			System.out.println(session.getAttribute("user_phone"));
			
			//메인페이지로 리다이렉트
			resp.sendRedirect("/main");
		} else {
			resp.sendRedirect("/member/login");
		}
		
		
	}
	
}

