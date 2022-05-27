package web.dto;

import java.sql.Date;

public class CafeReviewFile {
	private int rvw_file_no;
	private int review_no;
	private int cafe_no;
	private String rvw_org_file_name;
	private String rvw_cpy_file_name;
	private int filesize;
	private Date writedate;
	@Override
	public String toString() {
		return "CafeReviewFile [rvw_file_no=" + rvw_file_no + ", review_no=" + review_no + ", cafe_no=" + cafe_no
				+ ", rvw_org_file_name=" + rvw_org_file_name + ", rvw_cpy_file_name=" + rvw_cpy_file_name
				+ ", filesize=" + filesize + ", writedate=" + writedate + "]";
	}
	public int getRvw_file_no() {
		return rvw_file_no;
	}
	public void setRvw_file_no(int rvw_file_no) {
		this.rvw_file_no = rvw_file_no;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getCafe_no() {
		return cafe_no;
	}
	public void setCafe_no(int cafe_no) {
		this.cafe_no = cafe_no;
	}
	public String getRvw_org_file_name() {
		return rvw_org_file_name;
	}
	public void setRvw_org_file_name(String rvw_org_file_name) {
		this.rvw_org_file_name = rvw_org_file_name;
	}
	public String getRvw_cpy_file_name() {
		return rvw_cpy_file_name;
	}
	public void setRvw_cpy_file_name(String rvw_cpy_file_name) {
		this.rvw_cpy_file_name = rvw_cpy_file_name;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	
	
	
	
	
}
