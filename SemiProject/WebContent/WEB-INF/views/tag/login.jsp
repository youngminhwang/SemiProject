<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	
	//페이지 접속 시 아이디 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//패스워드 입력 창에서 엔터키 입력 시 submit하도록 한다
	$("input").eq(1).keydown(function( e ) {
		if( e.keyCode == 13 ) { //엔터키
			$(this).parents("form").submit();
		}
	});

	//로그인 버튼 클릭 시 submit하도록 한다
	$("#btnLogin").click(function() {
		var id = $("#user_id").val();
		var pw = $("#user_pw").val();
		var url ="/login/check";
		$.ajax({
			type:"get"
			,url:url
			,data:{"id":id,"pw":pw}
			,dataType:"json"
			,success:function(data){
				if(data.result){
					login();
				}else{
					alert("아이디 정보가 맞지 않습니다.");
					
				}
			}, error: function() {
	            console.log("실패");
			}
			
		});
	})

	
	//취소 버튼 클릭 시 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
// 		location.href = "/"
	})
	
})
function login(){  
// 	$(this).parents("form").submit();
	loginForm.submit();
}
</script>

<style type="text/css">
form {
	width: 600px;
	margin: 0 auto;
}

#user_id, #user_pw {
	width: 280px;
}

#btnLogin, #btnCancel {	
	width: 280px;
	height: 50px;
	border: 3px solid #FF792A;
	color: white;
	font-size: 30px;
	border-radius: 5px; 
	background: #FF792A;
	padding-right: 10px;
	padding-left: 10px;
	font-color: #FF792A;
	margin-bottom: 40px;
	margin-top: 20px;
}


</style>

<div class="container" style="height:830px;">
<br><br><br>

<br><br><br><br><br>

<form action="./login" method="post" class="form-horizontal" name=loginForm>

<div style="border: 1px solid #ccc; border-radius: 10px;">
<h2 style="margin-left: 20px;">로그인</h2>
<br>
	<div class="form-group" style="margin-left: 50px;">
		<label for="user_id" class="control-label col-xs-2"><b>아이디</b></label>
		<div class="col-xs-10">
			<input type="text" id="user_id" name="user_id" class="form-control">
		</div>
	</div>

	<div class="form-group" style="margin-left: 50px;">
		<label for="user_pw" class="control-label col-xs-2"><b>패스워드</b></label>
		<div class="col-xs-10">
			<input type="text" id="user_pw" name="user_pw" class="form-control">
		</div>
	</div>
	<div class="text-center">
		<button type="button" id="btnLogin">로그인</button>
	</div>
	
	<div class="find-information">
		<button type="button" id="btnFindUserid">아이디 찾기</button>
		<button type="button" id="btnFindUserpw">비밀번호 찾기</button>	
	</div>
	
</div>

</form>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>












