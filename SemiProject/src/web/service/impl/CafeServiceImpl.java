package web.service.impl;

import java.io.File;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
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


import web.dao.face.CafeDao;
import web.dao.face.TagDao;


import web.dao.impl.CafeDaoImpl;
import web.dao.impl.TagDaoImpl;
import web.dto.Cafe;
import web.dto.CafeFile;
import web.dto.CafeTag;
import web.dto.TagConn;
import web.service.face.CafeService;

public class CafeServiceImpl implements CafeService {

	private CafeDao cafeDao = new CafeDaoImpl();
	private TagDao tagDao = new TagDaoImpl();
	
	
	@Override
	public List<Cafe> getList() {
		return cafeDao.selectAll(JDBCTemplate.getConnection());
	}
	
	@Override
	public List<Cafe> getList(Paging paging) {

		//페이징 적용
		return cafeDao.selectAll(JDBCTemplate.getConnection(), paging);
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] CafeService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = cafeDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	
	@Override
	public Cafe getCafe_no(HttpServletRequest req) {
		
		//전달 파라미터 cafe_no를 저장할 DTO객체 생성
		Cafe cafe_no = new Cafe();
		
		//전달 파라미터를 String 타입에 저장
		String param = req.getParameter("cafe_no");

		//전달받은 param이 null값이 아니거나 비어있지 않다면 param을 int로 파싱해서 boardno에 넣기!
		//전달받은 param이 null값이거나 비어있다면 경고메시지 출력!
		if(param != null && !"".equals(param)) {
			cafe_no.setCafe_no(Integer.parseInt(param));
		} else {
			System.out.println("[WARRING] CafeService getCafe_no() - cafe_no값이 null이거나 비어있음");
		}
		
		//DTO 반환
		return cafe_no;
	}
	
	@Override
	public Cafe view(Cafe cafe_no) {

		Connection conn = JDBCTemplate.getConnection();
		
		
		//게시글 조회
		Cafe cafe = cafeDao.selectCafeByCafe_no(conn, cafe_no);
		
		
		return cafe;
	}


	@Override
	public void cafewrite(HttpServletRequest req) {
		
		//파일 업로드형식 인코딩 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) { 
			System.out.println("[ERROR] 파일 업로드 형식 아님 ");
			
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//업로드 파일 크기
		int maxMem = 1 * 1024 * 1024; //1MB
		factory.setSizeThreshold(maxMem);
		
		//서블릿 컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		
		//임시파일 저장 폴더 객체
		File tmpRepository = new File(path);
		
		System.out.println("경로 : " + path);
		
		String realpath = path;
		
		System.out.println("경로 : " + realpath);
		
		
		//폴더 생성
		tmpRepository.mkdir();
		
		//임시파일 저장 폴더를 factory객체에 설정
		factory.setRepository(tmpRepository);
		
		//파일 업로드를 수행하는 객체 설정
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; //10MB
		
		//파일 업로드 용량 제한 사이즈 설정
		upload.setFileSizeMax(maxFile);
		
		//파일 업로드 처리
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//게시글 정보 DTO객체
		Cafe cafe = new Cafe();
		
		//첨부파일 정보 DTO객체
		CafeFile cafeFile = new CafeFile();
		
		List<TagConn> tagConnList = new ArrayList<>();
		
		//파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		
		// -> 아래에 있던 거 올렸음
//		//DB 연결객체
		Connection conn = JDBCTemplate.getConnection();
		//카페 번호 생성
		int cafe_no = cafeDao.selectCafe_no(conn);

		
		while (iter.hasNext()) {
			
			FileItem item = iter.next();
			
			//빈파일에 대한 처리
			if(item.getSize() <= 0) {
				continue; //무시
			}
			//폼필드에 대한 처리
			if (item.isFormField()) {
				
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
				if ("cafe_name".equals(key)) {
					cafe.setCafe_name(value);
					
				} else if ("cafe_loc".equals(key)) {
					cafe.setCafe_loc(value);
					
				} else if ("cafe_menu".equals(key)) {
					cafe.setCafe_menu(value);
				
					
				} else if ("cafe_tel".equals(key)) {
					cafe.setCafe_tel(value);
					
				} else if ("cafe_time".equals(key)) {
					cafe.setCafe_time(value);
				
				} else if ("cafe_park".equals(key)) {
					cafe.setCafe_park(value);
				} else if ("tag_name".equals(key)) {
					System.out.println(value);
					
					tagConnList.add( new TagConn(Integer.parseInt(value), cafe_no) );
				}
				
			}//if(item.isFormFileld()) end
			
			//3. 파일에 대한 처리
			if ( !item.isFormField()) {
				
				//UUID 생성
				String uid = UUID.randomUUID().toString().split("-")[0];
				
				//파일 객체 생성
				File uploadFolder = new File(context.getRealPath("upload") );
				
				//실제 보관소 폴더 생성하기
				uploadFolder.mkdir();
				
				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				
				//업로드할 파일 객체 선택하기
				File up = new File(uploadFolder, stored);
				
				try {
					
					item.write(up);
					item.delete();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//업로드 된 파일의 정보를 DTO객체에 저장
				cafeFile.setCafe_org_file_name(origin);
				cafeFile.setCafe_cpy_file_name(stored);
				cafeFile.setCafe_cpy_size((int) item.getSize());
				
				//---
//				cafeFile.setCafe_cpy_ext(FilenameUtils.getExtension(cafeFile.getCafe_org_file_name()));
//				cafeFile.setCafe_cpy_path(realpath);
				
			}//if(!item.ifFormField()) end
		}//while end

		
		 //-> 위쪽으로 옮김
//		//DB 연결객체
//		Connection conn = JDBCTemplate.getConnection();
//		//카페 번호 생성
//		int cafe_no = cafeDao.selectCafe_no(conn);
		
		//게시글 정보 삽입
		cafe.setCafe_no(cafe_no);
		
		if(cafe.getCafe_name()==null || "".equals(cafe.getCafe_name())) {
			cafe.setCafe_name("(카페 이름)");

		} else if (cafe.getCafe_tel()==null || "".equals(cafe.getCafe_tel())) {
			cafe.setCafe_tel("(카페 phone)");

		} else if (cafe.getCafe_menu()==null || "".equals(cafe.getCafe_menu())) {
			cafe.setCafe_menu("(카페 menu)");
		
		} else if (cafe.getCafe_time()==null || "".equals(cafe.getCafe_time())) {
			cafe.setCafe_time("(카페 영업시간)");
		
		} else if (cafe.getCafe_park()==null || "".equals(cafe.getCafe_park())) {
			cafe.setCafe_park("(카페 주차 가능 유무)");
			
		} else if (cafe.getCafe_loc()==null || "".equals(cafe.getCafe_loc())) {
			cafe.setCafe_loc("(카페 주소)");
		}
			
			
		if(cafeDao.insert(conn, cafe) > 0) {
			JDBCTemplate.commit(conn);
		} else { 
			JDBCTemplate.rollback(conn);
		}
		
		//첨부파일 정보 삽입
		if (cafeFile.getCafe_cpy_size()!=0) {
			cafeFile.setCafe_no(cafe_no);
			
			if(cafeDao.insertFile(conn, cafeFile) > 0) {
					
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		int res = 0;
		//태그 연결 정보 삽입
		for( TagConn tagConn : tagConnList ) {
			tagConn.setCafeNo(cafe_no);
			
			res += tagDao.insertTagConn( conn, tagConn); //-> 구현하세요
		}

		if( res == tagConnList.size() ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		for( TagConn t : tagConnList) {
			System.out.println(t);
		}
		
		for(int i=0; i<tagConnList.size(); i++) {	
		System.out.println("리스트의 크기 :" +tagConnList.size());
		}
		
		for ( int i=0; i<tagConnList.size(); i++) { 
			
		}
		
		
		
	}//cafewrite() end
	
	
	@Override
	public void delete(Cafe cafe) {

		Connection conn = JDBCTemplate.getConnection();
		
		if( cafeDao.deleteCafeFile(conn, cafe) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if(cafeDao.deleteCafe(conn,cafe) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
	
	@Override
	public CafeFile viewcafeFile(Cafe viewCafe) {
		return cafeDao.selectCafeFile(JDBCTemplate.getConnection(), viewCafe);
	}
	
	@Override
	public CafeTag tagno(Cafe cafe_no) {

		Connection conn = JDBCTemplate.getConnection();
		
		Cafe cafe = new Cafe();
		
		cafe.setCafe_no(Integer.parseInt(String.valueOf(cafe_no.getCafe_no())));
		
		System.out.println("cafe=" + cafe);
		//태그번호 조회
		CafeTag cafetag = cafeDao.selectTag_noByCafe_no(conn, cafe);
		
		return cafetag;
	}

	@Override
	public Cafe update(HttpServletRequest req) {
		
		//파일 업로드형식 인코딩 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) { 
			System.out.println("[ERROR] 파일 업로드 형식 아님 ");
			
			
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//업로드 파일 크기
		int maxMem = 1 * 1024 * 1024; //1MB
		factory.setSizeThreshold(maxMem);
		
		//서블릿 컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		
		//임시파일 저장 폴더 객체
		File tmpRepository = new File(path);		
		

		//폴더 생성
		tmpRepository.mkdir();
		
		//임시파일 저장 폴더를 factory객체에 설정
		factory.setRepository(tmpRepository);
		
		//파일 업로드를 수행하는 객체 설정
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; //10MB
		
		//파일 업로드 용량 제한 사이즈 설정
		upload.setFileSizeMax(maxFile);
		
		//파일 업로드 처리
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//게시글 정보 DTO객체
		Cafe cafe = new Cafe();
		
		//첨부파일 정보 DTO객체
		CafeFile cafeFile = new CafeFile();
		
		List<TagConn> tagConnList = new ArrayList<>();
		
		//파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		
		// -> 아래에 있던 거 올렸음
//		//DB 연결객체
//		Connection conn = JDBCTemplate.getConnection();
		//카페 번호 생성
//		int cafe_no = cafeDao.selectFineCafe_no(conn);
//		int cafe_no =0;
		
//		int cafe_no = cafe.getCafe_no();
//		
//		System.out.println("얻어오는지 확인을 해보자 : " + cafe_no);
		
		
		while (iter.hasNext()) {
			
			FileItem item = iter.next();
			
			//빈파일에 대한 처리
			if(item.getSize() <= 0) {
				continue; //무시
			}
			//폼필드에 대한 처리
			if (item.isFormField()) {
				
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
				if ("cafe_name".equals(key)) {
					cafe.setCafe_name(value);
					
				} else if ("cafe_menu".equals(key)) {
					cafe.setCafe_menu(value);
				
				} else if ("cafe_loc".equals(key)) {
					cafe.setCafe_loc(value);
				
				} else if ("cafe_tel".equals(key)) {
					cafe.setCafe_tel(value);
					
				} else if ("cafe_time".equals(key)) {
					cafe.setCafe_time(value);
				
				}else if("cafe_no".equals(key)) {
					cafe.setCafe_no(Integer.valueOf(value));
				}else if ("cafe_park".equals(key)) {
					cafe.setCafe_park(value);
				} else if ("tag_name".equals(key)) {				
					tagConnList.add( new TagConn(Integer.parseInt(value), cafe.getCafe_no()) );
					
				}else if("fileno".equals(key)) {
					cafeFile.setCafe_file_no(Integer.valueOf(value));
				}
				
			}//if(item.isFormFileld()) end
//			System.out.println("cafeupdate"+cafe);
			//3. 파일에 대한 처리
			if ( !item.isFormField()) {
				
				//UUID 생성
				String uid = UUID.randomUUID().toString().split("-")[0];
				
				//파일 객체 생성
				File uploadFolder = new File(context.getRealPath("upload") );
				
				//실제 보관소 폴더 생성하기
				uploadFolder.mkdir();
				
				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				
				//업로드할 파일 객체 선택하기
				File up = new File(uploadFolder, stored);
				
				try {
					
					item.write(up);
					item.delete();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//업로드 된 파일의 정보를 DTO객체에 저장
				cafeFile.setCafe_org_file_name(origin);
				cafeFile.setCafe_cpy_file_name(stored);
				cafeFile.setCafe_cpy_size((int) item.getSize());
				
				//---

			}//if(!item.ifFormField()) end
		}//while end

		Connection conn = JDBCTemplate.getConnection();

		
		if(cafeDao.update(conn, cafe) > 0) {
		JDBCTemplate.commit(conn);
		} else { 
			JDBCTemplate.rollback(conn);
		}
		
		//첨부파일 정보 삽입
		if (cafeFile.getCafe_cpy_size()!=0) {
			cafeFile.setCafe_no(cafe.getCafe_no());
			
			if(cafeDao.updateFile(conn, cafeFile) > 0) {
					System.out.println("파일 업로드 성공");
				JDBCTemplate.commit(conn);
			} else {
				System.out.println("파일 업로드 실패" + cafeFile.getCafe_no());
				JDBCTemplate.rollback(conn);
			}
		}
		//태그 연결 정보 삽입
		if(tagConnList.size()>0) {
			if(tagDao.deleteTag(cafe,JDBCTemplate.getConnection())>0) {
				JDBCTemplate.commit(conn);
				System.out.println("성공");
				
			}else {
				JDBCTemplate.rollback(conn);
				System.out.println("실패");
			}
		}
		int res = 0;
	
		
		for( TagConn tagConn : tagConnList ) {
			
			tagConn.setCafeNo(cafe.getCafe_no());
			res += tagDao.insertTagConn( conn, tagConn); //-> 구현하세요
		}		
		if( res == tagConnList.size() ) {
			
			System.out.println("태그 수정 성공");
			JDBCTemplate.commit(conn);
		} else {
			System.out.println("태그 수정 실패");
			JDBCTemplate.rollback(conn);
		}
		
		
		for( TagConn t : tagConnList) {
			System.out.println(t);
	}
		
		return cafe;
		
		
		
	}//cafeupdate() end	
	   	
	
	@Override
	public List<Cafe> viewListSearchN(Paging paging) {
		return cafeDao.searchNameList(JDBCTemplate.getConnection(), paging);
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
			System.out.println("[WARNNING] cafeService getSearchPaging - curPage값이 null이거나 공백");
		}
		
		//총 게시글 수 조회하기
		int totalCount = cafeDao.selectCntNameSearch(JDBCTemplate.getConnection(), data);
		
		//Paging 객체 생성 - 페이징 계산
		Paging searchPaging = new Paging(totalCount, curPage, data);
				
		return searchPaging;
	}
	
	
	
	
}

