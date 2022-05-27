<%@page import="web.dto.Cafe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/head.jsp" %>

<% List<Cafe> searchList = (List) request.getAttribute("searchList"); %>

<script type="text/javascript">

$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/cafe/cafelist");
	})
});

</script>

<style type="text/css">

td, th {
	text-align: center;
}

</style>
<br><br><br><br>
<div id="content" class="container">


<h1>카페 검색 결과 목록 조회</h1>
<hr>
    
 <form action="/cafe/cafesearch" method="get">
<%--  <input type="hidden" name="serkey" value="<%=serkey %>" > --%>
	<input type="text" name="data" placeholder="검색어를 입력하세요" >
	&nbsp<button class="btn btn-warning">검 색</button>
</form>
<br>
<table class="table table-striped table-hover table-condensed">
<tr class="warning">
	<th>카페번호번호</th>
	<th>카페이름</th>
</tr>

<%	for(int i=0; i<searchList.size(); i++) { %>
<tr>
	<td><%=searchList.get(i).getCafe_no() %></td>


	<td><a href="/cafe/cafeview?cafe_no=<%=searchList.get(i).getCafe_no() %>"><%=searchList.get(i).getCafe_name() %></a></td>
	

</tr>
<%	} %>

</table>

<div style="text-align: center;">
	<button id="btnWrite" class="btn btn-default">글쓰기</button>
	<button id="btnList" class="btn btn-default">목록</button>
</div>

</div><!-- .container -->
<%@ include file="../cafe/cafesearchpaging.jsp" %>

</body>
</html>

