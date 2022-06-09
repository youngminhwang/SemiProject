<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="web.dto.User"%>
<%@page import="java.util.List"%>

<%@ include file="../layout/header.jsp" %>

<%	List<User> mypageList = (List) request.getAttribute("mypageList"); %>  
<% User viewMypage = (User) request.getAttribute("viewMypage"); %>

<style type="text/css">
#btnUpdate {
	border: 4px solid #FF792A;
	border-radius: 10px;
	background: white;
	color: #FF792A;
	margin-right: 5px;
	width: 90px;
	height: 30px;
}

#btnDelete {
	border: 4px solid red;
	border-radius: 10px;
	background: white;
	color: red;
	margin-left: 5px;
	width: 90px;
	height: 30px;
}
</style>

<div class="container" style="height:800px;">

<br><br><br><br><br><br>

<h2 style="margin-left: 20px;">회원 정보</h2>

<hr style="height:3px; border:none; color:#ccc; background-color:#ccc;">

<br><br>

	<div class="join_form_div" style="border: 0; width: 598px; margin: 0 auto; text-aling: center;	">
	
	<br><br><br><br>

		<div class="mypage">
			<table id="mypagetable" style="width: 100%; margin: 0 auto; font-size: 18px; border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
			<tr>
				<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
					아이디
				</th>
				
				<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
					<%=viewMypage.getUser_id() %>
				</td>
			</tr>
			
			<tr>
				<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
					닉네임
				</th>
				
				<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
					<%=viewMypage.getUser_nick() %>
				</td>
			</tr>
			
			<tr>
				<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
					이메일
				</th>
				
				<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
					<%=viewMypage.getUser_email() %>
				</td>
			</tr>
			
			<tr>
				<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
					전화번호
				</th>
				
				<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
					<%=viewMypage.getUser_phone() %>
				</td>
			</tr>
			
			<tr>
				<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
					성별
				</th>
				
				<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
					<%=viewMypage.getUser_gender() %>
				</td>
			</tr>
			
			<tr>
				<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
					생년월일
				</th>
				
				<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
					<%=viewMypage.getUser_year() %>.<%=viewMypage.getUser_month() %>.<%=viewMypage.getUser_day() %>
				</td>
			</tr>
			</table>
			
			<br><br>
			
			<div style="text-align: center;">
				<button id = "btnUpdate" onclick="location.href='<%=request.getContextPath() %>/mypage/mypageupdate'"><b>수정</b></button>
				<button id = "btnDelete" onclick="location.href='<%=request.getContextPath() %>/mypage/mypagedelete?user_id_no=<%=session.getAttribute("user_id") %>'"><b>회원 탈퇴</b></button>
			</div>
			
			<br><br>
			
		</div>
	</div>

</div><!-- .container -->

<br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>
