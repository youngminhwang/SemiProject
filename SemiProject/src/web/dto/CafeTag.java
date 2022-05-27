package web.dto;

public class CafeTag {

	private int cafe_no;
	private int tag_no;
	private String tag_name;
	
	@Override
	public String toString() {
		return "CafeTag [cafe_no=" + cafe_no + ", tag_no=" + tag_no + ", tag_name=" + tag_name + "]";
	}
	
	
	public int getCafe_no() {
		return cafe_no;
	}
	public void setCafe_no(int cafe_no) {
		this.cafe_no = cafe_no;
	}
	public int getTag_no() {
		return tag_no;
	}
	public void setTag_no(int tag_no) {
		this.tag_no = tag_no;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	
	
	
}
