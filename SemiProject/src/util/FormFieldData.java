package util;

public class FormFieldData {

	private int dataNo;
	private String title;
	private String data1;
	private String data2;
	
	@Override
	public String toString() {
		return "FormFieldData [dataNo=" + dataNo + ", title=" + title + ", data1=" + data1 + ", data2=" + data2 + "]";
	}
	
	public int getDataNo() {
		return dataNo;
	}
	public void setDataNo(int dataNo) {
		this.dataNo = dataNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}

}
