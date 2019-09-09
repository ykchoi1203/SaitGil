<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import='java.util.*, product.model.vo.*' %>
<%
	ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("oList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="adminMenubar.jsp"%>

	<div class="content-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h4 class="page-head-line">구매내역</h4>
				</div>
			</div>
			<div class="row" align="center">
				<div class="dashboard-div-wrapper bk-clr-two"
					style="width: 100%; height: 400px;">
					출고전
					<div style="width: 100%; height: 350px; overflow: auto">
						<table id='memberTable' width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<th style="text-align: center;">주문번호</th>
								<th style="text-align: center;">상품번호</th>
								<th style="text-align: center;">상품명</th>
								<th style="text-align: center;">수량</th>
								<th style="text-align: center;">수령인</th>
								<th style="text-align: center;">배송지</th>
								<th style="text-align: center;">전화번호</th>
								<th style="text-align: center;">배송요청사항</th>
								<th style="text-align: center;">주문날짜</th>
								<th style="text-align: center;">결제상태</th>
								<th style="text-align: center;">배송</th>
							</tr>
							<%
							if (list.isEmpty()) {
							%>
							<tr>
								<td colspan="11">조회된 리스트가 없습니다.</td>
							</tr>
							<%
							} else {
							%>
							<%
								for (Order o : list) {
								String status = o.getDeliveryStatus();
								String yes = "Y";
								if(o.getDeliveryStatus().equals("N")){
							%>
							<tr>
								<td><%= o.getbCode()%></td>
								<td><%=o.getpNo()%></td>
								<td><%=o.getpName()%></td>
								<td><%=o.getAmount()%></td>
								<td><%=o.getOrderName()%></td>
								<td><%=o.getShipping()%></td>
								<td><%=o.getPhone()%></td>
								<td><%=o.getRequest()%></td>
								<td><%=o.getBuyDate()%></td>
								<td><%=o.getStatus()%></td>
								<td><button class='startDel'
										style="color: white; background: darkgray;">배송시작</button></td>
							</tr>
								<%} %>
							<%
								}
							%>
							<%
							}
							%>
						</table>
					</div>
				</div>
			</div>
			<div class="row" align="center">
				<div class="dashboard-div-wrapper bk-clr-two"
					style="width: 100%; height: 400px;">
					출고 완료
					<div style="width: 100%; height: 350px; overflow: auto">
						<table id='memberTable' width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<th style="text-align: center;">주문번호</th>
								<th style="text-align: center;">상품번호</th>
								<th style="text-align: center;">상품명</th>
								<th style="text-align: center;">수량</th>
								<th style="text-align: center;">수령인</th>
								<th style="text-align: center;">배송지</th>
								<th style="text-align: center;">전화번호</th>
								<th style="text-align: center;">배송요청사항</th>
								<th style="text-align: center;">주문날짜</th>
								<th style="text-align: center;">결제상태</th>
								<th style="text-align: center;">배송</th>
							</tr>
							<%
							if (list.isEmpty()) {
							%>
							<tr>
								<td colspan="11">조회된 리스트가 없습니다.</td>
							</tr>
							<%
							} else {
							%>
							<%
								Collections.reverse(list);
								for (Order o : list) {
								String status = o.getDeliveryStatus();
								String yes = "Y";
								
								if(o.getDeliveryStatus().equals("Y")){
							%>
							<tr>
								<td><%= o.getbCode()%></td>
								<td><%=o.getpNo()%></td>
								<td><%=o.getpName()%></td>
								<td><%=o.getAmount()%></td>
								<td><%=o.getOrderName()%></td>
								<td><%=o.getShipping()%></td>
								<td><%=o.getPhone()%></td>
								<td><%=o.getRequest()%></td>
								<td><%=o.getBuyDate()%></td>
								<td><%=o.getStatus()%></td>
								<td>출고완료</td>
							</tr>
								<%} %>
							<%
								}
							%>
							<%
							}
							%>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".startDel").click(function(){
			var bCode = $(this).parent().parent().children().eq(0).text();
			
			if(confirm('배송을 시작했습니까?')){
				location.href="<%=contextPath%>/startDel.ad?bCode="+ bCode;
			}
		});
	</script>
</body>
</html>