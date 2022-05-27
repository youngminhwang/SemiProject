package web.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.CafeReview;
import web.service.face.CafeInfoService;
import web.service.impl.CafeInfoServiceImpl;

/**
 * Servlet implementation class CafeInfoReviewdeleteController
 */
@WebServlet("/review/delete")
public class CafeInfoReviewdeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CafeInfoService cafeInfoService = new CafeInfoServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		CafeReview cafereview= new CafeReview();
		cafereview = cafeInfoService.getrCafeno(req);
		
		cafeInfoService.reviewdelete(req);
		
		
		resp.sendRedirect("/cafe/view?cafeinfo="+cafereview.getCafe_no());
		
	}

}
