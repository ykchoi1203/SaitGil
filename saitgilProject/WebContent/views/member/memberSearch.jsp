<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 	
	String userId = (String)session.getAttribute("userId"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
        <!-- Font Icon -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="../../resources/css/joinSearchForm.css">
    <link rel="stylesheet" href="@import url('https://fonts.googleapis.com/css?family=Dancing+Script|Great+Vibes&display=swap');">

    <style>
        .register-form{
            /* border: 1px solid red; */
            width:30%;
            display: block;
            float: left;
            margin: 12px;
        }
        
		body {
			background-image: url("../../resources/images/mainImage.jpg");
			background-position: center;
			background-size: cover;
			background-repeat: no-repeat;
		}
		
		
    </style>
</head>
<body>

    <header>
        <a href="templated-urban\index.html">Sait Gil</a>
    </header>
    <div class="main">
        <div class="container"> 
                <div class="signup-form">
                    <div class="container-search">
                    
                    <form method="POST" class="register-form" id="register-form"  action="<%= request.getContextPath() %>/searchId.me" style="height:435px;">
                        <h1>Search ID</h1>
                        <hr>
                        <br>
                        <div class="form-group">
                            <div class="form-group">
                                <label for="userName">Name : </label>
                                <input type="text" name="userName" required>
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone Number : </label> 
                                <input type="number" id="phone" name="phone" placeholder="(-없이)01012345678" required>
                            </div>
                        </div>
                        <div class="form-submit">
                            <input type="submit" value="Reset" class="submit" name="reset" id="reset" required>
                            <input type="submit" value="Search" class="submit" name="submit" id="submit" required>
                        </div>
                    </form>
                    <script>
                  		$(document).ready(function(){
    						var userId = "<%= userId %>";
    					
	    					if(userId != "null"){
	    						window.open("<%= request.getContextPath() %>/views/member/searchResult.jsp", "아이디 찾기", 'width=500, height=300, scrollbars=no, menubar=no, resizable=0');
	    					} if(userId == "null") {
	    						alert("아이디가 존재하지 않습니다.")
	    					}
	    					
    					});
                    </script>


                    <form method="POST" class="register-form" id="register-form"  action="<%= request.getContextPath() %>/searchPwd.me" style="height:435px; ">
                        <h1>Search Password</h1>
                        <hr>
                        <br>
                        <div class="form-group">
                                <div class="form-group">
                                    <label for="userName">Name : </label>
                                    <input type="text" name="userName" required>
                                </div>
                                <div class="form-group">
                                    <label for="userId">ID : </label>
                                    <input type="text" name="userId" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email : </label> 
                                    <input type="email" name="email" required>
    
                                </div>
                            </div>
                            <div class="form-submit">
                                <input type="submit" value="Reset" class="submit" name="reset" id="reset" />
                                <input type="submit" value="Search" class="submit" name="submit" id="submit" />
                            </div>


                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>