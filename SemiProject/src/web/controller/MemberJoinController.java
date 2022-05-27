package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.MemberJoinService;
import web.service.impl.MemberJoinServiceImpl;

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberJoinService memberJoinService = new MemberJoinServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/member/join [POST]");
		
		//요청 데이터의 한글 UTF-8 처리 설정
		req.setCharacterEncoding("UTF-8");
		
		//회원가입 처리
		memberJoinService.join(req);
		
		//메인으로 리다이렉트
		resp.sendRedirect("/main");
		
	}

}