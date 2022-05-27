package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;

@WebServlet("/board/noticewrite")
public class NoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/noticewrite [GET]");
		
		//VIEW 지정하기
		req.getRequestDispatcher("/WEB-INF/views/board/noticewrite.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/noticewrite [POST]");
		
		//작성글 삽입
		noticeService.writeText(req);
		
		//게시판 목록으로 리다이렉트
		resp.sendRedirect("/board/noticelist");
		
	}
	
}
