<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("탈퇴 하시겠습니까?") ) {
			formDelete.submit();
		}
	})

});
</script>

<br><br><br><br><br><br>

<div id="content" class="container">

<h1 style="text-align: center;">회원탈퇴</h1>

<hr>

<form action="/mypage/mypagedelete" method="post" name="formDelete">
<h3 style="text-align: center;">회원탈퇴를 진행하시겠습니까?</h3><br><br>

	<div style="text-align: center;"><label>비밀 번호 &nbsp&nbsp<input type="text" name ="user_pw" id="user_pw"> </label></div>
	
	<br><br>
	
	<div style="text-align: center;">
		<button id ="btnDelete" class="btn btn-default">회원 탈퇴</button>
		<button id="btnCancel" class="btn btn-default">취소</button>
	</div>
</form>

</div><!-- container -->

<br><br><br><br><br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>
