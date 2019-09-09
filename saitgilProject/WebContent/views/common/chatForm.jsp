<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.*"%>
	<%
	
		Member me = (Member)request.getAttribute("me");
		Member you = (Member)request.getAttribute("you");

		String toID = null;
		if(request.getParameter("toID") != null) {
			toID = (String)request.getParameter("toID");
		}
	
	%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <title>채팅창</title>
    <style>
        .container{
            margin-right: auto;
            margin-left: auto;
            width: 500px;
            height: 700px;
            border: 2px solid pink;

        }

        .head{
            background-color: #e23a6e;
            width: 100%;
            height:10%;
            color:white;
            font-family:Noto Serif KR;
            align:center;
        }

        h4{
            margin:13px;
            font-size: 20px;
            font-family:Noto Serif KR;
            align:center;
        }

        #chatlist{
            height: 70%;
            width: 500px;
            background: white;
        }

        #footer {
            height: 20%;
            width: 80%;
        }

        #footer, #buttonArea {
            display: inline-block;
        }
        
        
        /* 메시지 영역 스타일 */
        
        .chatOutline-me{
            background: #fde8ee;
            width: 330px;
            height: 100px;
            box-sizing: border-box;
            border-radius:15px;
            margin-left:150px;
            font-family:Noto Serif KR;
        }
        
       .chatOutline-you{
            background: #e6f7ff;
            width: 330px;
            height: 100px;
            box-sizing: border-box;
            border-radius:15px;
            font-family:Noto Serif KR;
        }

        div{
            display: inline-block;
        }

        .media {
            align-content: center;
            width: 20%;
        }
        

        .date {
        	margin-left : 32px;
            font-size: 13px;
            font-weight: lighter;
        }
        
        #contentArea p{
        	margin:15px;
        }
        
        
        #sendBtn {
			background: #f7aec5;
			color: #FFF;
			border-radius: 4px;
			border: 0;
			cursor: pointer;
			display: inline-block;
			height: 2.85em;
			line-height: 2.95em;
			text-align: center;
			text-decoration: none;
			white-space: nowrap;
			width:80px;
			font-family:Noto Serif KR;
		}
	
		#sendBtn:hover{
			background: #e23a6e;
		}
		
		#chatContent {
			font-family:Noto Serif KR;
			resize: none;

		}
		

    </style>


</head>
<body>

    <div class="container">
        <div class="head">
            <h4>사잇길 채팅</h4> 
        </div>
            <div id="chatlist" style="overflow-y:auto; height: 70%;">
            </div>
            <div id="footer" style="display:inline; height:30%;">
            	<textarea cols="50" rows="6" type="text" id="chatContent" placeholder="메세지를 입력하세요." maxlength="100"></textarea>
            	<button type="button" id="sendBtn" onclick="submitFunction();">보내기</button>
            </div>
           <!--  <div id="buttonArea">
                    <button type="button" id="sendBtn" onclick="submitFunction();">보내기</button>
            </div> -->
    </div>


    <script>
  		  
		var lastID = 0;
	    $(function() {
				
				chatListFunction('ten');

				getInfiniteChat();
	    });
	    
		function getInfiniteChat() {
			setInterval(function() {
				chatListFunction(lastID);
			}, 100);
		}

    	function submitFunction() {
    		var fromID = '<%= me.getUserId()%>';
    		var toID = '<%= you.getUserId() %>';
    		var chatContent = $("#chatContent").val();
    		var cCode = '<%= me.getcCode() %>';
    		console.log(cCode);
    		console.log(fromID);
    		console.log(toID);
    		console.log(chatContent);
    		
    		
    		$.ajax({
    			type:"POST",
    			url:"<%= request.getContextPath() %>/chatSubmit.ch",
    			data:{
    				cCode:encodeURIComponent(cCode),
    				fromID :encodeURIComponent(fromID),
    				toID: encodeURIComponent(toID),
    				chatContent:encodeURIComponent(chatContent)
    			}, 
    			success: function(result) {
    				console.log("result: " + result);
    				if(result == "1") {
    				} else if(result =="0") {
    				 	alert("메세지값을 입력하세요");
    				} else {
    					alert("warningMessage");
    				}
    			}, error:function() {
					console.log("서버 통신 실패");
				}

    		});
    		
    		$("#chatContent").val("");
    	}
    	
    	
    	
    	
    	function chatListFunction(type) {
    		var fromID = '<%=me.getUserId() %>';
    		var toID = '<%= you.getUserId() %>';  
    		console.log("type : " +type );
    		
    		$.ajax({
    			type:"POST",
    			url:"<%= request.getContextPath() %>/chatListServlet", 
    			data:{
    				fromID:encodeURIComponent(fromID),
    				toID:encodeURIComponent(toID),
    				listType:type
    			}, 
    			success:function(data) {
    				console.log(data);
    				if(data =="") return;
    				var parsed = JSON.parse(data);
    				var result = parsed.result;
    				
    				for(var i =0; i<result.length; i++) {
    					addChat(result[i][0].value, result[i][2].value, result[i][3].value);
    				}
    				 
    				lastID = Number(parsed.last);
    				console.log("lastID:" + lastID);
    				var objDiv = document.getElementById("chatlist");
    				objDiv.scrollTop = objDiv.scrollHeight;

    			}, error:function() {
    				console.log("서버통신실패");
    			}
    		});
    	}
    	
    	function addChat(chatName, chatContent, chatTime) {
    		var name = '<%= me.getUserId() %>';

    		if(chatName == name) {
    		
	    		var source =  '<div class="chatOutline-me">' +
		            '<div class="media">' + 
		            '<img class="media-object img-circle" style="width:40px; height:40px; border-radius:50px; margin-left:20%;" src="/saitgil/resources/<%= me.getProfilePic() %>" id="photoBox">' +
		           '</div>' + 
		           '<div class="contentArea">' +
		               '<h4 class="media-heading">' +
		               '<%= me.getUserName() %>' +
		               '<span class = "date">' + 
		               chatTime + 
		               '</span>' + 
		               '</h4>' +                  
		               '<p>' + 
		            chatContent +  
			              '</p>' + 
			           '</div>' + 
			   		'</div>' + '<br><br>'; 
			   			
		
		    		$("#chatlist").append(source);
	    		
    		} else {
    			
	    		var source =  '<div class="chatOutline-you">' +
	            '<div class="media">' + 
	            '<img class="media-object img-circle" style="width:40px; height:40px; border-radius:50px; margin-left:20%;" src="/saitgil/resources/<%= you.getProfilePic() %>" id="photoBox">' +
	           '</div>' + 
	           '<div class="contentArea">' +
	               '<h4 class="media-heading">' +
	               '<%= you.getUserName() %>' +
	               '<span class = "date">' + 
	               chatTime + 
	               '</span>' + 
	               '</h4>' +                  
	               '<p>' + 
	            chatContent +  
		              '</p>' + 
		           '</div>' + 
		   		'</div>' + '<br><br>'; 
		   			
	
	    		$("#chatlist").append(source);
    			
    		}
    	}
    		
    </script>
</body>
</html>