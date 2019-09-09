<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, inquiry.model.vo.*, notice.model.vo.PageInfo" %>
<%
	ArrayList<Inquiry> list = (ArrayList<Inquiry>)request.getAttribute("list");	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();

%>
<%@ include file ="../common/menubar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/css/boardCommon.css">
<title>Insert title here</title>
</head>
<body>

	<div id="outline">
	<h1>개인 문의 게시판</h2>
	<hr>
		<div id="tableArea">
			<table id="listTable">
					<tr>
						<th>글번호</th>
						<th width="300">제목</th>
						<th width="100">작성자</th>
						<th>답변현황</th>
						<th width="100">작성일</th>
					</tr>
					<% if(list.isEmpty())  { %>
						<tr>
							<td colspan="5">존재하는 문의글이 없습니다.</td>
						</tr>
					<% } else { %>
						<% for(Inquiry i: list) { %>
							<tr>
								<td><%= i.getInquiryNo() %></td>
								<td><%= i.getInquiryTitle() %></td>
								<td><%= i.getWriter() %></td>
								<td><%= i.getrStatus() %></td>
								<td><%= i.getDate() %></td>
							</tr>
						<% } %>
					<% } %>
			</table>
		</div>
		
		<!-- 페이징 바 -->
		<div id="pagingArea" align="center">
			<!-- 맨 처음으로 (<<) -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%=loginUser.getUserId()%>&currentPage=1'">&lt;&lt;</button>
			
			<!-- 이전페이지(<) -->
			<% if(currentPage == 1) { %>
				<button disabled> &lt; </button>
			<% } else { %>
				<button onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%=loginUser.getUserId()%>&currentPage=<%= currentPage -1 %>'">&lt;</button>
			<% } %>
			

			
			<!-- 10개의 페이지 목록  -->
			<% for(int p=startPage; p<=endPage; p++ ) { %>
				
				<% if(p == currentPage){ %>
					<button disabled style="background:#e23a6e ;"> <%= p %> </button>			
				<% } else { %>
					<button onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%=loginUser.getUserId()%>&currentPage=<%= p %>'"><%= p %></button>
				<% } %>	
			<% } %>
			
			
			<!-- 다음페이지(>) -->
			<% if(currentPage == maxPage) { %>
				<button disabled> &gt; </button>
			<% } else { %>
				<button onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%=loginUser.getUserId()%>&currentPage=<%= currentPage + 1 %>'">&gt;</button>
			<% } %>
			
			
			<!-- 맨끝으로(>>) -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%=loginUser.getUserId()%>&currentPage=<%= maxPage %>'">&gt;&gt;</button>
		</div>
			 <button type="button" style="align:right;" onclick="location.href='<%= request.getContextPath() %>/insertForm.in'">작성하기</button>
		

	</div>
	
	
	<script>
		$(function() {
			$("#listTable td").mouseenter(function() {
				$(this).parent().css({"background":"#fde8ee", "cursor":"pointer"});
			}).mouseout(function() {
				$(this).parent().css({"background":"white"});
			}).click(function() {
				var num = $(this).parent().children().eq(0).text();
				if(num == '존재하는 문의글이 없습니다.') {
				} else {
					location.href="<%= request.getContextPath() %>/detail.in?inquiryNo=" + num;
				}
			})
			
		})
	
	</script>
		
	



</body>
</html>