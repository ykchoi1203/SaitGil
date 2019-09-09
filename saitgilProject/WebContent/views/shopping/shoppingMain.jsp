<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = 'java.util.*, product.model.vo.*' %>
<%
	ArrayList<Product> pList = (ArrayList<Product>)request.getAttribute("pList");
	ArrayList<Product> goodSeller = (ArrayList<Product>)request.getAttribute("goodSeller");
	ArrayList<ProductAttachment> paList = (ArrayList<ProductAttachment>)request.getAttribute("paList");   
	ArrayList<Product> goodpCount = (ArrayList<Product>)request.getAttribute("goodpCount");
%>
 
<!DOCTYPE html>
<html>
<head>
    <!-- <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>사잇길 쇼핑몰</title>
    <link href="resources/css/eShopping/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/eShopping/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/eShopping/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/eShopping/price-range.css" rel="stylesheet">
    <link href="resources/css/eShopping/animate.css" rel="stylesheet">
	<link href="resources/css/eShopping/main.css" rel="stylesheet">
	<link href="resources/css/eShopping/responsive.css" rel="stylesheet"> -->
    <!--[if lt IE 9]>
    
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->   
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <style>
    	.single-products:hover{
    		cursor:pointer
    	}
    	.cart{
    		background:white;
    	}
    </style>    
</head><!--/head-->

<body>
	<%@ include file="shoppingMenubar.jsp" %>
	<br>
	<br>
	<br>
	
	<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
							<div class="item active">
								
								<div class="col-sm-6" style="width:1040px;height:540;">
									<img src="resources/images/home/banner1.png"  width=930 height=380 />
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6" style="width:1040px;height:540;">
									<img src="resources/images/home/banner2.png"  width=930 height=380 />
								</div>
							</div>
							
							<div class="item">
								<div class="col-sm-6" style="width:1040px;height:540;">
									<img src="resources/images/home/banner3.png"  width=930 height=380 />
								</div>
							</div>
							
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</section><!--/slider-->
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<div class="shipping text-center" style="background:white;"><!--shipping-->
							<a href="http://ssd2.ilovegame.co.kr/"><img src="resources/images/shop/ad111.png" width="170"/></a>
						</div><!--/shipping-->
						
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">많이 팔린 상품</h2>
						<%for(int i=0; i<goodSeller.size(); i++){ %>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
										<div class="productinfo text-center">
											<input type='hidden' value="<%= goodSeller.get(i).getpNo() %>">
											<input type='hidden' value="<%= goodSeller.get(i).getAmount() %>">
											  <%for(int j=0; j<paList.size();j++){ %>
											 	<%if(goodSeller.get(i).getpNo() == paList.get(j).getpNo()){ %>
												<img width='268' height='249' src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(j).getChangeName() %>">
												<%} %>
											<%} %>
												<h2><%=goodSeller.get(i).getPrice() %>원</h2>
												<p><%= goodSeller.get(i).getpName() %></p>
												
										</div>
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href="#" class="ggim"><i class="fa"></i>찜하기</a></li>
										<li><a href="#" class="cart1"><i class="fa"></i>장바구니에 추가</a></li>
									</ul>
								</div>
							</div>
						</div>
						<%} %>
						<script>
							$(function(){
								$(".productinfo").children('img').click(function(){
									var pNo = $(this).parent().children('input').val();
									location.href="<%=request.getContextPath()%>/detail.sp?pNo="+pNo;
								});
								
								$(".cart1").click(function(){
									var pNo = $(this).parent().parent().parent().parent().children().children().children('input').eq(0).val();
									console.log(pNo);
									var amount = $(this).parent().parent().parent().parent().children().children().children('input').eq(1).val();
									if(amount>0){
										location.href="<%=request.getContextPath()%>/addCart.sp?pNo="+pNo;
									}else{
										alert("해당 상품은 매진되었습니다.");
									}
								});
								
								$(".ggim").click(function(){
									var pNo = $(this).parent().parent().parent().parent().children().children().children('input').val();
									console.log(pNo);
									location.href="<%=request.getContextPath()%>/addGgim.sp?pNo="+pNo;
								});
							});
							
						</script>
						
					</div><!--features_items-->
					
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center">조회수 높은 상품</h2>
						
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">	
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<input type='hidden' value="<%= goodpCount.get(0).getpNo() %>">
													<input type='hidden' value="<%= goodpCount.get(0).getAmount() %>">
													<p>1위</p>
													<%for(int i=0; i<paList.size(); i++){ 
														if(paList.get(i).getpNo()==goodpCount.get(0).getpNo()){
													%>
													<img src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(i).getChangeName() %>" width="98px" height="200px" />
													<%	} 
													}%>
													<h2><%= goodpCount.get(0).getPrice() %>원</h2>
													<p><%= goodpCount.get(0).getpName() %></p>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#" class="ggim"><i class="fa"></i>찜하기</a></li>
													<li><a href="#" class="cart1"><i class="fa"></i>장바구니에 추가</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<input type='hidden' value="<%= goodpCount.get(1).getpNo() %>">
													<input type='hidden' value="<%= goodpCount.get(1).getAmount() %>">
													<p>2위</p>
													<%for(int i=0; i<paList.size(); i++){ 
														if(paList.get(i).getpNo()==goodpCount.get(1).getpNo()){
													%>
													<img src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(i).getChangeName() %>"width="98px" height="200px" />
													<%	} 
													}%>
													<h2><%= goodpCount.get(1).getPrice() %>원</h2>
													<p><%= goodpCount.get(1).getpName() %></p>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#" class="ggim"><i class="fa"></i>찜하기</a></li>
													<li><a href="#" class="cart1"><i class="fa"></i>장바구니에 추가</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<input type='hidden' value="<%= goodpCount.get(2).getpNo() %>">
													<input type='hidden' value="<%= goodpCount.get(2).getAmount() %>">
													<p>3위</p>
													<%for(int i=0; i<paList.size(); i++){ 
														if(paList.get(i).getpNo()==goodpCount.get(2).getpNo()){
													%>
													<img src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(i).getChangeName() %>" width="98px" height="200px" />
													<%	} 
													}%>
													<h2><%= goodpCount.get(2).getPrice() %>원</h2>
													<p><%= goodpCount.get(2).getpName() %></p>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#" class="ggim"><i class="fa"></i>찜하기</a></li>
													<li><a href="#" class="cart1"><i class="fa"></i>장바구니에 추가</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="item">	
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<input type='hidden' value="<%= goodpCount.get(3).getpNo() %>">
													<input type='hidden' value="<%= goodpCount.get(3).getAmount() %>">
													<p>4위</p>
													<%for(int i=0; i<paList.size(); i++){ 
														if(paList.get(i).getpNo()==goodpCount.get(3).getpNo()){
													%>
													<img src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(i).getChangeName() %>" width="98px" height="200px"/>
													<%	} 
													}%>
													<h2><%= goodpCount.get(3).getPrice() %>원</h2>
													<p><%= goodpCount.get(3).getpName() %></p>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#" class="ggim"><i class="fa"></i>찜하기</a></li>
													<li><a href="#" class="cart1"><i class="fa"></i>장바구니에 추가</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<input type='hidden' value="<%= goodpCount.get(4).getpNo() %>">
													<input type='hidden' value="<%= goodpCount.get(4).getAmount() %>">
													<p>5위</p>
													<%for(int i=0; i<paList.size(); i++){ 
														if(paList.get(i).getpNo()==goodpCount.get(4).getpNo()){
													%>
													<img src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(i).getChangeName() %>" width="98px" height="200px"/>
													<%	} 
													}%>
													<h2><%= goodpCount.get(4).getPrice() %>원</h2>
													<p><%= goodpCount.get(4).getpName() %></p>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#" class="ggim"><i class="fa"></i>찜하기</a></li>
													<li><a href="#" class="cart1"><i class="fa"></i>장바구니에 추가</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<input type='hidden' value="<%= goodpCount.get(5).getpNo() %>">
													<input type='hidden' value="<%= goodpCount.get(5).getAmount() %>">
													<p>6위</p>
													<%for(int i=0; i<paList.size(); i++){ 
														if(paList.get(i).getpNo()==goodpCount.get(5).getpNo()){
													%>
													<img src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(i).getChangeName() %>" width="98px" height="200px"/>
													<%	} 
													}%>
													<h2><%= goodpCount.get(5).getPrice() %>원</h2>
													<p><%= goodpCount.get(5).getpName() %></p>
												</div>
											</div>
											<div class="choose">
												<ul class="nav nav-pills nav-justified">
													<li><a href="#" class="ggim"><i class="fa"></i>찜하기</a></li>
													<li><a href="#" class="cart1"><i class="fa"></i>장바구니에 추가</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							 <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							  </a>
							  <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
								<i class="fa fa-angle-right"></i>
							  </a>			
						</div>
					</div><!--/recommended_items-->
					
				</div>
			</div>
		</div>
	</section>
</body>
</html>