<%@page import="web.dto.Tag"%>
<%@page import="java.util.List"%>
<%@page import="web.dto.CafeTag"%>
<%@page import="web.dto.CafeFile"%>
<%@page import="web.dto.Cafe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/head.jsp" %>

<%-- <% Cafe viewCafe = (Cafe) request.getAttribute("viewCafe"); %> --%>
<% Cafe viewCafe = (Cafe) request.getAttribute("viewCafe"); %>
<% Cafe updateCafe = (Cafe) request.getAttribute("updateCafe"); %>
<% CafeFile cafeFile = (CafeFile) request.getAttribute("cafeFile"); %>
<% CafeTag cafeTagName = (CafeTag) request.getAttribute("cafeTagName"); %>

<% List<Tag> tagList = (List) request.getAttribute("tagList"); %>
<% Tag viewTag = (Tag) request.getAttribute("viewTag"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	
	//파일이 없을 경우
	if(<%=cafeFile != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=cafeFile == null %>) {
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


<div id="content" class="container">

<h1>카페 정보 입력</h1>
<hr>

<form action="/cafe/cafeupdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="cafe_no" value="<%=updateCafe.getCafe_no() %>" />
<input type="hidden" name="fileno" id="fileno" value="<%=cafeFile.getCafe_file_no()%>">
<table class="table table-bordered">
<tr>
	<td>카페이름</td><td><input type="text" name="cafe_name" style="width:100%" value="<%=updateCafe.getCafe_name()%>"/></td>
</tr>
<tr>
	<td>카페메뉴</td><td><textarea name="cafe_menu" style="width:100%"><%=updateCafe.getCafe_menu()%></textarea></td>
</tr>
<tr>
<td >카페 태그</td><td colspan="3"><%=cafeTagName.getTag_name() %></td>
</tr>
<tr>
	<td>카페주소</td><td><input type="text" name="cafe_loc" style="width:100%" value="<%=updateCafe.getCafe_loc()%>" /></td>
</tr>
<tr>
	<td>전화번호</td><td><input type="text" name="cafe_tel" style="width:100%" value="<%=updateCafe.getCafe_tel()%>"/></td>
</tr>
<tr>
	<td>영업시간</td><td><input type="text" name="cafe_time" style="width:100%" value="<%=updateCafe.getCafe_time()%>"/></td>
</tr>
<tr>
	<td>주차가능여부</td><td><input type="text" name="cafe_park" style="width:100%" value="<%=updateCafe.getCafe_park()%>"/></td>
</tr>
<tr>
	<td>카페 사진 새 첨부파일</td>
	<td>
		<input type="file" name="file" style="width:100%" />
	</td>
</tr>

</table>

	<div id="beforeFile">
<%	if( cafeFile != null ) { %>
		기존 첨부파일: 
		<a href="<%=request.getContextPath() %>/upload/<%=cafeFile.getCafe_cpy_file_name()%>"
		 download="<%=cafeFile.getCafe_org_file_name() %>">
			<%=cafeFile.getCafe_org_file_name() %>
		</a>
		<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
<%	} %>
	</div>


<h3>카페에 어울리는 태그 선택</h3> 


<%	for(int i=0; i<tagList.size(); i++) { %>

<label>&nbsp<input type="checkbox" name="tag_name" value="<%=tagList.get(i).getTag_no() %>">&nbsp&nbsp<%=tagList.get(i).getTag_name() %></label>	

<%	} %>





</form>

<div>
	<button id="btnWrite">작성</button>
	<button id="btnCancel">취소</button>
</div>
<br><br><br><br><br><br><br><br><br>

</div><!-- .container -->


</body>
</html>


