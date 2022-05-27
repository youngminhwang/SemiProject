package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Inquire;
import web.service.face.InquireService;
import web.service.impl.InquireServiceImpl;

@WebServlet("/board/inquiredelete")
public class InquireDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InquireService inquireService = new InquireServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/inquiredelete [GET]");
		
		Inquire inquire = inquireService.getIdx(req);
		
		inquireService.deleteText(inquire);
		
		System.out.println(inquire.getIdx());
		
		//목록으로 리다이렉트
		resp.sendRedirect("/board/inquirelist");
		
	}
	
}
