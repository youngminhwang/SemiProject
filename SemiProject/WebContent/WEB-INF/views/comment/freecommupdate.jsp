<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
div.comment_modify {
	border: 1px solid #ccc;
	border-radius: 5px;
	height: 95px;
}

div.comment_in {
	margin: 20px;
}

#commcntModify {
	border: 0;
	resize: none;
}

#commcntModify:focus {
	outline: none;
}
 
#commCancel, #commModify {
	float: right;
	border: 0;
	outline: 0;
	background: white;
	color: #ccc;
}

</style>


<form action="/comm/freecommupdate" method="post" class="updateForm">
<input type="hidden" name="idx" value="<%=viewFreeBoard.getIdx() %>" />
<input type="hidden" name="idx_comm" value="<%=freeCommList.get(i).getIdxComm() %>" />
<div class="comment_modify">
	<div class="comment_in">
		<div class="comment_insert">
			<textarea id="commcntModify" name="commcntModify" style="width: 1000px; height: 30px;"><%=freeCommList.get(i).getCommContent() %></textarea>
		</div>
		<div>
			<button id="commCancel" type="button" data-btn="cancel">취소</button>
			<button id="commModify" type="button" data-btn="modify">수정</button>
		</div>
	</div>
</div>
</form>