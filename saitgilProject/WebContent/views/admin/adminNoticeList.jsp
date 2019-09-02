<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import='java.util.*, notice.model.vo.*' %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("nList");
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
								<th style="text-align:center;">글번호</th>
								<th style="text-align:center;">제목</th>
								<th style="text-align:center;">내용</th>
								<th style="text-align:center;">작성자</th>
								<th style="text-align:center;">작성일</th>
								<th style="text-align:center;">조회수</th>
								<th style="text-align:center;">수정</th>
								<th style="text-align:center;">삭제</th>
							</tr>
							<%if(list.isEmpty()){ %>
							<tr>
								<td colspan="9">조회된 리스트가 없습니다.</td>
							</tr>
							<%}else{ %>
								<%for(Notice n : list){ %>
							<tr>
								<td><%= n.getNoticeNo() %></td>
								<td><%= n.getNoticeTitle() %></td>
								<td><%= n.getNoticeContent() %></td>
								<td><%= n.getWriter() %></td>
								<td><%= n.getNoticeDate() %></td>
								<td><%= n.getNoticeCount() %></td>
								<td><button id='fixbtn' style="color:white; background:darkgray;">수정</button></td>
								<td><button id='deletebtn' style="color:white; background:darkgray;">삭제</button></td>
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
		$("#fixbtn").click(function(){
			var noticeNo = $(this).parent().parent().children().eq(0).text();
			location.href="<%= contextPath%>/noticeFix.ad?noticeNo="+noticeNo;
		});
	
	
		$("#deletebtn").click(function(){
			var noticeNo = $(this).parent().parent().children().eq(0).text();
			
			if(confirm('정말로 삭제하십니까?')){
				location.href="<%= contextPath %>/noticeDelete.ad?noticeNo="+noticeNo;
			}
		});
    	
    </script>
</body>
</html>