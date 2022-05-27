<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
#select {
	height: 25px;
}
</style>

<div class="text-center">
	<form action="/board/noticelist/list" method="get">
		<select id="select" name="select">
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="usernick">작성자</option>
		</select>
			<input type="text" name="data" value="" placeholder="검색어를 입력해주세요"/>
			<input type="submit" value="검색">
	</form>
</div>