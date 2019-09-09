<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member m = (Member)session.getAttribute("loginUser");  
	String msg = (String)session.getAttribute("msg");
	String[] gender = new String[2];
	if(m.getGender() != null) {
		switch(m.getGender()) {
			case "M" : gender[0] = "checked"; break;
			case "F" : gender[1] = "checked"; break;
		}
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <style>
		body {
			background-image: url("resources/images/mainImage.jpg");
			background-position: center;
			background-size: cover;
			background-repeat: no-repeat;
			opacity:0.96;
		}
		
		.submit {
			font-family: Noto Serif KR ;
		}
	</style>   
</head>
<body>
	<%@ include file ="../common/menubar.jsp" %>
<script>
var msg = "<%= msg %>";
$(function() {
	if(msg != "null") {
		alert(msg);
		//세션 메세지 출력 후 삭제
		<% session.removeAttribute("msg"); %>
	}
})

</script>
<div class="main">
    <div class="container">
                <section class="signup-form">
                    <form id="updateForm" action="<%= request.getContextPath() %>/update.me" method="post" onsubmit="return joinValidate();" class="register-form" >
                        <h1>마이페이지</h1>
                        <hr>
                        <br>
                        <div class="form-row">
                            <div style="width:270px; height:150px; padding:30px; align:center;">
                                <img src="<%= request.getContextPath() %>/resources/<%= loginUser.getProfilePic() %>" id="myPhoto" style="width:150px; height:150px; margin:auto;"> <br>
                              	<button onclick="deleteProfile();">프로필삭제</button>
                            </div>
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
                                <label for="userPwd">New Password : </label>
                                <input type="password" id="newPwd" maxlength="15" placeholder="변경을 원하시면 변경할 비밀번호를 입력하세요.">
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">Confirm New Password : </label>
                                <input type="password" id="newPwd2" maxlength="15">
                            </div>
                            <div class="submit" id="changePwd" style="height:20px"onclick="changePwd();">&nbsp&nbsp&nbsp&nbsp&nbsp 변경</div>
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
                                <label for="ID">Address : </label>
                                <input type="text" name="address" id="address" value="<%= loginUser.getAddress() %>" style="width:85%; display:inline">
                                <div class="submit" id="idCheck" onclick="goPopup();">&nbsp;&nbsp;주소검색 &nbsp;&nbsp;</div>
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
                            <input type="date" name="birth" id="birth" value="<%= loginUser.getBirth() %>">
                        </div>
                        <div class="form-submit">
                        	<input type="button" value="뒤로가기" class="submit" id="back" onclick="location.href='<%= request.getContextPath() %>/loginMainPage.me'" />
                            <input type="reset" value="회원 탈퇴" class="submit" id="disconnect" onclick="deleteMember();" />
                            <input type="button" value="커플 연동끊기" class="submit" id="cutCouple"/>
                            <input type="submit" value="저장" class="submit" name="submit" id="submit" />
                        </div>
                    </form>
                </section>
            </div>
        </div>
        
        
        <script>
        
        $(function() {
        	
        	$('#cutCouple').click(function() {
        		var answer = confirm('정말 커플 연동을 끊겠습니까?');
        		
        		if(answer) {
        			var cCode = '<%= loginUser.getcCode() %>';
        			location.href = '<%= request.getContextPath() %>/cutCouple.sf?cCode=' + cCode; 
        		} else {
        			alert('감사합니다. 사랑하는 연인과의 추억을 이어가주세요.')
        		}
        	});
        	
        	
        });
        
        

	    	function deleteMember() {
	    		if(confirm("정말로 탈퇴하시겠습니까?")) {
		    		location.href="<%= request.getContextPath() %>/delete.me?userId=<%= loginUser.getUserId() %>";
	
	    		}
	    	}
	    	
	    	function cutCouple() {
	    		alert('헬로');
	    	}
	    		
	    	function deleteProfile(){
	    		if(confirm("정말로 삭제하시겠습니까?")) {
	    			var isDelete = "images/profile_boy.png";
	    			if("<%= loginUser.getGender() %>" == 'F') {
	    				isDelete = "images/profile_girl.png";
	    			}
					$.ajax({
						url:"updateProfile.me",
						type:"post",
						data:{isDelete:isDelete},
						success:function(result) {
							if(result == "success") {
								alert("프로필이 삭제되었습니다.");
							} else {
								alert("프로필 삭제 실패");
							}
							
						},
						error:function() {
							console.log("서버 통신 실패");
						}
					})
	    		}
	    	}
	    	
	    	
	    	function changePwd() {
	    		var newPwd = $("#newPwd").val();
	    		var newPwd2 = $("#newPwd2").val();
	    		
	    		if(newPwd.trim() == "" || newPwd2.trim() == "") {
	    			alert("변경할 비밀번호를 입력하세요");
	    			
	    			return;
	    		}
	    		
	    		if(newPwd != newPwd2) {
	    			alert("변경할 비밀번호가 일치하지 않습니다")
	    			return;
	    		}

	    		
	    		$.ajax({
	    			url:"updatePwd.me",
	    			type:"post",
	    			data:{newPwd:newPwd},
	    			success:function(result) {
	    				if(result == "성공") { 
	 	    				alert("비밀번호가 변경되었습니다. 새로운 비밀번호로 다시 로그인 해주세요");
	 	    				location.href = '<%= request.getContextPath() %>/logout.me';

	    				} else {
	    					alert("비밀번호 변경 실패");
		    				$("#newPwd").val("");
		    				$("#newPwd2").val("");
	    				}
	    			},
	    			error:function() {
	    				console.log("통신실패");
	    			}
	    		});
	    			
	    		
	    	}
	    	
	    	
	    	
	    	function goPopup(){
	    		// 주소검색을 수행할 팝업 페이지를 호출합니다.
	    		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	    		var pop = window.open("<%= request.getContextPath() %>/views/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	    		
	    		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	    	}
	    	
	    	
	    	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	    			$('#address').attr("value", roadFullAddr);
	    	}

        </script>
</body>
</html>