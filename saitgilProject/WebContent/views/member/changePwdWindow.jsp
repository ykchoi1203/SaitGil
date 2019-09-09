<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String userId = (String)session.getAttribute("userId");
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
		height:300px;
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
		<div id="header" >너와 나의 사잇길</div>
			<form id="newPwdForm" action="<%= request.getContextPath() %>/changePwd.me" method="post" >
				<div id="textArea">
					본인 확인이 되었습니다. <br>
					변경할 비밀번호를 입력해주세요. <br> <br>
					변경할 비밀번호 : <input type="password" id="newPwd" name="newPwd"> <br>
					비밀번호 확인 : <input type="password" id="newPwd2" name="newPwd2"><br>
					<input type="hidden" name="userId" value="<%= userId %>">
					<div id=button onclick="changePwd();">변경하기</div>
				</div>
			</form>
		</div>
	
	
	
	<script>
		function changePwd() {
			var newPwd = $('#newPwd').val();
			var newPwd2 = $('#newPwd2').val();
			console.log(newPwd + newPwd2);
			
			if(newPwd != newPwd2) {
				alert("비밀번호가 일치하지 않습니다.");
				return;
			} else {
				alert("일치해서 서블릿 보냅니다.");
				$('#newPwdForm').submit();
				<% request.getSession().removeAttribute("userId"); %>
				window.close();	
			}
			
			
		}
	</script>

</body>
</html>