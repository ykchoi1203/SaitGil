<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.*, shopping.model.vo.*' %>
<%
	ArrayList<Cart> glist = (ArrayList<Cart>)request.getAttribute("sblist");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	a:hover{
		cursor:pointer;
	}
</style>
</head>
<body>
	<%@ include file="shoppingMenubar.jsp" %>
	<br>
	<br>
	<br>
	<section id="cart_items">
		<div class="container">
			<h2 style="color:gray;">장바구니</h2>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td>구매갯수</td>
							<td>삭제</td>
						</tr>
					</thead>
					<tbody>
					<%if(!glist.isEmpty()) {%>
						<%for(int i=0; i<glist.size(); i++){ %>
						<tr>
							<td class="productinfo" width="100" height="100">
								<img src="<%= request.getContextPath()%>/resources/product_uploadFiles/<%=glist.get(i).getChangeName() %>" width="100" height="100">
							</td>
							<td class="productinfo">
								<h4><%=glist.get(i).getpName() %></h4>
								<p><%=glist.get(i).getpNo() %></p>
							</td>
							<td class="productinfo">
								<p><%=glist.get(i).getPrice() %>원</p>
							</td>
							<td >
								<input type="number" name="quantity" value="<%=glist.get(i).getAmount() %>" min="1" size="2">
							</td>
							<td>
								<a class="deleteCart"><i class="fa"></i>삭제</a>
							</td>
						</tr>
						<%} %>
					<%} else{%>
						<tr><td>조회된 장바구니 상품이 없습니다.</td></tr>
					<%} %>
					</tbody>
				</table>
				<script>
					$(function(){
						$(".productinfo").click(function(){
							var pNo = $(this).parent().children('td').eq(1).children('p').text();
							location.href="<%=request.getContextPath()%>/detail.sp?pNo="+pNo;
						});
						
						$(".cart1").click(function(){
							var pNo = $(this).parent().parent().children('td').eq(1).children('p').text();
							location.href="<%=request.getContextPath()%>/addCart.sp?pNo="+pNo;
						});
						
						$(".deleteCart").click(function(){
							var pNo = $(this).parent().parent().children('td').eq(1).children('p').text();
							
							location.href="<%=request.getContextPath()%>/deleteCart.sp?pNo="+pNo;
						});
						
					});
					
					
				</script>
				
			</div>
			<div class="pull-right">
				<button onclick="order();">주문하기</button>
			</div>
			<script>
				function order(){
					location.href="<%=request.getContextPath()%>/goOrder.sp";
				}
			</script>
			<br>
			<br>
			<br>
			<br>
		</div>
	</section> <!--/#cart_items-->
</body>
</html>