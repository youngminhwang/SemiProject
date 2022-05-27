package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.CafeInfo;
import web.service.face.SearchService;
import web.service.impl.SearchServiceImpl;

@WebServlet("/search/list")
public class CafeSearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SearchService searchService = new SearchServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/search/list [GET]");
		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");
		
		//-----------------------------------------------------------
		
		//"select"값 저장
		String select = req.getParameter("select");
		
		//"data"값 저장
		String data = req.getParameter("data");
		
		if(select.equals("cafe_name") ) {
		
			//전달 파라미터에서 현재 페이징 객체 알아내기
			Paging pagingN = searchService.getSearchPagingN(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<CafeInfo> searchCafeList = searchService.viewListSearchingN(pagingN);
	
			//모델 정보 전달
			req.setAttribute("searchCafePaging", pagingN);
			
			//모델 정보 전달
			req.setAttribute("searchCafeList", searchCafeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/search/searchcafelist.jsp").forward(req, resp);
			
		} else if( select.equals("cafe_loc") ) {
		
			//전달 파라미터에서 현재 페이징 객체 알아내기
			Paging pagingL = searchService.getSearchPagingL(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<CafeInfo> searchCafeList = searchService.viewListSearchingL(pagingL);
			
			//모델 정보 전달
			req.setAttribute("searchCafePaging", pagingL);
			
			//모델 정보 전달
			req.setAttribute("searchCafeList", searchCafeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/search/searchcafelist.jsp").forward(req, resp);
	
		} else if(select.equals("tag_conn") ) {
		
			//전달 파라미터에서 현재 페이징 객체 알아내기
			Paging pagingT = searchService.getSearchPagingT(req);
			
			//게시글 페이징 목록 조회 - BoardService 이용
			List<CafeInfo> searchCafeList = searchService.viewListSearchingT(pagingT);
			
			//모델 정보 전달
			req.setAttribute("searchCafePaging", pagingT);
			
			//모델 정보 전달
			req.setAttribute("searchCafeList", searchCafeList);
			
			req.setAttribute("select", select);
			
			req.setAttribute("data", data);
			
			//VIEW 및 forward
			req.getRequestDispatcher("/WEB-INF/views/search/searchcafelist.jsp").forward(req, resp);
		
		}
		
		//전달 파라미터에서 현재 페이징 객체 알아내기
		Paging paging = searchService.getcafeListPaging(req);
						
		//게시글 페이징 목록 조회 - BoardService 이용
		List<CafeInfo> newCafeAllList = searchService.newCafeAllList(paging);
						
		//페이징 모델 값 전달
		req.setAttribute("cafepaging", paging);

		//조회 결과 모델값으로 전달하기
		req.setAttribute("newCafeAllList", newCafeAllList);
		
		//게시글 페이징 목록 조회 - BoardService 이용
		List<CafeInfo> rcmCafeAllList = searchService.rcmCafeAllList(paging);
								
		//조회 결과 모델값으로 전달하기
		req.setAttribute("rcmCafeAllList", rcmCafeAllList);
	
	}

}
