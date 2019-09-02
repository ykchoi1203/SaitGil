<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, photoAlbum.model.vo.*"%>
<%
	ArrayList<Folder> folderList = (ArrayList<Folder>)request.getAttribute("folderList");
	ArrayList<Attachment> atList = (ArrayList<Attachment>)request.getAttribute("atList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="resources/css/main.css" />
<style>
	.content{
		height:420px;
		margin-left:auto;
		margin-right:auto;
	}
	
	.folder-list{
		display:inline-block;
		width:250px;
		align:center;
		margin:10px;
	}
	.folder-list:hover{
		opacity:0.7;
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
					<button onclick="window.open('<%= contextPath %>/insertForm.ph', '폴더생성창', 'width=500,height=500, location=no, status=no, scrollbar=no')">폴더 생성</button>
					<button>삭제하기</button>
					<button>수정하기</button>
				</ul>
			</header>



			<div class="content">
				
			 	<% for(Folder f : folderList){ %>
					<div class="folder-list" align="center">
						<input type="hidden" value="<%= f.getfId() %>">
						<div> 
							<% for(Attachment at : atList){ %> 
								 <% if(f.getfId() == at.getfId()){%>
									<img src="<%= contextPath %>/resources/uploadImages/<%= at.getChangeName() %>" width="210px" height="150px">
								<%-- <% } %> --%>
							<% } %>
						<%} %>
						</div>
						
					</div>
				<% } %>

			
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
	<script>
		$(function(){
			$(".folder-list").click(function(){
				var fId = $(this).children().eq(0).val();
				location.href="<%= contextPath %>/detail.ph?fId=" + fId;
			});
		});
	</script>

</body>
</html>