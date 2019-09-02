<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "notice.model.vo.Notice" %>
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
	<%@ include file ="../common/menubar.jsp" %>
	
	
	<div id="outline">
		<br>
		<h1>공지사항 상세보기</h1>
		<hr>
		<div id="listArea">
			<table id="insertTable">
				<tr>
					<th width="100" height="50">제목</th>
					<td colspan="3"><%= n.getNoticeTitle() %></td>
				</tr>
				<tr>
					<th height="50">작성자</th>
					<td><%= n.getWriter() %></td>
					<th>작성일</th>
					<td><%= n.getNoticeDate() %></td>
				</tr>
				<tr>
					<th rowspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="3"> 
						<textarea  name="content" cols="80" rows="16" style="resize:none;" readonly>
						<%= n.getNoticeContent() %>
						</textarea> 
					</td>
				</tr>
				</table>
				
				<div align="center">
					<button type="button" size="100" onclick="location.href='<%= request.getContextPath() %>/list.no'">목록으로</button>
					
					
					<% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
						<button onclick="location.href='<%= request.getContextPath()%>/updateForm.no?noticeNo=<%= n.getNoticeNo() %>'">수정하기</button>
						<button onclick="deleteNotice();">삭제하기</button>
					
					<% } %>
				</div>		
			</div>
	</div>
	
	<script>
		function deleteNotice() {
			var c = confirm("정말로 삭제하시겠습니까?");
			
			if(c) {
				location.href = "<%= request.getContextPath() %>/delete.no?noticeNo=" + <%= n.getNoticeNo() %>; 
			}
		}
	
	</script>
</body>
</html>