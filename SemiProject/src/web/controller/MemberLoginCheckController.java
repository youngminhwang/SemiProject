package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.MemberLoginService;
import web.service.impl.MemberLoginServiceImpl;

/**
 * Servlet implementation class MemberJoinCheckController
 */
@WebServlet("/login/check")
public class MemberLoginCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberLoginService memberLoginService = new MemberLoginServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		boolean result;
		
		String id=(String) req.getParameter("id");
		String pw=(String) req.getParameter("pw");
		
		System.out.println(id);
		
		if(memberLoginService.loginCheck(id,pw)) {
			result = true;
		}else {
			result =false;
		}
		
		resp.getWriter().append("{\"result\": "+result+"}");
	}
}
