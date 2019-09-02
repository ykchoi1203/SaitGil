<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='product.model.vo.*, java.util.ArrayList' %>
<%
	Product p = (Product)request.getAttribute("product");
	ArrayList<ProductAttachment> list = (ArrayList<ProductAttachment>)request.getAttribute("fileList");
	int listsize = list.size();
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
						<h1 class="page-head-line">ADD Product</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default" style="width: 950px;">
							<div class="panel-heading">상품 정보</div>
							<div class="panel-body">
								<form action='<%= request.getContextPath()%>/editProduct.ad' method='post' onsubmit="postForm();" enctype="multipart/form-data">
									<div class="form-group">
										<label for="Product_Name">상품명</label> <input type="text"
											class="form-control" id="Product_Name" name='pName'
											placeholder="상품명을 입력하세요." />
									</div>
									<div class="form-group">
										<label for="Product_Category">카테고리</label> <select
											name="category" id="Product_Category" class="form-control">
											<option value="상품">상품</option>
											<option value="상품">이모티콘</option>
										</select>
									</div>
									<div class="form-group">
										<label>상품 사진</label> <br>
										<table>
											<tr>
												<td>
													<div class="productPic" id="productPicArea1">
														<img id='pImg1' width='98px' height='98px'>
													</div>
												</td>
												<td>
													<div class="productPic" id="productPicArea2">
														<img id='pImg2' width='98px' height='98px'>
													</div>
												</td>
												<td>
													<div class="productPic" id="productPicArea3">
														<img id='pImg3' width='98px' height='98px'>
													</div>
												</td>
											</tr>
										</table>
										<div id='fileArea'>
												<% if(!list.isEmpty()){%>
													<% for(int i=0; i<list.size();i++){%>
														<input type="file" multiple name='productImg<%= i+1 %>' value="<%= list.get(i).getOriginName()%>" id='productImg<%= i+1 %>' onchange="loadImg(this, <%= i+1 %>);"> 			
													<%}%>
													<% for(int i=listsize; i<3;i++){%>
														<input type="file" multiple name='productImg<%= i+1 %>' id='productImg<%= i+1 %>' onchange="loadImg(this, <%= i+1 %>);"> 				
													<%}%>
												<%} else{%>
													<% for(int i=1; i<=3;i++){%>
														<input type="file" multiple name='productImg<%= i+1 %>' id='productImg<%= i+1 %>' onchange="loadImg(this, <%= i+1 %>);"> 				
													<%}%>
												<%}%>
										</div>
										<script>
											// 미리보기 영역을 클릭할 때 파일 첨부 창이 뜨도록
											$(function() {
												$("#fileArea").hide();
												
												

												$("#productPicArea1").click(function() {$("#productImg1").click();});

												$("#productPicArea2").click(function() {$("#productImg2").click();});

												$("#productPicArea3").click(function() {$("#productImg3").click();});
											});

											// 파일 첨부 했을 때 미리보기하는 기능
											// 참고 https://developer.mozilla.org/ko/docs/Web/API/FileReader
											function loadImg(value, num) {

												if (value.files
														&& value.files[0]) {
													// 파일을 읽어들일 FileReader 객체 생성
													var reader = new FileReader();

													reader.onload = function(e) {
														switch (num) {
														case 1:
															$('#pImg1').attr("src",e.target.result);
														break; // data:URL
														case 2:
															$('#pImg2').attr("src",e.target.result);
														break;
														case 3:
															$('#pImg3').attr("src",e.target.result);
														break;
														}
													}
													// 파일 읽어주는 메소드
													reader.readAsDataURL(value.files[0]);
													console.log($("#productImg1").val());
												}
											}
										</script>
									</div>
									<div class="form-group">
										<label for="Price">가격</label> <input type="number"
											class="form-control" id="Price" name='price' placeholder="상품 가격을 입력하세요." />
									</div>
									<div class="form-group">
										<label for="Price">수량</label> <input type="number"
											class="form-control" id="Amount" name = 'amount' placeholder="상품 수량을 입력하세요." />
									</div>
									<div class="form-group">
										<label>상세 설명</label>
										<div id='summernote' class='summernote' style='width: 900px; height: 500px;'>
											<%= p.getContents() %>
											<!--  <textarea id='summernote' class='summernote' name="content" style="display: none;"></textarea>-->
										</div>
										<script>
											$(function() {
												$(document).ready(function() {
													$('.summernote').summernote({
														width : 900,
														height : 450,
														lang : 'ko-KR',
													});
													
													$('#Product_Name').val('<%= p.getpName()%>');
													$('#Price').val('<%= p.getPrice()%>');
													$('#Amount').val('<%= p.getAmount()%>');
													
												});
												
											});
											
										</script>
									</div>
									<div align=right>
										<button type="submit" class="btn btn-default">상품 수정</button>
										<button type="reset" class="btn btn-default">취소하기</button>
									</div>
								</form>
								<script>
									function postForm() {
										$('textarea[name="Contents"]').html($('.summernote').code());
									}
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