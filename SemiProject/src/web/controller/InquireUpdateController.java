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

@WebServlet("/board/inquireupdate")
public class InquireUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	InquireService inquireService = new InquireServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/inquireupdate [GET]");
		
		//전달 파라미터 얻기 - idx
		Inquire idx = inquireService.getIdx(req);
		
		//상세보기 결과 조회
		Inquire updateInquireBoard = inquireService.viewText(idx);
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateInquireBoard", updateInquireBoard);
		
		
		//닉네임 전달
		req.setAttribute("writerNick", inquireService.getNick(updateInquireBoard));
		
		
		//첨부파일 정보 조회
		InquireFile inquireFile = inquireService.viewFile(updateInquireBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("inquireFile", inquireFile);
		
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/inquireupdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/inquireupdate [POST]");
		
		inquireService.updateText(req);
		
		resp.sendRedirect("/board/inquirelist");
		
		
	}

}
