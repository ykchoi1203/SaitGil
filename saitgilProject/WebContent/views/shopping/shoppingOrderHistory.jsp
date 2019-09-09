<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.*, shopping.model.vo.*, product.model.vo.PageInfo' %>
<%
	ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("list");
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
			<h2 style="color:gray;">구매내역</h2>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td>구매갯수</td>
							<td>구매날짜</td>
							<td>출고 상태<td>
						</tr>
					</thead>
					<tbody>
					<%if(!list.isEmpty()) {%>
						<%for(int i=0; i<list.size(); i++){ %>
						<tr>
							<td class="productinfo" width="100" height="100">
								<img src="<%= request.getContextPath()%>/resources/product_uploadFiles/<%=list.get(i).getChangeName() %>" width="100" height="100">
							</td>
							<td class="productinfo">
								<h4><%=list.get(i).getpName() %></h4>
								<p><%=list.get(i).getpNo() %></p>
							</td>
							<td >
								<p><%=list.get(i).getPrice() %>원</p>
							</td>
							<td >
								<p><%=list.get(i).getBuyAmount() %></p>
							</td>
							<td >
								<p><%=list.get(i).getBuyDate() %></p>
							</td>
							<td >
								<p><%=list.get(i).getDeliveryStatus() %></p>
							</td>
							
						</tr>
						<%} %>
					<%} else{%>
						<tr><td>조회된 주문내역이 없습니다.</td></tr>
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
			<div class='pagingArea' align="center">
						<!-- 맨처음으로 (<<) -->
						<button onclick="location.href='<%= request.getContextPath() %>/ocList.sp.sp?currentPage=1'">&lt;&lt;</button>
						<%if(currentPage == 1){%>
						<button disabled>&lt;</button>
						<%}else{%>
						<button onclick="location.href='<%= request.getContextPath() %>/ocList.sp?currentPage=<%= currentPage -1%>'">&lt;</button>
						<%}%>
						
						<%for(int p=startPage; p<endPage+1; p++){ %>
							<%if(p == currentPage){ %>
							<button disabled><%= p %></button>
							<%}else{ %>
							<button onclick="location.href='<%= request.getContextPath()%>/ocList.sp?currentPage=<%= p%>'"><%= p %></button>
							<%} %>			
						<%} %>
						
						<% if(currentPage == maxPage){ %>
						<button disabled>&gt;</button>
						<%} else{%>
						<button onclick="location.href='<%= request.getContextPath() %>/ocList.sp?currentPage=<%= currentPage + 1 %>'">&gt;</button>
						<%} %>
						<button onclick="location.href='<%= request.getContextPath() %>/ocList.sp?currentPage=<%= maxPage %>'">&gt;&gt;</button>
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