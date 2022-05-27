package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Tag;
import web.service.face.CafeService;
import web.service.face.TagService;
import web.service.impl.CafeServiceImpl;
import web.service.impl.TagServiceImpl;


@WebServlet("/cafe/cafewrite")
public class CafeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CafeService cafeService = new CafeServiceImpl();
	private TagService tagService = new TagServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 로그인 되어있지 않으면 리다이렉트
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			
			return;
		}
		
		//-/태그 전체 목록 조회
		List<Tag> tagList = tagService.getList();
		
		//-/조회결과 model값 전달
		req.setAttribute("tagList", tagList);
		
		//View 지정
		req.getRequestDispatcher("/WEB-INF/views/cafe/cafewrite.jsp").forward(req, resp);
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//1. 작성 글 삽입
		cafeService.cafewrite(req);
		
		resp.sendRedirect("/cafe/cafelist");
		

		
		
	}
}
