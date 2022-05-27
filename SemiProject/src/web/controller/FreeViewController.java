package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Free;
import web.dto.FreeComm;
import web.dto.FreeFile;
import web.service.face.FreeCommService;
import web.service.face.FreeService;
import web.service.impl.FreeCommServiceImpl;
import web.service.impl.FreeServiceImpl;

@WebServlet("/board/freeview")
public class FreeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FreeService freeService = new FreeServiceImpl();
	FreeCommService commService = new FreeCommServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/freeview [GET]");
		
		//게시글 조회 코드
		
		//전달 파라미터 얻기 - idx
		Free idx = freeService.getIdx(req);
		
		
		//상세 보기 결과 조회
		Free viewFreeBoard = freeService.viewText(idx);
				
		//조회 결과를 모델값으로 전달하기
		req.setAttribute("viewFreeBoard", viewFreeBoard);
		
		//첨부파일 정보 조회
		FreeFile freeFile = freeService.viewFile(viewFreeBoard);
				
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("freeFile", freeFile);
		
		//-----------------------------------------------------
		//--- 댓글 조회 코드 ---
	
		FreeComm freeIdx = commService.getIdx(req);

		//게시글 댓글 목록 조회 - CommService 이용
		List<FreeComm> freeCommList = commService.viewComm(freeIdx);
		
		//조회 결과 모델값으로 전달하기
		req.setAttribute("freeCommList", freeCommList);
		
		//VIEW 및 forward
		req.getRequestDispatcher("/WEB-INF/views/board/freeview.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");

		
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		//작성 댓글 삽입
		commService.insertComm(req);

		resp.sendRedirect("/board/freeview?idx=" + idx);
	
	}
	
}
