package web.dto;

public class User_rcm {
	private int user_no;
	private int cafe_no;
	@Override
	public String toString() {
		return "user_rcm [user_no=" + user_no + ", cafe_no=" + cafe_no + "]";
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getCafe_no() {
		return cafe_no;
	}
	public void setCafe_no(int cafe_no) {
		this.cafe_no = cafe_no;
	}
	
}
