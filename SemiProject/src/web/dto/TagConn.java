package web.dto;

public class TagConn {
	private int tagNo;
	private int cafeNo;
	
	public TagConn() { }
	
	public TagConn(int tagNo, int cafeNo) {
		this.tagNo = tagNo;
		this.cafeNo = cafeNo;
	}

	@Override
	public String toString() {
		return "TagConn [tagNo=" + tagNo + ", cafeNo=" + cafeNo + "]";
	}
	
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public int getCafeNo() {
		return cafeNo;
	}
	public void setCafeNo(int cafeNo) {
		this.cafeNo = cafeNo;
	}
	
}
