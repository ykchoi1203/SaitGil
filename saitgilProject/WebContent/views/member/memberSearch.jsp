<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
        <!-- Font Icon -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
        <a href="<%= request.getContextPath() %>">Sait Gil</a>
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
                                <input type="text" id="userName1" required>
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone Number : </label> 
                                <input type="number" id="phone" name="phone" placeholder="(-없이)01012345678" required>
                            </div>
                        </div>
                        <div class="form-submit">
                            <input type="reset" value="Reset" class="submit" name="reset" id="reset" >
                            <input type="button" value="Search" class="submit" name="submit" id="submit" onclick="searchId();">
                        </div>
                    </form>
                    <script>
                    
                    	function searchId() {
                    		var userName = $('#userName1').val();
                    		var phone = $('#phone').val(); 

        	        	    $.ajax({
        	        	        url : '<%= request.getContextPath() %>/searchId.me',
        	        	        type : 'POST',
        	        	        data : {userName:userName, phone:phone},
        						success:function(result) {
        							console.log("result: " + result);
        							if(result == "fail") {
        								alert("일치하는 회원 정보가 없습니다.");
        							} else {
        								window.open("<%= request.getContextPath() %>/views/member/searchResult.jsp", "아이디 찾기", 'width=500, height=300, scrollbars=no, menubar=no, resizable=0');
        								$('#userName').val("");
        								$('#phone').val("");
        							}						
        						},
        						error:function() {
        							console.log("서버통신 실패");
        						}
        					})
                    		
                    	}

                    </script>


                    <form method="POST" class="register-form" id="register-form"  action="<%= request.getContextPath() %>/searchPwd.me" style="height:435px; ">
                        <h1>Change Password</h1>
                        <hr>
                        <br>
                        <div class="form-group">
                                <div class="form-group">
                                    <label for="userName">Name : </label>
                                    <input type="text" id="userName2" required>
                                </div>
                                <div class="form-group">
                                    <label for="userId">ID : </label>
                                    <input type="text" id="userId" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email : </label> 
                                    <input type="email" id="email" required>
    
                                </div>
                            </div>
                            <div class="form-submit">
                                <input type="reset" value="Reset" class="submit" name="reset" id="reset" />
                                <input type="button" value="Change" class="submit" name="submit" id="submit" onclick="searchPwd();">
                            </div>


                    </form>
                    
                    <script>
                    
                    	function searchPwd() {
                    		var userName = $('#userName2').val();
                    		var userId = $('#userId').val(); 
                    		var email = $('#email').val();
                    		
                    		if(userName=="" || userId=="" || email=="") {
                    			alert("회원 정보를 입력해주세요.") ; 
                    		} else {

	        	        	    $.ajax({
	        	        	        url : '<%= request.getContextPath() %>/searchPwd.me',
	        	        	        type : 'POST',
	        	        	        data : {userName:userName, userId:userId, email:email},
	        						success:function(result) {
	        							console.log("result: " + result);
	        							if(result == "true") {
	        								window.open("<%= request.getContextPath() %>/views/member/changePwdWindow.jsp", "비밀번호 변경", 'width=500, height=300, scrollbars=no, menubar=no, resizable=0');
	        								$('#userName2').val("");
	        								$('#userId').val("");
	        								$('#email').val("");
	        							} else {
	        								alert("일치하는 회원 정보가 없습니다.");
	        							}						
	        						},
	        						error:function() {
	        							console.log("서버통신 실패");
	        						}
	        					})
	                    		
	                    	}
                    	}

                    </script>
                </div>
            </div>
        </div>
    </div>
</body>
</html>