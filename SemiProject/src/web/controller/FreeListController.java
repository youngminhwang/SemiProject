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

@WebServlet("/board/freelist")
public class FreeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freelist [GET]");
		
		//전달 파라미터에서 현재 페이징 객체 알아내기
		Paging paging = freeService.getFreeBoardPaging(req);
		
		//게시글 페이징 목록 조회 - BoardService 이용
		List<Free> freeList = freeService.viewFreeBoardList(paging);
		
		//조회 결과 모델값으로 전달하기
		req.setAttribute("freeList", freeList);
		
		//페이징 모델 값 전달
		req.setAttribute("freepaging", paging);
		
		//forward 및 VIEW
		req.getRequestDispatcher("/WEB-INF/views/board/freelist.jsp").forward(req, resp);
	}

}
