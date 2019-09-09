<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.ArrayList, photo.model.vo.*"%>
   <%@ include file="../common/menubar.jsp" %>
<%
   ArrayList<Folder> folderList = (ArrayList<Folder>)request.getAttribute("folderList");
   ArrayList<Attachment> atList = (ArrayList<Attachment>)request.getAttribute("atList");   
   String move = (String)request.getAttribute("move");
   int atId = (int)request.getAttribute("atId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="resources/css/photo.css" />

<style>

   
   .content{
      height:420px;
      margin-left:auto;
      margin-right:auto;
   }
   
   
   .folder-list:hover{
      background: #fbc8d6 ;
      opacity:0.7;
      cursor:pointer;
   }
   
   .page-wrap {
      overflow:auto;
   }

   
   
   #content{
      overflow:auto;
      margin-left:auto;
      margin-right:auto;
      height:600px;
   }
   
   
   
</style>
</head>
<body>

   <div class="page-wrap">

      

      <!-- Main -->
      <section id="main"> <!-- Header --> <header id="header">
      <div>
         우리가 함께 걷는 사잇길
      </div>
      </header> <!-- Gallery --> <section id="galleries"> <!-- Photo Galleries -->
      <div class="gallery">

         <!-- Filters -->
         <header>
         <h1>Gallery</h1>


         
            <ul class="tabs">
               <button id='createFolder'>폴더 생성</button>
               <button id="delete">폴더 삭제</button>
               <button id="updatefName">폴더 수정</button>
            </ul>
         </header>
         
         
         
         
            
         <div class="content" id="content">
            <input type="hidden" value="N" id="isDelete">
         </div>


      

      </section> <!-- Contact --> <section id="contact"> <!-- Social -->
      <div class="social column">
         
      </div>

      <!-- Form -->
      <div class="column">
         
      </div>

      </section> <!-- Footer --> <footer id="footer">
      <div class="copyright">
         &copy; Untitled Design: <a href="https://templated.co/">TEMPLATED</a>.
         Images: <a href="https://unsplash.com/">Unsplash</a>.
      </div>
      </footer> </section>
   </div>

   <!-- Scripts -->
    <script src="resources/js/jquery.photomain.js"></script>
   <script src="resources/js/jquery.popup.min.js"></script>
   <script src="resources/js/jquery.scrolly.min.js"></script>
   <script src="resources/js/skel.min.js"></script>
   <script src="resources/js/util.js"></script>
   <!-- <script src="resources/js/photo.js"></script> -->
   
   
   
   <script>
   
   
   // 댓글 리스트 출력하는 함수 
   function selectRlist() {
      //전달하고자 하는 게시글 번호 
      var cCode = '<%= loginUser.getcCode() %>'; 

      $.ajax({
         url:"test.ph", 
         data:{cCode:cCode},
         type:"GET",
         success:function(list) {
            var $content = $("#content"); //<div></div>

            $content.html('<input type="hidden" value="N" id="isDelete">'); //기존테이블 정보 초기화 

            $.each(list, function(index, value) { //value는 객체 하나하나 
               var path = '<%=  request.getContextPath() %>/resources/uploadImages/' + value.mainAttachment; 
               var $divLine = $('<div class="folder-list" align="center" onclick="check(' + value.fId + ')">');
               var $div = $('<div>').css("margin", "20px"); 
               var $image = $('<img>').attr("src", path).attr("class", "titleImage").css("width", "210px").css("height", "150px");
               var $fName = $('<p>').text(value.fName); 
               var $br = $('<br>');
               
               
               $divLine.append($div); 
               $div.append($image); 
               $divLine.append($fName);
               $fName.append($br);
               
               $content.append($divLine);
               
               
            });

         },
         error:function() {
            console.log("통신실패");
         }
      });
   } 
   
   
   
      function createFolder() {
         var create = confirm("폴더를 추가하시겠습니까?");
         
         if(create) {
            var w = window.open('<%= request.getContextPath() %>/insertForm.ph', '폴더생성창', 'width=500,height=510, location=no, status=no, scrollbar=no');
            var watchClose = setInterval(function() {
               if(w.closed){
                  selectRlist(); 
                  clearTimeout(watchClose);
                  
               }
               
            }, 200);
         }
         
         
      }
      
      
      
      
      function check(fId) {
          console.log('fId : ' + fId);
          if("<%=move%>" == "M"){
             location.href="<%= contextPath %>/realMovePhoto.ph?fId=" + fId + "&atId=" + <%=atId%>;
          }
          else if($("#isDelete").val() == "D"){
                console.log($("#isDelete").val());
                   $("#isDelete").val("N");
                   if(confirm("해당 폴더를 삭제하시겠습니까?")) {
                      location.href="<%= contextPath %>/deleteFolder.fo?fId=" + fId;
                   }
             }else if($("#isDelete").val() == "U"){
                console.log($("#isDelete").val());
                $("#isDelete").val("N");
                   var fName = prompt("변경할 이름 입력");
                      location.href="<%= contextPath %>/updateFolder.fo?fId=" + fId + "&fName=" + fName;
                
             }else{
             console.log($("#isDelete").val());
             location.href="<%=request.getContextPath() %>/detail.ph?fId=" + fId;
             }
             
          
       }
   
      
   
   
      $(function(){
         
         selectRlist(); 
         
         
         $('#createFolder').click(function() {
            createFolder();
         })
   
            
         console.log($("#isDelete").val());
         $("#delete").click(function(){
            $("#isDelete").val("D");
            console.log($("#isDelete").val());
         });
         
         $("#updatefName").click(function(){
            $("#isDelete").val("U");
            console.log($("#isDelete").val());
         });
         
      });
   </script>

</body>
</html>