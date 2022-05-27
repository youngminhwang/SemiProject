package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Notice;
import web.dto.NoticeFile;
import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;

@WebServlet("/board/noticeupdate")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/update [GET]");
		
		//전달 파라미터 얻기 - idx
		Notice idx = noticeService.getIdx(req);
		
		//상세보기 결과 조회
		Notice updateNoticeBoard = noticeService.viewText(idx);
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateNoticeBoard", updateNoticeBoard);
		
		
		//닉네임 전달
		req.setAttribute("writerNick", noticeService.getNick(updateNoticeBoard));
		
		
		//첨부파일 정보 조회
		NoticeFile noticeFile = noticeService.viewFile(updateNoticeBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("noticeFile", noticeFile);
		
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/noticeupdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/update [POST]");
		
		noticeService.updateText(req);
		
		System.out.println(req.getAttribute("idx"));
		
		resp.sendRedirect("/board/noticelist");
		
		
	}

}
