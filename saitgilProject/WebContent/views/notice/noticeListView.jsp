<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, notice.model.vo.Notice" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");

	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/css/boardCommon.css">
<style>
</style>
</head>
<body>

	<%@ include file ="../common/menubar.jsp" %>
	
	<div id="outline">
	<h1>공지사항</h2>
	<hr>
		<div id="tableArea">
			<table id="listTable">
					<tr>
						<th>글번호</th>
						<th width="300">제목</th>
						<th width="100">작성자</th>
						<th>조회수</th>
						<th width="100">작성일</th>
					</tr>
					<% if(list.isEmpty())  { %>
						<tr>
							<td colspan="5"> 존재하는 공지사항이 없습니다.</td>
						</tr>
					<% } else { %>
						<% for(Notice n: list) { %>
							<tr>
								<td><%= n.getNoticeNo() %></td>
								<td><%= n.getNoticeTitle() %></td>
								<td><%= n.getWriter() %></td>
								<td><%= n.getNoticeCount() %></td>
								<td><%= n.getNoticeDate() %></td>
							</tr>
						<% } %>
					<% } %>
			</table>
		</div>
		<div class="searchArea" align="center">
			<select id="searchCondition" name ="searchCondition">
				<option>-----</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="search">
			<button type="submit">검색하기</button>
	
			<!--관리자만 볼 수 있는 작성하기 버튼  -->
			<% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
				<button onclick="location.href='<%= request.getContextPath() %>/insertForm.no'">작성하기</button>
			<% } %>
			
		</div>
	</div>
	
	
	<script>
		$(function() {
			$("#listTable td").mouseenter(function() {
				$(this).parent().css({"background":"#fde8ee", "cursor":"pointer"});
			}).mouseout(function() {
				$(this).parent().css({"background":"white"});
			}).click(function() {
				var num = $(this).parent().children().eq(0).text();
				
				location.href="<%= request.getContextPath() %>/detail.no?noticeNo=" + num;
			})
			
		})
	
	</script>
		
	


</body>
</html>