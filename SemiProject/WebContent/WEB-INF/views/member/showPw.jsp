<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	String user_pw = (String) request.getAttribute("user_pw"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//취소버튼 동작
	$("#btnBack").click(function() {
		history.go(-1);
	});
	
});
</script>

<style type="text/css">
.btnFail {
	border: 0;
	border-radius: 5px;
	background: #FF792A;
	color: white;
	font-size: 16px;
	width: 100px;
	height: 30px;
}
</style>

<div class="container" style="height: 100%">

<%	if( user_pw == null ) { %>
<br><br><br><br><br>
<br><br><br><br><br>
	<div style="width: 600px; margin: 0 auto; text-align: center;">
		<img src="../resources/img/pwfindfail.jpg" style="width: 420.99px; height: 100%; margin: 0 auto;"/><br>
		<br>
		<h2>입력하신 정보로 조회된 결과가 없습니다.</h2>
		<h4>확인 후 다시 조회해주세요</h4>
		<button type="button" class="btnFail" onclick="location.href='<%=request.getContextPath() %>/'" style="margin-right: 5px;"><b>메인 페이지</b></button>
		<button type="button" class="btnFail" id="btnBack" style="margin-left: 5px;"><b>뒤로가기</b></button>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br>
<%	} else if( user_pw != null) { %>
<br><br><br><br><br>
<div style="width: 600px; margin: 0 auto;">
	<img src="../resources/img/successLoginUser.jpg" style="width: 420.99px; height: 100%; margin: 0 auto;"/>
	<br>
	<h2>비밀번호 찾기 성공!</h2>
	<span>고객님의 비밀번호는 <%=user_pw %>입니다.</span>
	<hr>
	<button type="button" onclick="location.href='<%=request.getContextPath() %>/'" style="margin-right: 5px;"><b>메인 페이지</b></button>
	<button type="button" onclick="location.href='<%=request.getContextPath() %>/member/login'" style="margin-left: 5px;"><b>로그인</b></button>
</div>
<br><br><br><br><br><br><br><br><br><br>
<%	} %>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>
