<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="inquiry.model.vo.Inquiry" %>
<% 
	Inquiry i = (Inquiry)request.getAttribute("inquiry");
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
	
	<script>
		$(function() {
			$('#content').focus();
		})
		
		
	</script>
	<div id="outline">
		<br>
		<h1>문의사항 수정하기</h1>
		<hr>
		<div id="listArea">
		<form action="<%= request.getContextPath() %>/update.in" method="post">
			<table id="insertTable">
				<tr>
					<th width="100" height="50">제목</th>
					<td colspan="3"><input type="text" size="75" name="title" value="<%= i.getInquiryTitle() %>"></td>
				</tr>
				<tr>
					<th>작성자 <input type="hidden" name="inquiryNo" value="<%= i.getInquiryNo() %>"> 
					<input type="hidden" name="userId" value="<%= i.getUserId() %>"></th>
					<td><%= i.getWriter() %></td>
					<th>작성일</th>
					<td><%= i.getDate() %></td>
				</tr>
				<tr>
					<th rowspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="3"> 
						<textarea id="content" name="content" cols="80" rows="16" style="resize:none;"><%= i.getInquiryContent() %></textarea> 
					</td>
				</tr>
				</table>
				
				<div align="center">
					<button type="button" onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%= loginUser.getUserId() %>'">취소</button>
					<button type="submit">수정하기</button>
				</div>		
		</form>
	</div>
	</div>

</body>
</html>