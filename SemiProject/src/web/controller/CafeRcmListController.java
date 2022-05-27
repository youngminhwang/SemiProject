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

@WebServlet("/cafe/rcmlist")
public class CafeRcmListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SearchService searchService = new SearchServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달 파라미터에서 현재 페이징 객체 알아내기
		Paging paging = searchService.getcafeListPaging(req);
		
		//게시글 페이징 목록 조회 - BoardService 이용
		List<CafeInfo> rcmCafeAllList = searchService.rcmCafeAllList(paging);
										
		//조회 결과 모델값으로 전달하기
		req.setAttribute("rcmCafeAllList", rcmCafeAllList);
		
		req.setAttribute("rcmpaging", paging);
		
		//forward 및 VIEW
		req.getRequestDispatcher("/WEB-INF/views/cafelist/cafelistrcm.jsp").forward(req, resp);
		
		
	}

}
