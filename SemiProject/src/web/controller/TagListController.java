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


@WebServlet("/tag/taglist")
public class TagListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TagService tagService = new TagServiceImpl();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1. 로그인 되어있지 않으면 리다이렉트
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			
			return;
		}
		
		//전달 파라미터에서 현재 페이징 객체 알아내기
		Paging paging = tagService.getPaging(req);
		
		//태그 전체 목록 조회
//		List<Tag> tagList = tagService.getList();
		
		//태그 페이징 목록 조회
		List<Tag> tagList = tagService.getList(paging);
		
		//조회결과 model값 전달
		req.setAttribute("tagList", tagList);
//		
		//페이징 model값 전달
		req.setAttribute("paging", paging);
		
		
		
		//view 지정 응답
		req.getRequestDispatcher("/WEB-INF/views/tag/taglist.jsp").forward(req, resp);

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
//		String[] tagno = req.getParameterValues("tag_no");
//		for(String str : tagno) {
//			System.out.println("선택된 태그 번호 : " + str);
//		}
		
		String tagno = req.getParameter("tag_no");
		System.out.println("라라라 : " + tagno);
		
		// 전달파라미터 Tag DTO에 저장
		Tag tag_no = tagService.getTag_no(req);
		System.out.println("태그넘버 : " + tag_no);
		
		// 상세보기 결과 조회
		Tag viewTag = tagService.view(tag_no);
		System.out.println("TagList-View viewTag - " + viewTag);
		
//		// 조회결과 model값 전달
		req.setAttribute("viewTag", viewTag);
		
		

	}
		
}
