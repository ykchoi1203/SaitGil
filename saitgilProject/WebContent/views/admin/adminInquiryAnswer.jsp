<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='inquiry.model.vo.*' %>
<%
	Inquiry i = (Inquiry)request.getAttribute("i");
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
						<h1 class="page-head-line">문의글 답변하기</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default" style="width: 950px;">
							<div class="panel-heading">문의글</div>
							<div class="panel-body">
								<form action='<%= request.getContextPath()%>/updateAnswer.ad' method='post'>
									<div class="form-group">
										<label for="Product_Name">문의글 번호 : </label>
										<label for="Product_Name"><%= i.getInquiryNo() %></label>
										<input type="hidden" value="<%= i.getInquiryNo() %>" name='pNo'>
									</div>
									<div class="form-group">
										<label for="writer">작성자 : </label>
										<label ><%= i.getWriter() %></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label for="writer">작성일 : </label>
										<label ><%= i.getDate() %></label>
									</div>
									<div class="form-group">
										<label for="Title">제목 : </label>
										<label ><%= i.getInquiryTitle() %></label>
									</div>
									<div class="form-group">
										<label>문의 내용</label>
										<div >
											<textarea  name="content" cols='120' rows='10' style="resize:none;" readonly><%= i.getInquiryContent() %></textarea>
										</div>
										
									</div>
									<br><br>
									<div class="form-group">
										<label>문의 답변</label>
										<div >
											<textarea  name="answer" cols='120' rows='10' style="resize:none;"><% if(i.getrStatus().equals("Y")){%><%=i.getrContent()%><%}%></textarea>
										</div>
										
									</div>
									
									<div align=right>
										<% if(i.getrStatus().equals("N")){%>
											<button type="submit" class="btn btn-default" id='t'>답변하기</button>
										<% } else{%>
										<button type="submit" class="btn btn-default">답변 수정하기</button>
										<%} %>
										<button type="reset" class="btn btn-default" onclick="history.back(-1);">취소하기</button>
									</div>
									
								</form>
							
								
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>