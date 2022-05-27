<%@page import="web.dto.Inquire"%>
<%@page import="web.dto.InquireFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	Inquire updateInquireBoard = (Inquire) request.getAttribute("updateInquireBoard"); %>
<%	InquireFile inquireFile = (InquireFile) request.getAttribute("inquireFile"); %>

<!-- 스마트에디터 2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js"></script>

<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		
		//스마트 에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
		submitContents( $("#btnUpdate") )
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	
	
	//파일이 없을 경우
	if(<%=inquireFile != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=inquireFile == null %>) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})

});
</script>

<style type="text/css">
#title {
	border: 1px solid #ccc;
	resize: none;
	padding: 10px;
	width: 100%;
	height: 40px;
	border-radius: 5px;
	overflow: hidden;
}

#content {
	width: 100%;
	height: 600px;
}

.btnMenu {
	border: none;
 	padding-top: 5px;
	padding-bottom: 5px;
	padding-left: 7px;
	padding-right: 7px;
 	background: #EFF0F2;
	color: black; 
	
 	border-radius: 5px; 
 	margin: 5px;
 	float: right;
}
</style>

<div class="container">

<br>
<h3>게시글 수정</h3>
<hr>

<div>
<form action="/board/inquireupdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="idx" value="<%=updateInquireBoard.getIdx() %>" />

<textarea id="title" name="title"><%=updateInquireBoard.getInquireTitle() %></textarea></td></tr>

<%@ include file="../layout/boardrule.jsp" %>

<textarea id="content" name="content"><%=updateInquireBoard.getInquireContent() %></textarea></td></tr>

<hr>

<!-- 첨부파일 -->
<div>

	<div id="beforeFile">
<%	if( inquireFile != null ) { %>
		기존 첨부파일: 
		<a href="<%=request.getContextPath() %>/upload/<%=inquireFile.getFileSto() %>"
		 download="<%=inquireFile.getFileOri() %>">
			<%=inquireFile.getFileOri() %>
		</a>
		<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
<%	} %>
	</div>

	<div id="afterFile">
		새 첨부파일:
		<input type="file" name="file" />
	</div>
</div>

<br>
</form>
</div>
<hr>

<div class="text-center">	
	<button type="button" id="btnCancel" class="btnMenu">취소</button>
	<button type="button" id="btnUpdate" class="btnMenu">수정</button>
</div>

<!-- .container -->
</div>
<br>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용될 <textarea>의 id를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

<%@ include file="../layout/footer.jsp" %>
