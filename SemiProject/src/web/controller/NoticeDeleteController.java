package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Notice;
import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;

@WebServlet("/board/noticedelete")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freedelete [GET]");
		
		Notice notice = noticeService.getIdx(req);
		
		noticeService.deleteText(notice);
		
		System.out.println(notice.getIdx());
		
		//목록으로 리다이렉트
		resp.sendRedirect("/board/noticelist");
		
	}
	
}
