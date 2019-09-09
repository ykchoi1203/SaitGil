<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.*" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link href="resources/css/bootstrap.css" rel="stylesheet" />
<link href="resources/css/font-awesome.css" rel="stylesheet" />
<link href="resources/css/style.css" rel="stylesheet" />
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.js"></script>
<style>
	.productPic {
		height: 100px;
		width: 100px;
		border: 2px dashed gray;
		margin: 10px;
	}
</style>
</head>
<body>
	<div>
		<div class="content-wrapper">
			<div class="container">
				<div class="row">
					<div class="col-md-12" style="width: 1000px;">
						<h1 class="page-head-line">Edit Notice</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default" style="width: 950px;">
							<div class="panel-heading">공지사항</div>
							<div class="panel-body">
								<form action='<%= request.getContextPath()%>/editNotice.ad' method='post'>
									<div class="form-group">
										<label for="Product_Name">제목</label> 
										<input type="text" class="form-control" id="nTitle" name='nTitle' value='<%= n.getNoticeTitle() %>' />
									</div>
									<div class="form-group">
										<label>상세 설명</label>
										<div  style='width: 900px; height: 500px;'>
											<textarea id='nContent' name="nContent" cols='130' rows='25' style="resize:none;"><%= n.getNoticeContent() %></textarea>
										</div>
									</div>
									<div align=right>
										<button type="submit" class="btn btn-default">공지사항 수정</button>
										<button type="reset" class="btn btn-default" onclick="history.back(-1);">취소하기</button>
										<input type="hidden" value='<%= n.getNoticeNo()%>' name='nNo'>
									</div>
								</form>
								<script>
									$(function(){
										
									});
									
								</script>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>