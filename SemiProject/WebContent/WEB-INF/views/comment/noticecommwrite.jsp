<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
div.comment_write {
	border: 1px solid #ccc;
	border-radius: 5px;
	height: 130px;
}

div.comment_in {
	margin: 20px;
}

#commcnt {
	border: none;
	resize: none;
}

#commcnt:focus {
	outline: none;
}

div.comment_usernick {
	margin-bottom: 10px;
	font-size: 16px;
	
}

#btnInsert {
	float: right;
	border: 0;
	outline: 0;
	background: white;
	color: #ccc;
}

</style>

<%	if( session.getAttribute("login") != null && (boolean) session.getAttribute("login") ) { %>
<form action="/board/noticeview" method="post" class="insertForm">
<input type="hidden" name="idx" value="<%=viewNoticeBoard.getIdx() %>" />
<div class="comment_write">
	<div class="comment_in">
	
		<div class="comment_usernick">
		<b><%=session.getAttribute("user_nick") %></b>
		</div>

		<div class="comment_insert">
			<textarea id="commcnt" name="commcnt" placeholder="댓글을 입력하세요." style="width: 900px; height: 30px;"></textarea>
		</div>
		
		<div>
			<button type="button" id="btnInsert" data-btn="insert">등록</button>
		</div>
	</div>
</div>
</form>

<hr>
<%	} %>