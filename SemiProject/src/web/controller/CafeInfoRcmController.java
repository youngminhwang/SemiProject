package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.CafeInfo;
import web.dto.UserInfo;
import web.service.face.CafeInfoService;
import web.service.impl.CafeInfoServiceImpl;

/**
 * Servlet implementation class CafeInfoRcmController
 */
@WebServlet("/cafeinfo/rcm")
public class CafeInfoRcmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CafeInfoService cafeInfoService = new CafeInfoServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/cafeInfo/rcm [get]");
		CafeInfo cafeinfo = new CafeInfo();
		HttpSession session = req.getSession();
		
		UserInfo userno=cafeInfoService.getUserInfoNo(session.getAttribute("user_id"));
		
		//카페 번호 받아옴
		cafeinfo.setCafe_no(Integer.valueOf(req.getParameter("cafeinfo")));
		
		if(cafeInfoService.getRcm(req,cafeinfo,userno)) {//있다면 delete
			cafeInfoService.deleteRcm(cafeinfo,userno);
		}else {//없다면 insert
			cafeInfoService.insertRcm(cafeinfo,userno);
		}
		cafeInfoService.updateRcm(cafeinfo);
		
		resp.sendRedirect("/cafe/view?cafeinfo="+cafeinfo.getCafe_no());
	}

}
