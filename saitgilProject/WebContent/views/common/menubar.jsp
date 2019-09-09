<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*, shareFile.model.vo.*"
	import="java.text.DateFormat"
	import="java.text.ParseException"
	import="java.text.SimpleDateFormat"
	import="java.util.Calendar"
	import="java.util.Date"
	import="notice.model.vo.Notice"
	
 %>
<%
	String contextPath = request.getContextPath(); 

	Member loginUser = (Member)session.getAttribute("loginUser");
	Member partner = (Member)session.getAttribute("partner");
	ShareFile sf = (ShareFile)session.getAttribute("sf");
	String couplePicture = sf.getcPicturePath(); 
	Date meetdate = sf.getMeetDate();
	Notice recentNotice = (Notice)session.getAttribute("recentNotice");
	int bNom = 0;
	String a = "N";
%>    
<%
	String result ="생일을 입력하세요.";
	String pResult="생일을 입력하세요."; 
	
	if(loginUser.getBirth() != null) {
		String birthday = loginUser.getBirth();
		String pBirthday = loginUser.getPartnerBirth();
	
		if(birthday == null || birthday == "") {
		    result = "입력된 값이 없습니다.";
		 } else {
		
			 if(birthday.charAt(5) == '0') {
			    if(birthday.charAt(8) == 0) {
			       result = birthday.charAt(6) + "월 " + birthday.charAt(9) + "일";
			    }else {
			       result = birthday.charAt(6) + "월 " + birthday.substring(8,10) + "일";
			    }
			 }else {
			    if(birthday.charAt(8) == 0) {
			       result = birthday.substring(5,7) + "월 " + birthday.charAt(9) + "일";
			    }else {
			       result = birthday.substring(5,7) + "월 " + birthday.substring(8,10) + "일";
			    }
			 }
		}
		if(pBirthday == null || pBirthday == "") {
		    pResult = "입력된 값이 없습니다.";
		 } else {
		
			 if(pBirthday.charAt(5) == '0') {
			    if(pBirthday.charAt(8) == 0) {
			       pResult = pBirthday.charAt(6) + "월 " + pBirthday.charAt(9) + "일";
			    }else {
			       pResult = pBirthday.charAt(6) + "월 " + pBirthday.substring(8,10) + "일";
			    }
			 }else {
			    if(pBirthday.charAt(8) == 0) {
			       pResult = pBirthday.substring(5,7) + "월 " + pBirthday.charAt(9) + "일";
			    }else {
			       pResult = pBirthday.substring(5,7) + "월 " + pBirthday.substring(8,10) + "일";
			    }
			 }
		}
	}
	
    Calendar cal = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    Calendar cal3 = Calendar.getInstance();
    Calendar today = Calendar.getInstance();

    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = meetdate;
    
    cal.setTime(startDate);
    cal2.setTime(startDate);
    cal3.setTime(startDate);
    
    int day = 100;
    int dday = (int)((today.getTimeInMillis()-cal.getTimeInMillis())/(1000*60*60*24)+1);
    while(dday > day) {
       day +=100;
    }
    
    cal2.add(Calendar.DATE, day-1);
    cal3.add(Calendar.DATE, day+99);
    int year = (int)(((today.getTimeInMillis()-cal.getTimeInMillis())/(1000*60*60*24)+1)/365)+1;
    
    cal.add(Calendar.YEAR, year);
    
    String anivDate = sdf.format(cal.getTime());
    String dDate1 = sdf.format(cal2.getTime());
    String dDate2 = sdf.format(cal3.getTime());
    
    long anivDay = ((cal.getTimeInMillis()-today.getTimeInMillis())/(1000*60*60*24)+1);
    long dDay1 = ((cal2.getTimeInMillis()-today.getTimeInMillis())/(1000*60*60*24)+1);
    long dDay2 = ((cal3.getTimeInMillis()-today.getTimeInMillis())/(1000*60*60*24)+1);
    

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>우리 둘만의 사잇길</title>
	<link href="resources/css/menubar.css" rel="stylesheet" type="text/css">	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>
<body>
	<div id="head">
		<div id="head-1">
			<a href="loginMainPage.me" id="logo">Sait Gil</a>
		</div>
		<div id="head-2">
			<img src="resources/images/icons/notice.png" width="25px" height="25px"> 
			<b onclick="location.href='<%= request.getContextPath() %>/list.no'">[공지사항]</b>
			<b id = "recentNotice" onclick="location.href='<%= request.getContextPath() %>/detail.no?noticeNo=<%= recentNotice.getNoticeNo() %>'"><%= recentNotice.getNoticeTitle() %></b>
		</div>
		<div id="logout" onclick="logout();">
			logout
		</div>
	</div>
	
	
    	 <aside id="profile-1" class="profileSide"> 

    	 <form method="POST" enctype="multipart/form-data" id="test">
    	    <div id="fileArea">
       			<input type="file" name="profile1" id="profile1" onchange="loadImg(this,1)">
     	    </div>
    	 	<div id="profileArea1">
            	<img src="<%= request.getContextPath() %>/resources/<%= loginUser.getProfilePic() %>" id="photo1" class="profile">
            </div>
          </form>
          
            <span id="myName"><%= loginUser.getUserName() %></span> ♥
			<p id="myBirthDay"><%= result %></p>
			<table id="menuTB"	>
				<tr>
					<th><img src="<%= request.getContextPath() %>/resources/images/icons/myPage2.png" class="icon" onclick="goMyPage();"></th>
					<td onclick="goMyPage();">마이페이지</td>
				</tr>
				<tr>
					<th><img src="resources/images/icons/calendar2.png" class="icon" onclick="location.href='<%= request.getContextPath() %>/clist.ca'"></th>
					<td onclick="location.href='<%= request.getContextPath() %>/clist.ca'">캘린더</td>
				</tr>
				<tr>
					<th><img src="resources/images/icons/store2.png" class="icon"></th>
					<td onclick="location.href='<%= contextPath %>/goMain.sp'">스토어</td>
				</tr>
				<tr>
					<th><img src="resources/images/icons/gallery2.png" class="icon" onclick="location.href='<%= contextPath %>/list.ph?move=<%= a %>&atId=<%= bNom%>'"></th>
					<td onclick="location.href='<%= contextPath %>/list.ph'">사진첩</td>
				</tr>
				<tr>
					<th><img src="resources/images/icons/sns2.png" class="icon" onclick="location.href='<%= contextPath %>/list.bo'"></th>
					<td onclick="location.href='<%= contextPath %>/list.bo'">SNS</td>
				</tr>
				<tr>
              		<th><img src="resources/images/icons/chat.png" class="icon" onclick="location.href='<%= contextPath %>/goChat.ch'"></th>
              		<td onclick="goChat();">채팅</td>
            	</tr>
				<tr>
					<th><img src="resources/images/icons/help3.png" class="icon" onclick="location.href='<%= contextPath %>/list.in?userId=<%= loginUser.getUserId()%>'"></th>
					<td onclick="location.href='<%= contextPath %>/list.in?userId=<%= loginUser.getUserId()%>'">문의하기</td>
				</tr>
			    
			</table>
			
			
			
			
<%-- 			<table id="menuTB"	>
				<tr>
					<th><img src="<%= request.getContextPath() %>/resources/images/icons/myPage2.png" class="icon" onclick="goMyPage();"></th>
					<th><img src="resources/images/icons/calendar2.png" class="icon" onclick="location.href='<%= request.getContextPath() %>/clist.ca'"></th>
					<th><img src="resources/images/icons/store2.png" class="icon"></th>
				</tr>
				<tr>
					<td>마이페이지</td>
					<td>캘린더</td>
					<td>스토어</td>
				</tr>
				<tr>
					<th><img src="resources/images/icons/gallery2.png" class="icon" onclick="location.href='<%= contextPath %>/list.ph'"></th>
					<th><img src="resources/images/icons/saving3.png" class="icon"></th>
					<th><img src="resources/images/icons/sns2.png" class="icon" onclick="location.href='<%= contextPath %>/list.bo'"></th>
				</tr>
				<tr>
					<td>사진첩</td>
					<td>가계부</td>
					<td>SNS</td>
				</tr>
			</table> --%>


		</aside>

        <aside id="profile-2" class="profileSide">
			<div id="profileArea2"> 
            	<img src="<%= request.getContextPath() %>/resources/<%= partner.getProfilePic() %>" id="photo2" class="profile">	
            </div>
            ♥ <span id="partnerName"><%= partner.getUserName() %></span>
            <p id="partnerBirthDay"><%= pResult %></p>
			<p id="firstDate"></p>
			
			<table id="anniversary">
				<tr>
					<th colspan="2" style="padding:1px;">우리가 사랑에 빠진 날 </th>
				</tr>
				<tr>
					<td colspan="2" style="padding:1px;"><%= startDate %></td>
				</tr>
				<tr>
					<th><%= day %>일</th>
					<th id="date1" class="date"><%= dDay1 %>일 남음</th>
				</tr>
				<tr>
					<th><%= day+100 %>일</th>
					<th id="date2" class="date"><%= dDay2 %>일 남음</th>
				</tr>
				<tr>
					<th><%= year %>주년</th>
					<th id="date3" class="date"><%= anivDay %>일 남음</th>
				</tr>
			</table>
        </aside>
       
        
        <script>
        	$(document).ready(function() {
        		$("#fileArea").hide();
        		
        		$("#profileArea1").click(function() {
        			$("#profile1").click();
        		});
        		
    			$("#menuTB td, #menuTB th").mouseenter(function() {
    				$(this).parent().css({"background":"#fde8ee", "cursor":"pointer"});
    			}).mouseout(function() {
    				$(this).parent().css({"background":"white"});
    			})
	
        	});
        	
        	function goChat(){
        		var width = window.innerWidth || document.body.clientWidth;

        		var height =  window.innerHeight || document.body.clientHeight;
        		var width2 = width/2 - 462;
        		var height2 = height/2 - 550;
        		if(height2<0){
        			height2=0;
        		}
        		if(width2<0){
        			width2=0;
        		}
				console.log(height);


        		window.open("<%=request.getContextPath()%>/goChat.ch","pop",'width=467,height=652,left='+ width2+',top='+ height2+', resizable=yes, status=no,toolbar=no,scrollbars=no');
        		
        	}
        	
        	function loadImg(value, num){
        		
        		var form = $('#test')[0];
        		var formData = new FormData(form);
        		var reader = new FileReader();
        		
        		var reader = new FileReader();
    			reader.onload = function(e) { 
        			if(confirm("정말로 바꾸시겠습니까?")) {

	        	    $.ajax({
	        	        url : 'updateProfile.me',
	        	        type : 'POST',
	        	        enctype:"multipart/form-data",
	        	        data : formData,
	        	        contentType : false,
	        	        processData : false,
						success:function(result) {
							console.log("result: " + result);
							if(result == "fail") {
								alert("프로필 변경에 실패하였습니다.");
								
							} else {
								var path = '<%= request.getContextPath() %>/resources/' + result;
								alert("프로필이 변경되었습니다.");
								$('#photo1').attr('src', path);
							}						
						},
						error:function() {
							console.log("서버통신 실패");
						}
					})
	        	}
	        			
	    	}
    			reader.readAsDataURL(value.files[0]);

        	}
        	
        
        	function goMyPage() {
        		location.href = "<%= request.getContextPath() %>/myPage.me";
        	}
        	
        	function logout() {
        		location.href = '<%= request.getContextPath() %>/logout.me';
        	}
        	
        	function goInquiry() {
        		var userId = '<%= loginUser.getUserId() %>';
        		location.href = '<%= request.getContextPath() %>/list.in?userId=' + userId;
        	}
        	
        
        </script>

</body>
</html>