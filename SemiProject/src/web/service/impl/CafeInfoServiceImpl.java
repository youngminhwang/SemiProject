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
import web.dao.face.CafeInfoDao;
import web.dao.impl.CafeInfoDaoImpl;
import web.dto.CafeReviewFile;
import web.dto.CafeTag;
import web.dto.CafeFile;
import web.dto.CafeInfo;
import web.dto.CafeReview;
import web.dto.UserInfo;
import web.service.face.CafeInfoService;

public class CafeInfoServiceImpl implements CafeInfoService {
	CafeInfoDao cafeInfoDao = new CafeInfoDaoImpl();
	
	
	
	@Override
	public CafeInfo getCafeno(HttpServletRequest req) {
		
		//전달파라미터 boardno를 저장할 DTO객체 생성
		CafeInfo cafeno = new CafeInfo();
		
		
		String param = req.getParameter("cafeinfo");
		if( param != null && !"".equals( param ) ) {
			cafeno.setCafe_no( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] BoardService getBoardno() - boardno값이 null이거나 비어있음");
		}

		return cafeno;
	}

	@Override
	public CafeInfo view(CafeInfo cafeno) {
		
		CafeInfo cafeinfo=cafeInfoDao.cafeview(JDBCTemplate.getConnection(), cafeno);
		
		
		return cafeinfo;
				
	}
	@Override
	public Paging getPaging(HttpServletRequest req,CafeInfo cafeno) {
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
//			System.out.println("[WARN] CafeService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = cafeInfoDao.selectCntAll(JDBCTemplate.getConnection(),cafeno);
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
//			System.out.println("[WARN] CafeService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = cafeInfoDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	@Override
	public List<CafeInfo> getList(Paging paging) {
		return cafeInfoDao.selectAll( JDBCTemplate.getConnection(), paging );
	}

	@Override
	public List<CafeReview> getreviewlist(Paging paging) {
		
		return cafeInfoDao.selectreview(JDBCTemplate.getConnection(),paging);
	}
	

	@Override
	public CafeReview getReviewno(HttpServletRequest req) {
			
		String param = req.getParameter("reviewno");
		
		CafeReview review = new CafeReview();
		
		if(param!=null && !"".equals(param)) {
			review.setReview_no(Integer.valueOf(param));
		}else {
			System.out.println("[에러]CafeInfoServiceImpl.java getReviewno 값이 안들어옴");
		}
		review = cafeInfoDao.getReviewInfo(JDBCTemplate.getConnection(), review);
		
		return review;
	}
	
	@Override
	public void reviewjoin(CafeReview cafereview,CafeInfo cafeno) {
		Connection conn = JDBCTemplate.getConnection();
		UserInfo user=new UserInfo();
		if(cafeInfoDao.reviewInsert(conn,cafeno,cafereview,user)>0) {
			System.out.println("리뷰 등록 완료");
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			System.out.println("리뷰 등록 실패");
		}
		//---------------------------------------------------
		
	}
	
	@Override
	public CafeReview write(HttpServletRequest req) {
		//파일업로드 형식 인코딩이 맞는지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		
		if(!isMultipart) {
			System.out.println("[Error파일 업로드 형식 데이터 아님");
			
		}
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//메모리에서 처리 사이즈 설정
		int maxMem = 1*1024*1024; //1MB ==1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);
		//파일 업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile=10*1024*1024;
		upload.setFileSizeMax(maxFile);
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items=null;
		
		try {
			items=upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//유저 정보 DTO 객체
		UserInfo user = new UserInfo();
		//카페리뷰 정보 DTO 객체		
		CafeReview review=new CafeReview();
		//카페리뷰 파일 정보 DTO 객체
		CafeReviewFile cafeFile =new CafeReviewFile();
		
		//파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while(iter.hasNext()) {
			FileItem item = iter.next();
			//빈 파일에 대한 처리
			if(item.getSize()<=0) {
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			
			//폼 필드에 대한 처리
			if(item.isFormField()) {
				//키 추출
				String key=item.getFieldName();
				//값 추출
				String value=null;
				try {
					value=item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				//key에 맞게 value를 DTO에 삽입
				if("content".equals(key)) {
					review.setReview_cont(value);
				}else if("star".equals(key)) {
					review.setStar_score(Integer.valueOf(value));	
				}else if("cafeinfo".equals(key)) {
					review.setCafe_no(Integer.valueOf(value));
				}
			}
			//파일에 대한 처리
			if(!item.isFormField()) {
				//UUId생성
				String uid = UUID.randomUUID().toString().split("-")[0];
				//파일 업로드 폴더
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir();
				//파읾명 처리
				String origin=item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성
				File up = new File(uploadFolder,stored);
				try {
					item.write(up); //임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				cafeFile.setRvw_org_file_name(origin);
				cafeFile.setRvw_cpy_file_name(stored);
				cafeFile.setFilesize((int) item.getSize());
			}
		}
		//DB연결
		Connection conn =JDBCTemplate.getConnection();
		
		//리뷰/파일 넘버 생성
		int reviewfileno = cafeInfoDao.reviewfileno(conn);
		int reviewno =cafeInfoDao.reviewno(conn);
		
		cafeFile.setRvw_file_no(reviewfileno);
		review.setReview_no(reviewno);
		
		user.setUser_id((String) req.getSession().getAttribute("user_id"));
		user.setUser_no(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_no"))));
		user=cafeInfoDao.getuserInfo(user,JDBCTemplate.getConnection());
		
		if(cafeInfoDao.reviewInsert(conn, review, user)>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		if(cafeFile.getFilesize()!=0) {
			cafeFile.setReview_no(reviewno);
						
			cafeFile.setCafe_no(review.getCafe_no());
			if(cafeInfoDao.insertFile(conn,cafeFile)>0) {
				System.out.println("파일 업로드 성공");
				JDBCTemplate.commit(conn);
			}else {
				System.out.println("파일 업로드 실패");
				JDBCTemplate.rollback(conn);
			}	
			
		}
		return review;
	}
	
	@Override
	public List<CafeReviewFile> getFileno(HttpServletRequest req, CafeReviewFile cafeFile) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<CafeReviewFile> cafereviewfile = cafeInfoDao.selectfile(conn, cafeFile);
		
		return cafereviewfile;
	}
	
	
	@Override
	public void reviewdelete(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		CafeReview review = new CafeReview();
		review.setReview_no(Integer.valueOf((req.getParameter("reviewno"))));
		
		
		if(cafeInfoDao.reviewfiledelete(conn, review)>0) {
			JDBCTemplate.commit(conn);
			System.out.println("삭제 성공");
		}else {
			JDBCTemplate.rollback(conn);
			System.out.println("삭제 실패");
		}
		if(cafeInfoDao.reviewdelete(conn, review)>0) {
			JDBCTemplate.commit(conn);
			System.out.println("삭제 성공");
		}else {
			JDBCTemplate.rollback(conn);
			System.out.println("삭제 실패");
		}
		
		
	}
	@Override
	public CafeReview getrCafeno(HttpServletRequest req) {
		return cafeInfoDao.getrCafeno(JDBCTemplate.getConnection(),req);
		
	}
	@Override
	public List<CafeFile> getcafeFile(CafeInfo cafeno) {
		List<CafeFile> cafefile = cafeInfoDao.selectCafeFile(JDBCTemplate.getConnection(),cafeno);
		return cafefile;
	}

	@Override
	public List<CafeTag> getTagList(CafeInfo cafeno) {
		List<CafeTag> cafetag = cafeInfoDao.selectCafeTag(JDBCTemplate.getConnection(),cafeno);
		return cafetag;
	}
	@Override
	public CafeReview reviewUpdate(HttpServletRequest req) {
		
		return null;
	}
	
	@Override
	public CafeReview getReviewInfo(HttpServletRequest req) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		UserInfo user=new UserInfo();
		
		if(!isMultipart) {
			System.out.println("[Error파일 업로드 형식 데이터 아님");
			return null;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//메모리에서 처리 사이즈 설정 
		int maxMem = 1*1024*1024;
		factory.setSizeThreshold(maxMem);
		//서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();
		//임시 파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);
		
		//파일 업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile=10*1024*1024; // 10MB
		upload.setFileSizeMax(maxFile);
		
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items=null;
		try {
			items=upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//리뷰 정보 DTO 객체
		CafeReview review=new CafeReview();
		
		//리뷰 사진 DTO 객체
		CafeReviewFile cafeFile =new CafeReviewFile();
		
		
		//파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			// 빈 파일에 대한 처리
			if(item.getSize()<=0) {
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			//폼 필드에 대한 처리
			if(item.isFormField()) {
				//키 추출하기
				String key=item.getFieldName();
				
				//값 추출하기
				String value=null;
				try {
					value=item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				//key에 맞게 value를 DTO에 삽입
				if("content".equals(key)) {
					review.setReview_cont(value);
				}else if("star".equals(key)) {
					review.setStar_score(Integer.valueOf(value));	
				}else if("cafeinfo".equals(key)) {
					review.setCafe_no(Integer.valueOf(value));
				}else if("reviewno".equals(key)){
					review.setReview_no(Integer.valueOf(value));
				}
				else if("fileno".equals(key)) {
					cafeFile.setRvw_file_no(Integer.valueOf(value));
				}
			}
		
			//파일에 대한 처리
			if(!item.isFormField()) {
				
				//UUID 생성
				String uid = UUID.randomUUID().toString().split("-")[0];
				//파일 업로드 폴더
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir();
				//파일명 처리
				String origin=item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File(uploadFolder,stored);
				
				try {
					item.write(up); //임시파일 -> 실제 업로드 파일
					item.delete(); //임시파일 제거
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				cafeFile.setRvw_org_file_name(origin);
				cafeFile.setRvw_cpy_file_name(stored);
				cafeFile.setFilesize((int) item.getSize());
			}
		}
		//DB연결
		Connection conn =JDBCTemplate.getConnection();
		//리뷰 파일 넘버
//		int reviewfileno = cafeInfoDao.reviewfileno(conn);
		//리뷰 넘버
//		int reviewno =cafeInfoDao.reviewno(conn);
		
		//카페 리뷰 파일 넘버 받기 
//		cafeFile.setCafe_file_no(reviewfileno);
		
//		review.setReview_no(reviewno);
		//로그인한 닉네임
		review.setUser_nick((String) req.getSession().getAttribute("user_id"));
		//로그인한 유저번호
		user.setUser_no(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_no"))));
		//리뷰 정보 삽입
		if(cafeInfoDao.reviewUpdate(conn, review)>0) {
			System.out.println("변경 성공");
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			System.out.println("변경 실패");
		}
		//리뷰 사진 정보 삽입
		if(cafeFile.getFilesize()!=0) {
			//리뷰넘버 리뷰 파일에 삽입
			cafeFile.setReview_no(review.getReview_no());
			//카페 넘버 리뷰 파일에 삽입 
			cafeFile.setCafe_no(review.getCafe_no());
			System.out.println(cafeFile+"asdasdasdasddsas");
			if(cafeInfoDao.updateFile(conn, cafeFile)>0) {
				System.out.println("파일 업로드 성공");
				JDBCTemplate.commit(conn);
			}else {
				System.out.println("파일 업로드 실패");
				JDBCTemplate.rollback(conn);
			}	
			
		}
		return review;
	
	}
	@Override
	public UserInfo getUserInfoNo(Object object) {
		UserInfo user= new UserInfo();
		user.setUser_id((String) object);
		
		return cafeInfoDao.getuserInfo(user, JDBCTemplate.getConnection());
	}
	@Override
	public boolean getRcm(HttpServletRequest req, CafeInfo cafeinfo,UserInfo userno) {
		if(cafeInfoDao.getRcm(userno,cafeinfo,JDBCTemplate.getConnection())>0) {
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public void deleteRcm(CafeInfo cafeinfo,UserInfo userno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		if(cafeInfoDao.deleteRcm(userno,cafeinfo,conn)>0) {
			
			JDBCTemplate.commit(conn);
		}else {
			
			JDBCTemplate.rollback(conn);
		}
		
		
		
	}
	@Override
	public void insertRcm(CafeInfo cafeinfo,UserInfo userno) {
		
		Connection conn = JDBCTemplate.getConnection();
				
		if(cafeInfoDao.insertRcm(userno,cafeinfo,conn)>0) {
			
			JDBCTemplate.commit(conn);
			
		}else {
			
			JDBCTemplate.rollback(conn);
		}
		
	}
	@Override
	public void updateRcm( CafeInfo cafeinfo) {
		Connection conn = JDBCTemplate.getConnection();
		if(cafeInfoDao.updateRcm(cafeinfo,conn)>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	@Override
	public int getUserRcm(int userno,CafeInfo cafeno) {
		
		System.out.println("CafeInfo =" +userno);
		
		return cafeInfoDao.getuserRcm(userno, cafeno,JDBCTemplate.getConnection());
	}
	
	@Override
	public List<CafeReview> getreviewlist() {
		
		
		return cafeInfoDao.selectreview(JDBCTemplate.getConnection());
	}
	
	@Override
	public float getstarAvg(List<CafeReview> cafereviewList, CafeInfo cafeno) {
		
		return cafeInfoDao.getStarAvg(JDBCTemplate.getConnection(),cafeno);
	}
}




























