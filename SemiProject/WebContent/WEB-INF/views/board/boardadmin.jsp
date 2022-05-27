<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/head.jsp" %>


<style>

.circle {

	background-color: #FFDAA2;
	width: 200px;
	height: 200px;
	-webkit-border-radius : 150px;
	-moz-border-radius:150px;
}

#free, #notice, #qna {

	display: inline-block;
	float: left;
	margin-left: 100px;
	font-size:	15pt;
	border: 0;
	outline: 0;

}


a {

	text-decoration: none;
	color: black;
}

</style>





<br><br><br><br>
<div id="content" class="container">

<h1>게시판 관리</h1>
<hr>
<br><br><br><br><br><br><br>
<!-- 추후에 링크 걸어야함 -->




<button  class = "circle" id = "free" onclick="location.href='<%=request.getContextPath() %>/board/freelist'" class="btn btn-default">자유게시판</button>
<button  class = "circle" id = "notice" onclick="location.href='<%=request.getContextPath() %>/board/noticelist'" class="btn btn-default">공지사항</button>
<button   class = "circle" id = "qna" onclick="location.href='<%=request.getContextPath() %>/board/inquirelist'" class="btn btn-default">문의사항</button>






</div><!-- .container -->

<%-- <%@ include file="../layout/footer.jsp" %> --%>
