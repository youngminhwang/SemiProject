package web.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.CafeInfo;
import web.dto.CafeReview;

import web.service.face.CafeInfoService;

import web.service.impl.CafeInfoServiceImpl;


@WebServlet("/review/write")
public class CafeInfoReviewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CafeInfoService cafeInfoService = new CafeInfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/cafe/write [get]");
		CafeInfo cafeno=cafeInfoService.getCafeno(req);
		System.out.println(cafeno);
		CafeInfo viewCafe=cafeInfoService.view(cafeno);
		req.setAttribute("viewCafe", viewCafe);
		
		
		req.getRequestDispatcher("/WEB-INF/views/review/write.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGet(req, resp);
		req.setCharacterEncoding("UTF-8");
		System.out.println("/review/write [dopost]");
		CafeReview cafe=cafeInfoService.write(req);
		
		
		resp.sendRedirect("/cafe/view?cafeinfo="+cafe.getCafe_no());	
	}
}
