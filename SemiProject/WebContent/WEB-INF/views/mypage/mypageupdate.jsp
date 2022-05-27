
<%@page import="web.dto.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<% User viewMypageUp = (User) request.getAttribute("viewMypageUp"); %>
<%	List<User> mypageList = (List) request.getAttribute("mypageList"); %>  
<% User viewMypage = (User) request.getAttribute("viewMypage"); %>

<script type="text/javascript">
$(document).ready(function() {
	   
	   //닉네임 중복 검사
	   $("#btnDuplicate").click(function() {
	      $.ajax({
	         type: "get"
	         , url: "/mypage/mypagenickcheck"
	         , data: {
	            user_nick: $("#user_nick").val()
	         }
	         , dataType: "json"
	         , success: function( res ) {
	            console.log("AJAX 성공")
	            console.log( res )
	            
	            if( res.dupl ) {
	               console.log("중복")
	               $("#duplMsg").css("color", "red").html("중복된 닉네임입니다")
	               duplCheck = false;
	            } else {
	               console.log("중복 아님")
	               $("#duplMsg").css("color", "blue").html("사용가능한 닉네임입니다")
	               duplCheck = true;
	            }
	         }
	         , error: function() {
	            console.log("AJAX 실패");
	         }
	      });
	   });
	   
	   $("#btnUpdate").click(function() {
	         if($("#user_pw").val().length == 0) {
	            alert("비밀번호를 입력하세요");
	            $("#user_pw").focus();
	            return false;
	         }
	         if($("#user_pw").val().length < 3) {
	            alert("비밀번호를 3자리 이상 입력하세요");
	            $("#user_pw").focus();
	            return false;
	         }
	         if($("#user_pw_check").val().length == 0) {
	            alert("비밀번호를 확인하세요");
	            $("#user_pw_check").focus();
	            return false;
	         }
	         
	         if( $("#user_pw").val() != $("#user_pw_check").val() ) {
	            alert("비밀번호 확인이 다릅니다");
	            $("#user_pw_check").focus();
	         }
	         
	         if($("#user_nick").val().length == 0) {
	            alert("닉네임을 입력하세요");
	            $("#user_nick").focus();
	            return false;
	         }
	         if($("#user_email").val().length == 0) {
	            alert("이메일을 입력하세요");
	            $("#user_email").focus();
	            return false;
	         }
	         if( !duplCheck ) {
	            return false;
	         } else if ( !duplCheck ) {
	         	return true;
	         }
	         
	         mypageform.submit();
	         
	   }) 
	});

</script>

<style type="text/css">

#btnUpdate, #btnMain {
	border: 4px solid #FF792A;
	border-radius: 10px;
	background: white;
	color: #FF792A;
	margin-right: 5px;
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
<br><br>
<br>
<br>
<div class="mypage">
<form action="/mypage/mypageupdate" method="post" name="mypageform">
	<table style="width: 100%; margin: 0 auto; font-size: 18px; border: 1px solid #888; border-left: 0; border-right: 0;">
	<tr>
		<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
			아이디
		</th>
	<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
		<%=viewMypageUp.getUser_id()%>
	</td>
	</tr>
	
	<tr>
		<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
			비밀번호
		</th>
		<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
			<input type="password" id="user_pw" name="user_pw"/>
		</td>
	</tr>

	<tr>
		<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
			비밀번호 확인
		</th>
		<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
			<input type="password" id="user_pw_check" name="user_pw_check"/>
		</td>
	</tr>
	
	<tr>
		<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
			닉네임
		</th>
		<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
		<div>
			<input type="text" name="user_nick" id ="user_nick" value="<%=viewMypageUp.getUser_nick()%>"class="form-control" style="width: 250px; display: inline-block;"/>
			<button type="button" id="btnDuplicate" style="display: inline-block;">중복확인</button> 
		</div>
			<div id="duplMsg" style="position: relative; "></div>
		</td>
	</tr>
	
	<tr>
		
	<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
		이메일
	</th>
		<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
		<input type="email" id="user_email" name="user_email" value="<%=viewMypageUp.getUser_email()%>"/>
	</td>

	</tr>
	
	<tr>
	<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
		전화번호
	</th>
	<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
		<input type="tel" maxlength="11" id="user_phone" name="user_phone" value="<%=viewMypageUp.getUser_phone()%>"/>
	</td>

	</tr>
	
	<tr>
		<th style="border: 1px solid #888; border-left: 0; border-right: 0; background: #ccc;">
			성별
		</th>

		<td style="border: 1px solid #888; border-left: 0; border-right: 0;">
			<%=viewMypageUp.getUser_gender()%>
		</td>
</tr>
	
</table>
</form>
	<br>
	<br>
	<div style="text-align: center;">
		<button type="button" id="btnUpdate"><b>수정</b></button>
		<button name="main" id="btnMain" onclick="location.href='<%=request.getContextPath() %>/'"><b>메인화면</b></button>
	</div>
	<br>
	<br>
	</div>
</div>

</div><!-- .container -->
<br><br><br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>

