<%@page import="web.dto.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/head.jsp" %>    
    
<%	List<User> userList = (List) request.getAttribute("userList"); %>    
   
<style type="text/css">

td, th {
	text-align: center;
}

</style>
	
	<br><br><br><br>
<div id="content" class="container">

<h1>회원 목록 조회</h1>
<hr>

<form action="/board/usersearch" method="get">
	<select name="select" >
		<option value="user_id">아이디</option>
		<option value="user_nick">닉네임</option>
</select>

<input type="text" name="data" placeholder="검색어를 입력하세요" >
	<button class="btn btn-warning">검 색</button>
</form>
<br>
<table class="table table-striped table-hover table-condensed"> 
<tr class = "warning">
	<th>회원번호</th>
	<th>아이디</th>
	<th>닉네임</th>
	<th>등급</th>
	<th>가입날짜</th>
	<th>이메일</th>
	<th>전화번호</th>
	<th>년</th>
	<th>월</th>
	<th>일</th>
<!-- 	<th>삭제</th> -->
</tr>

<% for(int i=0; i<userList.size(); i++) { %>
<tr>
	<td><%=userList.get(i).getUser_no() %></td>
<%-- 	<td><%=userList.get(i).getUser_id() %></td> --%>

	<td><a href="/board/userview?user_no=<%=userList.get(i).getUser_no() %>"><%=userList.get(i).getUser_id() %></a></td>

	<td><%=userList.get(i).getUser_nick() %></td>
	<td><%=userList.get(i).getUser_rank() %></td>
	<td><%=userList.get(i).getUser_join_date() %></td>
	<td><%=userList.get(i).getUser_email() %></td>
	<td><%=userList.get(i).getUser_phone() %></td>
	<td><%=userList.get(i).getUser_year() %></td>
	<td><%=userList.get(i).getUser_month() %></td>
	<td><%=userList.get(i).getUser_day() %></td>
<%-- 	<td><button id="btnDelete" onclick="deleteuser(<%=User.getUser_no()%>);">삭제</button></td> --%>
</tr>
<%	} %>

</table>	
	
	

</div>

<%@ include file="../layout/paging.jsp" %>


</body>
</html>
