package web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.CafeInfo;
import web.dto.CafeReview;
import web.dto.CafeReviewFile;
import web.service.face.CafeInfoService;
import web.service.impl.CafeInfoServiceImpl;

/**
 * Servlet implementation class CafeInfoReviewUpdateController
 */
@WebServlet("/review/update")
public class CafeInfoReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CafeInfoService cafeInfoService = new CafeInfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CafeInfo cafeinfo = new CafeInfo();
		CafeReviewFile cafeFile = new CafeReviewFile();
		//리뷰 정보 받아오기
		CafeReview review=cafeInfoService.getReviewno(req);
		cafeinfo.setCafe_no(review.getCafe_no());
		
		List<CafeReviewFile> reviewfile = cafeInfoService.getFileno(req,cafeFile);
		
		
		
		req.setAttribute("review", review);
		req.setAttribute("reviewfile", reviewfile);
		
		
		req.getRequestDispatcher("/WEB-INF/views/review/update.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/review/write [dopost]");

		req.setCharacterEncoding("UTF-8");
		CafeReview cafereview = cafeInfoService.getReviewInfo(req);
		
		
		
		
		resp.sendRedirect("/cafe/view?cafeinfo="+cafereview.getCafe_no());	
	}

}
