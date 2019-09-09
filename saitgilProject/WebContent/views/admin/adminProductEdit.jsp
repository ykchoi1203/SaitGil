<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='product.model.vo.*, java.util.ArrayList' %>
<%
	Product p = (Product)request.getAttribute("product");
	ArrayList<ProductAttachment> list = (ArrayList<ProductAttachment>)request.getAttribute("fileList");
	int listsize = list.size();
	String allPic = "";
	for(int i=0; i<listsize; i++){
		if(i==0){
			allPic += list.get(i).getChangeName();
		} else{
			allPic += ","+ list.get(i).getChangeName();
		}
	}
	
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
	#productPicArea1, #productPicArea2, #productPicArea3{
		width:100px;
		height:100px;
		border:2px dashed darkgray;
		display:table-cell;
	}
</style>
</head>
<body>
	<div>
		<div class="content-wrapper">
			<div class="container">
				<div class="row">
					<div class="col-md-12" style="width: 1000px;">
						<h1 class="page-head-line">Edit Product</h1>
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
											<option value="상품">커플옷</option>
											<option value="상품">커플아이템</option>
											<option value="상품">꽃</option>
											<option value="상품">커플용품</option>
											<option value="상품">파티용품</option>
											<option value="상품">기타</option>
										</select>
									</div>
									<div class="form-group">
										<label for="ThumbnailImg">상품 대표 사진</label>
										<div style="width:150; height:150;" id='titleImgArea'>
											<%for(int i=0; i<list.size(); i++){ 
												if(list.get(i).getFileLevel()==1){
											%>
											<img id='titleImg' width='150px' height='150px' src='resources/product_uploadFiles/<%= list.get(i).getChangeName()%>'>
												<%} %>
											<%} %>
											<input type="hidden" name="hid1" id='hid1' value='0'>
										</div>
									</div>
									<div class="form-group">
										<label>상품 추가 사진</label> <br>
											<%if(list.size() > 1) {
												for(int i=0; i<list.size();i++){
													if(list.get(i).getFileLevel()>=2){%>
											<div class="productPic" id="productPicArea<%=i%>">
												<img id='pImg<%=i %>' width='98px' height='98px' src='resources/product_uploadFiles/<%= list.get(i).getChangeName()%>'>
												<input type="hidden" name="hid<%=i +1%>" id='hid<%=i+1%>' value='0'>
											</div>
													<%}%>
												<%} %>
												<%for(int i=list.size(); i<4; i++){ %>
											<div class="productPic" id="productPicArea<%=i%>">
												<img id='pImg<%=i %>' width='98px' height='98px'>
												<input type="hidden" name="hid<%=i+1%>" id='hid<%=i+1%>' value='0'>
											</div>
												<%} %>
											<%} else{%>
											<div class="productPic" id="productPicArea1">
												<img id='pImg1' width='98px' height='98px'>
												<input type="hidden" name="hid2" id='hid2' value='0'>
											</div>
											<div class="productPic" id="productPicArea2">
												<img id='pImg2' width='98px' height='98px'>
												<input type="hidden" name="hid3" id='hid3' value='0'>
											</div>
											<div class="productPic" id="productPicArea3">
												<img id='pImg3' width='98px' height='98px'>
												<input type="hidden" name="hid4" id='hid4' value='0'>
											</div>
											<%} %>
										<div id='fileArea'>
											<input type="file" multiple name='productImg1' id='productImg1' onchange="loadImg(this, 1);"> 			
											<input type="file" multiple name='productImg2' id='productImg2' onchange="loadImg(this, 2);">
											<input type="file" multiple name='productImg3' id='productImg3' onchange="loadImg(this, 3);"> 			
											<input type="file" multiple name='productImg4' id='productImg4' onchange="loadImg(this, 4);">  				
										</div>
										
										<script>
											$(function(){
												$("#fileArea").hide();

												$("#titleImg").click(function() {$("#productImg1").click();});

												$("#productPicArea1").click(function() {$("#productImg2").click();});
												$("#productPicArea2").click(function() {$("#productImg3").click();});
												$("#productPicArea3").click(function() {$("#productImg4").click();});
												
												
											});

											// 파일 첨부 했을 때 미리보기하는 기능
											// 참고 https://developer.mozilla.org/ko/docs/Web/API/FileReader
											function loadImg(value, num) {
												var a = 1+"";
												var b = 2+"";
												var c = 3+"";
												var d = 4+"";
												

												if (value.files
														&& value.files[0]) {
													// 파일을 읽어들일 FileReader 객체 생성
													var reader = new FileReader();

													reader.onload = function(e) {
														
														switch (num) {
														case 1:
															$('#titleImg').attr("src",e.target.result);
															$('#hid1').val(a);
														break; // data:URL
														case 2:
															$('#pImg1').attr("src",e.target.result);
															$('#hid2').val(b);
														break;
														case 3:
															$('#pImg2').attr("src",e.target.result);
															$('#hid3').val(c);
														break; // data:URL
														case 4:
															$('#pImg3').attr("src",e.target.result);
															$('#hid4').val(d);
														break;
														}
													}
													// 파일 읽어주는 메소드
													reader.readAsDataURL(value.files[0]);
													
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
											class="form-control" id="Amount" name = 'amount' value='<%= p.getAmount() %>' readonly />
									</div>
									<div class="form-group">
										<label>상세 설명</label>
										<div  style='width: 900px; height: 500px;'>
											<textarea class='summernote' name="content" style="display: none;"><%= p.getContents() %></textarea>
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
									<input type='hidden' value='<%= p.getpNo() %>' name='pNo' id='pNo'>
									<input type="hidden" name="getoldPic" id='getoldPic' value = '<%= allPic%>'>
									<div align=right>
										<button type="submit" class="btn btn-default">상품 수정</button>
										<button type="reset" class="btn btn-default" onclick="history.back(-1);">취소하기</button>
									</div>
								</form>
								<script>
									function postForm() {
										$('textarea[name="content"]').html($('.summernote').code());
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