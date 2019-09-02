<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <title>Free Responsive Admin Theme - ZONTAL</title>
    <!-- BOOTSTRAP CORE STYLE  -->
    <link href="resources/css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICONS  -->
    <link href="resources/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE  -->
    <link href="resources/css/style.css" rel="stylesheet" />
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-md-12" style="text-align:left;">
                    <a onclick='href="<%= contextPath %>/main.ad";' style="margin-top:20px; padding-top:20px;">
                    	<img src="resources/images/logo.png" height="50px"/>
                	</a>
                </div>
            </div>
        </div>
    </header>
    <!-- HEADER END-->
    <!--  <div class="navbar navbar-inverse set-radius-zero" style="height:52px;">
        <div class="container">
            <div class="navbar-header">
               
            </div>
        </div>
    </div>-->
    <!-- LOGO HEADER END-->
    <section class="menu-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                            <li><a href='<%= contextPath%>/mList.ad'>회원</a></li>
                            <li><a href="<%= contextPath%>/nList.ad">공지글</a></li>
                            <li><a href="<%= contextPath%>/pList.ad">상품</a></li>
                            <li><a href="views/admin/adminProductInsert.jsp">Forms</a></li>
                            <li><a href="login.html">Login Page</a></li>
                            <li><a href="blank.html">Blank Page</a></li>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </section>
   
    <!-- CORE JQUERY SCRIPTS -->
    <script src="resources/js/jquery-1.11.1.js"></script>
    <!-- BOOTSTRAP SCRIPTS  -->
    <script src="resources/js/bootstrap.js"></script>
</body>
</html>
