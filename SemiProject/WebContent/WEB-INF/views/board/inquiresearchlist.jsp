<%@page import="web.dto.Inquire"%>
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
    
<%	List<Inquire> searchInquireList = (List) request.getAttribute("searchInquireList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/board/inquirewrite";
	});
	
});
</script>
<br><br><br><br>
<div class="container">
<br>
<h1>'<%=request.getAttribute("data") %>'의 검색 결과</h1>

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

<%	for(int i=0; i<searchInquireList.size(); i++) { %>
	<input type="hidden" name="user_no" value="<%=searchInquireList.get(i).getUserno() %>">
<tr>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchInquireList.get(i).getIdx() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><a href="../inquireview?idx=<%=searchInquireList.get(i).getIdx() %>">
			<%=searchInquireList.get(i).getInquireTitle() %>
		</a>
	</td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchInquireList.get(i).getUsernick() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchInquireList.get(i).getInquireHits() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchInquireList.get(i).getCreateDate() %></td>
</tr>
<%	} %>

</table>

<%	if(searchInquireList.size() == 0) {%>
<div style="text-align: center;">
<span style="font-size: 15px;"><b>검색된 게시글이 없습니다</b></span>
<hr>
</div>
<%	} %>

<!-- 글쓰기 버튼 -->
<%	if( session.getAttribute("login") != null && (boolean) session.getAttribute("login") ) { %>
<div id="btnBox" class="pull-left">
	<button id="btnWrite">글쓰기</button>
</div>
<%	} %>

</div><!-- .container -->

<%@ include file="../layout/inquiresearchpaging.jsp" %>

<hr>

<%@ include file="../layout/inquiresearching.jsp" %>
<br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>