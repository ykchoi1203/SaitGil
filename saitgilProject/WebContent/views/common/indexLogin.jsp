<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*, shareFile.model.vo.*, notice.model.vo.*"%>
<%
	String couplePic = ((ShareFile)session.getAttribute("sf")).getcPicturePath();
%>
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
				background-image: url("resources/shareFile/<%= couplePic %>");
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
	<%@ include file="../common/menubar.jsp" %>

	
		<!-- Banner -->
		<section id="banner">
			<div class="inner" style="width:100%; font-family:'Nanum Myeongjo'; margin: 0em !important; padding: 0em !important; border: 0em !important;">
					<header>
						<a href="#selectPhoto" id="photoBtn""style="float:right;"> <img src="resources/images/photoIcon.png" width="40px" class="icon"></a>
						<h3 class="mainDay" > <!--  &nbsp;&nbsp;&nbsp;&nbsp;  -->우리 함께한지</h3>
						<h2 class="mainDay" style="font-family:'Nanum Myeongjo'; color: #fa96b5" ><a href="#none" onclick="changeDate();"><!-- &nbsp;&nbsp;&nbsp; --><%= dday %>일째</a></h2>
						<h3 class="mainDay"><%= loginUser.getUserName() %> ♥ <%= partner.getUserName() %></h3>
					</header>
				</div>
			</section>
			
			<form action="updateProfile.co" method="post" id="updateProfileForm" enctype="mutipart/form-data">
				<input type="file" id="changePhoto" name="changePhoto" multiple onchange="updateCouplePhoto();">
			</form>
	
	
			<script>
				$(function() {
					$('#changePhoto').hide();
					
					$('#photoBtn').click(function() {
							if(confirm("커플사진을 변경하시겠습니까?")){
								$('#changePhoto').click();

							}
							
						
					})	
					
				})
				
				
				
				function updateCouplePhoto() {
		        		
		        		var form = $('#updateProfileForm')[0];
		        		var formData = new FormData(form);

			        	    $.ajax({
			        	        url : 'updateProfile.co',
			        	        type : 'POST',
			        	        enctype:"multipart/form-data",
			        	        data : formData,
			        	        contentType : false,
			        	        processData : false,
								success:function(result) {
									console.log('result:' + result);
									if(result == 'fail') {
										alert('커플 사진 변경에 실패하였습니다.');
									} else {
										alert("커플 사진이 변경되었습니다. ")
										$('#banner').css("background-image",'url("resources/shareFile/' + result + '")');
									}
								},
								error:function() {
									console.log("서버통신 실패");
								}
							})
			        	}
			        			
				
				

				function changeDate() {
					var firstDate = document.getElementById('firstDate');
					var date = prompt('처음 만날 날짜를 입력하세요(YYYY-MM-DD)');
					
					if(date == null) {
						alert("취소하셨습니다.");
					}else {
						confirm(date + "가 맞습니까?");
						
						location.href="<%= request.getContextPath() %>/changeDate.sh?meetDate=" + date + "&cCode=<%= sf.getcCode() %>";
					}
				}
			</script>

		<!-- Scripts -->
			<script src="resources/js/jquery.min.js"></script>
			<script src="resources/js/jquery.scrolly.min.js"></script>
			<script src="resources/js/jquery.scrollex.min.js"></script>
			<script src="resources/js/skel.min.js"></script>
			<script src="resources/js/util.js"></script>
			<script src="resources/js/main.js"></script>

	</body>
</html>