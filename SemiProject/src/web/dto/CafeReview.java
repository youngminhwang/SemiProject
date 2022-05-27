package web.dto;

import java.sql.Date;

public class CafeReview {
	private int review_no;
	private int cafe_no;
	private int user_no;
	private String user_nick;
	private Date review_date;
	private String review_cont;
	private int star_score;
	private String user_id;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getStar_score() {
		return star_score;
	}
	public void setStar_score(int star_score) {
		this.star_score = star_score;
	}
	@Override
	public String toString() {
		return "CafeReview [review_no=" + review_no + ", cafe_no=" + cafe_no + ", user_no=" + user_no + ", user_nick="
				+ user_nick + ", review_date=" + review_date + ", review_cont=" + review_cont + ", star_score="
				+ star_score + ", user_id=" + user_id + ", cafe_rcm=" + cafe_rcm + "]";
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
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getReview_cont() {
		return review_cont;
	}
	public void setReview_cont(String review_cont) {
		this.review_cont = review_cont;
	}
	public int getCafe_rcm() {
		return cafe_rcm;
	}
	public void setCafe_rcm(int cafe_rcm) {
		this.cafe_rcm = cafe_rcm;
	}
	private int cafe_rcm;

}
