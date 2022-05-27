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


@WebServlet("/cafe/cafelist")
public class CafeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CafeService cafeService = new CafeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1. 로그인 되어있지 않으면 리다이렉트
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			
			return;
		}
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = cafeService.getPaging(req);
		System.out.println("CafeController doGet() - " + paging);
		
		//게시글 전체 조회
		//List<Cafe> cafeList = cafeService.getList();
		
		//게시글 페이징 목록 조회
		List<Cafe> cafeList = cafeService.getList(paging);
		
		
		//조회결과 Model값 전달
		req.setAttribute("cafeList", cafeList);
		
		//페이징 Model값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/cafe/cafelist.jsp").forward(req, resp);
		
	}
}
