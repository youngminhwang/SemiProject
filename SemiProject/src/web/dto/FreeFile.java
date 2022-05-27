package web.dto;

import java.util.Date;

public class FreeFile {
	
	private int fileno;
	private int idx;
	private String fileOri;
	private String fileSto;
	private int fileSize;
	private Date createDate;
	
	
	@Override
	public String toString() {
		return "FreeFile [fileno=" + fileno + ", idx=" + idx + ", fileOri=" + fileOri + ", fileSto=" + fileSto
				+ ", fileSize=" + fileSize + ", createDate=" + createDate + "]";
	}
	
	public int getFileno() {
		return fileno;
	}
	
	public void setFileno(int fileno) {
		this.fileno = fileno;
	}
	
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getFileOri() {
		return fileOri;
	}
	
	public void setFileOri(String fileOri) {
		this.fileOri = fileOri;
	}
	
	public String getFileSto() {
		return fileSto;
	}
	
	public void setFileSto(String fileSto) {
		this.fileSto = fileSto;
	}
	
	public int getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
