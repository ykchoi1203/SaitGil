<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, photoAlbum.model.vo.*"%>
<%
	Folder f = (Folder)request.getAttribute("f");

	ArrayList<Attachment> fileList = (ArrayList<Attachment>)request.getAttribute("fileList");
	
	Attachment titleImg = fileList.get(0);	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="resources/css/main.css" />
<style>
	.outer{
		width:1000px;
		heigth:650px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	#titleImgArea{
		width:500px;
		heigth:300px;
		margin-left:auto;
		margin-right:auto;
	}
	#contentArea{
		height:30px;
	}
	#titleImg{
		width:350px;
		height:250px;
	}
	.detail td{
		width:1000px;
		text-align:center;
		border:1px solid white;
	}
	.detailImgArea{
		width:250px;
		height:210px;
		margin-left:auto;
		margin-right:auto;
	}
	.detailImg{
		width:250px;
		height:180px;
	}
	
	#thumbnailImg:hover{
		cursor:pointer;
	}
</style>
</head>
<body>
<body>

<%@ include file="../common/menubar.jsp" %>

	<div class="page-wrap">

		<!-- Nav -->
		<nav id="nav">
		<ul>
			<li><a href="index.html"><span class="icon fa-home"></span></a></li>
			<li><a href="gallery.html" class="active"><span
					class="icon fa-camera-retro"></span></a></li>
			<li><a href="generic.html"><span class="icon fa-file-text-o"></span></a></li>
		</ul>
		</nav>

		<!-- Main -->
		<section id="main"> <!-- Header --> <header id="header">
		<div>
			SitGil <span>photoalbum</span>
		</div>
		</header> <!-- Gallery --> <section id="galleries"> <!-- Photo Galleries -->
		<div class="gallery">

			<!-- Filters -->
			<header>
			<h1>Gallery</h1>


			
				<ul class="tabs">
					<button>삭제하기</button>
					<button>뒤로가기</button>
					<button>다운로드</button>
					<button>폴더이동(될까..)</button>
					<button onclick="location.href='<%= contextPath %>/insert.ph' method='post' enctype='multipart/form-data'">저장하기</button>
				</ul>
			</header>

		<div class="content">
				<div id="thumbnailImgArea">
					<img id="thumbnailImg" width="350px" height="250px" >
				</div>
				
				<!-- 파일 업로드 하는 div -->
				<form method="post" enctype="multipart/form-data" id="test">
				<div id="fileArea">
					<input type="file" multiple name="thumbnailImg1" id="thumbnailImg1" onchange="loadImg(this,1);">
				</div>
				</form>
				<script>
					$(function(){
						$("#fileArea").hide();
						
						$("#thumbnailImgArea").click(function(){
							$("#thumbnailImg1").click();
						});
						
					});
					

					function loadImg(value, num){
						
					}
					      var form = $('#test')[0];
			              var formData = new FormData(form);
			              var reader = new FileReader();
			              
			              var reader = new FileReader();
			             reader.onload = function(e) { 
			                 if(confirm("정말로 바꾸시겠습니까?")) {

			                  $.ajax({
			                      url : 'insert2.ph',
			                      type : 'POST',
			                      enctype:"multipart/form-data",
			                      data : formData,
			                      contentType : false,
			                      processData : false,
			                  success:function(result) {
			                     console.log("result: " + result);
			                     if(result == "success") {
			                        alert("프로필이 변경되었습니다.");
			                     } else {
			                        alert("프로필 변경에 실패하였습니다.");
			                     }                  
			                  },
			                  error:function() {
			                     console.log("서버통신 실패");
			                  }
			               })
			              }
					}
				</script>
			
			<table class="detail" align="center">
			<tr>
				
				
					<div id="titleImgArea" align="center">
						<img id="titleImg" src="<%= contextPath %>/resources/uploadImages/<%= titleImg.getChangeName() %>">
					</div>
				</td>
				<td>
				<%-- <button onclick="location.href='<%= contextPath %>/download.th?fId=<%= titleImg.getfId() %>'">다운로드</button>  --%>
				</td>
			</tr>
			
		</table>
		
		<table class="detail">
			<tr>
				<% for(int i=1; i<fileList.size(); i++){ %>
				<td>
					<div class="detailImgArea">
						<img class="detailImg" src="<%= contextPath %>/resources/uploadImages/<%= fileList.get(i).getChangeName() %>">
						<button onclick="location.href='<%= contextPath %>/download.ph?fId=<%= fileList.get(i).getfId() %>'">다운로드</button>
					</div>
				</td>
				<% } %>
			</tr>
		</table>
			
			
			
			
			

			
		</div>
		

		</section> <!-- Contact --> <section id="contact"> <!-- Social -->
		<div class="social column">
			
		</div>

		<!-- Form -->
		<div class="column">
			
		</div>

		</section> <!-- Footer --> <footer id="footer">
		<div class="copyright">
			&copy; Untitled Design: <a href="https://templated.co/">TEMPLATED</a>.
			Images: <a href="https://unsplash.com/">Unsplash</a>.
		</div>
		</footer> </section>
	</div>

	<!-- Scripts -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.poptrox.min.js"></script>
	<script src="resources/js/jquery.scrolly.min.js"></script>
	<script src="resources/js/skel.min.js"></script>
	<script src="resources/js/util.js"></script>
	<script src="resources/js/main.js"></script>
	
</body>
</html>