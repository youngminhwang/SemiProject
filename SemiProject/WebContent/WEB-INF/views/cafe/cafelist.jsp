<%@page import="web.dto.Cafe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/head.jsp" %>

<% List<Cafe> cafeList = (List) request.getAttribute("cafeList"); %>

<div id="content" class="container">

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/cafe/cafewrite";
	});
});

</script>

<style type="text/css">

td, th {
	text-align: center;
}

</style>
<br><br><br><br>
<div class="container">

<h1>게시글 목록</h1>
<hr>

<form action="/cafe/cafesearch" method="get">
	
	<input type="text" name="data" placeholder="검색어를 입력하세요" >
	&nbsp<button class="btn btn-warning">검 색</button>
</form>
<br>

<table class="table table-striped table-hover table-condensed">
<tr class="warning">
	<th>카페번호번호</th>
	<th>카페이름</th>
</tr>

<%	for(int i=0; i<cafeList.size(); i++) { %>
<tr>
	<td><%=cafeList.get(i).getCafe_no() %></td>


	<td><a href="/cafe/cafeview?cafe_no=<%=cafeList.get(i).getCafe_no() %>"><%=cafeList.get(i).getCafe_name() %></a></td>
	

</tr>
<%	} %>

</table>

<div style="text-align: center;">
	<button id="btnWrite" class="btn btn-default">글쓰기</button>
</div>






</div><!-- .container -->
<%@ include file="../cafe/paging.jsp" %>


</body>
</html>

