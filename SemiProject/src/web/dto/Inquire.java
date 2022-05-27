package web.dto;

import java.util.Date;

public class Inquire {
	
	private int idx;
	private String inquireTitle;
	private String inquireContent;
	private int userno;
	private String usernick;
	private int inquireHits;
	private Date createDate;
	
	
	@Override
	public String toString() {
		return "Inquire [idx=" + idx + ", inquireTitle=" + inquireTitle + ", inquireContent=" + inquireContent
				+ ", userno=" + userno + ", usernick=" + usernick + ", inquireHits=" + inquireHits + ", createDate="
				+ createDate + "]";
	}

	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getInquireTitle() {
		return inquireTitle;
	}
	
	public void setInquireTitle(String inquireTitle) {
		this.inquireTitle = inquireTitle;
	}
	
	public String getInquireContent() {
		return inquireContent;
	}
	
	public void setInquireContent(String inquireContent) {
		this.inquireContent = inquireContent;
	}
	
	public int getUserno() {
		return userno;
	}
	
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	public String getUsernick() {
		return usernick;
	}
	
	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}
	
	public int getInquireHits() {
		return inquireHits;
	}
	
	public void setInquireHits(int inquireHits) {
		this.inquireHits = inquireHits;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
