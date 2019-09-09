<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.Notice" %>
<%@ include file ="../common/menubar.jsp" %>
<% 
	Notice n = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/boardCommon.css">

</head>
<body>
	
	
	<div id="outline">
		<br>
		<h1>공지사항 수정하기</h1>
		<hr>
		<div id="listArea">
		<form action="<%= request.getContextPath() %>/update.no" method="post">
			<table id="insertTable">
				<tr>
					<th width="100" height="50">제목</th>
					<td colspan="3"><input type="text" size="75" name="title" value="<%= n.getNoticeTitle()%>"></td>
				</tr>
				<tr>
					<th>작성자 <input type="hidden" name="noticeNo" value="<%= n.getNoticeNo() %>"> </th>
					<td><%= n.getWriter() %></td>
					<th>작성일</th>
					<td><%= n.getNoticeDate() %></td>
				</tr>
				<tr>
					<th rowspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="3"> 
						<textarea name="content" cols="80" rows="16" style="resize:none;"><%= n.getNoticeContent() %></textarea> 
					</td>
				</tr>
				</table>
				
				<div align="center">
					<button type="button" onclick="javascript:history.back();">취소</button>
					<button type="submit">수정하기</button>
				</div>		
		</form>
	</div>
	</div>

</body>
</html>