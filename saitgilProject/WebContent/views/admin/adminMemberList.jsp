<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import='java.util.*, member.model.vo.*' %>
<%
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("mList");
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
					<h4 class="page-head-line">회원</h4>
				</div>
			</div>
			<div class="row" align="center">
				<div class="dashboard-div-wrapper bk-clr-two"
					style="width: 100%; height: 400px;">
					<div style="width:100%; height:350px; overflow:auto">
						<table id='memberTable' width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th style="text-align:center;">가입일</th>
								<th style="text-align:center;">아이디</th>
								<th style="text-align:center;">이름</th>
								<th style="text-align:center;">핸드폰번호</th>
								<th style="text-align:center;">생일</th>
								<th style="text-align:center;">주소</th>
								<th style="text-align:center;">이메일</th>
								<th style="text-align:center;">신고횟수</th>
								<th style="text-align:center;">강퇴</th>
							</tr>
							<%if(list.isEmpty()){ %>
							<tr>
								<td colspan="9">조회된 리스트가 없습니다.</td>
							</tr>
							<%}else{ %>
								<%for(Member m : list){ %>
							<tr>
								<td><%= m.getJoinDate() %></td>
								<td><%= m.getUserId() %></td>
								<td><%= m.getUserName() %></td>
								<td><%= m.getPhone() %></td>
								<td><%= m.getBirth() %></td>
								<td><%= m.getAddress() %></td>
								<td><%= m.getEmail() %></td>
								<td><%= m.getReports() %></td>
								<td><button style="color:white; background:darkgray;">강퇴</button></td>
							</tr>				
								<%} %>
							<%} %>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#memberTable td button").click(function(){
			var userId = $(this).parent().parent().children().eq(1).text();
			
			if(confirm('정말로 탈퇴시키십니까?')){
				location.href="<%= contextPath %>/memberDelete.ad?userId="+userId;
			}
		});
    	
    </script>
</body>
</html>