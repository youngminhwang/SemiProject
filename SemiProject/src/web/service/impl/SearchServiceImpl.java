package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.SearchDao;
import web.dao.impl.SearchDaoImpl;
import web.dto.CafeInfo;
import web.service.face.SearchService;

public class SearchServiceImpl implements SearchService {

	SearchDao searchDao = new SearchDaoImpl();
	
	@Override
	public List<CafeInfo> viewListSearchingN(Paging paging) {
		return searchDao.searchCafeNameList(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public List<CafeInfo> viewListSearchingL(Paging paging) {
		return searchDao.searchCafeLocList(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public List<CafeInfo> viewListSearchingT(Paging paging) {
		return searchDao.searchTagConnList(JDBCTemplate.getConnection(), paging);
	}


	@Override
	public Paging getSearchPagingN(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//전달 파라미터 seach 추출하기
		String data = req.getParameter("data");
		
		//전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] freeService getSearchPaging - curPage값이 null이거나 공백");
		}

		//총 게시글 수 조회하기
		int totalCount = searchDao.selectCntSearchCafeName( JDBCTemplate.getConnection(), data );
	
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
		return searchPaging;

	}
		
	@Override
	public Paging getSearchPagingL(HttpServletRequest req) {
			
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//전달 파라미터 seach 추출하기
		String data = req.getParameter("data");
			
		//전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
			
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] freeService getSearchPaging - curPage값이 null이거나 공백");
		}

		//총 게시글 수 조회하기
		int totalCount = searchDao.selectCntSearchCafeLoc( JDBCTemplate.getConnection(), data );
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
				
		return searchPaging;
	
	}
			
			
	@Override
	public Paging getSearchPagingT(HttpServletRequest req) {
			
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//전달 파라미터 seach 추출하기
		String data = req.getParameter("data");
			
		//전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
			
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] freeService getSearchPaging - curPage값이 null이거나 공백");
		}
			
		//총 게시글 수 조회하기
		int totalCount = searchDao.selectCntSearchTagConn( JDBCTemplate.getConnection(), data );
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
					
		return searchPaging;		
				
	}

	@Override
	public List<CafeInfo> rcmCafeSelectList() {
		return searchDao.rcmCafeSelectList( JDBCTemplate.getConnection() );
	}
	
	@Override
	public List<CafeInfo> newCafeSelectList() {
		return searchDao.newCafeSelectList( JDBCTemplate.getConnection() );
	}

	@Override
	public List<CafeInfo> newCafeAllList(Paging paging) {
		return searchDao.newCafeAllListPaging( JDBCTemplate.getConnection(), paging );
	}
	
	@Override
	public List<CafeInfo> rcmCafeAllList(Paging paging) {
		return searchDao.rcmCafeAllListPaging( JDBCTemplate.getConnection(), paging );
	}

	@Override
	public Paging getcafeListPaging(HttpServletRequest req) {
		
		//전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
				
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] freeService getPaging - curPage값이 null이거나 공백");
		}
				
		//총 게시글 수 조회하기
		int totalCount = searchDao.selectCntAllCafeInfo( JDBCTemplate.getConnection() );
				
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
			
		System.out.println(paging);
		return paging;
	}

	
	
}
