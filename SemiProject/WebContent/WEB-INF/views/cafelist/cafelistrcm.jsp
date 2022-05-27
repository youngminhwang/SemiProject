<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="web.dto.CafeInfo"%>
<%@page import="java.util.List"%>
    
<%@ include file="../layout/header.jsp" %>
    
<%	List<CafeInfo> rcmCafeAllList = (List) request.getAttribute("rcmCafeAllList"); %>

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
<br><br><br>
<div class="container">
<br>
<h2 style="color: #FF792A;"><b>좋아요순 카페 리스트</b></h2>

<hr style="height:3px;border:none; color:#ccc; background-color:#ccc;">

<div>

<div class="main_list">
	<div class="list_start">
<%	for(int i=0; i<rcmCafeAllList.size(); i++) { %>
		<div class="list_detail">
			<div>
			<a href="../cafe/view?cafeinfo=<%=rcmCafeAllList.get(i).getCafe_no() %>">
			<img src="<%=request.getContextPath() %>/upload/<%=rcmCafeAllList.get(i).getCafe_cpy_file_name() %>"
			style="width: 400px; height: 250px;">
			</a>
			</div>

			<div style="font-size: 25px;">
			<a href="../cafe/view?cafeinfo=<%=rcmCafeAllList.get(i).getCafe_no() %>">
			<%=rcmCafeAllList.get(i).getCafe_name() %><br>
			</a>
			</div>

			<div style="font-size: 16px; color: #ccc;">
			<b><%=rcmCafeAllList.get(i).getCafe_loc() %></b>
			</div>
			
		</div>	
	<%	} %>
	</div>
	</div>
</div>



</div>
<hr>

</div><!-- .container -->

<%@ include file="../layout/rcmallcafepaging.jsp" %>

<br><br><br><br><br><br>
<%@ include file="../layout/footer.jsp" %>