package web.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.NoticeCommService;
import web.service.impl.NoticeCommServiceImpl;

@WebServlet("/comm/noticecommupdate")
public class NoticeCommUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NoticeCommService noticeCommService = new NoticeCommServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/comm/commupdate [POST]");

		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
				
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");
		
		noticeCommService.updateComm(req);
		
		int idx = Integer.parseInt(req.getParameter("idx"));

		resp.sendRedirect("/board/noticeview?idx=" + idx);
		
	}
	
}
