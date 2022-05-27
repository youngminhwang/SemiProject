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

@WebServlet("/board/noticelist/list")
public class NoticeSearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/searchlist [GET]");
		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");
		
		//-----------------------------------------------------------
		
		//"select"값 저장
		String select = req.getParameter("select");
		
		//"data"값 저장
		String data = req.getParameter("data");
		
		if(select.equals("title") ) {
			//전달 파라미터에서 현재 페이징 객체 알아내기
			Paging pagingT = noticeService.getSearchPagingT(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<Notice> searchNoticeList = noticeService.viewListSearchingT(pagingT);
	
			//모델 정보 전달
			req.setAttribute("searchNoticePaging", pagingT);
			
			//모델 정보 전달
			req.setAttribute("searchNoticeList", searchNoticeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/noticesearchlist.jsp").forward(req, resp);
			
		} else if( select.equals("content") ) {
			Paging pagingC = noticeService.getSearchPagingC(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<Notice> searchNoticeList = noticeService.viewListSearchingC(pagingC);
			
			//모델 정보 전달
			req.setAttribute("searchNoticePaging", pagingC);
			
			//모델 정보 전달
			req.setAttribute("searchNoticeList", searchNoticeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/noticesearchlist.jsp").forward(req, resp);

			
		} else if(select.equals("usernick") ) {
			Paging pagingN = noticeService.getSearchPagingN(req);

			//게시글 페이징 목록 조회 - BoardService 이용
			List<Notice> searchNoticeList = noticeService.viewListSearchingN(pagingN);
			
			//모델 정보 전달
			req.setAttribute("searchNoticePaging", pagingN);
			
			//모델 정보 전달
			req.setAttribute("searchNoticeList", searchNoticeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/noticesearchlist.jsp").forward(req, resp);

			
		}
		

	}

}
