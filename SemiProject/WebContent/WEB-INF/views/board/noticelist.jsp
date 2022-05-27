<%@page import="web.dto.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css">
#btnWrite {
	background: #5A5A5A;
	color: white;
	
	border-radius: 5px;
	border: none;
	
	padding: 5px;
	
}
</style>
    
<%@ include file="../layout/header.jsp" %>
    
<%	List<Notice> noticeList = (List) request.getAttribute("noticeList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/board/noticewrite";
	});
	
});
</script>
<br><br><br><br>
<div class="container">
<br>
<h1>공지사항</h1>

<hr>
<br>
<table class="table table-striped">
<tr>
	<th>글번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>

<%	for(int i=0; i<noticeList.size(); i++) { %>
	<input type="hidden" name="user_no" value="<%=noticeList.get(i).getUserno() %>">
<tr>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=noticeList.get(i).getIdx() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><a href="./noticeview?idx=<%=noticeList.get(i).getIdx() %>">
			<%=noticeList.get(i).getNoticeTitle() %>
		</a>
	</td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=noticeList.get(i).getUsernick() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=noticeList.get(i).getNoticeHits() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=noticeList.get(i).getCreateDate() %></td>
</tr>
<%	} %>

</table>

<%	if(noticeList.size() == 0) {%>
<div style="text-align: center;">
<span style="font-size: 15px;"><b>작성된 게시글이 없습니다</b></span>
<hr>
</div>
<%	} %>

<!-- 글쓰기 버튼 -->
<%	if(  String.valueOf(session.getAttribute("user_rank")).equals("0") ) { %>
<div id="btnBox" class="pull-left">
	<button id="btnWrite">글쓰기</button>
</div>
<%	} %>

</div><!-- .container -->

<%@ include file="../layout/noticepaging.jsp" %>

<hr>

<%@ include file="../layout/noticesearching.jsp" %>
<br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>