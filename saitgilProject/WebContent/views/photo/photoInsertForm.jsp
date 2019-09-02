<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<style>
.outer {
	width: 500px;
	height: 300px;
	background: white;
	color: #f7aec5;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
}

table {
	border: 1px solid white;
}

.insertArea {
	width: 500px;
	height: 250px;
	margin-left: auto;
	margin-right: auto;
}

.btnArea {
	width: 150px;
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
	font-weight: 700;
	height: 2.85em;
	line-height: 2.95em;
	text-align: center;
	text-decoration: none;
	white-space: nowrap;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">폴더 생성</h2>

		<!-- 파일 업로드를 위해 enctype을 지정해줘야한다. -->
		<form action="<%= contextPath %>/insert.ph" method="post"
			enctype="multipart/form-data">

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
							placeholder="폴더명을 입력해주세요."></td>
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
				<button class="button" type="submit">작성완료</button>
				<button class="button" type="reset">취소하기</button>
			</div>

			
		</form>
	</div>
	
	<script>
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