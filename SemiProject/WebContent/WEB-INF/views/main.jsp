<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="web.dto.CafeInfo"%>
<%@page import="web.dto.Notice"%>
<%@page import="web.dto.Free"%>
<%@page import="web.dto.Inquire"%>
<%@page import="java.util.List"%>

<%	List<CafeInfo> rcmCafeSelectList = (List) request.getAttribute("rcmCafeSelectList"); %>
<%	List<CafeInfo> newCafeSelectList = (List) request.getAttribute("newCafeSelectList"); %>
<%	List<Notice> noticeList = (List) request.getAttribute("noticeList"); %>
<%	List<Free> freeList = (List) request.getAttribute("freeList"); %>
<%	List<Inquire> inquireList = (List) request.getAttribute("inquireList"); %>

<%@ include file="./layout/header.jsp" %>

<style type="text/css">
.container {
	width:1170px;
}
.event_title, .cafe_title {
	margin-left: 10px;
	color: #FF792A;
}

#sliderBox{
	width: 1100px;
	height: 130px;
	border: 1px solid black;
	
	/* 외부 정렬 : 중앙 */
	margin: 0 auto;
	
	/* div영역을 벗어난 내용물을 숨김 */
 	overflow: hidden;

	position: relative;
}

#slider {
	/* ul태그의 기본 스타일 없애기 */
	padding: 0;
	margin: 0;
	list-style-type: none;
}

#slider li {
	position: absolute;
	z-index:2;
}

#slider li img {
	width: 1100px;
	height: 130px;
}

.main_title{
	text-align: center;
}

.main_list {
    width: 100%;
    height: 100%;
}

.list_start {
    text-align: center;
}

.list_detail {
    float: left;
    width: 33.33%;
    height: 240px;
    margin-bottom: 10px;
	margin-top: 10px;
}

.list_detail_board {
    float: left;
    border: 1px solid #ccc;
    border-radius: 5px;
    width: 32%;
    height: 240px;
    margin: 5px;
}

.paging_start{
	text-align: center;
}

a:hover {
	text-decoration : none;
}

a:active {
	color: black;
}
div.list_detail > div > a:link {
	color: black;
}

</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	//모든 이미지 리스트
	var $slider_list = $("#slider li")
// 	console.log($slider_list)
	
	//div태그의 콘텐츠 영역 밖(오른쪽)으로 빼내기
	$slider_list.css("left", $("#sliderBox").css("width"))
	
	//첫번째 이미지를 div안쪽으로 보내기
	$("#slider li:first-child").css("left", 0)
	
	//--- 8초마다 이미지 한 장씩 교체하기 ---
	
	var curSlide = 0; //현재 보여지는 이미지 인덱스
	
	setInterval(function() {

		//다음에 보여져야하는 이미지 인덱스
		var nextSlide = curSlide + 1;
				
		//nextSlide 보정값 (0~4만 지속되도록 보정)
		nextSlide %= $slider_list.length;
		
// 		console.log(curSlide, nextSlide)
		
		$slider_list.eq(nextSlide).css("left", $("#sliderBox").css("width"))
		
		//현재 이미지 숨기기 ( 왼쪽 <- 가운데 )
		$slider_list.eq(curSlide).animate({"left": "-=" + $("#sliderBox").css("width")})
		
		//다음 이미지 보여주기 ( 가운데 <- 오른쪽  )
		$slider_list.eq(nextSlide).animate({"left": "-=" + $("#sliderBox").css("width")})
		
		//순환 구조
		curSlide++;
		
		//curSlide 보정값 (0~4만 지속되도록 보정)
		curSlide %= $slider_list.length;
	}, 8000)
})
</script>
<br>
<%	if(session.getAttribute("user_no") == null ) { 	%>
<%		session.setAttribute("user_no", 0); %> 
<%		session.setAttribute("user_rank", 1); %>
<%	} %>


<div class="container">
<br><br>

<div class="event_title">
<h3 style="margin: 0;"><b>진행중인 이벤트</b></h3>
</div>
<br>

<div class="main_event">

<div id="sliderBox" style="border: 1px solid #ccc;">
	<ul id="slider">
		<li><a href="/board/noticelist"><img src="/resources/img/open.jpg"/></a></li>
		<li><a href="/board/noticelist"><img src="/resources/img/review.jpg"/></a></li>
		<li><a href="/board/noticelist"><img src="/resources/img/card.jpg"/></a></li>
		<li><a href="/board/noticelist"><img src="/resources/img/environment.jpg"/></a></li>
	</ul>
</div>

</div><!-- .main_event -->

</div><!-- .container -->
<br>
<hr style="height:2px; border:none; color:#ccc; background-color:#ccc;">

<div class="container">
	<div>
	<h3 class="cafe_title"><b>게시판</b></h3>
	</div>

		<!-- 리스트 -->
	<div class="list_start">
		<div class="list_detail_board">
			<div>
			<h4 style=" padding-bottom: 0px; margin-left: 3px; font-size: 18px; float: left; display: inline-block;"><b>공지사항</b></h4>
			<a style="float: right; margin-top: 10px; margin-right: 3px;" href="./board/noticelist">+ 더보기</a>
			</div>
			<br>
			<hr style="height:2px; border:none; color:#ccc; background-color:#ccc; margin-bottom: 10px;">
			<div style="text-align: left;">
			<%	if( noticeList.size() == 0 ) { %>
			<p style="margin-left: 10px; margin-top: 12px;"> 게시물이 없습니다.</p>
			<%	} else { %>
			<%	for(int i=0; i<noticeList.size(); i++) { %>
			<%		if(i>5){ continue; } %>
			<p style="margin-left: 10px; margin-top: 12px;">
			&nbsp;<b><i><%=i+1 %></i></b>&nbsp;<a href="./board/noticeview?idx=<%=noticeList.get(i).getIdx() %>">
				<%=noticeList.get(i).getNoticeTitle() %>
				</a>
			</p>
			<%	} %>
			<%	} %>
			</div>	
		</div>
		<div class="list_detail_board">
			<div>
			<h4 style=" padding-bottom: 0px; margin-left: 3px; float: left; display: inline-block;"><b>자유게시판</b></h4>
			<a style="float: right; margin-top: 10px; margin-right: 3px;" href="./board/freelist">+ 더보기</a>
			</div>
			<br>
			<hr style="height:2px; border:none; color:#ccc; background-color:#ccc; margin-bottom: 10px;">
			<div style="text-align: left;">
			<%	if( freeList.size() == 0 ) { %>
			<p style="margin-left: 10px; margin-top: 12px;"> 게시물이 없습니다.</p>
			<%	} else { %>
			<%	for(int i=0; i<freeList.size(); i++) { %>
			<%		if(i>5){ continue; } %>
			<p style="margin-left: 10px; margin-top: 12px;">
			&nbsp;<b><i><%=i+1 %></i></b>&nbsp;<a href="./board/freeview?idx=<%=freeList.get(i).getIdx() %>">
				<%=freeList.get(i).getFreeTitle() %>
				</a>
			</p>
			<%	} %>
			<%	} %>
			</div>	
		</div>
		<div class="list_detail_board">
			<div>
			<h4 style=" padding-bottom: 0px; margin-left: 3px; font-size: 18px; float: left; display: inline-block;"><b>문의게시판</b></h4>
			<a style="float: right; margin-top: 10px; margin-right: 3px;" href="./board/inquirelist">+ 더보기</a>
			</div>
			<br>
			<hr style="height:2px; border:none; color:#ccc; background-color:#ccc; margin-bottom: 10px;">
			<div style="text-align: left;">
			<%	if( inquireList.size() == 0 ) { %>
			<p style="margin-left: 10px; margin-top: 12px;"> 게시물이 없습니다.</p>
			<%	} else { %>
			<%	for(int i=0; i<inquireList.size(); i++) { %>
			<%		if(i>5){ continue; } %>
			<p style="margin-left: 10px; margin-top: 12px;">
			&nbsp;<b><i><%=i+1 %></i></b>&nbsp;<a href="./board/inquireview?idx=<%=inquireList.get(i).getIdx() %>">
				<%=inquireList.get(i).getInquireTitle() %>
				</a>
			</p>
			<%	} %>
			<%	} %>
			</div>	
		</div>
	</div>
</div><!-- .container -->


<br>

<hr style="height: 2px;border:none; color:#ccc; background-color:#ccc;">

<div class="container">
<div>
<h3 class="cafe_title" style="display: inline-block;"><b>유저 추천 카페 리스트</b></h3>
<a href="./cafe/rcmlist" style="font-size: 20px; color: #ccc; float: right; margin-top: 20px; margin-right: 10px;"><u>리스트 더보기</u></a>
<br>
</div>

<div class="main_list">
<!-- 리스트 -->
<div class="list_start">
<%	if( rcmCafeSelectList.size() == 0 ) { %>
<p style="margin-left: 10px; margin-top: 12px;"> 게시물이 없습니다. </p>
<%	} else { %>
<%	for(int i=0; i<rcmCafeSelectList.size(); i++) { %>
	<div class="list_detail">
		<div>
		<a href="<%=request.getContextPath() %>/cafe/view?cafeinfo=<%=rcmCafeSelectList.get(i).getCafe_no() %>">
		<img src="<%=request.getContextPath() %>/upload/<%=rcmCafeSelectList.get(i).getCafe_cpy_file_name() %>"
		style="width: 360px; height: 180px;">
		</a>
<%-- 		<span><%=rcmCafeSelectList.get(i).getCafe_cpy_file_name() %></span> --%>
		</div>

		<div style="font-size: 25px; #ccc; text-align: left; margin-left: 7px; margin-top: 7px; margin-bottom: 0px; text-align: center;">
			<a href="/cafe/view?cafeinfo=<%=rcmCafeSelectList.get(i).getCafe_no() %>">
			<%=rcmCafeSelectList.get(i).getCafe_name() %><br>
			</a>
		</div>

	<div style="font-size: 16px; color: #ccc; text-align: left; margin-left: 7px; margin-top: 0px; margin-bottom: 0px; text-align: center;">
	<b><%=rcmCafeSelectList.get(i).getCafe_loc() %></b>
	</div>
</div><!-- list_detail -->
<%	} %>
<%	} %>

</div><!-- list_start -->

</div><!-- main_list -->


</div><!-- .container -->
<br>

<hr style="height:2px;border:none; color:#ccc; background-color:#ccc;">

<div class="container">
<div>
<h3 class="cafe_title" style="display: inline-block;"><b>신규 등록 카페 리스트</b></h3>
<a href="./cafe/idxlist" style="font-size: 20px; color: #ccc; float: right; margin-top: 20px; margin-right: 10px;"><u>리스트 더보기</u></a>
<br>
</div>

<div class="main_list">
<!-- 리스트 -->
<div class="list_start">
<%	if( newCafeSelectList.size() == 0 ) { %>
<p style="margin-left: 10px; margin-top: 12px;"> 게시물이 없습니다. </p>
<%	} else { %>
<%	for(int i=0; i<newCafeSelectList.size(); i++) { %>
	<div class="list_detail">
		<div>
		<a href="<%=request.getContextPath() %>/cafe/view?cafeinfo=<%=newCafeSelectList.get(i).getCafe_no() %>">
		<img src="<%=request.getContextPath() %>/upload/<%=newCafeSelectList.get(i).getCafe_cpy_file_name() %>"
		style="width: 360px; height: 180px;">
		</a>
		</div>

		<div style="font-size: 25px; text-align: left; margin-left: 7px; margin-top: 7px; margin-bottom: 0px; text-align: center;">
			<a href="/cafe/view?cafeinfo=<%=newCafeSelectList.get(i).getCafe_no() %>">
			<%=newCafeSelectList.get(i).getCafe_name() %><br>
			</a>
		</div>

	<div style="font-size: 16px; color: #ccc; text-align: left; margin-left: 7px; margin-top: 0px; margin-bottom: 0px; text-align: center;">
	<b><%=newCafeSelectList.get(i).getCafe_loc() %></b>
	</div>
</div><!-- list_detail -->
<%	} %>
<%	} %>

</div><!-- list_start -->

</div><!-- main_list -->


</div><!-- .container -->


<br><br><br><br><br><br>

<%@ include file="./layout/footer.jsp" %>