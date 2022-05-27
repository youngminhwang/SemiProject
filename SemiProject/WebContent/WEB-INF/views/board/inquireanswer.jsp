<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<!-- 스마트에디터2 설치 -->
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		//submit전에 스마트에디터에 작성된 내용을 <textarea>로 반영한다
		submitContents( $("#btnWrite") );
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});

//스마트에디터에 작성한 내용을 <textarea>에 반영하는 함수
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit을 수행한다
		elClickedObj.form.submit();
	} catch(e) {}
	
}
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
<br><br><br><br>
<div class="container">

<h3><b>답변 글쓰기</b></h3>
<hr>

<div>
<form action="/board/inquireanswer" method="post">

<textarea id="title" name="title" placeholder="제목을 입력하세요.">ㄴ(답변)</textarea>

<%@ include file="../layout/boardrule.jsp" %>

<textarea id="content" name="content" placeholder="내용을 입력하세요."></textarea>

<hr>

</form>
</div>


<div class="text-center">	
	<button type="button" id="btnCancel" class="btnMenu">취소</button>
	<button type="button" id="btnWrite" class="btnMenu">작성</button>
</div>

<!-- .container -->
</div>
<br><br><br><br><br><br>



<!-- <textarea>태그에 스마트에디터2를 스킨 적용하는 스크립트 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //스킨을 적용할 <textarea>의 id를 적어준다
	sSkinURI: "../resources/se2/SmartEditor2Skin.html",
	fCreate: "createSEditor2"
})
</script>



<%@ include file="../layout/footer.jsp" %>


