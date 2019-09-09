<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%	Member loginUser = (Member)session.getAttribute("loginUser");   %>
<html>
<head>
    <meta charset="UTF-8">
    <title>커플연동</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <style>
    
    	body {
			background-image:url("<%= request.getContextPath() %>/resources/images/mainImage.jpg");
			background-position: center;
			background-size: cover;
			background-repeat: no-repeat;
		}
		
        #outline {
            width: 60%;
            height: 100%;
            overflow: visible;
            border: none;
            margin: auto;
            margin-top:100px;
            background:white;
            padding:60px;
            opacity: 0.96;

        }    

        h1 {
			font-family:Noto Serif KR;
			color: #e23a6e;
		} 

        .way {
            padding: 20px;
            font-family:Noto Serif KR;
            border-radius: 8px;
            background: #fde8ee ;
            height: 210px;
        }
        

        .button {
            display: inline-block;
            width:80px;
            height: 15px;
            background: #fa96b5;
            color: #fff;
            text-align: center;
            font-size: 12px;
            font-family: Noto Serif KR;
            font-weight: 100;
            padding:10px;
            cursor:pointer;
            border-radius:4px;
            margin-right:0px;
            
        }
        
        .button:hover {
        	background: #e23a6e ;
        }
        
        
        #mainBtn {
        	margin-right:0px;
            width:100px;
            height: 25px;
            background: #fa96b5;
            color: white;
            text-align: center;
            font-size: 16px;
            font-family: Noto Serif KR;
            font-weight: bold;
            padding:10px;
            cursor:pointer;
            border-radius:4px;
            display:inline-block;
        }
        
        #mainBtn:hover {
        	background: #e23a6e ;        
        }

        input {
            height: 25px;
            font-size: 13pt;
		    font-weight: 300;
            line-height: 1.65;
			margin:5px;

        }
        
    	
    </style>
</head>
<body>

    <div id="outline">
        <h1>현재 연동된 커플이 없습니다. <br>
            함께 사잇길을 걸을 연인을 추가해주세요. </h1>
        <hr>
        <br>
        <div class="way">
            <p><b>방법 1. </b><br>
            아직 초대코드를 못받으셨나요? <br> 
            연인에게 사랑이 담긴 초대코드를 보내보세요. <br>
            내 연인이 초대코드를 입력한다면 둘만의 사잇길이 펼쳐집니다!</p>
	  연인의 이름 : <input type="text" id="partnerName"> <br>
            연인의 전화번호 : <input type="text" id="partnerPhone" placeholder="(-없이)01012345678"> 
            <div class="button" onclick="sendCode();">초대코드 발송</div> 
            <br>
        </div>
        <br><br>
        <div class="way">
        <div id="content2">
            <p><b>방법 2.</b> <br>
		            연인이 보낸 초대코드를 받으셨나요? <br>
		            받으신 초대코드를 입력하세요. 지금부터 둘만의 사잇길이 펼쳐집니다!</p>
			 연인의 이름 : <input type="text" id="partnerName2"> <br>
		            초대코드 : <input type="text" id="inputCode"> 
		            <div class="button" onclick="coupleLink();">연동하기</div>
        </div>
        </div>
    	<br><br>
    		<div id="mainBtn" onclick="location.href='<%= request.getContextPath() %>/myPageWithout.me'">마이페이지</div>
	  	  <div id="mainBtn" onclick="goMain()">로그아웃</div>
    </div>
    
    <script>
    	function goMain() {
    		location.href="<%= request.getContextPath() %>/logout.me"; 	
    	}
    	
    	function sendCode() {
    		var partnerName = $("#partnerName").val().trim(); 
    		var partnerPhone = $('#partnerPhone').val().trim();
    		var fromId = "<%= loginUser.getUserId() %>";
    		var fromName = "<%= loginUser.getUserName() %>"
    		
			$.ajax({
				url:"<%=request.getContextPath() %>/authNo.co",
				type:"post",
				data:{partnerName:partnerName, partnerPhone:partnerPhone, fromId:fromId, fromName:fromName},
				success:function(result) {			
					console.log("result : " + result);
					if(result == "notAvaliable") {
						alert("본인 아이디로 연동이 안됩니다. 상대방의 정보를 입력해주세요!"); 
						$("#partnerName").val("");
						$("#partnerPhone").val("");
						$("#partnerName").focus();
					} else if(result == "검색 실패") {
						alert("존재하는 회원이 없습니다.");
						$("#partnerName").val("");
						$("#partnerPhone").val("");
						$("#partnerName").focus();
					} else if (result == "success") {
						alert("인증번호가 전송되었습니다!"); 
						$("#partnerName").val("");
						$("#partnerPhone").val("");
					} else if(result == "exist") {
						alert("이미 전송된 인증번호가 있습니다.");
						$("#partnerName").val("");
						$("#partnerPhone").val("");
						$("#partnerName").focus();
					} else {
						alert("이미 커플연동이 되어있는 회원입니다.");
						$("#partnerName").val("");
						$("#partnerPhone").val("");
						$("#partnerName").focus();
					}
				},
				error:function() {
					console.log("서버 통신 실패");
				}
			})
    	}
    	
    	
    	
    	
    	function coupleLink() {
    		var inputCode = $('#inputCode').val().trim(); 
    		var partnerName = $('#partnerName2').val().trim();
    		var userId = "<%= loginUser.getUserId() %>";
    		var userName = "<%= loginUser.getUserName() %>";
    		
			$.ajax({
				url:"<%= request.getContextPath() %>/linkCouple.co",
				type:"post",
				data:{partnerName:partnerName,  inputCode:inputCode, userId:userId, userName:userName},
				success:function(result) {			
					console.log("result : " + result);
					
					if(result == "notMatch") {
						alert("존재하는 인증번호가 없습니다.");
						$('#partnerName2').val("");
						$('#inputCode').val("");
						$('#partnerName2').focus();
						return;
					} else if(result=="fail") {
						alert("커플코드 생성 실패");
						$('#partnerName2').val("");
						$('#inputCode').val("");
						return;
					} else {
						alert("커플 연동이 되었습니다. 사잇길에서 추억 쌓기를 시작하세요!"); 
						location.href = "<%= request.getContextPath() %>/goCoupleForm.me?cCode=" + result;
						return;
					}
				},
				error:function() {
					console.log("서버 통신 실패");
				}
			})
    	}
    	
    	
    </script>
    <br><br><br><br><br> 
</body>
</html>