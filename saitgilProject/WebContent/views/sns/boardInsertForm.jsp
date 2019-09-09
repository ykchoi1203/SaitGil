<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
	Date date = new Date();
	String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
	String isPublic = (String)request.getAttribute("isPublic");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/snsWrite.css">
<style>
	#addInput:hover{
		cursor:pointer;
		background:#fde8ee;
	}
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
		<h1>공유글 작성</h1>
		<%}else{ %>
		<h1>비밀글 작성</h1>
		<%} %>
		<hr>
		<form action="<%= request.getContextPath() %>/insert.bo?isPublic=<%= isPublic %>" method="post" enctype="multipart/form-data">
			<div id="listArea">
				<table id="insertTable">
					<tr>
						<th height="50" width="100">작성자</th>
						<td><%= loginUser.getUserName() %>
						<input type="hidden" name="writer" value="<%= loginUser.getUserId() %>">
						<input type="hidden" name="cCode" value="<%= loginUser.getcCode() %>"></td>
						<th>작성일</th>
						<td><%= now %></td>
					</tr>
					<tr>
						<th rowspan="2">내용</th>
					</tr>
					<tr>
						<td colspan="3"> 
						<%if(isPublic.equals("Y")) {%>
							<textarea name="content" cols="80" rows="16" style="resize:none;" placeholder="무슨 생각을 하고계신가요?"></textarea> 
						<%}else{ %>
							<textarea name="content" cols="80" rows="16" style="resize:none;" placeholder="둘만의 비밀스러운 추억을 기록해보세요."></textarea> 							
						<%} %>
						</td>
					</tr>
					<tr>
						<th height="50">사진 추가</th>
						<td align="center"><div id="addInput" onclick="addInput();"><h1>+</h1></div></td>
						<td colspan="2" width="500px"><div id="imgSample"></div></td>
					</tr>
					</table>
					<div id="imgInputArea"></div>
				</div>
				
				<div align="center">
					<button type="button" onclick="javascript:history.back();">취소</button>
					<button type="submit">등록</button>
				</div>		
		</form>
	</div>
	<script>
			num = 1;
		$(function(){
			$("#imgInputArea").hide();
			$(document).on("click", ".snsImgArea", function(e){
				$("#"+e.target.className).click();
			});
		});
		function addInput(){
			var input = "<input type='file' multiple name='snsImg" + num + "' id='snsImg" + num + "' onchange='loadImg(this," + num + ");'>"
			var div = "<div class='snsImgArea'><img class='snsImg" + num + "' id='imgzz" + num + "' width='120px' height='100px'></div>";
			$("#imgInputArea").append(input);
			$("#imgSample").append(div);
			console.log($("#imgInputArea"));
			num++;
		}
		// 파일 첨부했을 때 미리보기 공간에 미리보기 가능하게 하는 함수
		// [참고] https://developer.mozilla.org/ko/docs/Web/API/FileReader
		function loadImg(value, num){
			// value => input태그
			// num => 조건문으로 작업
			// file이 존재하는지
			 if(value.files && value.files[0]){
				
				// 파일을 읽어들일 FileReader객체 생성
				var reader = new FileReader();
				
				// 파일 읽기가 다 완료되었을 때 실행되는 메소드
				reader.onload = function(e){
					$("#imgzz" + num).attr("src", e.target.result);// data:URL
					}
				
			
				// 파일 읽어주는 메소드
				reader.readAsDataURL(value.files[0]);
			} 
		}  
	</script>
</body>
</html>