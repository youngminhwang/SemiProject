<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	$("#btnFind").click(function() {
	    if($("#user_email").val().length == 0) {
	        alert("이메일을 입력하세요");
	        $("#user_email").focus();
	        return false;
	    }
	         
	         idFind.submit();
	         
	   }) 
});

</script>

<style type="text/css">
#btnFind, #btnCancel {
	border: 0;
	border-radius: 5px;
	background: #FF792A;
	color: white;
	width: 80px;
	height: 30px;
	font-size: 15px;
}
</style>
<br><br><br><br><br>
<br><br><br><br><br>
<div class="container">

<form action="/member/idFind" method="post" name="idFind" >

	<div style="width:100%; height:550px;">
       <div style="width:470px; margin:0 auto; height:290px; border:2px solid #FF792A;; border-radius:10px;">
       <h1 style="margin-left: 20px; color:#FF792A;"><b>아이디 찾기</b></h1>
       <hr style="height: 2px;border:none; color:#FF792A; background-color:#FF792A;">
		
		<br><br>
		<div>
		
			<label for="user_email" style="margin-left: 20px; margin-right: 20px; margin-bottom: 20px; color:#888;"><b>이메일 입력</b></label>
		
			<input type="text" id="user_email" name="user_email" size=40 style="height:30px; border: 2px solid #ccc; border-radius: 5px;">	
		
		</div>
		
		<div class="text-center">
			<br>
			<button id="btnFind" style="margin-right: 5px;">찾기</button>
			<input type="button" value="취소" onclick="history.go(-1);" id="btnCancel" style="margin-left: 5px;" />
			
		</div>
		</div>
	
	</div>
	
</form>
	
</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>


