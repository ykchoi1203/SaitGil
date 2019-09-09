<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.*"%>
<%
	Member loginUser = (Member)request.getSession().getAttribute("loginUser");
	Member partner = (Member)session.getAttribute("partner");
	
%>
<!DOCTYPE html>
<html>
   
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>커플 정보</title>
    <link rel="stylesheet" href="resources/css/boardCommon.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<style>
	
		body {
			background:pink;
			margin:50px;
		
		}
		#photoArea {
			border: 1px solid pink;
			background:pink;
			width:250px;
			height:250px;
			
		}
		
		#outline {
			background:white;
			width : 800px;
			height: 700px;
			padding: 60px;
			font-family:Noto Serif KR;
			margin-right:auto;
			margin-left:auto;
			opacity:0.9;
		}
		
		h1 {
			color:#e23a6e;
		}
		
		h3 { 
			color:#404040 ;
		}
		
		b{
			text-decoration: underline ;
			color : #e23a6e;
		}
		
		input {
			border-radius:4px;
			color:inherit;
			padding: 0 1em;
			height:2.5em;
			border: solid 1px white;
			background:#fde8ee;
		}
		
		table {
			padding:20px;
		}
		
		button {
			background: #fa96b5;
			color: #fff;
			margin-left: 10px; 
			display: inline;
			width: 100px;
			height: 2.5em;
			font-size: 14px;
			margin: auto;
			border:1px solid white;
			border-radius:4px;
			font-family:Noto Serif KR;
		}
		
		#buttonArea {
			text-align:center;
		}
		
	
	</style>
</head>

<body>
	
	<div id="outline">
		<form action="<%= request.getContextPath() %>/updateInfo.co" id="infoForm" method="post" enctype="multipart/form-data"> 
			<h1>커플 정보 등록</h1>
			<hr>

 			<h3><b> <%= loginUser.getUserName() %></b>님 사잇길에 오신 것을 환영합니다!</h3>
			<h3>사잇길의 동반자 <b><%= partner.getUserName() %></b>님과의 커플 정보를 등록해주세요.</h3>
			<br>
			
			
			<table>
				<tr>
					<td>* 처음 사랑한 날짜를 기록하세요.<td>
					<td><input type="date" name="meetDate" id="meetDate"></td>
				</tr>
				<tr>
				<td><input type="hidden" name="cCode" value="<%= loginUser.getcCode() %>"><br><br><td> 
				</tr>
				<tr>
					<td>* 커플 사진을 선택하세요.</td>
				</tr>
				<tr>
					<td>
						<input type="radio" name="profile" id="basic"><label for="basic">기본 사진</label>
						<input type="radio" name="profile" id="self"><label for="self" >사진 선택</label>
					</td>
				</tr>
				<tr>
					<td><div id="photoArea"><img id="photo" width="250px" height="250px"></div><td>
					<td><input type="file" multiple id="photoBtn" name="photoBtn" onchange="loadImg(this, 1);"></td>
				</tr>
			</table>
			<div id="buttonArea">
				<button type="submit">시작하기</button>
			</div>
		</form>
	</div>
	
	<script>
		$(function() {
			$("#photoBtn").hide();
			
			$("#photoArea").click(function() {
				$("#photoBtn").click();
			});
			
			$('#self').click(function() {
				$("#photoBtn").click();
			});
			
			$('#basic').click(function() {
				$("#photo").attr("src", '<%= request.getContextPath() %>/resources/shareFile/mainImage.jpg');
			});
			
			
		});
		
		
		function loadImg(value, num) {

			if(value.files && value.files[0]) {

				var reader = new FileReader();
				
				reader.onload = function(e) {
					switch(num) {
					case 1: $("#photo").attr("src", e.target.result); break;

					}
				}
				//파일을 읽어주는 메소드 
				reader.readAsDataURL(value.files[0]); 
			}
		}
		
	</script>


</body>
</html>