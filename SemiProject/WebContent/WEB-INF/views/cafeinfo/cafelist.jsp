<%@page import="web.dto.CafeInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	List<CafeInfo> cafeList = (List) request.getAttribute("cafeList");
%>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/board/write";
	});
	
});
</script>

<div class="container">

<h1>게시글 목록</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr class="success">
	<th>글번호</th>
	<th>제목</th>
	<th>아이디</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>

<%	for(int i=0; i<cafeList.size(); i++) { %>
<tr>
	<td><%=cafeList.get(i).getCafe_no() %></td>
	<td><a href="./view?cafeinfo=<%=cafeList.get(i).getCafe_no() %>">
	<%=cafeList.get(i).getCafe_name() %></a></td>
	<td><%=cafeList.get(i).getCafe_tel() %></td>
	<td><%=cafeList.get(i).getCafe_park() %></td>
	<td><%=cafeList.get(i).getCafe_no() %></td>
</tr>
<%	} %>

</table>

<!-- 글쓰기 버튼 -->
<!-- <div id="btnBox" class="pull-left"> -->
<!-- 	<button id="btnWrite" class="btn btn-primary">글쓰기</button> -->
<!-- </div> -->

</div><!-- .container -->



