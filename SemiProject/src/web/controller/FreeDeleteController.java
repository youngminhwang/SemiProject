package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Free;
import web.service.face.FreeService;
import web.service.impl.FreeServiceImpl;

@WebServlet("/board/freedelete")
public class FreeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FreeService freeService = new FreeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freedelete [GET]");
		
		Free free = freeService.getIdx(req);
		
		freeService.deleteText(free);
		
		System.out.println(free.getIdx());
		
		//목록으로 리다이렉트
		resp.sendRedirect("/board/freelist");
		
	}
	
}
