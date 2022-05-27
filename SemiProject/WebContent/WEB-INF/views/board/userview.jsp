<%@page import="web.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/head.jsp" %>

<% User viewUser = (User) request.getAttribute("viewUser"); %>

<script type="text/javascript">

$(document).ready(function() {
	
	//변경버튼
	$("#btnUpdate").click(function() {
		if( confirm("회원 등급을 변경하시겠습니까?") ) {
			$("form").submit();
<%-- 			$(location).attr("href", "<%=request.getContextPath() %>/board/userrankupdate?"); --%>
<%-- 			$(location).attr("href", "<%=request.getContextPath() %>/board/userrankupdate?user_no=<%=viewUser.getUser_no() %>"); --%>
		}
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("회원을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/board/userdelete?user_no=<%=viewUser.getUser_no() %>");
		}
	})
});

</script>


<style type="text/css">

td, th {
	text-align: center;
}

</style>

<div id="content" class="container">

<h1>회원 상세페이지</h1>
<hr>

<form action="/board/userrankupdate" method="post" name="viewform">
 
<table class="table table-striped">

<tr>
<td class="info">회원번호</td><td><%=viewUser.getUser_no() %></td>
</tr>

<tr>
<td class="info">회원아이디</td><td><%=viewUser.getUser_id() %></td>
</tr>

<tr>
<td class="info">회원 닉네임</td><td><%=viewUser.getUser_nick() %></td>
</tr>

<tr>
<td class="info">회원 등급</td><td><%=viewUser.getUser_rank() %></td>
</tr>

<tr>
<td  class="info" colspan="1">회원등급변경</td>



<%-- <label><input type="radio" name="rank1" value= "<%=1 %>"> &nbsp일반회원</label>  --%>
<%-- <label><input type="radio" name="rank2" value="<%=0 %>"> &nbsp관리자</label>  --%>


<td>
<input type="hidden" name="user_no" value="<%=viewUser.getUser_no() %>" >

<div style="text-align: center;">
<select name="userChangeRank" class="form-control"  style="width:100px; margin: auto;">
	<option value="1">일반회원 </option>
	<option value="0">관리자 </option>
</select>
</div>
</td>
</tr>



		
</table>

</form>
	
<div class="text-center">
	<button id = "btnList" onclick="location.href='<%=request.getContextPath() %>/board/userlist'" class="btn btn-default">돌아가기</button>
	<button id = "btnUpdate"  class="btn btn-default">변경</button>
	<button id = "btnDelete"  class="btn btn-default">회원 삭제</button>
</div>



</div><!-- .container -->


</body>
</html>

