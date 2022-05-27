<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
#footer{
/*   height: auto; */
  min-height: 100%;
  padding-bottom: 50px;
}

footer{
  height: 30px;
  position : relative;
  transform : translateY(-100%);
}

 #list { 
 	list-style: none;
 	display: inline-block;
 	float: left;
	
} 

#li {
	list-style: none;


}

#info, #notice, #qna {
	display: inline;
	list-style: none;
}

#info, #notice #qna {

 	text-decoration: none;
	color: black;
}

</style>
<footer style="text-align: center; margin: 0; auto;">
<div id="footer"  style=" width: 100%;  display: inline-block;  " >
	<div id="fup">
			<div style="margin: 0; auto;">
			<ul><br><br><br><br>
<%-- 			<li id = "info" style="margin-right: 50px; margin-left: 50px; font-weight: bold; font-size: 15pt; "><a onclick="location.href='<%=request.getContextPath() %>/board/freelist'"  style="text-decoration: none; color: black;;">자유게시판</li> --%>
<%-- 			<li id = "notice" style="margin-right: 50px; font-weight: bold; font-size: 15pt;"><a onclick="location.href='<%=request.getContextPath() %>/board/noticelist'" style="text-decoration: none; color: black;;">공지사항</a></li> --%>
<%-- 			<li id = "qna" style="margin-right: 50px; font-weight: bold; font-size: 15pt;"><a onclick="location.href='<%=request.getContextPath() %>/board/inquirelist'"  style="text-decoration: none; color: black;">문의게시판</a></li><br><br><br> --%>
			<li id = "com" style="list-style: none; line-height: 2px;"><br>(주) 커피 한 잔</li><br>
			<li id = "add" style="list-style: none; line-height: 2px;">서울특별시 강남구 테헤란로14길 6 남도빌딩 2F, 3F, 4F, 5F, 6F</li><br>
			<li id = "name"style="list-style: none; line-height: 2px;">대표자 : 김지웅 신대범 이은지 황영민</li><br>
			<li id = "num"style="list-style: none; line-height: 2px;">사업자 등록번호 : 123-123-12345</li><br>
			<li id = "cop"style="list-style: none; line-height: 2px;">© 2022 A Coffee Co., Ltd. All rights reserved</li>
			
			</ul>			
			<br>
	</div>
			<div style="margin: 0; auto;">	
			<ul id = "list" style="text-align: center;" >
			</ul>
	
	</div>
	
	
	
	
</div>
	<div id = "fdown">
		<ul id="last">
		</ul>
		
	</div>
</div>	
</footer>
</body>
</html>