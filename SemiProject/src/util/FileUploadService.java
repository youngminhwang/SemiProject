package util;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadService {

	FileUploadAndDtoSet dtoReady = new FileUploadAndDtoSet();
	
	public void fileUploadService(HttpServletRequest req, HttpServletResponse resp) {
		
		HashMap<String, Object> dtoMap = new HashMap<>();
		
		dtoMap = dtoReady.fileUploadAndDtoSet(req, resp);
		
		//Form_Field_data를 저장할 DTO객체 -> 1개만 필요
		FormFieldData formFieldData = new FormFieldData();
		
		//여러개의 파일 정보를 저장할 ArrayList_DTO객체 -> 여러개가 필요
		ArrayList<UploadFileData> uploadFileDataList = new ArrayList<UploadFileData>();
		
		formFieldData = (FormFieldData)dtoMap.get("formFieldData");
		
//		----------------------------------------------------------------------------
		
		//상황에 따라 수정할 Code
		uploadFileDataList.add(0, (UploadFileData)dtoMap.get("0"));
//		uploadFileDataList.add(1, (UploadFileData)dtoMap.get("1"));
//		...
		
		//Session 객체에서 ID 가져온 후, 해당 ID로 회원정보를 가져온다.
		//게시글번호를 가져온다.
		
		//uploadFileData_DTO를 Set (회원번호, 게시글번호) 
//		uploadFileDataList.get(0).setMemberNo(회원번호);
//		uploadFileDataList.get(0).setPostNo(게시글번호);

//		uploadFileDataList.get(1).setMemberNo(회원번호);
//		uploadFileDataList.get(1).setPostNo(게시글번호);
//		...
		
//		----------------------------------------------------------------------------

		//Setting된 DTO를 DAO처리
		
//		int[] chkArr = new int[DAO Method 개수];
//		
//		if( dao.insertBoard() ) { 
//			chkArr[0] = 1;
//		}else {
//			chkArr[0] = 0;
//		}
//		
//		if( dao.insertFile(uploadFileDataList.get(0)) ) { 
//			chkArr[1] = 1;
//		}else {
//			chkArr[1] = 0;
//		}
//		
//		if( dao.insertFile(uploadFileDataList.get(1)) ) { 
//			chkArr[2] = 1;
//		}else {
//			chkArr[2] = 0;
//		}
//		
//		...
//		
//		//전체 유효성 검사 
//		Boolean totalChk = false;
//	
//		for(int i = 0; i < chkArr.length; i++){ 
//			if(chkArr[i] == 0) { 
//				totalChk = true;
//			} 
//		} 
//		
//		if(totalChk) { 	 
//			commit();
//		}else {
//			rollback();
//		}
			
	}//fileUploadService() end
	
}//FileUploadService 클래스 end
