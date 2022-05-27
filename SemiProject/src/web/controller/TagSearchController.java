package web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Tag;
import web.service.face.TagService;
import web.service.impl.TagServiceImpl;


@WebServlet("/tag/tagsearch")
public class TagSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TagService tagService = new TagServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");
		
		String data = req.getParameter("data");
		
		Paging pagingT = tagService.getSearchPagingT(req);
		
		//유저리스트 페이징 목록 조회
		List<Tag> searchList = tagService.viewListSearchT (pagingT);
		
		//모델 정보 전달
		req.setAttribute("searchPaging", pagingT);
		req.setAttribute("searchList", searchList);
		
		req.setAttribute("data", data);
		
		//view 및 forward
		req.getRequestDispatcher("/WEB-INF/views/tag/tagsearch.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		
	}
	
}
