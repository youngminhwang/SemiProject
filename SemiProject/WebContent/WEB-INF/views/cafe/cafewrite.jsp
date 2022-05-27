<%@page import="web.dto.Tag"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/head.jsp" %>

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
	
});





</script>


<style type="text/css">
td {
	text-align: center;
}

</style>

<div id="content" class="container">

<h1>카페 정보 입력</h1>
<hr>

<form action="./cafewrite" method="post" enctype="multipart/form-data" class="form-inline">

<table class="table table-bordered">
<tr><td>카페이름</td><td><input type="text" name="cafe_name" style="width:100%" placeholder ="예시) 카페 이름" class="form-control"/></td></tr>
<!-- <tr><td>카페메뉴</td><td><input type="text" name="cafe_menu" style="width:100%"class="form-control"/></td></tr> -->
<tr><td>카페메뉴</td><td><textarea id="cafe" name="cafe_menu" style="width:100%" placeholder ="예시) 아이스아메리카노, 아이스라떼..." class="form-control"></textarea></td></tr>
<tr><td>카페주소</td><td><input type="text" name="cafe_loc" style="width:100%" placeholder ="예시) 서울시 강남구" class="form-control"/></td></tr>
<tr><td>전화번호</td><td><input type="text" name="cafe_tel" style="width:100%" placeholder ="예시) 0212345678" class="form-control"/></td></tr>
<tr><td>영업시간</td><td><input type="text" name="cafe_time" style="width:100%" placeholder ="예시) 09:00 ~ 18:00" class="form-control"/></td></tr>
<tr>
	<td>주차가능여부</td>
	<td><label><input type="radio" name="cafe_park" value = "Y"/>주차 가능</label>&nbsp&nbsp&nbsp&nbsp&nbsp
		<label><input type="radio" name="cafe_park" value = "N" checked="checked"/>주차 불가</label>	
	</td>
</tr>
<tr><td>카페 사진 첨부파일</td><td><input type="file" name="file" style="width:100%"/></td></tr>


</table>


<h3>카페에 어울리는 태그 선택</h3> 


<%	for(int i=0; i<tagList.size(); i++) { %>

<label>&nbsp<input type="checkbox" name="tag_name" value="<%=tagList.get(i).getTag_no() %>">&nbsp&nbsp<%=tagList.get(i).getTag_name() %></label>	

<%	} %>

</form>



<div>
	<button id="btnWrite">작성</button>
	<button id="btnCancel">취소</button>
</div>



</div><!-- .container -->

</body>
</html>

