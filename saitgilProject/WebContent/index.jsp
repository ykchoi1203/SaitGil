<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>우리 둘만의 사잇길</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="resources/css/main.css" />
		<style>
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
		</style>
	</head>	
	<body>
		<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<header>
						<h1 id="mainFont" style="display:inline">Sait Gil</h1>
						<p style="font-family:'Noto Serif KR'; font-size: 20px; margin-top:10px;">
							사잇길에 오신 것을 환영합니다. <br>
							프라이빗 채팅 및 다양한 서비스로 연인과의 추억을 쌓으세요. 
						</p>
					</header>
					<form action="<%= request.getContextPath() %>/login.me" method="post" onsubmit="return validate();">
						<input type="text" id="userId"  name="userId" placeholder="ID를 입력하세요." style="background-color:white; width:35%; display:inline !important" >
						<input type="password" id="userPwd" name="userPwd" placeholder="비밀번호를 입력하세요." style="display:inline !important; background-color:white; width:35%">
						<input type="submit" class="button big scrolly" value="LOGIN">
					</form>
					<br>
					<a href="views/member/agreePage.jsp" style="color:white;  font-weight: lighter; display: inline !important">SignUp</a>
					<a href="views/member/memberSearch.jsp" style="color:white; font-weight: lighter; display: inline !important; margin: 20px;">Forgotten Account</a>
					<a href="views/member/invitationPage.jsp">connectTest</a>
				</div>
			</section>
			
			<script>

				//로그인 
				function validate() {  
					if($('#userId').val().trim().length == 0 ){
						alert('아이디를 입력해주세요.');
						$('#userId').focus();
						return false; 
					} 
					
					if($('#userPwd').val().trim().length == 0){
						alert('비밀번호를 입력해주세요.');
						$('#userPwd').focus();
						return false;
					} 
					true;
				}

			 
			</script>

		<!-- Main -->
			<div id="main">

				<!-- Section -->
					<section class="wrapper style1">
						<div class="inner">
							<!-- 2 Columns -->
								<div class="flex flex-2">
									<div class="col col1">
										<div class="image round fit">
											<a href="generic.html" class="link"><img src="resources/images/pic01.jpg" alt="" /></a>
										</div>
									</div>
									<div class="col col2">
										<h3 style="font-family:'Do Hyeon'; color: #e23a6e; font-weight:200;">우리의 달콤한 추억 저장소, 사잇길</h3>
										<p style="font-family:'Noto Serif KR'; font-size: 17px;">
											<br>
											지금 연애하고 계신가요? <br>
											사잇길에서 프라이빗 채팅 및 다양한 서비스를 통해 연인과의 소중한 추억을 저장하세요. <br>
											복잡한 커플앱들은 안녕~! <br>
											기존의 커플들을 위한 앱-웹의 서비스들을 한 곳에서 편리하게 즐기실 수 있습니다! 

										</p>
										<p style="font-family:'Noto Serif KR'; font-size: 17px;">
											사잇길은 연인과 더 사랑스럽게 소통하고, 소중한 추억을 간편하게 저장할 수 있는 공간입니다. 	
										</p>
										<a href="#" class="button">Let's Start</a>
									</div>
								</div>
						</div>
					</section>

				<!-- Section -->
					<section class="wrapper style2">
						<div class="inner">
							<div class="flex flex-2">
								<div class="col col2">
									<h3 style="font-family:'Do Hyeon'; color:#e23a6e; font-weight:200;">사잇길의 다양한 기능을 즐겨보세요!</h3>
									<p>
										<ul>
											<li>사랑스런 둘만의 대화</li>
											<li>소중한 추억 앨범</li>
											<li>특별한 기념일</li>
											<li>함께 쓰는 캘린더 </li>
											<li>함께 달성할 버킷리스트</li>
											<li>실제 커플들이 공유하는 커뮤니티</li>
											<li>연인과의 친밀도와 유대감을 높혀줄 스토어</li>
										</ul>
									</p>
									<a href="#" class="button">Let's Start</a>
								</div>
								<div class="col col1 first">
									<div class="image round fit">
										<a href="generic.html" class="link"><img src="resources/images/pic02.jpg"/></a>
									</div>
								</div>
							</div>
						</div>
					</section>

				<!-- Section -->
					<section class="wrapper style1">
						<div class="inner">
							<header class="align-center">
								<h2 style="color:#e23a6e; font-family: Noto Serif KR">Created By</h2>
								<!-- <p>Cras sagittis turpis sit amet est tempus, sit amet consectetur purus tincidunt.</p> -->
							</header>
							<div class="flex flex-3">
								<div class="col align-center">
									<div class="image round fit">
										<img src="resources/images/team1.png"/>
									</div>
									<p>최 영근</p>
								</div>
								<div class="col align-center">
									<div class="image round fit team">
										<img src="resources/images/team5.png" alt="" />
									</div>
									<p>한 미선</p>
								</div>
								<div class="col align-center">
									<div class="image round fit team">
										<img src="resources/images/team2.png" alt="" />
									</div>
									<p>김 성은</p>
								</div>
							</div>
							<div class="flex flex-3">
									<div class="col align-center">
										<div class="image round fit team">
											<img src="resources/images/team3.png" alt="" />
										</div>
										<p>손 권모</p>
									</div>
									<div class="col align-center">
											<h1 style="display:inline; line-height: 270px; font-size:70px; font-family: 'Great Vibes'; color:#fa96b5">Sait Gil</h1>
									</div>
									<div class="col align-center">
										<div class="image round fit">
											<img src="resources/images/team4.png" alt="" />
										</div>
										<p>유 현규</p>
									</div>
								</div>
						</div>
					</section>

			</div>

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