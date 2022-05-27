package web.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.FreeCommDao;
import web.dao.impl.FreeCommDaoImpl;
import web.dto.FreeComm;
import web.service.face.FreeCommService;

public class FreeCommServiceImpl implements FreeCommService {

	private FreeCommDao freeCommDao = new FreeCommDaoImpl();
	
	//게시글 번호로 해당 게시글의 댓글 조회하기
	@Override
	public List<FreeComm> viewComm(FreeComm idx) {
		return freeCommDao.selectFreeCommByIdx(JDBCTemplate.getConnection(), idx);
	}
	
	
	//게시글 번호 조회하기
	@Override
	public FreeComm getIdx(HttpServletRequest req) {
		
		//전달파라미터 idx를 저장할 DTO객체 생성
		FreeComm idx = new FreeComm();
				
		String param = req.getParameter("idx");
		
		if( param != null && !"".equals( param ) ) {
			idx.setIdx( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] BoardService getBoardno() - boardno값이 null이거나 비어있음");
		
		}

		return idx;
	}
	
	//신규 댓글 삽입하기
	@Override
	public void insertComm(HttpServletRequest req) {
		
		FreeComm freeComm = new FreeComm();
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		freeComm.setIdx(idx);
		
		//유저 번호 처리
		freeComm.setUserno(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_no"))));
		
		//댓글 작성 내용 받아오기
		freeComm.setCommContent( req.getParameter("commcnt") );

		//작성자 닉네임 처리
		freeComm.setUsernick( (String) req.getSession().getAttribute("user_nick") );

		//DB 연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		if(freeComm.getCommContent()==null || "".equals(freeComm.getCommContent())) {
			freeComm.setCommContent("(댓글 내용 없음)");
		}
		if( freeCommDao.insert(conn, freeComm) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void updateComm(HttpServletRequest req) {
		
		FreeComm freeComm = new FreeComm();

		int idx = Integer.parseInt(req.getParameter("idx"));
		int idxComm = Integer.parseInt(req.getParameter("idx_comm"));
		
		freeComm.setIdx(idx);
		
		freeComm.setIdxComm(idxComm);
		
		freeComm.setCommContent( req.getParameter("commcntModify") );

		Connection conn = JDBCTemplate.getConnection();
		
		if(freeComm.getCommContent()==null || "".equals(freeComm.getCommContent())) {
			freeComm.setCommContent("(댓글 내용 없음)");
		}
		if( freeCommDao.update(conn, freeComm) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void deleteComm(FreeComm freeComm) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( freeCommDao.delete(conn, freeComm) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public FreeComm getIdxIdxComm(HttpServletRequest req) {
		
		//전달파라미터 idx를 저장할 DTO객체 생성
		FreeComm freeComm = new FreeComm();
				
		String param1 = req.getParameter("idx");
		String param2 = req.getParameter("idx_comm");
		
		
		if( param1 != null && !"".equals( param1 ) ) {
			freeComm.setIdx( Integer.parseInt(param1) );
			freeComm.setIdxComm( Integer.parseInt(param2) );
		} else {
			System.out.println("[WARN] BoardService getBoardno() - boardno값이 null이거나 비어있음");
			
		}

		return freeComm;
	}

}
