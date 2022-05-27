<%@page import="web.dto.CafeFile"%>
<%@page import="web.dto.CafeReviewFile"%>
<%@page import="java.util.List"%>
<%@page import="web.dto.CafeReview"%>
<%@page import="web.dto.CafeInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--     <%@ include file="../layout/header.jsp" %> --%>
<%-- <%@ include file="../layout/header.jsp" %> --%>
<%@ include file="../layout/header.jsp" %> 

<%	CafeReview review =(CafeReview) request.getAttribute("review"); 
	List<CafeReviewFile> reviewFile = (List) request.getAttribute("reviewfile"); %>


<script type="text/javascript">
function checkFile(f){

	// files 로 해당 파일 정보 얻기.
	var file = f.files;

	// file[0].name 은 파일명 입니다.
	// 정규식으로 확장자 체크
	if(!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name)) alert('gif, jpg, png 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);

	// 체크를 통과했다면 종료.
	else return;

	f.outerHTML = f.outerHTML;
}

$(function() {
    $("#myFile").on('change', function(){
    readURL(this);
    });
});

$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
// 		submitContents( $("#btnWrite") );
		if(cmtform.content.value==""){
			alert("리뷰를 작성하세요!");	
		}else{
			
			$("form").submit();
		}
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});

function submitContents( elClickedObj ) {
	
// 	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit을 수행한다
		elClickedObj.form.submit();
	} catch(e) {}
	
}

function starToPercent() {
    const score = +this.restaurant.averageScore * 20;
    return score + 1;
}


</script>

<style type="text/css">
#View{
	width :150px;
	height: 150px;
}

 .star{ 
 	margin: 0 auto; 
 } 

 .staron{ 
   position: absolute; 
   top: 0px; 
   left: 0px; 
 } 
.review{
	width:50px; 
 	heighr:50px;
}
textarea{
	width:100%;
	azimuth;
	height: 6.25em;
	border:none;
	resize:none;
}
#myFile{
	display: none;
}
#plus{
	width: 100%;
	
}
.star-star {
  display: flex;
  flex-direction: row-reverse;
  font-size: 2.25rem;
  line-height: 2.5rem;
  justify-content: space-around;
  padding: 0 0.2em;
  text-align: center;
  width: 5em;
}
 
.star-star input {
  display: none;
}
 
.star-star label {
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 2.3px;
  -webkit-text-stroke-color: #2b2a29;
  cursor: pointer;
}
 
.star-star :checked ~ label {
  -webkit-text-fill-color: gold;
}
 
.star-star label:hover,
.star-star label:hover ~ label {
  -webkit-text-fill-color: #fff58c;
}
table{
	margin: 0 auto;
	text-align: center;
	border: 1px solid #b2876f;
}
.file{
	width: 100px;
	float: left;	
}
.gradient-btn{
	display: inline-block;
    padding: 1em 5em;
    border-radius: 0;
    color: #FF792A;
    margin-top:2rem;
    letter-spacing: 2px;
    text-transform: uppercase;
    text-decoration: none;
    background: white;
    background-position: 1% 50%;
	background-size: 400% 300%;
	border: 1px solid #FF792A;
	font-size: 15px;
	
}
.gradient-btn:hover{
/* 	font-size:1.100rem; */
	color: #ccc;
    background-position: 99% 50%;
  }

.bottom{
	text-align:center;
/* 	float:left; */
	margin:0 auto;
	
}
#content:focus {
	outline: none;
}
</style>

<br><br><br><br><br>
<form action="/review/update" method="post" name="cmtform" enctype="multipart/form-data">

<!-- </form> -->
<!-- <form action="/review/write" method="post" name="cmtform" > -->
<table width='700' >

<tr>
	<td rowspan="2" width="100px">
		<label for="myFile" class="file">
		<img src="../resources/img/plus.jpg" id="plus" name="file"  class="file">	
	</label>
	<input type="file" name="file" id="myFile" accept="image/*" onchange="checkFile(this)"/>
	</td>
	<td width="500" height="50" colspan="2">
		<div id="star" align="center">
			<div class="star-star space-x-4 mx-auto">
			<%for(int i =5; i>=1; i--){ 
				if(i==review.getStar_score()){%>
				<input type="radio" id="<%=i%>-stars" name="star" value="<%=i %>" class="star" checked/>
				<label for="<%=i%>-stars" class="star">★</label>
				<%}else{ %>
				<input type="radio" id="<%=i%>-stars" name="star" value="<%=i %>" class="star" />
				<label for="<%=i%>-stars" class="star">★</label>
				<%}} %>
			</div>
		
		</div>
		
		<input type="hidden" name="cafeinfo" id="cafeinfo" value="<%=review.getCafe_no()%>">
		<input type="hidden" name="reviewno" id="reviewno" value="<%=review.getReview_no()%>">
		<%for(int i=0; i<reviewFile.size();i++ ){%>
		<input type="hidden" name="fileno" id="fileno" value="<%=reviewFile.get(i).getRvw_file_no()%>">
		<%} %>
		
	</td>
	
	
</tr>
<tr>
	<td><textarea id="content" name="content"><%=review.getReview_cont() %></textarea></td>
</tr>
</table>
</form>
<div class="bottom">
	<button name="submit" id="btnWrite" class="gradient-btn" style="margin-right: 5px;"><b>작성</b></button>
	<button name="submit" id="btnCancel" class="gradient-btn" style="margin-left: 5px;"><b>취소</b></button><br>

</div>
<br><br><br><br>
<br><br><br><br>

<%@ include file="../layout/footer.jsp" %>
