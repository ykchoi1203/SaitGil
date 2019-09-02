<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>우리 둘만의 사잇길</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="resources/css/mainLogin.css" />
		<style>
		    /* 커플 메인사진  */
			#banner {  
				display: -ms-flexbox;
				-ms-flex-pack: center;
				-ms-flex-align: center;
				padding: 0em 0em 0em 0em;
				-moz-align-items: center;
				-webkit-align-items: center;
				-ms-align-items: center;
				align-items: center;
				display: -moz-flex;
				display: -webkit-flex;
				display: -ms-flex;
				display: flex;
				-moz-justify-content: center;
				-webkit-justify-content: center;
				-ms-justify-content: center;
				justify-content: center;			
				background-image: url("resources/images/mainImage.jpg");
				background-position: center;
				background-size: cover;
				background-repeat: no-repeat;
				border-top: 0;
				min-height: 100vh;
				height: 100vh !important;
				position: relative;
				text-align: center;
			}
			
			#notice {
				width:80%;
				margin:auto;
				color:red;
				text-align:left;
				margin-left:15% ;
			}
		</style>
	</head>
	<body>
		<div id="notice">
		<img src="resources/images/icons/notice.png" width="30px" height="30px"> <b onclick="location.href='<%= request.getContextPath() %>/list.no'">[공지사항]</b> 사잇길 점검시간 공지안내
		</div>
	<%@ include file="../common/menubar.jsp" %>
		<!-- Banner -->
		<section id="banner">
			<div class="inner" style="width:100%; font-family:'Nanum Myeongjo'; margin: 0em !important; padding: 0em !important; border: 0em !important;">
					<header>
						<a href="#selectPhoto" style="float:right;" onclick="changePhoto();"> <img src="resources/images/photoIcon.png" width="40px" class="icon"></a>
						<h3 class="mainDay" >  &nbsp;&nbsp;&nbsp;&nbsp; 우리 함께한지</h3>
						<h2 class="mainDay" style="font-family:'Nanum Myeongjo'; color: #fa96b5" ><a href="#none" onclick="changeDate();">&nbsp;&nbsp;&nbsp;<%= dday %>일째</a></h2>
						<h3 class="mainDay"><%= loginUser.getUserName() %> ♥ <%= partner.getUserName() %></h3>
					</header>
				</div>
			</section>

			<script>
				function changePhoto() {
					alert('배경 사진바꿔라');
				}

				function changeDate() {
					var firstDate = document.getElementById('firstDate');
					var date = prompt('처음 만날 날짜를 입력하세요(YYYY-MM-DD)');
					confirm(date + "가 맞습니까?");
					
					location.href="<%= request.getContextPath() %>/changeDate.sh?meetDate=" + date + "&cCode=<%= sf.getcCode() %>";

				}
			</script>


		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-snapchat"><span class="label">Snapchat</span></a></li>
					</ul>
					<p>&copy; Untitled. All rights reserved. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com">Unsplash</a>.</p>
				</div>
			</footer>

		<!-- Scripts -->
			<script src="resources/js/jquery.min.js"></script>
			<script src="resources/js/jquery.scrolly.min.js"></script>
			<script src="resources/js/jquery.scrollex.min.js"></script>
			<script src="resources/js/skel.min.js"></script>
			<script src="resources/js/util.js"></script>
			<script src="resources/js/main.js"></script>

	</body>
</html>