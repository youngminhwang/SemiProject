package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Notice;
import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;

@WebServlet("/board/noticelist")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/noticelist [GET]");
		
		//전달 파라미터에서 현재 페이징 객체 알아내기
		Paging paging = noticeService.getNoticeBoardPaging(req);

		//게시글 페이징 목록 조회 - NoticeService 이용
		List<Notice> noticeList = noticeService.viewNoticeBoardList(paging);
		
		//조회 결과 모델값으로 전달하기
		req.setAttribute("noticeList", noticeList);
		
		//페이징 모델 값 전달
		req.setAttribute("noticepaging", paging);
		
		//forward 및 VIEW
		req.getRequestDispatcher("/WEB-INF/views/board/noticelist.jsp").forward(req, resp);
	}

}
