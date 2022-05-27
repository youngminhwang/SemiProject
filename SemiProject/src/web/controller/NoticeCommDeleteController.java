package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.NoticeComm;
import web.service.face.NoticeCommService;
import web.service.impl.NoticeCommServiceImpl;

@WebServlet("/comm/noticecommdelete")
public class NoticeCommDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NoticeCommService noticeCommService = new NoticeCommServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/comm/noticecommdelete [GET]");
		
		//idx, idx_comm값 전달
		NoticeComm noticeComm = noticeCommService.getIdxIdxComm(req);
		
		noticeCommService.deleteComm(noticeComm);
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		//게시글로 리다이렉트
		resp.sendRedirect("/board/noticeview?idx=" + idx);
		
	}
	
}
