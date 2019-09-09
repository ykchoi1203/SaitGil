<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.ArrayList, photo.model.vo.*"%>
<%
   Folder f = (Folder)request.getAttribute("f");

   ArrayList<Attachment> fileList = (ArrayList<Attachment>)request.getAttribute("fileList");
   
   Attachment titleImg = fileList.get(0);   
   
   
   int fId = (int)request.getAttribute("fId");
   
   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/photo.css?ver=123" />
<!-- Scripts -->
<script src="<%= request.getContextPath() %>/resources/js/jquery.photomain.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery.popup.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery.scrolly.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/skel.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/util.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/photo.js"></script>
<style>
.content {
   heigth: 650px;
   color: black;
   margin-left: auto;
   margin-right: auto;
   margin-top: 30px;
}

#titleImgArea {
   width: 320px;
   heigth: 220px;
}

#titleImg {
   width: 320px;
   height: 220px;
}

/* .detail td {
   width: 1000px;
   text-align: center;
   border: 1px solid black;
   padding: 0px;
   margin: auto;
} */

.detailImgArea {
   width: 320px;
   height: 220px;
   margin-left: auto;
   margin-right: auto;
}

.detailImgArea:hover {
   
}

.detailImg {
   width: 320px;
   height: 220px;
}

#photoImg:hover {
   cursor: pointer;
}
</style>
</head>
<body>
<body>

   <%@ include file="../common/menubar.jsp"%>

   <div class="page-wrap">

      <!-- Main -->
      <section id="main"> <!-- Header --> <header id="header">
      <div>
         SitGil <span>photoalbum</span>
      </div>
      </header> <!-- Gallery --> <section id="galleries"> <!-- Photo Galleries -->
      <div class="gallery">

         <!-- Filters -->
         <header>
         <h1>Gallery</h1>



         <ul class="tabs">
            <button onclick="location='<%= contextPath %>/list.ph'">뒤로가기</button>
            <button id="delete">삭제하기</button>
            <button id="move">폴더이동</button>
            <button onclick="insertFile();">추가하기</button>
         </ul>
         </header>

         <div class="content">
            
            <div id="photoImgArea">
               <img id="photoImg" width="320px" height="220px">
            </div>


            <form id="insertForm" action="<%= contextPath %>/insert.ph" method="post" enctype="multipart/form-data">
               <div id="fileArea" style="display:none;">
                  
                  <input type="file" multiple name="photoImg1" id="photoImg1" onchange="loadImg(this,1);">
                  <input type="hidden" name="fId" value="<%= fId %>">
                  
               </div>
            </form>

         <%-- <div>
            <a id="<%= fileList.get(0).getAtId() %>" href="<%= contextPath %>/resources/uploadImages/<%= fileList.get(0).getChangeName() %>"><img id="titleImg" src="<%= contextPath %>/resources/uploadImages/<%= titleImg.getChangeName() %>"></a>
         </div> --%>

            <table class="detail" align="center">
            
               <tr>
                  
                     
                     <div id="titleImgArea" align="center">
                                       
                        <input type="hidden" value="N" id="isDelete">
                        
                        <% for(int i=0; i<fileList.size(); i++){ %>
                        
                        <a id="<%= fileList.get(i).getAtId() %>" href="<%= contextPath %>/resources/uploadImages/<%= fileList.get(i).getChangeName() %>"><img title="<%= fileList.get(i).getOriginName() %>" id="<%= fileList.get(i).getAtId() %>" class="detailImg" src="<%= contextPath %>/resources/uploadImages/<%= fileList.get(i).getChangeName() %>"></a>
                        
                     </div>

                   <% } %>
                  
               </tr>

            </table>


</div>



         </div>
      </section> <!-- Contact --> 
      <section id="contact"> <!-- Social -->
      <div class="social column"></div>

      <!-- Form -->
      <div class="column"></div>

      </section> <!-- Footer -->
      <footer id="footer">
      <div class="copyright">
         &copy; Untitled Design: <a href="https://templated.co/">TEMPLATED</a>.
         Images: <a href="https://unsplash.com/">Unsplash</a>.
      </div>
      </footer> 
      </section>
   </div>

   <script>
               $(function(){
                  $("#fileArea").hide();
                  console.log(<%fileList.size();%>);
                  $("#photoImgArea").click(function(){
                     $("#photoImg1").click();
                  });
                  
               });
               

               function loadImg(value, num){
                  
                  if(value.files && value.files[0]){
                     
                     var reader = new FileReader();
                     
                     reader.onload = function(e){
                        
                        switch(num){
                        case 1:   $("#photoImg").attr("src",e.target.result);   break; // data:URL

                        }
                        
                     }
                     
                     reader.readAsDataURL(value.files[0]);
                     
                  }
               }
               
               function insertFile(){
                  $("#insertForm").submit();
                  
               }
               
               $(function(){
                  console.log($("#isDelete").val());
                  $("#delete").click(function(){
                     $("#isDelete").val("D");
                     console.log($("#isDelete").val());
                  });
                  
                  $("#move").click(function(){
                     $("#isDelete").val("M");
                     console.log($("#isDelete").val());
                  })
               
                  
                  $(".detailImg").click(function(){
                     if($("#isDelete").val() == "D"){
                        console.log($("#isDelete").val());
                           $("#isDelete").val("N");
                           var atId = $(this).attr("id");
                           var fId = <%=fId%>;
                           console.log(atId + ", fid = " + fId);
                            if(confirm("해당 사진을 삭제하시겠습니까?")) {   
                              location.href="<%=contextPath%>/deletePhoto.ph?atId="+ atId + "&fId=" + <%=fId%>;
                           }
                        }else if($("#isDelete").val() == "M"){
                           console.log($("#isDelete").val());
                           $("#isDelete").val("N");
                           var atId = $(this).attr("id");
                           var fId = <%= fId %>;                           
                              console.log(atId + ", fid = " + fId);
                              location.href="<%= contextPath %>/movePhoto.ph?atId=" + atId + "&fId=" + <%= fId %> +"&move=M";
                           
                           
                        }
                     });

      });
   </script>

</body>
</html>