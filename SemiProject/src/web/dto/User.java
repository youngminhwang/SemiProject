package web.dto;

import java.util.Date;

public class User {

	private int user_no;
	private int user_rank;
	private String user_id;
	private String user_pw;
	private String user_nick;
	private Date user_join_date;
	private String user_gender;
	private String user_email;
	private String user_phone;
	private int user_year;
	private int user_month;
	private int user_day;
	
	private String search_kind;
	private String search_word;
	
	
	
	
	
	@Override
	public String toString() {
		return "User [user_no=" + user_no + ", user_rank=" + user_rank + ", user_id=" + user_id + ", user_pw=" + user_pw
				+ ", user_nick=" + user_nick + ", user_join_date=" + user_join_date + ", user_gender=" + user_gender
				+ ", user_email=" + user_email + ", user_phone=" + user_phone + ", user_year=" + user_year
				+ ", user_month=" + user_month + ", user_day=" + user_day + ", search_kind=" + search_kind
				+ ", search_word=" + search_word + "]";
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getUser_rank() {
		return user_rank;
	}
	public void setUser_rank(int user_rank) {
		this.user_rank = user_rank;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public Date getUser_join_date() {
		return user_join_date;
	}
	public void setUser_join_date(Date user_join_date) {
		this.user_join_date = user_join_date;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public int getUser_year() {
		return user_year;
	}
	public void setUser_year(int user_year) {
		this.user_year = user_year;
	}
	public int getUser_month() {
		return user_month;
	}
	public void setUser_month(int user_month) {
		this.user_month = user_month;
	}
	public int getUser_day() {
		return user_day;
	}
	public void setUser_day(int user_day) {
		this.user_day = user_day;
	}
	public String getSearch_kind() {
		return search_kind;
	}
	public void setSearch_kind(String search_kind) {
		this.search_kind = search_kind;
	}
	public String getSearch_word() {
		return search_word;
	}
	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
