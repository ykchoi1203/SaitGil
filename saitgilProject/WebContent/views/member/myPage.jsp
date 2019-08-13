<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../common/menubar.jsp" %>
<%
	Member m = (Member)session.getAttribute("loginUser");  
	String[] gender = new String[2];
	switch(m.getGender()) {
		case "M" : gender[0] = "checked"; break;
		case "F" : gender[1] = "checked"; break;
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/myPage.css">
    <style>
		body {
			background-image: url("resources/images/mainImage.jpg");
			background-position: center;
			background-size: cover;
			background-repeat: no-repeat;
			opacity:0.96;
		}
	</style>   
</head>
<body>

<div class="main">
    <div class="container">
                <section class="signup-form">
                    <form id="joinForm" action="<%= request.getContextPath() %>/update.me" method="post" onsubmit="return joinValidate();" class="register-form" >
                        <h1>마이페이지</h1>
                        <hr>
                        <br>
                        <div class="form-row">
                            <span style="text-align: center;">
                                <img src="resources/images/profile_boy.png" id="myPhoto" style="width:150px; height:150px;"> 
                                <input type="file" style="border:none;">
                            </span>
                            <div> 
                            	<table id="idTable">
                            		<tr> 
                            			<th colspan="2" id="welcome"><%= loginUser.getUserName() %>님 사잇길에 오신 것을 환영합니다</th>
                            		</tr>
                            		<tr>
                            			<th width="100px"><label>Name : </label></th>
                            			<th width="300px"><label class="readonly"><%= loginUser.getUserName() %></label></th>
                            		</tr>
                            		<tr>
                            			<th><label>ID : </label></th>
                            			<th><label class="readonly"><%= loginUser.getUserId() %></label></th>
                            		</tr>
                            	</table>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="userPwd">Password : </label>
                                <input type="password" name=userPwd maxlength="15" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">Confirm Password : </label>
                                <input type="password" name="userPwd2" maxlength="15" required >
                            </div>
                            <input type="submit" class="submit" id="changePwd" value="Change">
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label for="phone">Phone Number : </label>
                                <input type="number" name="phone" value="<%= loginUser.getPhone() %>">
                            </div>
                            <div class="form-group">
                                <label for="email">Email ID :</label>
                                <input type="email" name="email" id="email" value="<%= loginUser.getEmail() %>">
                            </div>
                            <div class="form-group">
                                    <label for="address">Address :</label>
                                    <input type="text" name="address" value="<%= loginUser.getAddress() %>">
                                </div>
                        </div>
                        
                        <div class="form-radio">
                            <label for="gender" class="radio-label">Gender :</label>
                            <div class="form-radio-item">
                                <input type="radio" name="gender" id="M" value="M" <%= gender[0] %>>
                                <label for="M">Male</label>
                                <span class="check"></span>
                            </div>
                            <div class="form-radio-item">
                                <input type="radio" name="gender" id="F" value="F" <%= gender[1] %>>
                                <label for="F">Female</label>
                                <span class="check"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="birth_date">Birth Of Date :</label>
                            <input type="date" name="birth" id="birth" value=<%= loginUser.getBirth() %>>
                        </div>
                        <div class="form-submit">
                        	<input type="button" value="Back" class="submit" id="back" onclick="goBack();" />
                            <input type="reset" value="Disconnect" class="submit" id="disconnect" onclick="disconnect();" />
                            <input type="submit" value="Save" class="submit" name="submit" id="submit" />
                        </div>
                    </form>
                </section>
            </div>
        </div>
        
        
        <script>
    	function goBack() {
    		history.back();
    	}
        
        </script>
</body>
</html>