<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*, shareFile.model.vo.*"
		 import="java.text.DateFormat"
		 import="java.text.ParseException"
		 import="java.text.SimpleDateFormat"
		 import="java.util.Calendar"
		 import="java.util.Date"
 %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>사잇길 쇼핑몰</title>
    <link href="resources/css/eShopping/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/eShopping/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/eShopping/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/eShopping/price-range.css" rel="stylesheet">
    <link href="resources/css/eShopping/animate.css" rel="stylesheet">
	<link href="resources/css/eShopping/main.css" rel="stylesheet">
	<link href="resources/css/eShopping/responsive.css" rel="stylesheet">
	<script src="resources/js/eShopping/jquery.js"></script>
	<script src="resources/js/eShopping/bootstrap.min.js"></script>
	<script src="resources/js/eShopping/jquery.scrollUp.min.js"></script>
	<script src="resources/js/eShopping/price-range.js"></script>
    <script src="resources/js/eShopping/jquery.prettyPhoto.js"></script>
    <script src="resources/js/eShopping/main.js"></script>
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->    
</head><!--/head-->

<body>
	<header id="header"><!--header-->
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-md-4 clearfix">
						<div class="logo pull-left">
							<a href="goMain.sp" style="color:gray;"><img src="resources/images/logo.png" width="40" height="40"> 사잇길 스토어</a>
						</div>
					</div>
					<div class="col-md-8 clearfix">
						<div class="shop-menu clearfix pull-right">
							<ul class="nav navbar-nav">
								<li><a href="loginMainPage.me"><img src='resources/images/shop/mainPage.png'>&nbsp;&nbsp;&nbsp;홈으로</a></li>
								<li><a href="goGgim.sp"><img src='resources/images/shop/star.png'>&nbsp;&nbsp;&nbsp;찜목록</a></li>
								<li><a href='goCart.sp'><img src='resources/images/shop/order.png'>&nbsp;&nbsp;&nbsp;장바구니</a></li>
								<li><a href='ocList.sp'><img src='resources/images/shop/cart.png'>&nbsp;&nbsp;&nbsp;주문내역</a></li>
								<li><a href='logout.me'><img src='resources/images/shop/logout.png'>&nbsp;&nbsp;&nbsp;Logout</a></li>
							</ul>
						</div>
						<script>
							function goCart(){
								location.href='goCart.sp';
							}
							
							function goLogout(){
								location.href='logout.me';
							}
						</script>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="goMain.sp">Home</a></li>
								<li><a href="pList.sp">상품</a></li> 
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header><!--/header-->
</body>
</html>