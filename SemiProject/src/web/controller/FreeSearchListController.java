package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Free;
import web.service.face.FreeService;
import web.service.impl.FreeServiceImpl;

@WebServlet("/board/freelist/list")
public class FreeSearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freelist/list [GET]");
		
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
			Paging pagingT = freeService.getSearchPagingT(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<Free> searchFreeList = freeService.viewListSearchingT(pagingT);
	
			//모델 정보 전달
			req.setAttribute("searchFreePaging", pagingT);
			
			//모델 정보 전달
			req.setAttribute("searchFreeList", searchFreeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/freesearchlist.jsp").forward(req, resp);
			
		} else if( select.equals("content") ) {
			Paging pagingC = freeService.getSearchPagingC(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<Free> searchFreeList = freeService.viewListSearchingC(pagingC);
			
			//모델 정보 전달
			req.setAttribute("searchFreePaging", pagingC);
			
			//모델 정보 전달
			req.setAttribute("searchFreeList", searchFreeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/freesearchlist.jsp").forward(req, resp);

			
		} else if(select.equals("usernick") ) {
			Paging pagingN = freeService.getSearchPagingN(req);

			//게시글 페이징 목록 조회 - BoardService 이용
			List<Free> searchFreeList = freeService.viewListSearchingN(pagingN);
					
			//모델 정보 전달
			req.setAttribute("searchFreePaging", pagingN);
			
			//모델 정보 전달
			req.setAttribute("searchFreeList", searchFreeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/freesearchlist.jsp").forward(req, resp);

		}
		
		
	}

}
