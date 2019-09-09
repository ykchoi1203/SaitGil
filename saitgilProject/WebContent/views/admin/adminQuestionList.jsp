<%@page import="inquiry.model.vo.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import='java.util.*, inquiry.model.vo.*, product.model.vo.*' %>
<%
	ArrayList<Inquiry> list = (ArrayList<Inquiry>)request.getAttribute("iList");
	String msg = null;
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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Insert title here</title>

<link href="resources/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME ICONS  -->
<link href="resources/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="resources/css/style.css" rel="stylesheet" />
<script src="resources/js/jquery-1.11.1.js"></script>
<!-- BOOTSTRAP SCRIPTS  -->
<script src="resources/js/bootstrap.js"></script>
</head>
<body>
	<%@ include file="adminMenubar.jsp"%>

	<div class="content-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h4 class="page-head-line">문의글</h4>
				</div>
			</div>
			<div class="row" align="center">
				<div class="dashboard-div-wrapper bk-clr-two"
					style="width: 100%; height: 400px;">
					<div style="width:100%; height:360px; overflow:auto">
						<table id='memberTable' width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th style="text-align:center;">글번호</th>
								
								<th style="text-align:center;">제목</th>
								<th style="text-align:center;">작성자</th>
								<th style="text-align:center;">작성일</th>
								<th style="text-align:center;">답변작성 및 수정</th>
							</tr>
							<%if(list.isEmpty()){ %>
							<tr>
								<td colspan="6">조회된 리스트가 없습니다.</td>
							</tr>
							<%}else{ %>
								<%for(Inquiry n : list){ %>
							<tr>
								<td><%= n.getInquiryNo() %></td>
								<td><%= n.getWriter() %></td>
								<td><%= n.getInquiryTitle() %></td>
								<td><%= n.getDate() %></td>
								<%if(n.getrStatus().equals("N")){ %>
								<td><button class='answerbtn' style="color:white; background:darkgray;">답변하기</button></td>
								<%} else{%>
								<td><button class='answerEditbtn' style="color:white; background:darkgray;">답변수정</button></td>
								<%} %>
							</tr>				
								<%} %>
							<%} %>
						</table>
					</div>
				</div>
				<div class='pagingArea' align="center">
					<!-- 맨처음으로 (<<) -->
					<button onclick="location.href='<%= request.getContextPath() %>/qList.ad?currentPage=1'">&lt;&lt;</button>
					<%if(currentPage == 1){%>
					<button disabled>&lt;</button>
					<%}else{%>
					<button onclick="location.href='<%= request.getContextPath() %>/qList.ad?currentPage=<%= currentPage -1%>'">&lt;</button>
					<%}%>
					
					<%for(int p=startPage; p<endPage+1; p++){ %>
						<%if(p == currentPage){ %>
						<button disabled><%= p %></button>
						<%}else{ %>
						<button onclick="location.href='<%= request.getContextPath()%>/qList.ad?currentPage=<%= p%>'"><%= p %></button>
						<%} %>			
					<%} %>
					
					<% if(currentPage == maxPage){ %>
					<button disabled>&gt;</button>
					<%} else{%>
					<button onclick="location.href='<%= request.getContextPath() %>/qList.ad?currentPage=<%= currentPage + 1 %>'">&gt;</button>
					<%} %>
					<button onclick="location.href='<%= request.getContextPath() %>/qList.ad?currentPage=<%= maxPage %>'">&gt;&gt;</button>
				</div>
			</div>
			
		</div>
	</div>
	<script>
		$(".answerbtn").click(function(){
			var qNo = $(this).parent().parent().children().eq(0).text();
			location.href="<%= contextPath%>/answer.ad?qNo="+qNo;
		});
	
	
		$(".answerEditbtn").click(function(){
			var qNo = $(this).parent().parent().children().eq(0).text();
			
			location.href="<%= contextPath%>/answer.ad?qNo="+qNo;
		});
    	
    </script>
</body>
</html>