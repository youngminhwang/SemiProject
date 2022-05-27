<%@page import="web.dto.Inquire"%>
<%@page import="web.dto.InquireFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	Inquire viewInquireBoard = (Inquire) request.getAttribute("viewInquireBoard"); %>
<%	InquireFile inquireFile = (InquireFile) request.getAttribute("inquireFile"); %>

<script type="text/javascript">
$(document).ready(function() {

	//게시글 목록 버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/board/inquirelist");
	})
	
	
	//게시글 수정 버튼
	$("#btnUpdate").click(function() {
		console.log("클릭")
		
		$(location).attr("href", "<%=request.getContextPath() %>/board/inquireupdate?idx=<%=viewInquireBoard.getIdx() %>");
	})
	
	
	//게시글 삭제 버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/board/inquiredelete?idx=<%=viewInquireBoard.getIdx() %>");
		}
	})
	
	//--- 댓글 버튼 ---
	
	//수정 Form 숨기기
	$(".updateForm").hide(0, function() { })
	
	//댓글 form 수정 버튼
	$(".btnModify").click(function() {
		$(this).parents(".parentdiv").find(".updateForm").show();
	})
	
	//댓글 작성 버튼
	$("[data-btn='insert']").click(function() {
		$(this).parents(".insertForm").submit();
	})

	
	//댓글 수정 버튼
	$("[data-btn='modify']").click(function() {
		$(this).parents(".updateForm").submit();
	})
	
	
	//댓글 수정취소 버튼
	$("[data-btn='cancel']").click(function() {
		$(this).parents(".updateForm").hide();
	});
	
});
</script>

<style type="text/css">
a:hover {
	text-decoration: none;
}

.container {
	border: 1px solid #ccc;
	border-radius: 5px;

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
}

#btnList {
 float: left;
}

#btnUpdate {
	float: right;
}

#btnDelete {
	float: right;
} 


}
</style>
<br><br><br><br><br><br><br><br>
<div class="container">

<br>
<a href="<%=request.getContextPath() %>/board/inquirelist">문의 게시판 ></a>
<h2><%=viewInquireBoard.getInquireTitle() %></h2>
<div><%=viewInquireBoard.getUsernick() %> (작성자)</div>
<div style="font-size: 12px; color: #ccc;"><%=viewInquireBoard.getCreateDate() %> 조회 <%=viewInquireBoard.getInquireHits() %></div>
<hr>

<div style="margin: 5px;">
<%=viewInquireBoard.getInquireContent() %>
</div>
<br>
<hr>


<!-- 첨부파일 -->
<div style="margin: 5px;">
첨부파일 목록
<%	if( inquireFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=inquireFile.getFileSto() %>"
 download="<%=inquireFile.getFileOri() %>">
	<%=inquireFile.getFileOri() %>
</a>
<%	} %>
</div>
<hr>

<div class="text-center">
	<button id="btnList" class="btnMenu">목록</button>
	<%	if( Integer.parseInt(String.valueOf(session.getAttribute("user_no"))) == viewInquireBoard.getUserno() 
		|| String.valueOf(session.getAttribute("user_rank")).equals("0") ) { %>
	<button id="btnDelete" class="btnMenu">삭제</button>
	<%	} %>
	<%	if( Integer.parseInt(String.valueOf(session.getAttribute("user_no"))) == viewInquireBoard.getUserno() ) { %>
	<button id="btnUpdate" class="btnMenu">수정</button>
	<%	} %>
</div>
<br><br>
<hr>

</div><!-- .container -->
<br><br><br><br><br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>