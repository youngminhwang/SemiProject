package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.FreeCommService;
import web.service.face.FreeService;
import web.service.impl.FreeCommServiceImpl;
import web.service.impl.FreeServiceImpl;

@WebServlet("/board/freewrite")
public class FreeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FreeService freeService = new FreeServiceImpl();
	FreeCommService commService = new FreeCommServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freewrite [GET]");
		
		//로그인, 회원가입 구현 후 주석 풀기
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/");
			
			return;
		}
		
		//VIEW 지정하기
		req.getRequestDispatcher("/WEB-INF/views/board/freewrite.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freewrite [POST]");
		
		//작성글 삽입
		freeService.writeText(req);
		
		//게시판 목록으로 리다이렉트
		resp.sendRedirect("/board/freelist");
		
	}
	
}
