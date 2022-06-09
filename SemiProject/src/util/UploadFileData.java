package util;

public class UploadFileData {

	private int fileNo;
	private String originName;
	private String storedName;
	
	public UploadFileData() {
		
	}
	
	public UploadFileData(int paramFileNo, String paramOriginName, String paramStoredName) {
		fileNo = paramFileNo;
		originName = paramOriginName;
		storedName = paramStoredName;
	}

	@Override
	public String toString() {
		return "UploadFileData [fileNo=" + fileNo + ", originName=" + originName + ", storedName=" + storedName + "]";
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

}
