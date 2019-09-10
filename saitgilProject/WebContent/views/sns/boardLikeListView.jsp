<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, sns.model.vo.*" %>

<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
	ArrayList<Photo> phList = (ArrayList<Photo>)request.getAttribute("phList");
	ArrayList<Comment> comList = (ArrayList<Comment>)request.getAttribute("comList");
	ArrayList<Board> likeList = (ArrayList<Board>)request.getAttribute("likeList");
	ArrayList<Board> scrapList = (ArrayList<Board>)request.getAttribute("scrapList");
	int num = 1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/sns.css">
    <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>

</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

    <!-- Thumbnails - START -->
    <div class="content-write-button">
        <button type="button" class="btn btn-danger add-btn">+</button>
        <button type="button" id="share" class="btn btn-primary write-btn" onclick="location.href='<%= request.getContextPath() %>/insertForm.bo?isPublic=Y'">공유글쓰기</button>
        <button type="button" id="secret" class="btn btn-primary write-btn" onclick="location.href='<%= request.getContextPath() %>/insertForm.bo?isPublic=N'">비밀글쓰기</button>
    </div>
    <script>
         /* $(function(){
             $(".add-btn").on('click',function(){
                 if($(this).text()=='+'){
                     $(this).text("x");
                     $(this).siblings("button").slideDown();
                     // $(this).next().slideDown();
                 }else{
                     $(this).text("+");
                     $(this).siblings("button").slideUp();
                 }
             });
         }); */
    </script>
    <div class="all">
        <div class="container">
            <div id="viewPoint" style=" margin-top:5px;">
                <div class="category" onclick="location.href='<%= request.getContextPath()%>/list.bo'">모두 보기</div>
                <div class="category"onclick="location.href='<%= request.getContextPath()%>/secretList.bo'">비밀 글 보기</div>
                <div class="category" style="background:pink;" onclick="location.href='<%= request.getContextPath() %>/likeList.bo?userId=<%= loginUser.getUserId() %>'">좋아요 한 목록 보기</div>
                <div class="category" onclick="location.href='<%= request.getContextPath() %>/scrapList.bo?cCode=<%= loginUser.getcCode() %>'">스크랩 보기</div>
            </div>
            <script>
                 /* $(function(){
                     $(".category").on("click",function(){
                         $(this).css("background", "lightblue");
                         $(this).siblings("div").css("background", "white");
                         if($(this).text() == '모두 보기'){
                             $(".share-category").first().css("background", "lightblue");
                             $(".share-category").css("display", "inline-block");
                         }else{
                             $(".share-category").css({"display":"none", "background":"white"});

                         }
                     });
                     $(".share-category").on("click",function(){
                         $(this).css("background", "lightblue");
                         $(this).siblings("div").css("background", "white");
                     });
                 }); */
            </script>
            <div id="board">
            <%for(Board b : bList){ %>
	            <div class="board">
	            	<div class="page-header">
	            		<h3><%= b.getUserId() %> ♥ <%= b.getPartnerName() %></h3>
	            		<div class="content-date"><%= b.getbDate() %></div>
	            	</div>
	            	
	            	<div class="row">
	            		<div class="content-board">
	            			<%for(Photo p : phList){ %>
	            				<% if(p.getbNo() == b.getbNo()){ %>
	            				<a href="#" data-toggle="modal" data-target=".dialog<%=num%>">
	                            	<img src="<%= request.getContextPath() %>/resources/snsImages/<%= p.getChangeName() %>" width="200" class="img-responsive img-rounded center-block" alt="" />
	                        	</a>
	                        	<div class="modal fade dialog<%=num%>" tabindex="-1">
			                        <div class="modal-dialog modal-lg">
			                            <div class="modal-content">
			                                <div class="modal-header">
			                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
			                                </div>
			                                <div class="modal-body active" style="align:center;">
			                                    <img src="<%= request.getContextPath() %>/resources/snsImages/<%= p.getChangeName() %>" class="img-responsive img-rounded center-block" alt="" />
			                                </div>
			                            </div>
			                        </div>
			                    </div>
	            				<%num++;%>
	            				<%} %>
	            			<%} %>
	            			<h3><%= b.getbContent() %></h3>
	                        <hr>
	            		</div>
	            		
	            		<div class="like-area">
	                        <!-- like button -->
	                        <div class="like-content like-scrap">
	                        	<div class="like-count">
	                        		좋아요 수 : <span><%=b.getLikeCount() %></span>
	                        	</div>
	                        	<%if(likeList.size() == 0) {%>
	                        		<button class="btn-secondary like-review">
	                                	<i class="fa fa-heart" aria-hidden="true" id="like"></i> Like
	                            	</button>
	                        	<%}else { %>
		                        	<%for(int i=0; i<likeList.size(); i++) {%>
		                        		<% if(loginUser.getUserId().equals(likeList.get(i).getUserId()) && b.getbNo() == likeList.get(i).getbNo()){ %>
		                        		<button class="btn-secondary like-review">
		                        			<i class="fa fa-heart" aria-hidden="true" id="unlike"></i> You liked this
		                        		</button>
		                        		
		                        		<%break;}else if(i == likeList.size()-1){%>
		                            	<button class="btn-secondary like-review">
		                                	<i class="fa fa-heart" aria-hidden="true" id="like"></i> Like
		                            	</button>
		                        		<%} %>
		                        	<%} %>
	                        	<%} %>
	                            <input type="hidden" value="<%= loginUser.getUserId() %>" name="userId">
	                            <div class="scrap">
	                            <%if(scrapList.size() == 0) {%>
	                            <button class="scrap-review btn">스크랩됨</button>
	                            <%}else { %>
	                            	<%for(int i=0; i<scrapList.size(); i++) {%>
		                        		<% if(loginUser.getcCode().equals(scrapList.get(i).getcCode()) && b.getbNo() == scrapList.get(i).getbNo()){ %>
	                            		<button class="scrap-review btn">스크랩됨</button>
	                            		<%break;}else if(i == scrapList.size()-1){%>
	                            		<button class="scrap-review btn">스크랩</button>
	                            		<%} %>
		                        	<%} %>
	                        	<%} %>
		                            <input type="hidden" value="<%= loginUser.getcCode() %>" name="cCode">
		                            <%if(b.getUserId().equals(loginUser.getUserName())){ %>
	                             <div class="updel">
	                             
	                             <button class="btn btn-primary up-btn" style="margin-top:5px; background: #e23a6e;">수정</button>
	                             <button class="btn btn-primary del-btn" style="margin-top:5px; background: #e23a6e;">삭제</button>	
	                             </div>
	                             <%} %>
	                            </div>
	                            <div class="comment-button">댓글달기</div>
	                        </div>
	                        <hr>
	                    </div>
	                    
	                    <div class="comment-all-area comment-all-none">
	                    	<div class="comment-area comment">
	                    	<%for(Comment c : comList){ %>
	                    		<%if(c.getbNo() == b.getbNo()) {%>
		                    	<div class="comment-area-sample">
		                            <div class="comment comment-image-main">
		                                <img class="comment-image" src="<%= request.getContextPath() %>/resources/<%= c.getProfile_pic() %>">
		                            </div>
		                            <div class="comment comment-board-all">
		                                
		                                <div class="comment-board">
		                                    <div class="comment-id"><span><%= c.getUserId() %> ♥ <%= c.getPartnerName() %></span></div>
		                                    <div class="comment-content"><%= c.getContent() %></div>
		                                </div>
		                                <div class="comment-date"><%= c.getCommentDate() %></div>
		                            </div>
		                            <br clear="both">
		                            <hr>
		                        </div>
		                        <%} %>
		                    <%} %>
	                    	</div>
	                        
		                    <div class="comment-write comment" style="width:95%;">
                              <div class="comment comment-image-main" style="float:left;">
                                   <img class="comment-image" src="<%= request.getContextPath() %>/resources/<%= loginUser.getProfilePic() %>">
                                   
                               </div>
                               <textarea style="margin-left:5px; resize:none; width:95%; float:right;" name="comment-write" class="comment-text" rows="2"></textarea>
                               <br clear="both"><br>
                               <button class="btn add-comment" style="float:right;">작성</button>
                               <input type="hidden" value="<%= b.getbNo() %>" name="bNo">

	                        </div>
	                    </div>
	            		
	            	</div>
	            </div>
            <%} %>
            </div>
        
        </div>
    </div>
    <script>
    	page = 2;
    	$(window).scroll(function(){   //스크롤이 최하단 으로 내려가면 리스트를 조회하고 page를 증가시킨다.
    	     if($(window).scrollTop() >= $(document).height() - $(window).height()){
    	    	 console.log(page);
    	         getList();
    	         console.log(page);
    	     } 
    	});
    	$(function(){
    		
    		
                 
    		
		   	$(document).on("click", ".add-comment", function(){
		   		var content = $(this).siblings("textarea").val();
		   		var bNo = $(this).next().val();
		   		var userId = "<%= loginUser.getUserId() %>";
		   		
		   		$.ajax({
		   			url:"rInsertSelect.bo",
		   			type:"post",
		   			data:{content:content,bNo:bNo,userId:userId},
		   			success:function(result){
		   				var html = ""
		   				$("input[name=bNo][value=" + bNo + "]").parent().prev().html(html);
		   				$.each(result, function(index, value){
		   				
		   				html += "<div class='comment-area-sample'>" +
		   					    "<div class='comment comment-image-main'>" +
		   					    "<img class='comment-image' src='<%= request.getContextPath() %>/resources/" + value.profile_pic + "'>" +
		   					    "</div>" +
		   					    "<div class='comment comment-board-all'>" +
		   					    "<div class='comment-board'>" +
		   					    "<div class='comment-id'><span>" + value.userId + " ♥ " + value.partnerName + "</span></div>" +
                           	    "<div class='comment-content'>" + value.content + "</div>" +
                           	    "</div>" +
                           	    "<div class='comment-date'>" + value.commentDate + "</div>" +
                           	    "</div>" +
                           	    "<br clear='both'>" +
                           	    "<hr>" +
                           	    "</div>";
		   				});
		   				
		   				$("input[name=bNo][value=" + bNo + "]").parent().prev().append(html);
		   				$(".comment-text").val("");
		   			},
		   			error:function(){
		   				
		   			}
		   		});
		   	});
    	});
    	
    	 function getList(){
    		 var userId = "<%= loginUser.getUserId() %>";
    	    $.ajax({
    	        type : 'POST',  
    	        dataType : 'json', 
    	        data : {page:page,userId:userId},
    	        url : "likeAjax.bo",
    	        success : function(result) {
    	            var data = result;
    	            var html = "";
    	            if(data.length>0){
   	                // for문을 돌면서 행을 그린다.
   	                $.each(result, function(index, value){
   	                	html += "<div class='board'>" + 
   	                		 "<div class='page-header'>" + 
   	                		 "<h3>" + value.userId + " ♥ " + value.partnerName + "</h3>" + 
   	                		 "<div class='content-date'>" + value.bDate + "</div>" + 
   	                		 "</div>" + 
   	                		 "<div class='row'>" + 
   	                		 "<div class='content-board'>";
   	                		 
   	                		 
   	                		<% for(Photo p : phList){  %>
   	                		if(<%= p.getbNo()%> == value.bNo){
   	                		html += "<a href='#' data-toggle='modal' data-target='.dialog<%=num%>'>" +
   	                				"<img src='<%= request.getContextPath() %>/resources/snsImages/<%= p.getChangeName() %>' width='200' class='img-responsive img-rounded center-block' alt='' />" + 
   	                				"</a>" + 
   	                				"<div class='modal fade dialog<%=num%>' tabindex='-1'>" + 
   	                				"<div class='modal-dialog modal-lg'>" + 
   	                				"<div class='modal-content'>" + 
   	                				"<div class='modal-header'>" + 
   	                				"<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>x</button>" + 
   	                				"</div>" + 
   	                				"<div class='modal-body active' style='align:center;'>" + 
   	                				"<img src='<%= request.getContextPath() %>/resources/snsImages/<%= p.getChangeName() %>' class='img-responsive img-rounded center-block' alt='' />" + 
   	                				"</div>" + 
   	                				"</div>" + 
   	                				"</div>" + 
   	                				"</div>";
   	                		}
   	                		<%num++;%>
   	                		<% } %>
   	                		html += "<h3>" + value.bContent +"</h3>" + 
   	                				"<hr>" + 
   	                				"</div>" + 
   	                				"<div class='like-area'>" +
	                        		"<div class='like-content like-scrap'>" +
	                        		"<div class='like-count'>" +
	                        		"좋아요 수 : <span>" + value.likeCount + "</span>" +
	                        		"</div>";
	                	    
	                        var flag = false;
	                        <%for(int i=0; i<likeList.size(); i++) {%>
	                        	if(value.bNo == <%= likeList.get(i).getbNo() %> && "<%= likeList.get(i).getUserId()%>" == "<%= loginUser.getUserId()%>"){
	                        		flag = true;
	                        	}
	                        <%} %>
	                        if(flag){
	                        	html += "<button class='btn-secondary like-review'>" +
            				   	"<i class='fa fa-heart' aria-hidden='true' id='unlike'></i> You liked this" +
            				   	"</button>";
	                        }else{
	                        	html += "<button class='btn-secondary like-review'>" +
                    			"<i class='fa fa-heart' aria-hidden='true' id='like'></i> Like" +
                				"</button>";
	                        }
	                        html += "<input type='hidden' value='<%= loginUser.getUserId() %>' name='userId'>" +
	                        		"<div class='scrap'>";
	                        		
                            
                            var flag2 = false;
                           	<%for(int i=0; i<scrapList.size(); i++) {%>
                           	if(value.bNo == <%= scrapList.get(i).getbNo() %> && "<%= scrapList.get(i).getcCode()%>" == "<%= loginUser.getcCode()%>"){
                           		flag2=true;
                           	}
                       		<%} %>
                        	if(flag2){
                           		html += "<button class='scrap-review btn'>스크랩됨</button>";
                        	}else{
                        		html += "<button class='scrap-review btn'>스크랩</button>";                        			
                        	}
                            		
                            
                        	html += "<input type='hidden' value='<%= loginUser.getcCode() %>' name='cCode'>";
	                        if(value.userId == '<%=loginUser.getUserName()%>'){
                            
	                        	html += "<div class='updel'>" +
	                        	        "<button class='btn btn-primary up-btn' style='margin-top:5px; background: #e23a6e;'>수정</button>" +
                            			"<button class='btn btn-primary del-btn' style='margin-top:5px; background: #e23a6e;'>삭제</button>" +	
                            			"</div>";
                            }
	                        html += "</div>" +
	                            	"<div class='comment-button'>댓글달기</div>" + 
	                            	"</div>" + 
	                            	"<hr>" + 
	                            	"</div>" +
	                            	"<div class='comment-all-area comment-all-none'>" +
	                    			"<div class='comment-area comment'>"; 
	                        <%for(Comment c : comList){ %>	// 코멘트들 삽입하는 div 생성한겁니다.
		                    	if(<%= c.getbNo() %> == value.bNo) {
		                    		html += "<div class='comment-area-sample'>" +
					   					    "<div class='comment comment-image-main'>" +
					   					    "<img class='comment-image' src='<%= request.getContextPath() %>/resources/<%= c.getProfile_pic() %>'>" +
					   					    "</div>" +
					   					    "<div class='comment comment-board-all'>" +
					   					    "<div class='comment-board'>" +
					   					    "<div class='comment-id'><span><%= c.getUserId()%> ♥ <%= c.getPartnerName() %></span></div>" +
			                           	    "<div class='comment-content'><%= c.getContent() %></div>" +
			                           	    "</div>" +
			                           	    "<div class='comment-date'><%= c.getCommentDate() %></div>" +
			                           	    "</div>" +
			                           	    "<br clear='both'>" +
			                           	    "<hr>" +
			                           	    "</div>";
		                    	}
		                    <%}%>
		                    
							html += "</div>" +
									"<div class='comment-write comment' style='width:95%;''>" +
									"<div class='comment comment-image-main' style='float:left;'>" +
									"<img class='comment-image' src='<%= request.getContextPath() %>/resources/<%= loginUser.getProfilePic() %>'>" +
	                            	"</div>" +
	                            	"<textarea style='margin-left:5px; resize:none; width:95%; float:right;' name='comment-write' class='comment-text' rows='2'></textarea>" +
	                            	"<br clear='both'><br>" +
	                            	"<button class='btn add-comment' style='float:right;''>작성</button>" +
	                            	"<input type='hidden' value='" + value.bNo + "' name='bNo'>" +
	                            	"</div>" +
	                            	"</div>" +
	                            	"</div></div>";// 이거는 위에 board랑 row div입니다. 이 바로 위에 코멘트가 나와야됨 !!
   	                		
   	                });
					page++;
    	            }else{
   	                //데이터가 없을경우
   	                }
    	            
    	            if (page==1){  //페이지가 1이 아닐경우 데이터를 붙힌다.
    	                $("#board").html(html);
    	            }else{
    	                $("#board").append(html);
    	            }
    	            console.log(page);
    	       },error:function(e){
    	               alert("데이터를 가져오는데 실패하였습니다.");
    	       }
    	    }); 
    	    
    	    	
    	    }


    </script>
        
        <script>
				/*
                 $(function(){
                     $(document).on('click', '.comment-button', function(){
                         if($(this).parent().parent().next().attr('class') == "comment-all-none"){
                             $(this).parent().parent().next().attr('class', 'comment-all');
                         }else{
                             $(this).parent().parent().next().attr('class', 'comment-all-none');
                         }
                     })
                 }); */
            </script>
        <!-- Thumbnails - END -->
</body>
</html>