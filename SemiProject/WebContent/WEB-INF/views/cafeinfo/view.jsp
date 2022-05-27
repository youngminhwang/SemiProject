<%@page import="web.dto.UserInfo"%>
<%@page import="web.dto.CafeTag"%>
<%@page import="web.dto.CafeFile"%>
<%@page import="web.dto.CafeReview"%>
<%@page import="java.util.List"%>
<%@page import="web.dto.CafeReviewFile"%>
<%@page import="web.dto.CafeInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>    
<%
	List<CafeReview> cafereview =(List) request.getAttribute("cafereviewList");
	CafeInfo viewCafeInfo = (CafeInfo) request.getAttribute("viewCafe");
  	List<CafeReviewFile> cafereviewFile = (List) request.getAttribute("cafereviewFile");  
// 	CafeReviewFile cafereviewFile = (CafeReviewFile) request.getAttribute("cafereviewFile");
  	List<CafeReview> cafeList = (List) request.getAttribute("reviewList");
    List<CafeFile> cafefile=(List) request.getAttribute("CafeFileList");
    List<CafeTag> cafetag = (List) request.getAttribute("cafetag");
    int res =(int) request.getAttribute("res");
    float starAvg = (float) request.getAttribute("starAvg");
    
%>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />



<style>
.delete{
	float: right;
}
.cafeimg {
    max-width: 100%;
    height: 300px;
    width:100%;
    margin: 0px auto;
    
}

.cafephoto{
	height : 300px;
  	width : 100%;
}
 
.slider {
	width: 80%;
	margin: 0px auto;
	bottom : -27px;
}
.slick-prev:before, .slick-next:before {
	color: #444444;
	width:700px;
	margin: 0px auto;
}

.middle{
   height: 50px;
   margin:0px auto;
}
#star{
  	height: 15px;
    width:15px;
}
.photo{
	height: 250px;
}
.cafeFile{
  	height : 300px;
  	width : 100%;
}

div.cafeinfo{
	padding: 40px 10px;
	
}
ul{
   list-style:none;
}
#btnReview{
	border: 3px solid #FF792A;
	border-radius: 50%;	
	background: white;
	color: #FF792A;
	float: right;
	font-size: 18px;
	width: 70px;
	height: 70px;
	position: relative;
 	top: -76px;
 	margin-right: 90px;
}   

.rcm.true{
 -webkit-text-fill-color: red;
}
.rcm.false{
	display: red;
}

#btnLike {
	border: 3px solid #FF792A;
	border-radius: 50%;	
	background: white;
	color: red;
	float: right;
	font-size: 41px;
	width: 70px;
	height: 70px;
	position: relative;
 	top: -127px;
 	margin-right: -150px;
}
</style>
<script type="text/javascript">


function delete1(){
	var link = document.getElementById("delete_review").value;
	 if (confirm("삭제하시면 복구가 되지 않습니다.\n정말로 삭제하시겠습니까?") == true){  
		 location.href=link;
	 }
}

 
$(".btn-delete").click(function(event) {
    var $input = $(".inp-img");
    var $preview = $('#preview');
    resetInputFile($input, $preview);
});
$(document).ready(function(){
	
	$('.slider').slick({
		  infinite: true,
		  slidesToShow: 4,
		  slidesToScroll: 4
		});
})
function starToPercent() {
    const score = +this.restaurant.averageScore * 20;
    return score + 1;
}



</script>
	
<br><br><br><br>

<div class="sliderbox" style="height: 300px;">
<div class="slider" style="height:300px;">
<%-- <% for(int i =0; i<0;i++){ %> --%>
<%-- 	<img src="/upload/<%=cafeFile.getCafe_cpy_file_name()%>"> --%>
<%--  <%} %> --%>
	<%for(int i=0; i<cafefile.size();i++){ %>      
      <div class="photo">
      <a href="#cafe<%=i%>" rel="modal:open">
      <img src="/upload/<%=cafefile.get(i).getCafe_cpy_file_name()%>"class="cafephoto">
      </a>
      </div>
     <%}%>
     <%for(int i=0; i<cafereview.size();i++){ 
		if(viewCafeInfo.getCafe_no() ==cafereview.get(i).getCafe_no()){
			for(int j =0; j<cafereviewFile.size();j++){ 
				if(cafereview.get(i).getReview_no()==cafereviewFile.get(j).getReview_no()){%>
					<div class="photo">
     				<a href="#review<%=j%>" rel="modal:open">
     				<img src="/upload/<%=cafereviewFile.get(j).getRvw_cpy_file_name() %>"class="cafephoto">
     				</a>
     				</div>
      <%}}}}%>
      
</div>
</div>
	<%for(int i=0; i<cafefile.size();i++){ %>
  		<div id="cafe<%=i%>" class="modal">
    	<img  src="/upload/<%=cafefile.get(i).getCafe_cpy_file_name()%>"class="cafeimg" >
     	</div>
    <%}%>
     <%for(int i=0; i<cafereview.size();i++){ 
		if(viewCafeInfo.getCafe_no() ==cafereview.get(i).getCafe_no()){
			for(int j =0; j<cafereviewFile.size();j++){ 
				if(cafereview.get(i).getReview_no()==cafereviewFile.get(j).getReview_no()){%>
						<div id="review<%=j%>" class="modal">
    						<img  src="/upload/<%=cafereviewFile.get(j).getRvw_cpy_file_name() %>"class="cafeimg" >
     					</div>
	<%}}}}%>
	
<!-- 카페 정보 -->
<div style="width: 900px; margin: 0 auto;">
      <div class="cafeinfo" style="margin-top: 10px;">
      <br><br><br>
      <div id="cafename"> <p style="font-size: 50px; display: inline-block; margin: 5px 10px;"><%=viewCafeInfo.getCafe_name() %></p><p style="font-size: 30px; display: inline-block; margin-left: 5px; color:#FF792A;"><%=starAvg%></p> </div>
      
      <!-- 리뷰 작성 버튼 -->
      <%if(session.getAttribute("login")!=null && (boolean) session.getAttribute("login")){ %>
		<div  class="middle" >
		<button class="review" id="btnReview" onclick="location='/review/write?cafeinfo=<%=viewCafeInfo.getCafe_no() %>'"><b>리뷰 작성</b></button></div>
		<%}%>
	
		<!-- 찜하기 버튼 -->
		<%if(session.getAttribute("login")!=null && (boolean) session.getAttribute("login")){
		if(res==1){ %>
		<div class="Like">
      	<button class="like" id="btnLike" onclick="location='/cafeinfo/rcm?cafeinfo=<%=viewCafeInfo.getCafe_no() %>'"><b>♥</b></button>
      	</div>
		
      	<%}else if(res==0 ){ %>
      	<div class="Like">
     	 <button class="like" id="btnLike" onclick="location='/cafeinfo/rcm?cafeinfo=<%=viewCafeInfo.getCafe_no() %>'"><b>♡</b></button>
      	</div>
      	<%}}%>	
      	<div> <span style="font-size: 18px; color:#888">&nbsp;&nbsp;&nbsp;♥ <%=viewCafeInfo.getCafe_rcm() %></span></div>
      	
	<hr style="height:2px; border:none; color:#ccc; background-color:#ccc;">
 
      <br><br><br>
      <div id="cafeloc">
      	<span style="color: #888; font-size: 20px;">주소</span>
      	<span style="font-size: 20px; margin-left: 138px;"><%=viewCafeInfo.getCafe_loc() %></span>
      </div>
      <br>
      
      <div id="cafetel">
      	<span style="color: #888; font-size: 20px;">전화번호</span>
      	<span style="font-size: 20px; margin-left: 100px;"><%=viewCafeInfo.getCafe_tel() %></span>
      </div>
	  <br>

      <div id="cafemeni">
      <span style="color: #888; font-size: 20px;">메뉴</span>
      <span style="font-size: 20px; margin-left: 138px;"><%=viewCafeInfo.getCafe_menu() %></span>
      </div>
	  <br>

      <div id="cafetime">
      <span style="color: #888; font-size: 20px;">영업시간</span>
      <span style="font-size: 20px; margin-left: 100px;"><%=viewCafeInfo.getCafe_time() %></span>
      </div>
      <br>

      <div id="cafepark">
      <span style="color: #888; font-size: 20px;">주차</span>
      <%if (viewCafeInfo.getCafe_park().equals("y")||viewCafeInfo.getCafe_park().equals("Y") ){%>
      <span style="font-size: 20px; margin-left: 138px;">주차가능</span>
      <%}else if(viewCafeInfo.getCafe_park().equals("n") ||viewCafeInfo.getCafe_park().equals("N")){ %>
      <span style="font-size: 20px; margin-left: 138px;">주차불가</span>      
      <%} %>
      <!--아스키 코드값 n:110 N:78 y:121 Y:89   -->
      </div>
      <br>

      <div id="cafetag">
      <span style="color: #888; font-size: 20px;">태그</span>
      <span style="margin-left: 138px;">
      <%for(int i=0;i<cafetag.size();i++){%>
	  <span style= "font-size: 20px;">
		<button name="data" id="btnTagSearch" style="border: 0; color: #888; background: white;" onclick="location='<%=request.getContextPath() %>/search/list?select=tag_conn&data=<%=cafetag.get(i).getTag_name() %>'" value="<%=cafetag.get(i).getTag_name() %>">#<%=cafetag.get(i).getTag_name() %></button>&nbsp;
      </span>
      <%} %>
      </span>
      </div>
</div>
<br>
<br>
<hr style="height:3px; border:none; color:#ccc; background-color:#ccc;">

<h2>리뷰</h2>
<hr>

<!-- 리뷰 -->

<div class="review">
<%-- <%=cafeList.get(1).getUser_nick() %> --%>


<%for(int i=0; i<cafeList.size();i++){ %>

	<%if(viewCafeInfo.getCafe_no() ==cafeList.get(i).getCafe_no()){ %>
	<div>
		<span style="font-size: 15px;"><b><%=cafeList.get(i).getUser_nick() %> (작성자)</b></span>
		<br>
	</div>

	<div>
		<%for(int j =0;j<cafeList.get(i).getStar_score();j++){ %>
		<img src="../resources/img/staron.jpg" id="star" style="width:17px; height:17px;">
		<%} %>
	</div>

	<div id="review_view">
	<div style="display: inline-block; float:left;">
	<%for(int j =0; j<cafereviewFile.size();j++){ 
		if(cafeList.get(i).getReview_no()==cafereviewFile.get(j).getReview_no()){%>
			<div id="review<%=j %>" class="modal"><img src="/upload/<%=cafereviewFile.get(j).getRvw_cpy_file_name() %>" class="cafeimg"  style="width:300px; height:300px;">		
			</div>
			<a href="#review<%=j %>" rel="modal:open" ><img src="/upload/<%=cafereviewFile.get(j).getRvw_cpy_file_name() %>" class="cafeFile"  style="width: 120px; height: 120px;"></a>
	<%}}%>
	</div>

	<div style="display: inline-block; float:left; width: 700px; height: 120px; margin-left: 20px;">
		<span style="font-size: 14px; color: #ccc"><b><%=cafeList.get(i).getReview_date() %></b></span><br>
		<span style="font-size: 14px;"><%=cafeList.get(i).getReview_cont() %></span>
	</div>
	<br><br><br>
	</div>
	
	<%if(session.getAttribute("login")!=null && (boolean) session.getAttribute("login")){
		if(Integer.parseInt(String.valueOf(session.getAttribute("user_no"))) == cafeList.get(i).getUser_no() ){ %>

		<ul>
		<li><form action="/review/delete?reviewno=<%=cafeList.get(i).getReview_no() %>" name="deleteform" method="post"></form>
<%-- 		<a class="delete" onclick="location='/review/delete?reviewno=<%=cafeList.get(i).getReview_no() %>'" >삭제</a> --%>
			<a class="delete" onclick="delete1()" >삭제<input type="hidden" value="/review/delete?reviewno=<%=cafeList.get(i).getReview_no() %>" id="delete_review"/></a>
		</li>

		<li style="margin-right: 5px;"><a class="delete" onclick="location='/review/update?reviewno=<%=cafeList.get(i).getReview_no() %>'" >수정</a><li><br>
		</ul>
	
	<%}else if(Integer.parseInt(String.valueOf(session.getAttribute("user_rank"))) == 0){ %>

		<ul>
			<li><a class="delete" onclick="delete1()" >삭제<input type="hidden" value="/review/delete?reviewno=<%=cafeList.get(i).getReview_no() %>" id="delete_review"/></a></li>
		</ul>	
	<%}} %>
	<br><br>
	<br>
<hr style="height:1.5px; border:none; color:#ccc; background-color:#ccc;">
	
<%}} %>

</div>

</div>

<%@ include file="../layout/review_paging.jsp" %> 
<br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>
