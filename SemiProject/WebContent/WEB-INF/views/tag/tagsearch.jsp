<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="web.dto.Tag"%>
<%@page import="java.util.List"%>

<%@ include file="../layout/adminNav.jsp" %>

<% List<Tag> searchList = (List) request.getAttribute("searchList"); %>
<% Tag viewTag = (Tag) request.getAttribute("viewTag"); %>

<div id="content" class="container">

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/tag/tagwrite";
	});
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("태그를 삭제하시겠습니까?") ) {
			$("form").submit();
<%-- 			$(location).attr("href", "<%=request.getContextPath() %>/tag/tagdelete?tag_no=<%=viewTag.getTag_no() %>"); --%>
		}
	})
});

</script>

<style type="text/css">
td, th {
	text-align: center;
}
</style>

<br><br><br><br>

<h1>태그 검색 목록</h1>

<hr>

<form action="/tag/tagsearch" method="get">
	<input type="text" name="data" placeholder="검색어를 입력하세요" >
	&nbsp<button class="btn btn-default">검 색</button>
</form>

<br>

<form action="/tag/tagdelete" method="post">

<table class="table table-striped table-hover table-condensed">
<tr class = "warning">
	<th>태그 선택</th>
	<th>태그 번호</th>
	<th>태그</th>
	<th>태그 삭제</th>
</tr>

<%for(int i=0; i<searchList.size(); i++) { %>
	<tr>
		<td><input type="checkbox" name="tag_no" value="<%=searchList.get(i).getTag_no() %>"></td>
		<td><%=searchList.get(i).getTag_no() %></td>
		<td><%=searchList.get(i).getTag_name() %></td>
		<td><button id = "btnDelete" class="btn btn-default">태그 삭제</button></td>
<!-- 	<td> -->
<%-- 		<a href="/tag/taglist?tag_no=<%=tagList.get(i).getTag_no() %>">  --%>
<%-- 			<input type="button" name="tag_no" value="<%=tagList.get(i).getTag_no() %>"> --%>
<!-- 		</a> -->
<!-- 	</td> -->
	</tr>
<%}%>

</table>

</form>

<form action="/tag/tagwrite" method="post" name="writeform">

<label>태그 추가 &nbsp&nbsp <input type="text" name="tag_name">&nbsp&nbsp&nbsp<button id="btnWrite" class="btn btn-default">추가</button></label>

</form>



</div><!-- .container -->
<%@ include file="../tag/tagsearchpaging.jsp" %>


</body>
</html>


