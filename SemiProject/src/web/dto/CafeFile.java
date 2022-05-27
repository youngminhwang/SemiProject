package web.dto;

public class CafeFile {

	private int cafe_file_no; //카페 사진 번호
	private int cafe_no; // 카페 번호
	private String cafe_org_file_name; //원본 파일 명
	private String cafe_cpy_file_name; // 사본 파일 명
	private String cafe_cpy_ext; // 사본 확장자
	private String cafe_cpy_path; //파일 경로
	private int cafe_cpy_size; //파일 크기
	
	
	
	
	@Override
	public String toString() {
		return "CafeFile [cafe_file_no=" + cafe_file_no + ", cafe_no=" + cafe_no + ", cafe_org_file_name="
				+ cafe_org_file_name + ", cafe_cpy_file_name=" + cafe_cpy_file_name + ", cafe_cpy_ext=" + cafe_cpy_ext
				+ ", cafe_cpy_path=" + cafe_cpy_path + ", cafe_cpy_size=" + cafe_cpy_size + "]";
	}
	
	
	public int getCafe_file_no() {
		return cafe_file_no;
	}
	public void setCafe_file_no(int cafe_file_no) {
		this.cafe_file_no = cafe_file_no;
	}
	public int getCafe_no() {
		return cafe_no;
	}
	public void setCafe_no(int cafe_no) {
		this.cafe_no = cafe_no;
	}
	public String getCafe_org_file_name() {
		return cafe_org_file_name;
	}
	public void setCafe_org_file_name(String cafe_org_file_name) {
		this.cafe_org_file_name = cafe_org_file_name;
	}
	public String getCafe_cpy_file_name() {
		return cafe_cpy_file_name;
	}
	public void setCafe_cpy_file_name(String cafe_cpy_file_name) {
		this.cafe_cpy_file_name = cafe_cpy_file_name;
	}
	public String getCafe_cpy_ext() {
		return cafe_cpy_ext;
	}
	public void setCafe_cpy_ext(String cafe_cpy_ext) {
		this.cafe_cpy_ext = cafe_cpy_ext;
	}
	public String getCafe_cpy_path() {
		return cafe_cpy_path;
	}
	public void setCafe_cpy_path(String cafe_cpy_path) {
		this.cafe_cpy_path = cafe_cpy_path;
	}
	public int getCafe_cpy_size() {
		return cafe_cpy_size;
	}
	public void setCafe_cpy_size(int cafe_cpy_size) {
		this.cafe_cpy_size = cafe_cpy_size;
	}
	
	
	
}
