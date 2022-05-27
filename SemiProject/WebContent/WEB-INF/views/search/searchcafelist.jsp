<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="web.dto.CafeInfo"%>
<%@page import="java.util.List"%>
    
<%@ include file="../layout/header.jsp" %>
    
<%	List<CafeInfo> searchCafeList = (List) request.getAttribute("searchCafeList"); %>

<style type="text/css">
a:link{
	color: #000;
}

a:visited{
	color: #000;
}

a:hover {
	text-decoration: none;
}

.main_list {
    width: 100%;
    height: 100%;
}

.list_start {
	text-align: center;
 	padding-left: 100px;
}

.list_detail {
	float: left;
    width: 40%;
    height: 320px;
    margin: 25px;
}
</style>
<div class="container">
<br><br><br><br>

<h1 style="color: #FF792A;">'<%=request.getAttribute("data") %>'의 검색 결과</h1>

<hr style="height:3px;border:none; color:#ccc; background-color:#ccc;">

<div>

<div class="main_list">
	<div class="list_start">
<%	for(int i=0; i<searchCafeList.size(); i++) { %>
		<div class="list_detail">
			<div>
			<a href="../cafe/view?cafeinfo=<%=searchCafeList.get(i).getCafe_no() %>">
			<img src="<%=request.getContextPath() %>/upload/<%=searchCafeList.get(i).getCafe_cpy_file_name() %>"
			style="width: 400px; height: 250px;">
			</a>
			</div>

			<div style="font-size: 25px;">
			<a href="../cafe/view?cafeinfo=<%=searchCafeList.get(i).getCafe_no() %>">
			<%=searchCafeList.get(i).getCafe_name() %><br>
			</a>
			</div>

			<div style="font-size: 16px; color: #ccc;">
			<b><%=searchCafeList.get(i).getCafe_loc() %></b>
			</div>
			
		</div>	
	<%	} %>
	</div>
	</div>
</div>



</div>
<hr>

<%	if(searchCafeList.size() == 0) {%>
<div style="text-align: center;">
<span style="font-size: 15px;"><b>검색된 카페가 없습니다</b></span>
<hr>
</div>
<%	} %>

</div><!-- .container -->

<%@ include file="../layout/mainsearchpaging.jsp" %>
<br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>