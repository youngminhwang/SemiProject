package web.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.NoticeCommDao;
import web.dao.impl.NoticeCommDaoImpl;
import web.dto.NoticeComm;
import web.service.face.NoticeCommService;

public class NoticeCommServiceImpl implements NoticeCommService {

	private NoticeCommDao noticeCommDao = new NoticeCommDaoImpl();
	
	//게시글 번호로 해당 게시글의 댓글 조회하기
	@Override
	public List<NoticeComm> viewComm(NoticeComm idx) {
		return noticeCommDao.selectNoticeCommByIdx(JDBCTemplate.getConnection(), idx);
	}
	
	
	//게시글 번호 조회하기
	@Override
	public NoticeComm getIdx(HttpServletRequest req) {
		
		//전달파라미터 idx를 저장할 DTO객체 생성
		NoticeComm idx = new NoticeComm();
				
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
		
		NoticeComm noticeComm = new NoticeComm();
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		noticeComm.setIdx(idx);
		
		//유저 번호 처리
		noticeComm.setUserno(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_no"))));
		
		//댓글 작성 내용 받아오기
		noticeComm.setCommContent( req.getParameter("commcnt") );

		//작성자 닉네임 처리
		noticeComm.setUsernick( (String) req.getSession().getAttribute("user_nick") );

		//DB 연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		if(noticeComm.getCommContent()==null || "".equals(noticeComm.getCommContent())) {
			noticeComm.setCommContent("(댓글 내용 없음)");
		}
		if( noticeCommDao.insert(conn, noticeComm) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void updateComm(HttpServletRequest req) {
		
		NoticeComm noticeComm = new NoticeComm();

		int idx = Integer.parseInt(req.getParameter("idx"));
		int idxComm = Integer.parseInt(req.getParameter("idx_comm"));
		
		noticeComm.setIdx(idx);
	
		noticeComm.setIdxComm(idxComm);
		
		noticeComm.setCommContent( req.getParameter("commcntModify") );

		Connection conn = JDBCTemplate.getConnection();
		
		if(noticeComm.getCommContent()==null || "".equals(noticeComm.getCommContent())) {
			noticeComm.setCommContent("(댓글 내용 없음)");
		}
		if( noticeCommDao.update(conn, noticeComm) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void deleteComm(NoticeComm noticeComm) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( noticeCommDao.delete(conn, noticeComm) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public NoticeComm getIdxIdxComm(HttpServletRequest req) {
		
		//전달파라미터 idx를 저장할 DTO객체 생성
		NoticeComm noticeComm = new NoticeComm();
				
		String param1 = req.getParameter("idx");
		String param2 = req.getParameter("idx_comm");
		
		
		if( param1 != null && !"".equals( param1 ) ) {
			noticeComm.setIdx( Integer.parseInt(param1) );
			noticeComm.setIdxComm( Integer.parseInt(param2) );
		} else {
			System.out.println("[WARN] BoardService getBoardno() - boardno값이 null이거나 비어있음");
			
		}

		return noticeComm;
	}
	
}
