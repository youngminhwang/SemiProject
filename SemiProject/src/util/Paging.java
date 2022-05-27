package util;

public class Paging {
	
	private int curPageNum;	//현재 페이지 번호

	private int totalCount;	//총 게시글 수

	private int listCount; //한 페이지 당 보여질 게시글 수

	private int totalPage; //총 페이지의 수

	private int pageCount; //한 화면에 출력될 페이지네이션의 개수

	private int startPageNum; //curPageNum에 따라 화면에 출력되는 페이지네이션의 시작 번호

	private int endPageNum; //curPageNum에 따라 화면에 출력되는 페이지네이션의 끝 번호

	private int startPostNo; //curPageNum에 따라 화면에 출력되는 게시글의 시작 번호

	private int endPostNo; //curPageNum에 따라 화면에 출력되는 게시글의 끝 번호

	private String select; //검색 목록
	
	private String search; //검색어
	
	//디폴트 생성자 - 페이징로직이 완성되지 않는다
	public Paging() { }
	
	//조건 및 상황(= 페이징로직에 적용될 실인자가 존재할 경우)에 따른 생성자
	public Paging(int totalCount, int curPageNum) {
		setTotalCount(totalCount);
		setCurPageNum(curPageNum);
		
		makePaging();
	}

	public Paging(int totalCount, int curPageNum, String search) {
		setTotalCount(totalCount);
		setCurPageNum(curPageNum);
		setSearch(search);
		
		makePaging();
	}

	public Paging(int totalCount) {
		setTotalCount(totalCount);
		
		makePaging();
	}
	
	public Paging(int totalCount, int curPageNum, int listCount) {
		setTotalCount(totalCount);
		setCurPageNum(curPageNum);
		setListCount(listCount);
		
		makePaging();
	}
	
	public Paging(int totalCount, int curPageNum, int listCount, int pageCount) {
		setTotalCount(totalCount);
		setCurPageNum(curPageNum);
		setListCount(listCount);
		setPageCount(pageCount);
		
		makePaging();
	}

	// 페이징 정보를 생성하는 메소드
	private void makePaging() {
		
		if( totalCount == 0 ) {	//게시글이 없는 경우		
			return;	//페이징 정보 생성 중단
		}
		
		//기본값 설정
		if( curPageNum == 0 ) {	//현재 페이지 번호가 0일 경우,
			setCurPageNum(1); //첫 페이지를 기본 페이지로 설정한다
		}
		
		if( listCount == 0 ) {	 //한 페이지 당 보여질 게시글 수가 0일 경우,
			setListCount(10); //화면에 보여질 게시글 수를 10개로 설정한다
		}
		
		if( pageCount == 0 ) {	//한 화면에 출력될 페이지네이션의 개수가 0일 경우,
			setPageCount(10); //화면에 출력될 페이징 수를 10개로 설정한다
		}
		
		//-------------------------------------------------------
		
		//총 페이지수의 계산
		totalPage = totalCount / listCount;	//총 페이지의 수 = 총 게시글 수 / 한 페이지 당 보여질 게시글 수
		
		if( totalPage % totalCount > 0 ) {	//(총 페이지의 수 / 총 게시글 수)의 결과값이 실수라면, 
			totalPage++;	// 페이지의 수가 하나 더 필요하기 때문에, 총 페이지의 수를 하나 더 추가해준다. 
		}

		//총 페이지의 수 계산 보정 작업
		if( curPageNum > totalPage ) {		//총 페이지 수보다 현재 페이지 번호가 클 경우,
			totalPage = curPageNum;
		}

		//-------------------------------------------------------

		//curPageNum에 따라 화면에 출력되는 페이지네이션의 시작 번호 계산 
		startPageNum = ( (curPageNum-1)/pageCount ) * pageCount + 1;
		 	
		//curPageNum에 따라 화면에 출력되는 페이지네이션의 끝 번호 계산
		endPageNum = startPageNum + pageCount - 1;

		//페이지네이션 보정 작업
		if( endPageNum > totalPage ) {
			endPageNum = totalPage;
		}

		//-------------------------------------------------------
		
		//curPageNum에 따라 화면에 출력되는 게시글의 시작 번호, 끝 번호 계산
		startPostNo = ( curPageNum-1 ) * listCount + 1;

		endPostNo = curPageNum * listCount;
		
	}
	
	@Override
	public String toString() {
		return "Paging [curPageNum=" + curPageNum + ", totalCount=" + totalCount + ", listCount=" + listCount
				+ ", totalPage=" + totalPage + ", pageCount=" + pageCount + ", startPageNum=" + startPageNum
				+ ", endPageNum=" + endPageNum + ", startPostNo=" + startPostNo + ", endPostNo=" + endPostNo
				+ ", select=" + select + ", search=" + search + "]";
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}

	public int getStartPostNo() {
		return startPostNo;
	}

	public void setStartPostNo(int startPostNo) {
		this.startPostNo = startPostNo;
	}

	public int getEndPostNo() {
		return endPostNo;
	}

	public void setEndPostNo(int endPostNo) {
		this.endPostNo = endPostNo;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}

