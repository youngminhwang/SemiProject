package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.CafeInfo;
import web.service.face.SearchService;
import web.service.impl.SearchServiceImpl;

@WebServlet("/cafe/idxlist")
public class CafeIdxListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SearchService searchService = new SearchServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//�쟾�떖 �뙆�씪誘명꽣�뿉�꽌 �쁽�옱 �럹�씠吏� 媛앹껜 �븣�븘�궡湲�
		Paging paging = searchService.getcafeListPaging(req);
		
		//寃뚯떆湲� �럹�씠吏� 紐⑸줉 議고쉶 - BoardService �씠�슜
		List<CafeInfo> newCafeAllList = searchService.newCafeAllList(paging);
										
		//議고쉶 寃곌낵 紐⑤뜽媛믪쑝濡� �쟾�떖�븯湲�
		req.setAttribute("newCafeAllList", newCafeAllList);
		
		req.setAttribute("idxpaging", paging);
		
		//forward 諛� VIEW
		req.getRequestDispatcher("/WEB-INF/views/cafelist/cafelistidx.jsp").forward(req, resp);
		
	
	}

}
