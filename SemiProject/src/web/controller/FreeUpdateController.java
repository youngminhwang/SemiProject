package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Free;
import web.dto.FreeFile;
import web.service.face.FreeService;
import web.service.impl.FreeServiceImpl;

@WebServlet("/board/freeupdate")
public class FreeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/update [GET]");
		
		//전달 파라미터 얻기 - idx
		Free idx = freeService.getIdx(req);
		
		//상세보기 결과 조회
		Free updateFreeBoard = freeService.viewText(idx);
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateFreeBoard", updateFreeBoard);
		
		
		//닉네임 전달
		req.setAttribute("writerNick", freeService.getNick(updateFreeBoard));
		
		
		//첨부파일 정보 조회
		FreeFile freeFile = freeService.viewFile(updateFreeBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("freeFile", freeFile);
		
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/freeupdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/update [POST]");
		
		freeService.updateText(req);
		
		System.out.println(req.getAttribute("idx"));
		
		resp.sendRedirect("/board/freelist");
		
		
	}

}
