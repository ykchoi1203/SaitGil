<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = 'java.util.*, product.model.vo.*' %>
<% 
	Product p = (Product)request.getAttribute("p");
	ArrayList<ProductAttachment> paList = (ArrayList<ProductAttachment>)request.getAttribute("paList");
	ArrayList<ProductAttachment> pList = (ArrayList<ProductAttachment>)request.getAttribute("pList");
	ArrayList<Product> goodpCount = (ArrayList<Product>)request.getAttribute("goodpCount");
 %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<title>Insert title here</title>
<style>
	table{
		width:300px;
		height:300px;
	}
	label{
		font-size:20px;
	}
	.item{
		width:380;
		height:380;
		text-align:center;
	}
	
</style>
</head>
<body>
	<%@ include file="shoppingMenubar.jsp" %>
	<br>
	<br>
	<br>
	
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
					<div class="product-details"><!--product-details-->
						<div class="col-sm-5">
							<div id='similar-product' class="carousel slide" class="carousel slide" data-ride="carousel">
								 <div class="carousel-inner">
								 	<%int cnt = 0; %>
								 	<%for(ProductAttachment pa : pList) {%>
								 		<%if(cnt == 0) {%>
										<div class="item active">
										 	<img class="pic" width='250' height='300' src="resources/product_uploadFiles/<%= pa.getChangeName()%>" alt="" />
										</div>
										
										<%cnt++;} else{ %>
										<div class="item">
										 	<img class="pic" width=250 height=300 src="resources/product_uploadFiles/<%= pa.getChangeName()%>" alt="" />
										</div>
										<%cnt++;} %>
									<%} %>
									</div>
									<%if(cnt > 1) {%>
									<a class="left item-control" href="#similar-product" data-slide="prev">
										<i class="fa fa-angle-left"></i>
								 	 </a>
								  	<a class="right item-control" href="#similar-product" data-slide="next">
										<i class="fa fa-angle-right"></i>
								  	</a>
								  	<%} %>
							</div>

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<table>
									<tr>
										<td>
											<label>상품명 : </label>
										</td>
										<td>
											<label><%= p.getpName() %></label>
										</td>
									</tr>
									<tr>
										<td>
											<label>상품 번호 : </label>
										</td>
										<td>
											<label><%= p.getpNo() %></label>
										</td>
									</tr>
									<tr>
										<td>
											<label>가격 : </label>
										</td>
										<td>
											<label><%= p.getPrice() %>원</label>
										</td>
									</tr>
									<tr>
										<td>
											<label>구매 갯수:</label>
										</td>
										<td>
											<input type="text" value="1" name='amount' id='amount'/>
										</td>
										<td>
											<label>개</label>
										</td>
									</tr>
									<tr>
										<td>
											<label>남은 수량:</label>
										</td>
										<td>
											<label><%=p.getAmount() %></label>
										</td>
										<td>
											<label>개</label>
										</td>
									</tr>
								
								</table>
								<span>
									<%if(p.getAmount()>0) {%>
									<button type="button" class="btn btn-fefault cart" onclick="addCart();">
										<i class="fa fa-shopping-cart"></i>
										장바구니에 추가
									</button>
									<%} else{ %>
									<button type="button" class="btn btn-fefault cart" disabled>
										<i class="fa fa-shopping-cart"></i>
										이미 매진되었습니다.
									</button>
									<%} %>
									<button type="button" class="btn btn-fefault cart" onclick="addGgim();">
										<i class="fa fa-shopping-cart"></i>
										찜목록에 추가
									</button>
								</span>
								
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
					<script>
						function addCart(){
							var pNo = <%=p.getpNo()%>;
							var amount = $('#amount').val();
							location.href="<%=request.getContextPath()%>/addCart.sp?pNo="+pNo+"&amount="+amount;
						}
						function addGgim(){
							var pNo = <%=p.getpNo()%>;
							var amount = $('#amount').val();
							location.href="<%=request.getContextPath()%>/addGgim.sp?pNo="+pNo;
						}
					</script>
					
					<div class="category-tab shop-details-tab"><!--category-tab-->
						<div class="col-sm-12" >
							<ul class="nav nav-tabs" >
								<li style="backgroundColor:orange"><a>Details</a></li>
							</ul>
						</div>
						<div class="tab-content">
							<div  id="details" >
								<div class="col-sm-3">
								<%=p.getContents() %>
								</div>
							</div>
						</div>
					</div>
					
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
					<script>
					console.log('<%= pList.get(0).getFileLevel()%> <%= pList.get(0).getChangeName()%>');
						
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
					
				</div>
			</div>
		</div>
	</section>
</body>
</html>