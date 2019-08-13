<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member"
	import="java.text.DateFormat"
	import="java.text.ParseException"
	import="java.text.SimpleDateFormat"
	import="java.util.Calendar"
	import="java.util.Date"
	
 %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	
%>    
<%
	String birthday = loginUser.getBirth();
	String pBirthday = loginUser.getPartnerBirth();
	String result ="";
	String pResult=""; 

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
	
    Calendar cal = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    Calendar cal3 = Calendar.getInstance();
    Calendar today = Calendar.getInstance();
	
    String start = "2017-01-01";
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = null;

    try {         
       startDate = sdf.parse(start);

    } catch (ParseException e) {
       e.printStackTrace();

    }
    
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
</head>
<body>
    	 <aside id="profile-1" class="profileSide"> 
            <a href="#"><img src="resources/images/profile_boy.png" id="photo1" class="profile"></a>
            <span id="myName"><%= loginUser.getUserName() %></span> ♥
			<p id="myBirthDay"><%= result %></p>
			<table id="menuTB"	>
				<tr>
					<th><img src="<%= request.getContextPath() %>/resources/images/icons/myPage2.png" class="icon" onclick="goMyPage();"></th>
					<th><img src="resources/images/icons/calendar2.png" class="icon"></th>
					<th><img src="resources/images/icons/store2.png" class="icon"></th>
				</tr>
				<tr>
					<td>마이페이지</td>
					<td>캘린더</td>
					<td>스토어</td>
				</tr>
				<tr>
					<th><img src="resources/images/icons/gallery2.png" class="icon"></th>
					<th><img src="resources/images/icons/saving3.png" class="icon"></th>
					<th><img src="resources/images/icons/sns2.png" class="icon"></th>
				</tr>
				<tr>
					<td>사진첩</td>
					<td>가계부</td>
					<td>SNS</td>
				</tr>
			</table>

			<div id="help">
				<img src="resources/images/icons/help3.png" alt="">
				문의하기
			</div>
		</aside>
        <aside id="profile-2" class="profileSide">
			<div id="logout" onclick="logout();">
				LOGOUT
			</div>
            <a href="#"><img src="resources/images/profile_girl.png" id="photo2" class="profile"></a>	
            ♥ <span id="partnerName"><%= loginUser.getPartnerName() %></span>
            <p id="partnerBirthDay"><%= pResult %></p>
			<p id="firstDate"></p>
			
			<table id="anniversary">
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
        	function goMyPage() {
        		location.href = "<%= request.getContextPath() %>/myPage.me";
        	}
        	
        	function logout() {
        		location.href = '<%= request.getContextPath() %>/logout.me';
        	}
        </script>

</body>
</html>