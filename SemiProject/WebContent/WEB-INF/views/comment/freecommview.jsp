<%@page import="java.util.List"%>
<%@page import="web.dto.FreeComm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	List<FreeComm> freeCommList = (List) request.getAttribute("freeCommList"); %>

<%-- 댓글 입력 영역 --%>
<%@ include file="../comment/freecommwrite.jsp" %>

<h4><b>댓글</b></h4>
<hr>

<!-- 댓글 목록 출력 -->
<%	if( freeCommList.size() == 0 ) { %>
	<p>등록된 댓글이 없습니다</p>
<%	} else {%>
<%	for(int i=0; i<freeCommList.size(); i++) { %>
<div style="margin: 5px;">
	<b><%=freeCommList.get(i).getUsernick() %> (작성자)</b><br>
</div>

<div style="margin: 5px;">
	<%=freeCommList.get(i).getCommContent() %><br>
</div>

<div class="commdiv" style="margin: 5px;">
	<span style="color: #ccc;"><%=freeCommList.get(i).getCommDate() %></span>
	
<%	if( freeCommList.get(i).getUserno() == Integer.parseInt(String.valueOf(session.getAttribute("user_no"))) ) { %>
	<button type="button" class="btnModify" style="border: 0; outline: 0; background: white;"> 수정 </button>
<%	} %>

<%	if( freeCommList.get(i).getUserno() == Integer.parseInt(String.valueOf(session.getAttribute("user_no")))
		|| String.valueOf(session.getAttribute("user_rank")).equals("0") ) { %>
	<a href="<%=request.getContextPath() %>/comm/freecommdelete?idx=<%=viewFreeBoard.getIdx() %>&idx_comm=<%=freeCommList.get(i).getIdxComm() %>" class="btnCommDelete">삭제 </a>
<%	} %>

<%@ include file="./freecommupdate.jsp" %>
</div>
<hr>
<%	} %>

<%	} %>
