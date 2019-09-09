<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="inquiry.model.vo.Inquiry" %>
<%
	Inquiry i = (Inquiry)request.getAttribute("inquiry");
%>
<%@ include file ="../common/menubar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/boardCommon.css">
</head>
<body>
	

	<div id="outline" style="overflow:auto;">
		<br>
		<h1>공지사항 상세보기</h1>
		<hr>
		<div id="listArea">
			<table id="insertTable">
				<tr>
					<th width="100" height="50">제목</th>
					<td colspan="3"><%= i.getInquiryTitle() %></td>
				</tr>
				<tr>
					<th height="50">작성자</th>
					<td><%= i.getWriter() %></td>
					<th>작성일</th>
					<td><%= i.getDate() %></td>
				</tr>
				<tr>
					<th rowspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="3"> 
						<textarea  name="content" cols="80" rows="16" style="resize:none;" readonly><%= i.getInquiryContent() %></textarea> 
					</td>
				</tr>
				</table>
				<hr>
				<br>
				<% if(i.getrStatus().equals("Y") && i.getrContent() != null) { %>
				<div style="background:pink; padding:20px; margin:15px; border-radius:10px">
					<table id="replyTable">
						<tr>
							<th>답변일</th>
							<td><%= i.getrDate() %></td>
						</tr>
						<tr>
							<th rowspan="2">답변 내용</th>
						</tr>
						<tr>
							<td colspan="3"> 
								<textarea  name="content" cols="80" rows="16" style="resize:none;" readonly><%= i.getrContent() %></textarea> 
							</td>
						</tr>
					</table>
				</div>
				<% } %> 
				
				<div align="center">
					<button type="button" size="100" onclick="location.href='<%= request.getContextPath() %>/list.in?userId=<%= loginUser.getUserId() %>'">목록으로</button>
					
						<button onclick="location.href='<%= request.getContextPath()%>/updateForm.in?inquiryNo=<%= i.getInquiryNo() %>'">수정하기</button>
						<button onclick="deleteNotice();">삭제하기</button>
				</div>		
			</div>
	</div>
	
	<script>
		function deleteNotice() {
			var c = confirm("정말로 삭제하시겠습니까?");
			
			if(c) {
				location.href = "<%= request.getContextPath() %>/delete.in?inquiryNo="+<%= i.getInquiryNo() %>+"&&userId="+"<%=i.getUserId()%>"; 
			}
		}
	</script>

</body>
</html>