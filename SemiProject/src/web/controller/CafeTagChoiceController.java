package web.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/cafe/cafetagchoice")
public class CafeTagChoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//-/전달 파라미터에서 현재 페이징 객체 알아내기
//		Paging paging = tagService.getPaging(req);
		
		//태그 전체 목록 조회
//		List<Tag> tagList = tagService.getList();
		
		//-/태그 페이징 목록 조회
//		List<Tag> tagList = tagService.getList(paging);
		
		//-/조회결과 model값 전달
//		req.setAttribute("tagList", tagList);
		
		//-/페이징 model값 전달
//		req.setAttribute("paging", paging);
	
		//View 지정
//		req.getRequestDispatcher("/WEB-INF/views/cafe/cafewrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//POST 한글
		req.setCharacterEncoding("UTF-8");
	
		//체크박스 값
		String[] tag = req.getParameterValues("tag_name");
		
		
		System.out.println("체크한 항목" + tag);
		for(String t : tag) {
			System.out.println(t);
		}
	}
	
	
}
