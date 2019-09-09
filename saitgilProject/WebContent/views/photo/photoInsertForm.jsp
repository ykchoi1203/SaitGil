<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<% 
	Member loginUser = (Member)session.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
<title>Insert title here</title>


<style>
.outer {
	width: 510px;
	height: 450px;
	background: white;
	color: #f7aec5;
	margin-top:30px;
}

table {
	border: 1px solid white;
}

input {
	border-radius:4px;
	color:inherit;
	padding: 0 1em;
	height:2.5em;
	width: 330px;
	border: solid 1px white;
	background:#fde8ee;
	margin-button:30px;
}



.insertArea {
	width: 500px;
	height: 250px;
	margin-left: auto;
	margin-right: auto;
}

.btnArea {
	width: 200px;
	margin-left: auto;
	margin-right: auto;

}

#thumbnailImgArea {
	width: 350px;
	height: 200px;
	display: table-cell;

}

#thumbnailImgArea:hover {
	cursor: pointer;
}

.button {
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

.button:hover{
	background: #e23a6e;
}

body{
	font-family:Noto Serif KR;
	background:#f7aec5;
	width:180px;
	height:100px;
	margin:0;

}

h2{
		font-size:30px;
		font-weight:900;
		color:#e23a6e ;
		font-family: 'Noto Serif KR', serif;
		margin-block-start: 0.70em;

}
</style>
</head>
<body>

	<div class="outer">
	<br>
		<h2 align="center">폴더 생성</h2>

		<!-- 파일 업로드를 위해 enctype을 지정해줘야한다. -->
		<form action="<%= request.getContextPath() %>/fInsert.ph" method="post"
			enctype="multipart/form-data" id="folderForm">

			<div class="insertArea">
				<table align="center">

					<tr>
						<td></td>
						<td colspan="3">

							<div id="thumbnailImgArea">
								<img id="thumbnailImg" width="350px" height="200px">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3"><input type="text" size="52" name="title"  id="title"
							placeholder="폴더명을 입력해주세요.">
						</td>

					</tr>


				</table>

				<!-- 파일 업로드 하는 div -->
				<div id="fileArea">
					<input type="file" multiple name="thumbnailImg1" id="thumbnailImg1"
						onchange="loadImg(this,1);">
				</div>


			</div>


			<br>

			<div class="btnArea">
				<button class="button" type="button" onclick="done();">작성완료</button>
				<button class="button" type="reset">취소하기</button>
			</div>

			
		</form>
	</div>
	
	<script>
	


		function done() {
			
			if($('#title').val() == "") {
				alert('폴더 이름을 입력해주세요 ');
				$('#title').focus();
				return;
			} else {
				$('#folderForm').submit();
				alert("폴더 생성이 완료되었습니다.");
				window.close();
			}
		}
	
					// 미리보기 이미지 영역을 클릭할 때 파일 첨부 창이 뜨도록!
					$(function(){
						$("#fileArea").hide();
						
						$("#thumbnailImgArea").click(function(){
							$("#thumbnailImg1").click();
						});
						
					});
					
					
					function loadImg(value, num){
						
						if(value.files && value.files[0]){
							
							var reader = new FileReader();
							
							reader.onload = function(e){
								
								switch(num){
								case 1:	$("#thumbnailImg").attr("src",e.target.result);	break; // data:URL

								}
								
							}
							
							reader.readAsDataURL(value.files[0]);
							
						}
					}
				</script>
	
</body>
</html>