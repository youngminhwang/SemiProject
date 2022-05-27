package web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.CafeInfo;
import web.dto.Free;
import web.dto.Inquire;
import web.dto.Notice;
import web.service.face.FreeService;
import web.service.face.InquireService;
import web.service.face.NoticeService;
import web.service.face.SearchService;
import web.service.impl.FreeServiceImpl;
import web.service.impl.InquireServiceImpl;
import web.service.impl.NoticeServiceImpl;
import web.service.impl.SearchServiceImpl;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SearchService searchService = new SearchServiceImpl();
		FreeService freeService = new FreeServiceImpl();
		NoticeService noticeService = new NoticeServiceImpl();
		InquireService inquireService = new InquireServiceImpl();
		
		
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//응답 웹페이지 한글 인코딩
		resp.setContentType("text/html; charset=UTF-8");
		
		//-----------------------------------------------------------

		//카페 목록 전체 조회
		List<CafeInfo> rcmCafeSelectList = searchService.rcmCafeSelectList();

		//모델 정보 전달
		req.setAttribute("rcmCafeSelectList", rcmCafeSelectList);
		
		//카페 목록 전체 조회
		List<CafeInfo> newCafeSelectList = searchService.newCafeSelectList();

		//모델 정보 전달
		req.setAttribute("newCafeSelectList", newCafeSelectList);
		
		//자유 게시판 목록 전체 조회
		List<Free> freeList = freeService.freeBoardList();
		
		//모델 정보 전달
		req.setAttribute("freeList", freeList);
		
		//공지사항 게시판 목록 조회
		List<Notice> noticeList = noticeService.noticeBoardList();
		
		//조회 결과 모델값으로 전달하기
		req.setAttribute("noticeList", noticeList);
		
		//게시글 페이징 목록 조회 - BoardService 이용
		List<Inquire> inquireList = inquireService.inquireBoardList();
			

		//조회 결과 모델값으로 전달하기
		req.setAttribute("inquireList", inquireList);
		
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
		
	}
	
}
