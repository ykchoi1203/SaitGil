<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입</title>
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="../../resources/css/joinSearchForm.css">
    <link rel="stylesheet" href="@import url('https://fonts.googleapis.com/css?family=Dancing+Script|Great+Vibes&display=swap');">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<style>
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
                    <form id="joinForm" action="<%= request.getContextPath() %>/insert.me" method="post" onsubmit="return joinValidate();" class="register-form" >
                        <br><br>
                        <h1>사잇길 회원가입</h1>
                        <hr>
                        <br>
                        <div class="form-group">
                            <div class="form-group">
                                <label for="ID">* ID :</label>
                                <input type="text" name="userId" id="userId" style="width:80%; display:inline" required>
                                <div class="submit" id="idCheck"> &nbsp;&nbsp;IDCHECK &nbsp;&nbsp;</div>
                            </div>
                            <div class="form-group">
                                <label for="name">* Name :</label>
                                <input type="text" maxlength="13" name="userName" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="password">* Password : </label>
                                <input type="password"  maxlength="15" name="userPwd" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">* Confirm Password : </label>
                                <input type="password"  maxlength="15" name="userPwd2" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label for="phone">* Phone Number : </label>
                                <input type="tel" maxlength="11" name="phone" placeholder="(-없이)01012345678" required>
                            </div>
                            <div class="form-group">
                                <label for="email">* Email ID :</label>
                                <input type="email" name="email" required>
                            </div>
                           	<div class="form-group">
                                <label for="ID">Address : </label>
                                <input type="text" name="address" id="address" placeholder="직접입력" style="width:85%; display:inline">
                                <div class="submit" id="idCheck" onclick="goPopup();">&nbsp;&nbsp;주소검색 &nbsp;&nbsp;</div>
                            </div> 
                        <div class="form-radio">
                            <label for="gender" class="radio-label">Gender :</label>
                            <div class="form-radio-item">
                                <input type="radio" name="gender" id="M" value="M">
                                <label for="M">Male</label>
                                <span class="check"></span>
                            </div>
                            <div class="form-radio-item">
                                <input type="radio" name="gender" id="F" value="F">
                                <label for="F">Female</label>
                                <span class="check"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="birth_date">Birth Of Date :</label>
                            <input type="date" name="birth" id="birth">
                        </div>
                        <div class="form-submit">
                        	<input type="button" value="뒤로가기" class="submit" id="back" onclick="goBack();" />
                            <input type="reset" value="초기화" class="submit" name="reset" id="reset" />
                            <input type="submit" value="회원가입" class="submit" name="submit" id="submit" style="background:gray;" disabled />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <script>
	function goBack() {
		history.back();
	}
	
	
	function joinValidate() {
		
		//이름 검사( 2글자 이상 )
		if(!(/^[가-힣]{2,}$/.test($("#joinForm input[name=userName]").val()))) {
			$("#joinForm input[name=userName]").focus();
			alert("이름 오류");
			return false;
		}
		
	
		//아이디 검사 총 4~12글자(대문자 포함)
		if(!(/^[a-z][a-z\d]{3,11}$/i.test($("#joinForm input[name=userId]").val()))) {
			$("#joinForm input[name=userId]").focus() 
			alert("아이디값 오류");
			return false;
			
		} 
		
		
		//비밀번호와 비밀번호 확인 일치하기 
		if($("#joinForm input[name=userPwd]").val() != $("#joinForm input[name=userPwd2]").val()) {
			alert("비번 오류");
			return false;
		}
		
		return true;
	}
	
	
	$(function() {
		var isUsable = false; //ID중복 X -> True
		
		$("#idCheck").click(function() {
			var userId =  $("#userId");

			
			$.ajax({ //아이디가 사용 가능한지 아닌지 유무 판단만 
				url:"<%= request.getContextPath() %>/idCheck.me",
				type:"post",
				data:{userId:userId.val()},
				success:function(result){
					
					if(result == "fail") {
						alert("사용할 수 없는 아이디 입니다!");
						userId.focus(); 
					} else { //result == success 
						if(confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")) {
							userId.attr("readonly", "true"); //더이상 바꿀 수 없게
							isUsable = true;
						} else {
							userId.focus()
						}
					}
					
					if(isUsable) {
						$("#submit").removeAttr("disabled").removeAttr("style","none");
					}

				}, 
				error:function() {
					console.log("서버 통신 안됨");
				}
				
			});
			
		});
	});
	
	
	
	
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("../juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}
	
	
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			$('#address').attr("value", roadFullAddr);
	}

	
	
	
	
	
</script>    
    

</body>
</html>