<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="web.dto.CafeTag"%>
<%@page import="web.dto.CafeFile"%>
<%@page import="web.dto.Cafe"%>

<%@ include file="../layout/adminNav.jsp" %>

<% Cafe viewCafe = (Cafe) request.getAttribute("viewCafe"); %>
<% CafeFile cafeFile = (CafeFile) request.getAttribute("cafeFile"); %>
<% CafeTag cafeTagName = (CafeTag) request.getAttribute("cafeTagName"); %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/cafe/cafelist");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/cafe/cafeupdate?cafe_no=<%=viewCafe.getCafe_no() %>");
		
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("카페를 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/cafe/cafedelete?cafe_no=<%=viewCafe.getCafe_no() %>");
		}
	})
});
</script>

<div id="content" class="container">

<h1>게시글 상세페이지</h1>

<hr>

<table class="table table-bordered" style="text-align: center;">

<tr>
	<td class="info">카페번호</td><td colspan="3"><%=viewCafe.getCafe_no() %></td>
</tr>

<tr>
	<td class="info">카페이름</td><td colspan="3"><%=viewCafe.getCafe_name() %></td>
</tr>

<tr>
	<td class="info">카페메뉴</td><td colspan="3"><%=viewCafe.getCafe_menu() %></td>
</tr>

<tr>
	<td class="info">카페 태그</td><td colspan="3"><%=cafeTagName.getTag_name() %></td>
</tr>

<tr>
	<td class="info">카페 주소</td><td><%=viewCafe.getCafe_loc() %></td>
</tr>

<tr>
	<td class="info">전화번호</td><td><%=viewCafe.getCafe_tel() %></td>
</tr>

<tr>
	<td class="info">영업시간</td><td><%=viewCafe.getCafe_time() %></td>
</tr>

<tr>
	<td class="info">주차 가능 여부</td><td><%=viewCafe.getCafe_park() %></td>
</tr>

<tr>
	<td class="info">좋아요</td><td><%=viewCafe.getCafe_rcm() %></td>
</tr>

<tr>
	<td class="info" colspan="2">카페 사진</td>
</tr>

<tr>
	<td colspan="2">
		<% if (cafeFile != null) { %>
			<img src="/upload/<%=cafeFile.getCafe_cpy_file_name() %>" width="300" height="300" alt="이미지아님">
		<% } %>
	</td>
</tr>

</table>

	<!-- 첨부파일 -->
	<div>
		<% if(cafeFile != null) { %>
			<a href="<%=request.getContextPath() %>/upload/<%=cafeFile.getCafe_cpy_file_name() %>"
			download="<%=cafeFile.getCafe_org_file_name() %>"><%=cafeFile.getCafe_org_file_name() %> </a>
		<% } %>
	</div>

	<div class="text-center">
		<button id = "btnList" class="btn btn-default">목록</button>
		<button id = "btnUpdate" class="btn btn-default">수정</button>
		<button id = "btnDelete" class="btn btn-default">삭제</button>
	</div>

</div><!-- container -->

<br><br><br><br><br>

</body>

</html>

