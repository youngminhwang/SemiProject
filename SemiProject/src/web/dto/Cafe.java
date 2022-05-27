package web.dto;

public class Cafe {

	private int cafe_no;
	private String cafe_name;
	private String cafe_tel;
	private String cafe_time;
	private String cafe_park;
	private String cafe_loc;
	private int cafe_rcm;
	
	private String search_word;
	
	private String cafe_menu;
	
	
	

	@Override
	public String toString() {
		return "Cafe [cafe_no=" + cafe_no + ", cafe_name=" + cafe_name + ", cafe_tel=" + cafe_tel + ", cafe_time="
				+ cafe_time + ", cafe_park=" + cafe_park + ", cafe_loc=" + cafe_loc + ", cafe_rcm=" + cafe_rcm
				+ ", search_word=" + search_word + ", cafe_menu=" + cafe_menu + "]";
	}

	public int getCafe_no() {
		return cafe_no;
	}

	public void setCafe_no(int cafe_no) {
		this.cafe_no = cafe_no;
	}

	public String getCafe_name() {
		return cafe_name;
	}

	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}

	public String getCafe_tel() {
		return cafe_tel;
	}

	public void setCafe_tel(String cafe_tel) {
		this.cafe_tel = cafe_tel;
	}

	public String getCafe_time() {
		return cafe_time;
	}

	public void setCafe_time(String cafe_time) {
		this.cafe_time = cafe_time;
	}

	public String getCafe_park() {
		return cafe_park;
	}

	public void setCafe_park(String cafe_park) {
		this.cafe_park = cafe_park;
	}

	public String getCafe_loc() {
		return cafe_loc;
	}

	public void setCafe_loc(String cafe_loc) {
		this.cafe_loc = cafe_loc;
	}

	public int getCafe_rcm() {
		return cafe_rcm;
	}

	public void setCafe_rcm(int cafe_rcm) {
		this.cafe_rcm = cafe_rcm;
	}

	public String getSearch_word() {
		return search_word;
	}

	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}

	public String getCafe_menu() {
		return cafe_menu;
	}

	public void setCafe_menu(String cafe_menu) {
		this.cafe_menu = cafe_menu;
	}
	
	
	
	
	

	
}
