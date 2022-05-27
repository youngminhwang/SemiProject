package web.dto;

public class Tag {

	private int tag_no;
	private String tag_name;
	private String search_word;
	
	
	
	@Override
	public String toString() {
		return "Tag [tag_no=" + tag_no + ", tag_name=" + tag_name + ", search_word=" + search_word + "]";
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
	public String getSearch_word() {
		return search_word;
	}
	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}
	


	
	
}
