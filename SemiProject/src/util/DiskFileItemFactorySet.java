package util;

import java.io.File;


import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class DiskFileItemFactorySet {
	
	public DiskFileItemFactory fileUploadReady(HttpServletRequest req, HttpServletResponse resp) {
		
		//오류상황 발생 시, 이에 대한 HTML 응답을, Controller가 아니라 현재 Method에서 직접 하기 위한 설정
		
		//응답 data 형식 설정
		resp.setContentType("text/html;charset=utf-8");
		
		//응답 출력 스트림 객체_Handler 
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();	//text_기록_작업_기능이 구현된 Method를 이용하는 응답 출력 스트림 객체를 생성한 후, Handler 장착 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//응답 출력 스트림 객체의 println()를 사용하여, 응답 출력 스트림에 text_기록_작업을 수행할 수 있다.
		//out.println("<script>");
		
//		out.println("<h1>응답 출력 스트림 테스트</h1>");
//		out.println("<h3>Web_Browser에 보여지는지 확인</h3>");
		
		//MVC 패턴을 지켜야 할 경우에는 위의 로직을 오류상황 발생 시 오류상황 응답 전용 컨트롤러로 return
	
//		--------------------------------------------------------
		
		// 파일업로드 기능 구현 Code
		
		//1. <form>의 인코딩타입이 파일업로드형식(= multipart/form-data)이 맞는지 검사
		//	-> Client 측 요청_메시지(= HttpServletRequest_객체)의 content-type이 multipart/form-data가 맞는지 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);	//req = HttpServletRequest_객체_Handler
		// isMultipart == true : multipart/form-data 형식 O
		// isMultipart == false : multipart/form-data 형식 X
		
		//1-1. multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if( !isMultipart ) {
			out.println("<h1>파일 업로드 처리 에러</h1>");
			out.println("<h3 style='color: red;'>인코딩이 multipart/form-data가 아닙니다</h3>");
			out.println("<a href='/commons/fileupload'>업로드 폼으로 이동하기</a>");
			
			//Client 측 요청에 대한 응답을 수행했더라도 return_Code가 실행되지 않는 한, Method의 Java_Code_실행은 중단되지 않는다.
			// = Client 측 요청에 대한 응답을 수행하는 기능도 결국 해당 Method에 구현된 기능의 일부분일 뿐이다.
			//즉, 해당 Method의 기능 중, Client 측 요청에 대한 응답을 수행하는 기능만을 사용한 후, Java_Code_실행을 중단시키기 위해서는,
			//Client 측 요청에 대한 응답을 수행한 후, return_Code를 작성해야 한다.  
			
			return null;
		}
		
		//1-2. multipart/form-data 형식이 맞을 경우 파일업로드 처리를 정상적으로 수행
		System.out.println("[OK] multipart/form-data 형식이 맞음");
		
		//--------------------------------------------------------

		//	파일업로드 처리 Code
		
		//2. 업로드 된 파일을 처리하는 방법을 설정하는 객체 생성
		
		// FileItem 클래스
		// 보유 기능(= Method) : Client 측 요청_data(= 모든 <input>의 data(File 포함) 즉, Form_Field)를 객체화(= Field화)
		
		// FileItemFactory 클래스 : FileItem 클래스의 객체화(= 인스턴스화) 시 사용되는 Factory 클래스
		// Factory 클래스 : 특정 클래스의 객체화(= 인스턴스화) 시, 생성될 객체(= 인스턴스)에 적용될(= 필요한) 설정 항목(= Kategorie)을 정리(= 클래스화)한 표준 틀 
		
		// DiskFileItemFactory 클래스 : HDD 기반으로 FileItem 클래스를 객체화(= 인스턴스화)할 때 사용되는 Factory 클래스
		// HDD 기반으로 FileItem 클래스를 객체화(= 인스턴스화) : 업로드 된 파일을 HDD에 임시 저장하여 처리하도록 설정
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//--------------------------------------------------------

		//3. 업로드 된 파일이 저장될 Field의 용량(= 크기)을 정함
		int maxMem = 1 * 1024 * 1024; // 1 MB == 1048576 Byte
		
		//3-1. 업로드 된 파일이 앞서 정해준 용량(= 크기)보다 작을 경우, 메모리에서 처리하도록 설정 
		factory.setSizeThreshold(maxMem);
		
		//--------------------------------------------------------

		//4. 업로드 된 파일이 앞서 정해준 용량(= 크기)보다 클 경우, HDD에 임시 저장하여(= HDD에 임시파일을 생성) 처리
		//HDD 내에 임시파일이 저장될 폴더를 설정(= 폴더의 경로 지정)할 수 있다.
		//Client는 Server 측에 요청하는 것이기 때문에, 폴더의 경로는 개발자 컴퓨터 측(= Eclipse) HDD 경로가 아닌, 반드시 Server 측 HDD 경로여야 한다.
		
		//ServletContext 객체를 통해, 요청을 받은 서블릿의 정보를 확인할 수 있다. (JSP의 변수 영역 컨텍스트와는 별개다.)
		//ServletContext 객체는 요청 객체의 getServletContext()를 호출하여 반환받을 수 있다.
		// = 서블릿은 요청 URL과 매핑돼있기 때문에, 요청 URL이 달라질 때마다 ServletContext 객체도 달라진다. 
		ServletContext context = req.getServletContext();	//요청 객체의 getServletContext()를 호출하여 현재 요청 URL과 매핑돼있는 ServletContext 객체를 반환받은(= 생성한) 후, Handler 장착  
		
		//Server에 배포된 서블릿의 실제 경로가, 업로드 된 파일이 Server 측에서 임시 저장될 폴더(= tmp(= Temporary))의 경로로써 사용된다.
		String path = context.getRealPath("tmp");	//ServletContext 객체의 getRealPath()를 호출하여 Server에 배포된 서블릿의 실제 경로를 반환받는다.
		// tmp : Server에 배포된 서블릿의 실제 경로를 반환받기 위해 요구되는 조건으로써 임의로 작성한 가상폴더명(= getRealPath()를 호출하기 위해 요구되는 실인자로써 임의로 작성한 String형_data) 
		System.out.println("FileUploadService fileupload() - tmp : " + path);
		// 파일탐색기에 path를 검색해도 tmp는 현재 생성되지 않은 가상폴더명이기 때문에 찾을 수 없다.  
		// = 즉, 해당 path를, 업로드 된 파일이 Server 측에서 임시 저장될 폴더(= tmp(= Temporary))의 경로로써 사용하기 위해서는, 직접 tmp 폴더를 생성해야 한다.   
		
		//tmp 폴더를 직접 생성하지 않고, 필요한 때에 자동으로 생성되도록 설정하는 방법
		//해당 path를 이용하여 File 객체를 생성한 후, Handler 장착
		File tmpRepository = new File(path);
		
		//File 객체의 mkdir()를 호출하여 해당 path에 폴더 생성 - 이미 존재하면 생성하지 않음
		tmpRepository.mkdir();
		
		//DiskFileItemFactory의 설정 항목 중, 임시파일이 저장될 폴더 설정 항목에, 앞서 자동으로 생성된 폴더로 설정
		factory.setRepository(tmpRepository);
	
		return factory;
		
	}
	
}
