package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Cafe;
import web.service.face.CafeService;
import web.service.impl.CafeServiceImpl;


@WebServlet("/cafe/cafedelete")
public class CafeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CafeService cafeService = new CafeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cafe cafe = cafeService.getCafe_no(req);
		
		cafeService.delete(cafe);
		
		resp.sendRedirect("/cafe/cafelist");
		
	}
	
}
