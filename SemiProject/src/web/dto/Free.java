package web.dto;

import java.util.Date;

public class Free {
	
	private int idx;
	private String freeTitle;
	private String freeContent;
	private int userno;
	private String usernick;
	private int freeHits;
	private Date createDate;
	
	
	@Override
	public String toString() {
		return "FreeDTO [idx=" + idx + ", freeTitle=" + freeTitle + ", freeContent=" + freeContent + ", userno="
				+ userno + ", usernick=" + usernick + ", freeHits=" + freeHits + ", createDate=" + createDate + "]";
	}
	
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getFreeTitle() {
		return freeTitle;
	}
	
	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}
	
	public String getFreeContent() {
		return freeContent;
	}
	
	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
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
	
	public int getFreeHits() {
		return freeHits;
	}
	
	public void setFreeHits(int freeHits) {
		this.freeHits = freeHits;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
