package web.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Inquire;
import web.dto.InquireFile;
import web.service.face.InquireService;
import web.service.impl.InquireServiceImpl;

@WebServlet("/board/inquireview")
public class InquireViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InquireService inquireService = new InquireServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freeview [GET]");
		
		//게시글 조회 코드
		
		//전달 파라미터 얻기 - idx
		Inquire idx = inquireService.getIdx(req);
		
		//상세 보기 결과 조회
		Inquire viewInquireBoard = inquireService.viewText(idx);
				
		//조회 결과를 모델값으로 전달하기
		req.setAttribute("viewInquireBoard", viewInquireBoard);
		
		//첨부파일 정보 조회
		InquireFile inquireFile = inquireService.viewFile(viewInquireBoard);
				
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("inquireFile", inquireFile);
		
		//-----------------------------------------------------
		//--- 댓글 조회 코드 ---
	
//		InquireComm inquireIdx = inquireCommService.getIdx(req);
//
//		//게시글 댓글 목록 조회 - CommService 이용
//		List<FreeComm> freeCommList = commService.viewComm(freeIdx);
//		
//		//조회 결과 모델값으로 전달하기
//		req.setAttribute("freeCommList", freeCommList);
		
		//VIEW 및 forward
		req.getRequestDispatcher("/WEB-INF/views/board/inquireview.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");

		
//		int idx = Integer.parseInt(req.getParameter("idx"));
//		
//		//작성 댓글 삽입
//		commService.insertComm(req);
//
//		resp.sendRedirect("/board/freeview?idx=" + idx);
	
	}
	
}
