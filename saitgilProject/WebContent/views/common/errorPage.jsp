<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String message = (String)request.getAttribute("msg"); 
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center"><%= message %></h1>
	
	<div align="center">
		<button onclick="location.href='<%= request.getContextPath() %>'" style="width:50%">홈으로 돌아가기</button>
	</div>

</body>
</html>