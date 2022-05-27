<%@page import="java.util.List"%>
<%@page import="web.dto.NoticeComm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	List<NoticeComm> noticeCommList = (List) request.getAttribute("noticeCommList"); %>

<%-- 댓글 입력 영역 --%>
<%@ include file="./noticecommwrite.jsp" %>

<h4><b>댓글</b></h4>
<hr>

<!-- 댓글 목록 출력 -->
<%	if( noticeCommList.size() == 0 ) { %>
	<p>등록된 댓글이 없습니다</p>
<%	} else {%>
<%	for(int i=0; i<noticeCommList.size(); i++) { %>
<div style="margin: 5px;">
	<b><%=noticeCommList.get(i).getUsernick() %> (작성자)</b><br>
</div>

<div style="margin: 5px;">
	<%=noticeCommList.get(i).getCommContent() %><br>
</div>

<div class="commdiv" style="margin: 5px;">
	<span style="color: #ccc;"><%=noticeCommList.get(i).getCommDate() %></span>
	
<%	if( noticeCommList.get(i).getUserno() == Integer.parseInt(String.valueOf(session.getAttribute("user_no"))) ) { %>
	<button type="button" class="btnModify" style="border: 0; outline: 0; background: white;"> 수정 </button>
<%	} %>

<%	if( noticeCommList.get(i).getUserno() == Integer.parseInt(String.valueOf(session.getAttribute("user_no"))) 
		|| String.valueOf(session.getAttribute("user_rank")).equals("0") ) { %>
	<a href="<%=request.getContextPath() %>/comm/noticecommdelete?idx=<%=viewNoticeBoard.getIdx() %>&idx_comm=<%=noticeCommList.get(i).getIdxComm() %>" class="btnCommDelete">삭제 </a>
<%	} %>

	<%@ include file="./noticecommupdate.jsp" %>
</div>
<hr>
<%	} %>

<%	} %>
