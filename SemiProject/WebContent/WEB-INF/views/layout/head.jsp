<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>++관리자페이지++</title>


<style type="text/css">
#header, #footer {
	text-align: center;
	background:#FFDAA2;
}

#header  a {
	/* 하이퍼링크 밑줄 없애기(안보이게 하기) */
	text-decoration: none;
}

#menu {
	list-style-type: none;
	background-color: #FFDAA2;
	width : 15%;
	padding: 0;
	margin: 0;
	position: fixed;
	height: 100%;
	overflow: auto;
	text-align: center;
	margin : 0;
	font-weight: bold;
	color: #FFF;
}

#sidemenu a {
	text-decoration: none;
	display: block;
 	line-height: 50px;
	color: #000;
}

div li a:hover {
	background-color: tomato;
}

#content {
	margin-left: 15%;
	height: 100%
}

</style>

</head>

<body>

<div id="wrap">


<section>
<!-- <div id="header"> -->
	
<!-- 	<!-- header.jsp 의 로고(상단 텍스트)에 "/" 로 <a> 를 적용 -->
<!-- 	<h1 ><a href = "/">관리자페이지</a></h1> -->

<!-- </div> -->

<div id="sidemenu">

<ul id="menu">

<div>
	<h1 ><a href = "/" style="text-decoration: none; color: #FFF;">MAIN</a></h1>
</div>

<br><br><br>
	<li><a href="/board/userlist">회원 관리</a></li>
	<li><a href="/board/boardadmin">게시판 관리</a></li>
	<li><a href="/cafe/cafelist">카페 관리</a></li>
	<li><a href="/tag/taglist">태그 관리</a></li>
</ul>
</div>
</section>
</div>

	
