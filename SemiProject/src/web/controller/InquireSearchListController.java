package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Inquire;
import web.service.face.InquireService;
import web.service.impl.InquireServiceImpl;

@WebServlet("/board/inquirelist/list")
public class InquireSearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	InquireService inquireService = new InquireServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/inquirelist/list [GET]");
		
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
			Paging pagingT = inquireService.getSearchPagingT(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<Inquire> searchInquireList = inquireService.viewListSearchingT(pagingT);
	
			//모델 정보 전달
			req.setAttribute("searchInquirePaging", pagingT);
			
			//모델 정보 전달
			req.setAttribute("searchInquireList", searchInquireList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/inquiresearchlist.jsp").forward(req, resp);
			
		} else if( select.equals("content") ) {
			Paging pagingC = inquireService.getSearchPagingC(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<Inquire> searchInquireList = inquireService.viewListSearchingC(pagingC);
			
			//모델 정보 전달
			req.setAttribute("searchInquirePaging", pagingC);
			
			//모델 정보 전달
			req.setAttribute("searchInquireList", searchInquireList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/inquiresearchlist.jsp").forward(req, resp);

			
		} else if(select.equals("usernick") ) {
			Paging pagingN = inquireService.getSearchPagingN(req);

			//게시글 페이징 목록 조회 - BoardService 이용
			List<Inquire> searchInquireList = inquireService.viewListSearchingN(pagingN);
			
			//모델 정보 전달
			req.setAttribute("searchInquirePaging", pagingN);
			
			//모델 정보 전달
			req.setAttribute("searchInquireList", searchInquireList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/inquiresearchlist.jsp").forward(req, resp);

		}
		
	}

}
