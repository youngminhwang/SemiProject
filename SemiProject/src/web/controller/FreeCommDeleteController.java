package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.FreeComm;
import web.service.face.FreeCommService;
import web.service.impl.FreeCommServiceImpl;

@WebServlet("/comm/freecommdelete")
public class FreeCommDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FreeCommService freeCommService = new FreeCommServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/comm/freecommdelete [GET]");
		
		//idx, idx_comm값 전달
		FreeComm freeComm = freeCommService.getIdxIdxComm(req);
		
		freeCommService.deleteComm(freeComm);
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		//게시글로 리다이렉트
		resp.sendRedirect("/board/freeview?idx=" + idx);
		
	}
	
}
