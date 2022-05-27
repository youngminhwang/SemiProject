<%@page import="web.dto.Free"%>
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
    
<%	List<Free> searchFreeList = (List) request.getAttribute("searchFreeList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/board/freewrite";
	});
	
});
</script>

<div class="container">
<br><br><br><br>
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

<%	for(int i=0; i<searchFreeList.size(); i++) { %>
	<input type="hidden" name="user_no" value="<%=searchFreeList.get(i).getUserno() %>">
<tr>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchFreeList.get(i).getIdx() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><a href="../freeview?idx=<%=searchFreeList.get(i).getIdx() %>">
			<%=searchFreeList.get(i).getFreeTitle() %>
		</a>
	</td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchFreeList.get(i).getUsernick() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchFreeList.get(i).getFreeHits() %></td>
	<td style="border-bottom: 1px solid #ccc; border-right: 0; border-left: 0; height:45px;"><%=searchFreeList.get(i).getCreateDate() %></td>
</tr>
<%	} %>

</table>

<%	if(searchFreeList.size() == 0) {%>
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

<%@ include file="../layout/freesearchpaging.jsp" %>

<hr>

<%@ include file="../layout/freesearching.jsp" %>
<br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>