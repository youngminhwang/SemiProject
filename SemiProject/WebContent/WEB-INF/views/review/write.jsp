<%@page import="web.dto.CafeInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<%CafeInfo viewCafeInfo = (CafeInfo) request.getAttribute("viewCafe"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
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
function readURL(input) {
	
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
        $('#View').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}


$(document).ready(function() {
	
	
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		submitContents( $("#btnWrite") );
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
$(function() {
    $("#myFile").on('change', function(){
    readURL(this);
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
	width:98%;
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
file{
	border :none;
}
.gradient-btn{
	display: inline-block;
    padding: 1em 5em;
    border-radius: 0;
    color: #FF792A;
    margin-top:2rem;
    font-size: 1rem;
    letter-spacing: 2px;
    text-transform: uppercase;
    text-decoration: none;
    background: white;
    background-position: 1% 50%;
	border: 1px solid #FF792A;
	border-radius: 5px;
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

</head>
<body>
<br><br><br><br><br>
<!-- <form action="/review/write" method="post" name="cmtform" enctype="multipart/form-data"> -->

<form action="/review/write" method="post" name="cmtform" enctype="multipart/form-data">

<!-- </form> -->
<!-- <form action="/review/write" method="post" name="cmtform" > -->
	
<table width='700' border="1">

<tr>
	<td rowspan="2" width="100px" class="file">
	
		<label for="myFile" >
			<img src="../resources/img/plus.jpg" id="plus" name="file"  class="file">	
		</label>
		<input type="file" name="file" id="myFile" accept="image/*" onchange="checkFile(this)"/>
	</td>
	
	<td width="500" height="50" colspan="2" class="reviewWrite">
		<div id="star" align="center">
			<div class="star-star space-x-4 mx-auto">
				<input type="radio" id="5-stars" name="star" value="5" class="star"/>
				<label for="5-stars" class="star">★</label>
				<input type="radio" id="4-stars" name="star" value="4" class="star"/>
				<label for="4-stars" class="star">★</label>
				<input type="radio" id="3-stars" name="star" value="3" class="star" checked/>
				<label for="3-stars" class="star">★</label>
				<input type="radio" id="2-stars" name="star" value="2" class="star"/>
				<label for="2-stars" class="star">★</label>
				<input type="radio" id="1-star" name="star" value="1" class="star"/>
				<label for="1-star" class="star">★</label>
			</div>
		
		</div>
		
		
	<input type="hidden" name="cafeinfo" id="cafeinfo" value="<%=viewCafeInfo.getCafe_no()%>">
		
	</td>
	
	
	
	
</tr>
<tr>
	<td><textarea id="content" name="content" placeholder="리뷰를 입력하세요."></textarea></td>
</tr>
</table>
<div class="file">

</div>


</form>
<div class="bottom">
	<button name="submit" id="btnWrite" class="gradient-btn" style="margin-right: 5px;"><b>등록</b></button>
	<button name="submit" id="btnCancel" class="gradient-btn" style="margin-left: 5px;"><b>취소</b></button><br>	
</div>
<br><br><br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>
