<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./layout/head.jsp" %>

<style type="text/css">
.btnMove {
	border: 0;
	border-radius: 5px;
	background: #FFDAA2;
	color: red;
	font-size: 16px;
	width: 100px;
	height: 30px;
}
</style>

<div id="content" class="container">

<div class = "text-center" style="width: 800px; height: 830px;">

<!-- 비로그인 상태 -->
<% if ( session.getAttribute("login")==null) { %>
<strong>관리자 전용 메뉴를 이용하기 위해 관리자 계정으로 로그인이 필요합니다</strong><br><br><br><br>
<button onclick="location.href='<%=request.getContextPath() %>/member/login'" class="btn btn-default">로그인!</button>
<button onclick="location.href='<%=request.getContextPath() %>/member/join'" class="btn btn-default">회원가입!</button>
<% } %>

<!-- 로그인 -->
<% if (session.getAttribute("login") != null &&(boolean) session.getAttribute("login") && Integer.parseInt(String.valueOf(session.getAttribute("user_rank"))) == 0 ) { %>
<br><br><br>
<div style="width: 1170px; margin: 0 auto;">
<img src="../resources/img/successLoginAdmin.png" style="width:500px; height:600px; "/>
<div style="position: relative; top: -485px; right: -588px;">
	<p style="font-size: 20px; position: absolute;"><b><%=session.getAttribute("user_nick") %></b></p>
</div>
<div style="position: relative; top: -535px; right: -497px;">
	<p style="font-size: 20px; position: absolute;"><b>Server</b></p>
</div>
<br><br>
<button type="button" class="btnMove" onclick="location.href='<%=request.getContextPath() %>/'" class="btn btn-default" style="margin-right: 5px;"><b>메인 페이지</b></button>
<button type="button" class="btnMove" onclick="location.href='<%=request.getContextPath() %>/member/logout'" class="btn btn-default" style="margin-right: 5px;"><b>로그아웃!</b></button>
</div>
<% } %>

</div>
</div><!-- .container -->


</body>
</html>
