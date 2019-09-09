<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date, java.text.SimpleDateFormat, sns.model.vo.*, java.util.ArrayList" %>
<%
	Board b = (Board)request.getAttribute("b");
	ArrayList<Photo> pList = (ArrayList<Photo>)request.getAttribute("pList");
	Date date = b.getbDate();
	String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
	String isPublic = b.getIsPublic();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/snsWrite.css">
<style>
	.snsImgArea{
		display:inline-block;
	}
	body {
		background:pink;
	}
	#outline{
		overflow:auto;
		
	}

</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div id="outline">
		<br>
		<%if(isPublic.equals("Y")) {%>
		<h1>공유글 수정</h1>
		<%}else{ %>
		<h1>비밀글 수정</h1>
		<%} %>
		<hr>
		<form action="<%= request.getContextPath() %>/update.bo" method="post">
			<div id="listArea">
				<table id="insertTable">
					<tr>
						<th height="50" width="100">작성자</th>
						<td><%= loginUser.getUserName() %>
						<input type="hidden" name="bNo" value="<%= b.getbNo() %>">
						</td>
						<th>작성일</th>
						<td><%= now %></td>
					</tr>
					<tr>
						<th rowspan="2">내용</th>
					</tr>
					<tr>
						<td colspan="3"> 
						<%if(isPublic.equals("Y")) {%>
							<textarea name="content" cols="80" rows="16" style="resize:none;" placeholder="무슨 생각을 하고계신가요?"><%= b.getbContent() %></textarea> 
						<%}else{ %>
							<textarea name="content" cols="80" rows="16" style="resize:none;" placeholder="둘만의 비밀스러운 추억을 기록해보세요."><%= b.getbContent() %></textarea> 							
						<%} %>
						</td>
					</tr>
					<tr style="background:#fde8ee">
						<th height="50">사진 추가</th>
						<td align="center"><div id="addInput"><h1>x</h1></div></td>
						<td colspan="2" width="500px">
							<div id="imgSample">
								<%for(Photo p : pList){ %>
								<div class="snsImgArea"><img src="<%= request.getContextPath() %>/resources/snsImages/<%=p.getChangeName() %>" width="120px" height="100px"></div>
								<%} %>
							</div>
						</td>
					</tr>
					</table>
					<div id="imgInputArea"></div>
				</div>
				
				<div align="center">
					<button type="button" onclick="location.href='<%= request.getContextPath()%>/list.bo'">취소</button>
					<button type="submit">수정</button>
				</div>		
		</form>
	</div>
	
</body>
</html>