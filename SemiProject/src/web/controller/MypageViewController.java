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


@WebServlet("/mypage/mypageview")
public class MypageViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MypageService MypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String userid = (String) session.getAttribute("user_id");
		String userpw = (String) session.getAttribute("user_pw");

		System.out.println("지금세션에는 : " + userid + userpw);
		
		User user = new User();
		
		user.setUser_id(userid);
		
		
		System.out.println("확인을 하자, 이걸로 진행하자 : " + user);
		
		
		User viewMypage = MypageService.mypageview(user);
		
		System.out.println("확인하자 뷰 마이페이지 : " + viewMypage);
		System.out.println("왜 널포인트야 왜 : " + viewMypage.getUser_pw());

//		
//		List<User> mypageList = MypageService.getMypageList(user);
		
		//조회결과 모델값 전달
//		req.setAttribute("mypageList", mypageList);
		
		req.setAttribute("viewMypage", viewMypage);
		
		// view 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/mypage/mypageview.jsp").forward(req, resp);
		
		
		
	}
}
