package util;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadAndDtoSet {
	
	DiskFileItemFactorySet factory = new DiskFileItemFactorySet();
	
	public HashMap<String, Object> fileUploadAndDtoSet(HttpServletRequest req, HttpServletResponse resp) {
		
		//1. 파일업로드 수행 객체 생성
		//factory 객체가 정상적으로 생성될 경우(= <form>의 인코딩타입이 파일업로드형식(= multipart/form-data)이 맞을 경우), 
		if( factory.fileUploadReady(req, resp) != null) {	
			
			//1-1. factory 객체를 이용하여 파일업로드 수행 객체를 생성한 후, Handler 장착
			ServletFileUpload upload = new ServletFileUpload( factory.fileUploadReady(req, resp) );	
			
			//파일의 최대 업로드 허용 사이즈를 정함
			int maxFile = 10 * 1024 * 1024; // = 10 MB
			
			//파일 업로드 용량 제한 사이즈 설정 : Server 과부화 방지
			upload.setFileSizeMax(maxFile);
			
			//파일의 최대 업로드 허용 사이즈를 초과할 시 예외 처리 로직 작성 필요
			
			//------------- 파일 업로드 준비 완료 -------------
			
			//2. 파일 업로드 처리 수행
			
			//FileItem 객체 전용 List 객체_Handler 생성
			List<FileItem> items = null;
			
			try {
				//요청 객체에 저장돼있는 전달파라미터 중, 파일을 업로드 하면서 
				//동시에 파일에 대한 정보와, Form_Field_data를 파싱(= 추출)하기 위해서는
				//ServletFileUpload 객체의 parseRequest()를 호출해야 한다.
				//호출 시, 요청 객체에 저장돼있는 전달파라미터(File 포함)가 파싱(= 추출)되어 FileItem형 객체로 객체화된 후, List형 객체로 반환된다.
				
				items = upload.parseRequest(req);	// Handler 장착
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			//전달 파라미터 추출데이터 확인
			for( FileItem item : items ) {
				System.out.println(item);
			}
			//--------------------------------------------------------
			
			//3. 파싱되어(= 추출되어) FileItem형 객체로 객체화된 후, List형 객체로 반환된 data 처리
			
			//	-> data의 3가지 상태
			//		1. 파일
			//			1-1. 빈 파일(= 용량이 0)일 경우,
			//		 	-> 처리 중단(= upload 무시) 
			//			이유 : 용량이 0인 빈 파일을 upload할 경우, 해당 파일과 관련된 data(= 파일에 관한 설명)를
			//			OS가 유지하기 위해서 약 4kB(= 부가적인 Overhead)를 사용하게 되고, 
			//			그로인해 Server는 해당 파일이 빈 파일이라는 것은 인지하지만, HDD의 용량은 4kB만큼 줄어들게 된다. 
			
			//			1-2. 빈 파일이 아닐 경우,
			//			-> DTO객체에 파일 정보를 저장해서 DB에 INSERT
						
			//		2. Form_Field_data
			//		 -> DTO객체에 저장해서 DB에 INSERT
			
			//Form_Field_data를 저장할 DTO객체 -> 1개만 필요
			FormFieldData formFieldData = new FormFieldData();
			
			//여러개의 파일 정보를 저장할 ArrayList_DTO객체 -> 여러개가 필요
			ArrayList<UploadFileData> uploadFileDataList = new ArrayList<UploadFileData>();
			
			//data 반복 처리
			Iterator<FileItem> iter = items.iterator();
			
			while( iter.hasNext() ) {
				
				//data를 하나씩 꺼내서 사용하기
				FileItem item = iter.next();
				
				//--- 1) 빈 파일에 대한 처리 ---
				if( item.getSize() <= 0 ) {
					
					//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
					continue;
				}
				
				//--- 2) 폼필드에 대한 처리 ---
				if( item.isFormField() ) {	//현재 꺼낸 data가 폼필드 data인 경우,
					
					//폼필드의 key=value 쌍을 추출하는 Method를 이용한다
					
					//	key는 item.getFieldName()를 호출하여 얻어온다
					//	value는 item.getString()를 호출하여 얻어온다
					
					//	req.setCharacterEncoding("UTF-8"); 명령문이 적용되지 않기 때문에, (문자열 구조가 유지된 상태가 아니기 때문에) 
					//	-> item.getString("UTF-8"); 명령문을 대신 사용해야 한다.
					
					//키 추출하기
					String key = item.getFieldName();
					
					//값 추출하기
					String value = null;
					try {
						 value = item.getString("UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					//----------------------------------------------------
					
					//상황에 따라 수정할 Code
					//key에 맞게 value를 DTO에 삽입
					if( "title".equals(key) ) {
						formFieldData.setTitle(value);
						
					} else if ( "data1".equals(key) ) {
						formFieldData.setData1(value);
						
					} else if ( "data2".equals(key) ) {
						formFieldData.setData2(value);
						
					}

					//----------------------------------------------------

				} //if( item.isFormField() ) end
							
				//--- 3) 파일에 대한 처리 ---
				if( !item.isFormField() ) {	//현재 꺼낸 data가 폼필드 data가 아닌 경우 = 파일일 경우,
					
					//업로드된 파일은, 파일명이 중복될 수 있는 경우를 방지하기 위해서
					//원본파일명(= origin_name)을 그대로 사용하지 않고, 
					//저장용파일명(= stored_name)을 따로 생성하여 사용한다. 
					//Ex) 원본파일명 + 숫자, 현재시간, UUID
					//DB는 Client에게 저장용파일명이 아니라 원본파일명으로 알려줘야 하기 때문에
					//원본파일명과 저장용파일명 둘 다 저장한다.
					//사용처의 구분을 위해서 상황에 따라 회원번호, 게시판번호, 대/댓글번호가 DB에 추가된다. 
					
					//저장용파일명(= stored_name)으로 현재시간을 사용할 경우, 
					//이대로는 확률적으로 중복될 가능성이 있기 때문에, 저장용파일명에
					//파일을 업로드한 대상의 ID를 추가하고,
					//생성한 저장용파일명을 사용하기 전에, DB에서 해당 파일명이 이미 존재하는지 확인하고, 
					//이미 존재하면 현재시간을 변경하는 반복문 로직이 추가되야 한다.
					
					//현재시간_data가 저장되는 객체 생성 : new Date() -> Date형 현재시간_data
					//Date형 data -> String형 data로 변환하기 위해 필요한 Method : SimpleDateFormat형 객체의 format() 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
					// -> 현재시간을 표현할 Format(= 형식) : "yyyyMMddHHmmssS"
					
					//Date형 현재시간_data -> String형 현재시간_data로 변환
					String rename = sdf.format( new Date() );
					
					System.out.println("[TEST] 원본 파일명 : " + item.getName());
					System.out.println("[TEST] 저장할 파일명 : " + rename);
					
//					-------------------------------------------------------------------
					
					//임시 저장된 파일이 확정적으로 저장될 폴더를 자동으로 생성하기
					
					//요청 객체의 getServletContext()를 호출하여 현재 요청 URL과 매핑돼있는 ServletContext 객체를 반환받은(= 생성한) 후, Handler 장착  
					ServletContext context = req.getServletContext();	
					
					//임시 저장된 파일을 실제 보관소(파일전용 HDD가 구비된 Server 또는 DB의 BLOB_컬럼)에 확정적으로 저장하기
					//파일이 확정적으로 저장될 폴더의 경로를 얻어오기
					String path = context.getRealPath("upload");	//upload : 파일이 확정적으로 저장될 폴더명
					
					//upload 폴더를 직접 생성하지 않고, 필요한 때에 자동으로 생성되도록 설정
					//해당 path를 이용하여 File 객체를 생성한 후, Handler 장착
					File uploadFolder = new File(path);
					
					//File형 객체의 mkdir()를 호출하여 해당 path에 자동으로 폴더 생성 - 이미 존재하면 생성하지 않음
					uploadFolder.mkdir();
					
//					-------------------------------------------------------------------

					//생성된 폴더에 파일 업로드하기
					
					//파일을 업로드 시켜줄 파일 객체를, 업로드될 경로와 파일명을 지정하여 생성한 후, Handler 장착
					File up = new File(uploadFolder, rename);
					
					try {
						item.write(up); // 임시로 업로드된 파일 -> 확정적으로 업로드를 진행하여 확정파일을 생성
						item.delete(); // 임시파일 제거
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
//					-----------------------------------------------------------
					
					//상황에 따라 수정할 Code
					//확정적으로 업로드된 파일의 정보를 DTO객체에 저장하기
					//업로드 경로 : upload_폴더를 고정적으로 사용 -> DTO에 저장할 필요가 없음
					
					//fileNo = file_TB의 시퀀스를 받아온다.
					int fileNo = 0;
					uploadFileDataList.add( new UploadFileData(fileNo, item.getName(), rename) );
					
//					-----------------------------------------------------------
					
				} //if( !item.isFormField() ) end
				
			} //while( iter.hasNext() ) end
			
			System.out.println("[DB정보] formFieldData : " + formFieldData);
			
			for( UploadFileData item : uploadFileDataList ) {
				System.out.println(String.format("[DB정보] uploadFileData : 파일번호:%d, 원본명:%s, 사본명:%s", item.getFileNo(), item.getOriginName(), item.getStoredName() ) );
			}
			
			HashMap<String, Object> resMap = new HashMap<>();
			
			resMap.put("formFieldData", formFieldData);
			
			for(int i = 0; i < uploadFileDataList.size(); i++ ) {
				
				String str = String.valueOf(i);
				
				resMap.put(str, uploadFileDataList);

			}
		     
			//resMap Check
			for (Entry<String, Object> entrySet : resMap.entrySet()) {            
				System.out.println(entrySet.getKey() + " : " + entrySet.getValue());        
			} 
		
			return resMap;
			
		} //if( factory.fileUploadReady(req, resp) != null) end
		else { 
		
			//파일업로드 진행 중, 오류상황 발생 시, 이에 대한 HTML 응답을, Controller가 아니라 현재 Method에서 직접 하기 위한 설정
			
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
			
//			out.println("<h1>응답 출력 스트림 테스트</h1>");
//			out.println("<h3>Web_Browser에 보여지는지 확인</h3>");
			
			//MVC 패턴을 지켜야 할 경우에는 위의 로직을 오류상황 발생 시 오류상황 응답 전용 컨트롤러로 return
		
//			--------------------------------------------------------
			
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
			
		}
		
		return null;
	
	}//fileUpload() end
		
}//FileUploadService 클래스 end
