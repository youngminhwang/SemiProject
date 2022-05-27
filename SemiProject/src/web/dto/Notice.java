package web.dto;

import java.util.Date;

public class Notice {
	
	private int idx;
	private String noticeTitle;
	private String noticeContent;
	private int userno;
	private String usernick;
	private int noticeHits;
	private Date createDate;
	
	
	@Override
	public String toString() {
		return "Notice [idx=" + idx + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent + ", userno="
				+ userno + ", usernick=" + usernick + ", noticeHits=" + noticeHits + ", createDate=" + createDate + "]";
	}
	
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getNoticeTitle() {
		return noticeTitle;
	}
	
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
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
	
	public int getNoticeHits() {
		return noticeHits;
	}
	
	public void setNoticeHits(int noticeHits) {
		this.noticeHits = noticeHits;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
