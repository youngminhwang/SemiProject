package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import util.Paging;
import web.dao.face.InquireDao;
import web.dao.impl.InquireDaoImpl;
import web.dto.Inquire;
import web.dto.InquireFile;
import web.service.face.InquireService;

public class InquireServiceImpl implements InquireService {
	
	InquireDao inquireDao = new InquireDaoImpl();

	//--- 문의 게시판 조회 함수 ---
	@Override
	public List<Inquire> viewInquireBoardList(Paging paging) {
		
		//페이징 적용해서 조회 결과 반환
		return inquireDao.selectAllInquireBoard(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public Paging getInquireBoardPaging(HttpServletRequest req) {
		
		//전달 파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNNING] freeService getPaging - curPage값이 null이거나 공백");
		}
		
		//총 게시글 수 조회하기
		int totalCount = inquireDao.selectCntAllInquireBoard( JDBCTemplate.getConnection() );
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	
	
	@Override
	public void writeText(HttpServletRequest req) {
		
		//첨부파일을 추가하여 게시글 처리하기
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart/form-data형식이 아닐 경우 파일 업로드 처리 중단
		if( !isMultipart ) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);
		
		//서블릿 컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시 파일 폴더
		String path = context.getRealPath("tmp");
		File temRepository = new File(path);
		temRepository.mkdir();
		factory.setRepository(temRepository);
		
		//파일 업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);
		
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		//게시글 정보 DTO객체
		Inquire inquire = new Inquire();
		
		//첨부파일 정보 DTO객체
		InquireFile inquireFile = new InquireFile();
		
		//파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//1. 빈 파일에 대한 처리
			if( item.getSize() <= 0 ) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			
			//2. 폼 필드에 대한 처리
			if( item.isFormField() ) {
				
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key에 맞게 value를 DTO에 삽입
				if( "title".equals(key) ) {
					inquire.setInquireTitle(value);
					
				} else if ( "content".equals(key) ) {
					inquire.setInquireContent(value);
					
				}
				
			} //if( item.isFormField() ) end
			
			
			//3. 파일에 대한 처리
			if( !item.isFormField() ) {
				
				//UUID 생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();
				
				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File( uploadFolder, stored );
				
				try {
					item.write(up); //임시 파일 -> 실제 업로드 파일
					item.delete(); // 임시 파일 제거
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				inquireFile.setFileOri(origin);
				inquireFile.setFileSto(stored);
				inquireFile.setFileSize( (int)item.getSize() );
			
			} // if( !item.isFormField() ) end
			
		} // while( iter.hasNext() ) end
		
		//DB 연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 번호 생성
		int idx = inquireDao.selectBoardIdx(conn);
		
		//게시글 정보 삽입
		inquire.setIdx(idx);
		
		if(inquire.getInquireTitle()==null || "".equals(inquire.getInquireTitle())) {
			inquire.setInquireTitle("(제목 없음)");
		}
		inquire.setUsernick( (String)req.getSession().getAttribute("user_nick") );
		inquire.setUserno(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_no"))));
		
		if( inquireDao.insertInquire(conn, inquire) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		//첨부파일 정보 삽입
		if( inquireFile.getFileSize() != 0 ) {
			inquireFile.setIdx(idx);
			
			if( inquireDao.insertFile(conn, inquireFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}
	
	
	@Override
	public void writeAnswerText(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//--- 첨부파일없이 게시글 처리하기 ---
		Inquire inquire = new Inquire();
		
		//게시글 번호 생성
		int idx = inquireDao.selectAnswerIdx(conn);
		
		//게시글 번호 삽입
		inquire.setIdx(idx);
		
		inquire.setUserno(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_no"))));

		inquire.setInquireTitle( req.getParameter("title") );
		inquire.setInquireContent( req.getParameter("content") );

		//작성자nick 처리
		inquire.setUsernick( (String) req.getSession().getAttribute("user_nick") );

		if(inquire.getInquireTitle()==null || "".equals(inquire.getInquireTitle())) {
			inquire.setInquireTitle("ㄴ(답변)");
		}

		if( inquireDao.insertInquire(conn, inquire) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}
	
	
	@Override
	public Inquire getIdx(HttpServletRequest req) {
		
		//전달파라미터 boardno를 저장할 DTO객체 생성
		Inquire idx = new Inquire();
				
		String param = req.getParameter("idx");
		
		if( param != null && !"".equals( param ) ) {
			idx.setIdx( Integer.parseInt(param) );
		
		} else {
			System.out.println("[WARN] NoticeService getIdx() - boardno값이 null이거나 비어있음");
		
		}

		return idx;
	}

	
	@Override
	public Inquire viewText(Inquire idx) {
	
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( inquireDao.updateHits(conn, idx) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Inquire inquire = inquireDao.selectInquireByIdx(conn, idx);
		
		return inquire;
	}
	
	
	@Override
	public InquireFile viewFile(Inquire viewInquireBoard) {
		return inquireDao.selectFile(JDBCTemplate.getConnection(), viewInquireBoard);
	}
	
	
	@Override
	public void updateText(HttpServletRequest req) {
		
		//파일 업로드 형식 인코딩이 맞는지 검사하기
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart/form-data형식이 아닐 경우 파일 업로드 처리 중단
		if( !isMultipart ) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);
		
		//서블릿 컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시 파일 폴더
		String path = context.getRealPath("tmp");
		File temRepository = new File(path);
		temRepository.mkdir();
		factory.setRepository(temRepository);
		
		//파일 업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);
		
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		//게시글 정보 DTO객체
		Inquire inquire = new Inquire();
		
		//첨부파일 정보 DTO객체
		InquireFile inquireFile = new InquireFile();
		
		
		//파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//1. 빈 파일에 대한 처리
			if( item.getSize() <= 0 ) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			
			//2. 폼 필드에 대한 처리
			if( item.isFormField() ) {
				
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key에 맞게 value를 DTO에 삽입
				if( "idx".equals(key) ) {
					inquire.setIdx( Integer.parseInt(value) );
				
				} else if( "title".equals(key) ) {
					inquire.setInquireTitle(value);
					
				} else if ( "content".equals(key) ) {
					inquire.setInquireContent(value);
					
				}
				
			} //if( item.isFormField() ) end
			
			
			//3. 파일에 대한 처리
			if( !item.isFormField() ) {
				
				//UUID 생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();
				
				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File( uploadFolder, stored );
				
				try {
					item.write(up); //임시 파일 -> 실제 업로드 파일
					item.delete(); // 임시 파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				inquireFile.setFileOri(origin);
				inquireFile.setFileSto(stored);
				inquireFile.setFileSize( (int)item.getSize() );
			
			} // if( !item.isFormField() ) end
			
		} // while( iter.hasNext() ) end
		
		//DB 연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		
		//게시글 정보 삽입
		if(inquire.getInquireTitle()==null || "".equals(inquire.getInquireTitle())) {
			inquire.setInquireTitle("(제목 없음)");
		}	
		if( inquireDao.updateInquire(conn, inquire) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		//첨부파일 정보 삽입
		if( inquireFile.getFileSize() != 0 ) {
			inquireFile.setIdx(inquire.getIdx());
			
			if( inquireDao.insertFile(conn, inquireFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}
	
	
	@Override
	public String getNick(Inquire viewInquireBoard) {
		return inquireDao.selectNickByUserNo(JDBCTemplate.getConnection(), viewInquireBoard);
		
	}
	
	@Override
	public void deleteText(Inquire inquire) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( inquireDao.deleteFile(conn, inquire) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		if( inquireDao.deleteComm(conn, inquire) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( inquireDao.deleteInquire(conn, inquire) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public List<Inquire> viewListSearchingT(Paging paging) {
		return inquireDao.searchTitleInquireList(JDBCTemplate.getConnection(), paging);
	}

	
	@Override
	public List<Inquire> viewListSearchingC(Paging paging) {
		return inquireDao.searchContentInquireList(JDBCTemplate.getConnection(), paging);
	}

	
	@Override
	public List<Inquire> viewListSearchingN(Paging paging) {
		return inquireDao.searchUsernickInquireList(JDBCTemplate.getConnection(), paging);
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
			System.out.println("[WARNNING] noticeService getSearchPaging - curPage값이 null이거나 공백");
		}

		//총 게시글 수 조회하기
		int totalCount = inquireDao.selectCntTitleSearching( JDBCTemplate.getConnection(), data );
	
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
		return searchPaging;

	}
		
	@Override
	public Paging getSearchPagingC(HttpServletRequest req) {
			
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
			System.out.println("[WARNNING] noticeService getSearchPaging - curPage값이 null이거나 공백");
		}

		//총 게시글 수 조회하기
		int totalCount = inquireDao.selectCntContentSearching( JDBCTemplate.getConnection(), data );
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
				
		return searchPaging;
	
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
			System.out.println("[WARNNING] noticeService getSearchPaging - curPage값이 null이거나 공백");
		}
			
		//총 게시글 수 조회하기
		int totalCount = inquireDao.selectCntUsernickSearching( JDBCTemplate.getConnection(), data );
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
					
		return searchPaging;		
				
	}

	@Override
	public List<Inquire> inquireBoardList() {
		return inquireDao.selectAllList(JDBCTemplate.getConnection());
	}
	
	
}
