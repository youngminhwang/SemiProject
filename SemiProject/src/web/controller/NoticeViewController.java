package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Notice;
import web.dto.NoticeComm;
import web.dto.NoticeFile;
import web.service.face.NoticeCommService;
import web.service.face.NoticeService;
import web.service.impl.NoticeCommServiceImpl;
import web.service.impl.NoticeServiceImpl;

@WebServlet("/board/noticeview")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NoticeService noticeService = new NoticeServiceImpl();
	NoticeCommService noticeCommService = new NoticeCommServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/noticeview [GET]");
		
		//게시글 조회 코드
		
		//전달 파라미터 얻기 - idx
		Notice idx = noticeService.getIdx(req);
		
		
		//상세 보기 결과 조회
		Notice viewNoticeBoard = noticeService.viewText(idx);
				
		//조회 결과를 모델값으로 전달하기
		req.setAttribute("viewNoticeBoard", viewNoticeBoard);
		
		//첨부파일 정보 조회
		NoticeFile noticeFile = noticeService.viewFile(viewNoticeBoard);
				
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("noticeFile", noticeFile);
		
		//-----------------------------------------------------
		//--- 댓글 조회 코드 ---
	
		NoticeComm noticeIdx = noticeCommService.getIdx(req);

		//게시글 댓글 목록 조회 - CommService 이용
		List<NoticeComm> noticeCommList = noticeCommService.viewComm(noticeIdx);
		
		//조회 결과 모델값으로 전달하기
		req.setAttribute("noticeCommList", noticeCommList);
		
		//VIEW 및 forward
		req.getRequestDispatcher("/WEB-INF/views/board/noticeview.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");

		
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		//작성 댓글 삽입
		noticeCommService.insertComm(req);

		resp.sendRedirect("/board/noticeview?idx=" + idx);
	
	}
	
}
