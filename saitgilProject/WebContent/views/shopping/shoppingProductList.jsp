<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.*, product.model.vo.*' %>
<%
	ArrayList<Product> pList = (ArrayList<Product>)request.getAttribute("list");
	ArrayList<ProductAttachment> paList = (ArrayList<ProductAttachment>)request.getAttribute("paList");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">상품</h2>
						<%for(int i=0; i<pList.size(); i++){ %>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
										<div class="productinfo text-center">
											<input type='hidden' value="<%= pList.get(i).getpNo() %>">
											<input type='hidden' value="<%= pList.get(i).getAmount() %>">
											  <%for(int j=0; j<paList.size();j++){ %>
											 	<%if(pList.get(i).getpNo() == paList.get(j).getpNo()){ %>
												<img width='268' height='249' src="<%= request.getContextPath() %>/resources/product_uploadFiles/<%= paList.get(j).getChangeName() %>">
												<%} %>
											<%} %>
												<h2><%=pList.get(i).getPrice() %>원</h2>
												<p><%= pList.get(i).getpName() %></p>
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
					<div class='pagingArea' align="center">
						<!-- 맨처음으로 (<<) -->
						<button onclick="location.href='<%= request.getContextPath() %>/pList.sp?currentPage=1'">&lt;&lt;</button>
						<%if(currentPage == 1){%>
						<button disabled>&lt;</button>
						<%}else{%>
						<button onclick="location.href='<%= request.getContextPath() %>/pList.sp?currentPage=<%= currentPage -1%>'">&lt;</button>
						<%}%>
						
						<%for(int p=startPage; p<endPage+1; p++){ %>
							<%if(p == currentPage){ %>
							<button disabled><%= p %></button>
							<%}else{ %>
							<button onclick="location.href='<%= request.getContextPath()%>/pList.sp?currentPage=<%= p%>'"><%= p %></button>
							<%} %>			
						<%} %>
						
						<% if(currentPage == maxPage){ %>
						<button disabled>&gt;</button>
						<%} else{%>
						<button onclick="location.href='<%= request.getContextPath() %>/pList.sp?currentPage=<%= currentPage + 1 %>'">&gt;</button>
						<%} %>
						<button onclick="location.href='<%= request.getContextPath() %>/pList.sp?currentPage=<%= maxPage %>'">&gt;&gt;</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>