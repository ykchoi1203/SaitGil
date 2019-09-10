<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import='java.util.*, product.model.vo.*' %>
<%
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("pList");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
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
					<h4 class="page-head-line">상품</h4>
				</div>
			</div>
			<div class="row" align="center">
				<div class="dashboard-div-wrapper bk-clr-two"
					style="width: 100%; height: 400px;">
					<div style="width: 100%; height: 360px; overflow: auto">
						<table id='memberTable' width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<th style="text-align: center;">상품번호</th>
								<th style="text-align: center;">이름</th>
								<th style="text-align: center;">가격</th>
								<th style="text-align: center;">재고</th>
								<th style="text-align: center;">재고수정</th>
								<th style="text-align: center;">카테고리</th>
								<th style="text-align: center;">판매량</th>
								<th style="text-align: center;">수정</th>
								<th style="text-align: center;">삭제</th>
							</tr>
							<%
							if (list.isEmpty()) {
							%>
							<tr>
								<td colspan="9">조회된 리스트가 없습니다.</td>
							</tr>
							<%
							} else {
							%>
							<%
								for (Product p : list) {
							%>
							<tr>
								<td><%=p.getpNo()%></td>
								<td><%=p.getpName()%></td>
								<td><%=p.getPrice()%></td>
								<td><%=p.getAmount()%></td>
								<td><button class='addAmount'
										style="color: white; background: darkgray;">추가</button></td>
								<td><%=p.getCategory()%></td>
								<td><%=p.getPurchase()%></td>
								<td><button class='fixbtn'
										style="color: white; background: darkgray;">수정</button></td>
								<td><button class='deletebtn'
										style="color: white; background: darkgray;">삭제</button></td>
							</tr>
							<%
								}
							%>
							<%
							}
							%>
						</table>
					</div>
				</div>
				<div class='pagingArea' align="center">
					<!-- 맨처음으로 (<<) -->
					<button onclick="location.href='<%= request.getContextPath() %>/pList.ad?currentPage=1'">&lt;&lt;</button>
					<%if(currentPage == 1){%>
					<button disabled>&lt;</button>
					<%}else{%>
					<button onclick="location.href='<%= request.getContextPath() %>/pList.ad?currentPage=<%= currentPage -1%>'">&lt;</button>
					<%}%>
					
					<%for(int p=startPage; p<endPage+1; p++){ %>
						<%if(p == currentPage){ %>
						<button disabled><%= p %></button>
						<%}else{ %>
						<button onclick="location.href='<%= request.getContextPath()%>/pList.ad?currentPage=<%= p%>'"><%= p %></button>
						<%} %>			
					<%} %>
					
					<% if(currentPage == maxPage){ %>
					<button disabled>&gt;</button>
					<%} else{%>
					<button onclick="location.href='<%= request.getContextPath() %>/pList.ad?currentPage=<%= currentPage + 1 %>'">&gt;</button>
					<%} %>
					<button onclick="location.href='<%= request.getContextPath() %>/pList.ad?currentPage=<%= maxPage %>'">&gt;&gt;</button>
				</div>
			</div>
			<div align='right'>
				<button onclick='location.href="<%= contextPath%>/insertProductPage.ad"'>상품 추가</button>
			</div>
		</div>
	</div>
	<script>
		$(".fixbtn").click(function(){
			var pNo = $(this).parent().parent().children().eq(0).text();
			
			location.href="<%=contextPath%>/pEditForm.ad?pNo="+pNo;
		});
		
		$(".addAmount").click(function(){
			var pNo = $(this).parent().parent().children().eq(0).text();
			var amount = prompt("추가할 갯수를 입력하세요.");
			location.href="<%=contextPath%>/addAmount.ad?pNo="+pNo+"&amount="+amount;
		});
	
		$(".deletebtn").click(function(){
			var pNo = $(this).parent().parent().children().eq(0).text();
			
			if(confirm('정말로 삭제하십니까?')){
				location.href="<%=contextPath%>/productDelete.ad?pNo="+ pNo;
			}
		});
	</script>
</body>
</html>