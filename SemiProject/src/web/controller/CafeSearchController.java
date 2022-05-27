package web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Cafe;
import web.service.face.CafeService;
import web.service.impl.CafeServiceImpl;



@WebServlet("/cafe/cafesearch")
public class CafeSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CafeService cafeService = new CafeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");
		
		String data = req.getParameter("data");
		
		//전달 파라미터에서 현재 페이징 객체 알아내기
		Paging pagingN = cafeService.getSearchPagingN(req);
		System.out.println("페이징객체" + pagingN);
		
		//유저리스트 페이징 목록 조회
		List<Cafe> searchList = cafeService.viewListSearchN (pagingN);
		
		
		//모델 정보 전달
		req.setAttribute("searchCafePaging", pagingN);
		req.setAttribute("searchList", searchList);
		
		req.setAttribute("data", data);
		
		
		
		//view 및 forward
		req.getRequestDispatcher("/WEB-INF/views/cafe/cafesearch.jsp").forward(req, resp);
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
