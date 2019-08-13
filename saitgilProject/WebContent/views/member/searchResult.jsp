<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	String userId = (String)session.getAttribute("userId");
	String user = "존재하지 않습니다.";

	if(userId != "null") { 
		user = userId.substring(0, userId.length()-2);  		
		user = user + "**";
	} 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">

<style>
	body {
		width:200px;
		height:100px;
		background:#f7aec5;
		margin:0;
		
		 }

	#main {
		margin-top:20px;
		width:510px;
		height:250px;
		background:white;
		text-align:center;
	}
	
	#header {
		font-size:30px;
		font-weight:900;
		color:#e23a6e ;
		font-family: 'Noto Serif KR', serif;
		padding: 25px;
		
	}
	
	#textArea {
		font-size:15px;
		color:black ;
		font-family: 'Noto Serif KR', serif;
		margin-top:15px;
	}
	
	#button {
		width:80px;
		height:30px;
		border-radius: 5%;
		background:#f7aec5 ;
		color:white;
		font-size:13px;
		font-family: 'Noto Serif KR', serif;
		margin:auto;
		margin-top:30px;
		line-height: 25px;
		
	}
	
	#button:hover {
		cursor:pointer;
		background:#e23a6e ;
	}
	
	span {
		font-weight:900;
		color:#e23a6e;
	}
</style>
</head>
<body>


	<div id="main">
		<div id="header">너와 나의 사잇길</div>
		<%  %>
		<div id="textArea">
			회원님의 아이디는 <span><%= user %></span> 입니다. <br>
			메인 페이지로 이동해 로그인을 해주세요. 
			<div id=button onclick="goMain();">확인</div>
		</div>
	</div>
	
	<script>
		function goMain() {
			
			<%request.getSession().invalidate();%>
			window.close();
			
		}
	</script>

</body>
</html>