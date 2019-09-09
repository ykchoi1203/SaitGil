<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ include file ="../common/menubar.jsp" %>
<%
	Date date = new Date();
	String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/boardCommon.css">
<style>
	body {
		background:pink;
	}


</style>
</head>
<body>
	
	<div id="outline">
		<br>
		<h1>공지사항 작성</h1>
		<hr>
		<form action="<%= request.getContextPath() %>/insert.no" method="post">
			<div id="listArea">
				<table id="insertTable">
					<tr>
						<th height="50">제목</th>
						<td colspan="3"><input type="text" size="74" name="title"></td>
					</tr>
					<tr>
						<th height="50" width="100">작성자</th>
						<td><%= loginUser.getUserId() %>
						<input type="hidden" name="writer" value="<%= loginUser.getUserId() %>"></td>
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
					<button type="button" onclick="javascript:history.back();">취소</button>
					<button type="submit">등록</button>
				</div>		
		</form>
	</div>
	


</body>
</html>