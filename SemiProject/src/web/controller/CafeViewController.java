package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Cafe;
import web.dto.CafeFile;
import web.dto.CafeTag;
import web.service.face.CafeService;
import web.service.impl.CafeServiceImpl;


@WebServlet("/cafe/cafeview")
public class CafeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CafeService cafeService = new CafeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 전달파라미터를 Cafe DTO에 저장
		Cafe cafe_no = cafeService.getCafe_no(req);
		System.out.println(cafe_no);
		
		//2. 상세보기 결과 조회 (카페 번호에 맞는 게시글 조회)
		Cafe viewCafe = cafeService.view(cafe_no);
		System.out.println("CafeViewController viewCafe222 - " + viewCafe);
		
		
		///1. 전달파라미터 저장한 cafe_no를 이용하자
		
		///2-1. 태그 이름 조회 결과 (카페 번호에 맞는 태그 이름 조회)--
		CafeTag cafeTagName = cafeService.tagno(cafe_no);
		System.out.println("CafeViewController cafeTagName 222- " + cafeTagName);
		
		
		///4. 조회결과 태그 이름 값 전달(태그이름)
		req.setAttribute("cafeTagName", cafeTagName);
		
		
		
		//3. 조회결과 model값 전달(카페 정보)
		req.setAttribute("viewCafe", viewCafe);
		

		//첨부파일 정보 조회
		CafeFile cafeFile = cafeService.viewcafeFile(viewCafe);
		
		//첨부파일 정보 model값 전달
		req.setAttribute("cafeFile", cafeFile);
		
		// view 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/cafe/cafeview.jsp").forward(req, resp);

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
