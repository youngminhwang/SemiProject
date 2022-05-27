package web.dto;

import java.util.Date;

public class NoticeComm {
	private int idxComm; //댓글 번호(PK)
	private int idx; //게시글 번호(FK) - FreeDTO
	private int userno; //유저 번호(FK) - UserDTO
	private String usernick; //유저 닉네임
	private String commContent; //리뷰 내용
	private Date commDate; //댓글 등록 날짜
	

	@Override
	public String toString() {
		return "FreeComm [idxComm=" + idxComm + ", idx=" + idx + ", commContent=" + commContent + ", userNo=" + userno
				+ ", commDate=" + commDate + ", usernick=" + usernick + "]";
	}
	
	public int getIdxComm() {
		return idxComm;
	}
	
	public void setIdxComm(int idxComm) {
		this.idxComm = idxComm;
	}
	
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getCommContent() {
		return commContent;
	}
	
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	
	public int getUserno() {
		return userno;
	}
	
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	public Date getCommDate() {
		return commDate;
	}
	
	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}
	
	public String getUsernick() {
		return usernick;
	}
	
	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

}
