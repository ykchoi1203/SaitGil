<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import='java.util.*, product.model.vo.*' %>
<%
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("pList");
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
					<h4 class="page-head-line">회원</h4>
				</div>
			</div>
			<div class="row" align="center">
				<div class="dashboard-div-wrapper bk-clr-two"
					style="width: 100%; height: 400px;">
					<div style="width: 100%; height: 350px; overflow: auto">
						<table id='memberTable' width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<th style="text-align: center;">상품번호</th>
								<th style="text-align: center;">이름</th>
								<th style="text-align: center;">가격</th>
								<th style="text-align: center;">재고</th>
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
	
	
		$(".deletebtn").click(function(){
			var pNo = $(this).parent().parent().children().eq(0).text();
			
			if(confirm('정말로 삭제하십니까?')){
				location.href="<%=contextPath%>/productDelete.ad?pNo="+ pNo;
			}
		});
	</script>
</body>
</html>