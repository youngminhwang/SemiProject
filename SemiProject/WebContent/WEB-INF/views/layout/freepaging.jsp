<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="util.Paging"%>

<%	Paging paging = (Paging) request.getAttribute("freepaging"); %>

<div class="text-center">

<ul class="pagination">

	<%-- 첫 페이지로 이동 --%>
	<%	if( paging.getCurPageNum() != 1 ) { %>	
		<li><a href="<%=request.getContextPath() %>/board/freelist">&larr; 처음</a></li>
	<%	} %>
	
	<%-- 이전 페이징 리스트로 이동 --%>
	<%	if( paging.getStartPageNum() == 1 ) { %>
		<li class="disabled"><a>&laquo;</a></li>
	<%	} else {%>
		<li>
			<a href="./freelist?curPage=<%=paging.getStartPageNum() - paging.getPageCount() %>">
				&laquo;
			</a>
		</li>
	<%	} %>
	
	<%-- 이전 페이지로 이동 --%>
	<%	if( paging.getCurPageNum() > 1 ) { %>	
		<li>
			<a href="<%=request.getContextPath() %>/board/freelist?curPage=<%=paging.getCurPageNum()-1 %>">
				&lt; 
			</a>
		</li>
	<%	} %>
	

	<%-- 페이징 번호 리스트 --%>
	<% for(int i=paging.getStartPageNum(); i <= paging.getEndPageNum(); i++) { %>
		<% if( paging.getCurPageNum() == i ) { %>
			<li class="active">
				<a href="<%=request.getContextPath() %>/board/freelist?curPage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } else { %>
			<li>
				<a href="<%=request.getContextPath() %>/board/freelist?curPage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } %>
	<% } %>
	
	<%-- 다음 페이지로 이동 --%>
	<%	if( paging.getCurPageNum() < paging.getTotalPage() ) { %>	
		<li>
			<a href="<%=request.getContextPath() %>/board/freelist?curPage=<%=paging.getCurPageNum()+1 %>">
				&gt; 
			</a>
		</li>
	<%	} %>
	
	<%-- 다음 페이징 리스트로 이동 --%>
	<%	if( paging.getEndPageNum() == paging.getTotalPage() ) { %>
		<li class="disabled"><a>&raquo;</a></li>
	<%	} else {%>
		<li>
			<a href="./freelist?curPage=<%=paging.getStartPageNum() + paging.getPageCount() %>">
				&raquo;
			</a>
		</li>
	<%	} %>
	
	<%-- 마지막 페이지로 이동 --%>
	<%	if( paging.getCurPageNum() != paging.getTotalPage() ) { %>	
		<li>
			<a href="<%=request.getContextPath() %>/board/freelist?curPage=<%=paging.getTotalPage() %>">
				끝 &rarr;
			</a>
		</li>
	<%	} %>
	
</ul>

</div>