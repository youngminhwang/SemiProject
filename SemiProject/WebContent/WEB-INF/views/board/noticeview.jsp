<%@page import="web.dto.Notice"%>
<%@page import="web.dto.NoticeFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	Notice viewNoticeBoard = (Notice) request.getAttribute("viewNoticeBoard"); %>
<%	NoticeFile noticeFile = (NoticeFile) request.getAttribute("noticeFile"); %>

<script type="text/javascript">
$(document).ready(function() {

	//게시글 목록 버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/board/noticelist");
	})
	
	
	//게시글 수정 버튼
	$("#btnUpdate").click(function() {
		console.log("클릭")
		
		$(location).attr("href", "<%=request.getContextPath() %>/board/noticeupdate?idx=<%=viewNoticeBoard.getIdx() %>");
	})
	
	
	//게시글 삭제 버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/board/noticedelete?idx=<%=viewNoticeBoard.getIdx() %>");
		}
	})
	
	//--- 댓글 버튼 ---
	
	//수정 Form 숨기기
	$(".updateForm").hide(0, function() { })
	
	//댓글 form 수정 버튼
	$(".btnModify").click(function() {
		$(this).parents(".commdiv").find(".updateForm").show();
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

</style>
<br><br><br><br>
<div class="container">

<br>
<a href="<%=request.getContextPath() %>/board/noticelist">자유 게시판 ></a>
<h2><%=viewNoticeBoard.getNoticeTitle() %></h2>
<div><%=viewNoticeBoard.getUsernick() %> (작성자)</div>
<div style="font-size: 12px; color: #ccc;"><%=viewNoticeBoard.getCreateDate() %> 조회 <%=viewNoticeBoard.getNoticeHits() %></div>

<hr>

<div style="margin: 5px;"><%=viewNoticeBoard.getNoticeContent() %></div>
<br>
<hr>

<!-- 첨부파일 -->
<div style="margin: 5px;">
첨부파일 목록
<%	if( noticeFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=noticeFile.getFileSto() %>"
 download="<%=noticeFile.getFileOri() %>">
	<%=noticeFile.getFileOri() %>
</a>
<%	} %>
</div>
<hr>

<div class="text-center">
	<button id="btnList" class="btnMenu">목록</button>
	<%	if( Integer.parseInt(String.valueOf(session.getAttribute("user_no"))) == viewNoticeBoard.getUserno() 
			|| String.valueOf(session.getAttribute("user_rank")).equals("0") ) { %>
	<button id="btnDelete" class="btnMenu">삭제</button>
	<%	} %>
	<%	if( String.valueOf(session.getAttribute("user_rank")).equals("0") ){ %>
	<button id="btnUpdate" class="btnMenu">수정</button>
	<%	} %>
</div>
<br><br>
<hr>

<%@ include file="../comment/noticecommview.jsp" %>

</div><!-- .container -->
<br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>