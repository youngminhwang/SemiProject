package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.User;
import web.service.face.UserListService;
import web.service.impl.UserListServiceImpl;


@WebServlet("/board/usersearch")
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스객체
	private UserListService userListService = new UserListServiceImpl();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");
		
		//"select"값 저장
		String select = req.getParameter("select");
		
		//"data"값 저장
		String data = req.getParameter("data");
		
		if(select.equals("user_id")) {
			
			//전달 파라미터에서 현재 페이징 객체 알아내기
			Paging pagingI = userListService.getSearchPagingI(req);
			
			//유저리스트 페이징 목록 조회
			List<User> searchList = userListService.viewListSearchI (pagingI);
			
			//모델 정보 전달
			req.setAttribute("searchPaging", pagingI);
			req.setAttribute("searchList", searchList);
			
			req.setAttribute("select", select);
			req.setAttribute("data", data);
			
			
			
			//view 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/usersearch.jsp").forward(req, resp);
			
		} else if (select.equals("user_nick")) {
			
			//전달 파라미터에서 현재 페이징 객체 알아내기
			Paging pagingN = userListService.getSearchPagingN(req);
			
			//유저리스트 페이징 목록 조회
			List<User> searchList = userListService.viewListSearchN (pagingN);
			
			//모델 정보 전달
			req.setAttribute("searchPaging", pagingN);
			
			req.setAttribute("searchList", searchList);
			
			req.setAttribute("select", select);
			req.setAttribute("data", data);
			
			//view 및 forward
			req.getRequestDispatcher("/WEB-INF/views/board/usersearch.jsp").forward(req, resp);
			
			
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
