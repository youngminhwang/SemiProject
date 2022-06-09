<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta charset="UTF-8">

<title> Would u like some Coffee? </title>

<style type="text/css">
nav {
	position:fixed; 
	width:100%;
	height:60px; 
	text-align:center;
	bodrer-bottom: 1px solid #ccc;
	top:0;
	left:0;
	z-index: 3;
}

#footer {
	text-align: center;
	background: #FF792A;
}

#header a {
	text-decoration: none;
}

#select, #cafe_park {
	height: 25px;
}

button.headerBtn {
	border: 0;
	outline: 0;
	background: #ccc;
	color: #000;
}

.nav_table > table,td {
	border: 1px solid #ccc;
	border-bottom: 2px solid #ccc;
	background: white;
	padding: 10px;
}

.nav_table > a {
	color: #ccc
}

a:link {
	color: black;
}
a:visited {
	color: black; 
}

input:focus {
	box-shadow: 0;
}
</style>

</head>

<body>

<%if(session.getAttribute("user_no") == null ) { %>
	<% session.setAttribute("user_no", 0); %> 
	<% session.setAttribute("user_rank", 1); %>
<%}%>

<nav>
	<div id="header" style="width: 2114px; align: center;">
		<%if(session.getAttribute("login")==null) { %>
			<table class="nav_table">
			<tr>
				<td style="width: 167.78px; height: 64.39px; overflow: hidden; padding: 0; ">
					<a href="/" style="font-size: 30px;"><img src="<%=request.getContextPath() %>/resources/img/logo.png" style="width: 167.78px; height: 100%; margin:0;"/></a>
				</td>
				
				<td style="width: 1270px; text-align: center; background: white;"></td>
				
				<td style="width: 135px; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/board/freelist'" style="font-size: 18px;"><b>자유 게시판</b></a>
				</td>
				
				<td style="width: 135px; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/board/noticelist'" style="font-size: 18px;"><b>공지사항</b></a>
				</td>
				
				<td style="width: 135px; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/board/inquirelist'" style="font-size: 18px;"><b>문의 게시판</b></a>
				</td>
				
				<td style="width: 135px; background-color: #888; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/member/login'" style="font-size: 18px;"><b>로그인</b></a>
				</td>
			
				<td style="width: 135px; background-color: #888; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/member/join'" style="font-size: 18px;"><b>회원가입</b></a>
				</td>
			</tr>
			</table>
		<%}%>
		
		<%if (session.getAttribute("login") != null &&(boolean) session.getAttribute("login")) { %>
			<table>
			<tr>
				<td>
					<a href="/" style="font-size: 30px; border-right: 0;">커피 한잔?</a>
				</td>
				
				<td style="width: 1271px;">	</td>
			
				<td style="width: 135px; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/board/freelist'" style="font-size: 18px;"><b>자유 게시판</b></a>
				</td>
				
				<td style="width: 135px; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/board/noticelist'" style="font-size: 18px;"><b>공지사항</b></a>
				</td>
				
				<td style="width: 135px; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/board/inquirelist'" style="font-size: 18px;"><b>문의 게시판</b></a>
				</td>	
				
				<td style="width: 135px; background-color: #888; text-align: center;">
					<a onclick="location.href='<%=request.getContextPath() %>/member/logout'" style="font-size: 18px;"><b>로그아웃</b></a>
				</td>
				
				<%	if(Integer.parseInt(String.valueOf(session.getAttribute("user_rank"))) == 0) { %>
					<td style="width: 135px; background-color: #888; text-align: center;">
						<a onclick="location.href='<%=request.getContextPath() %>/admin'" style="font-size: 18px;"><b>관리자 페이지</b></a>
					</td>
				<%	} else if(Integer.parseInt(String.valueOf(session.getAttribute("user_rank"))) == 1) { %>
					<td style="width: 135px; background-color: #888; text-align: center;">
						<a onclick="location.href='<%=request.getContextPath() %>/mypage/mypageview'" style="font-size: 18px;"><b>마이 페이지</b></a>
					</td>
				<%	} %>
			</tr>
			</table>
		<%} %>
	</div>
</nav>

<header>

<div style="width: 100%; height: 400px; border-bottom: 2px solid #ccc; position: relative; overflow: hidden;">
	<img src="<%=request.getContextPath() %>/resources/img/banner.jpg" style="width: 100%; height: 535.2px; position: absolute;"/>

	<br><br><br><br><br><br><br><br><br><br><br><br><br>

	<form action="/search/list" method="get" id="selectForm" >
	<div id="search_bar"style="width: 600px; height: 50px; border:1px solid:#ccc; border-radius: 5px; margin:0 auto; position: relative; background: white;">
		<select id="select" name="select"  class="form-control" style="width: 100px; height: 48.0px; float:left; border: 1px solid white;">
			<option value="cafe_name">카페명</option>
			<option value="cafe_loc">지역</option>
			<option value="tag_conn">태그</option>
		</select>
	
		<input id="textbox" type="text" name="data" value="" class="form-control input-lg" placeholder="검색어를 입력해주세요" style="width: 450px; height: 100%; border:0;"/>
		<input class="headerBtn" type="submit" value="검색" style="font-size: 17px; position: relative; width: 50px; height: 100%; border: 0px; border-radius: 5px; background:#FF792A; color:white; margin-left: 448px; position: absolute; top:0% ">
	</div>
	</form>
</div>

</header>


