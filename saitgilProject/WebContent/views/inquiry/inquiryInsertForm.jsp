<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
	Date date = new Date();
	String now = new SimpleDateFormat("yyyy-MM-dd").format(date);

%>
<%@ include file ="../common/menubar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/boardCommon.css">
<title>Insert title here</title>
</head>
<body>
	
	<div id="outline">
		<br>
		<h1>문의게시판 작성</h1>
		<hr>
		<form action="<%= request.getContextPath() %>/insert.in" method="post">
			<div id="listArea">
				<table id="insertTable">
					<tr>
						<th height="50">제목</th>
						<td colspan="3"><input type="text" size="74" name="title"></td>
					</tr>
					<tr>
						<th height="50" width="100">작성자</th>
						<td><%= loginUser.getUserName() %>
						<input type="hidden" name="writer" value="<%= loginUser.getUserName() %>">
						<input type="hidden" name="userId" value="<%= loginUser.getUserId() %>"></td>
						<th>작성일</th>
						<td><%= now %></td>
					</tr>
					<tr>
						<th rowspan="2">내용</th>
					</tr>
					<tr>
						<td colspan="3"> 
							<textarea name="content" cols="80" rows="16" style="resize:none;"></textarea> 
						</td>
					</tr>
					</table>
				</div>
				
				<div align="center">
					<button type="button" onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%= loginUser.getUserId() %>'">취소</button>
					<button type="submit">등록</button>
				</div>		
		</form>
	</div>
</body>
</html>