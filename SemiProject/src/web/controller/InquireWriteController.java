package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.InquireService;
import web.service.impl.InquireServiceImpl;

@WebServlet("/board/inquirewrite")
public class InquireWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InquireService inquireService = new InquireServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/inquirewrite [GET]");
		
		//VIEW 지정하기
		req.getRequestDispatcher("/WEB-INF/views/board/inquirewrite.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/inquirewrite [POST]");
		
		//작성글 삽입
		inquireService.writeText(req);
		
		//게시판 목록으로 리다이렉트
		resp.sendRedirect("/board/inquirelist");
		
	}
	
}
