package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.MemberJoinService;
import web.service.impl.MemberJoinServiceImpl;

@WebServlet("/member/duplicate_useremail")
public class MemberDuplicateUseremailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberJoinService memberJoinService = new MemberJoinServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user_email = req.getParameter("user_email");
		
		boolean dupl = memberJoinService.duplicateUserEmail(user_email);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().println("{\"dupl\": "+ dupl +"}");
		
	}
}
