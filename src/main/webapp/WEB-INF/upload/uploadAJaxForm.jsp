<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 업로드</title>
<link rel="stylesheet" href="../css/uploadForm.css">
</head>
<body>
	<form id="uploadAJaxForm">
		<table>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" name="imageName" size="35" placeholder="상품명을 입력하세요">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="imageContent" rows="10" cols="50" placeholder="상품 설명을 입력하세요"></textarea>
				</td>
			</tr>
			 <!-- 한 번에 1개 또는 여러개(드래그)를 선택 => 서버에서 List로 받는다-->
			 <tr>
				<td colspan="2">
					<img src="../image/camera.png" alt="카메라" id="camera" width="50" height="50"/>
					<span id="showImageList">이미지 미리보기</span>
					<input type="file" name="img" id="img" multiple="multiple" style="visibility:hidden"/>
				</td>
			</tr>
			<tr>
				 <td colspan="2">
					 <div  class="button-group">
						<input type="button" id="uploadBtn" value="이미지 업로드"/>
						<input type="button" id="listBtn" value="이미지 목록" onclick="location.href='/spring/user/uploadList'"/>
						<input type="reset" value="취소"/>
					</div>
    			</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/uploadAJax.js"></script>
</body>
</html>
<!-- 
FileReader 란?
FileReader는 type이 file인 input 태그 또는 API 요청과 같은 인터페이스를 통해 
File 또는 Blob 객체를 편리하게 처리할수있는 방법을 제공하는 객체이며
abort, load, error와 같은 이벤트에서 발생한 프로세스를 처리하는데 주로 사용되며,
File 또는 Blob 객체를 읽어서 result 속성에 저장한다.

FileReader도 비동기로 동작한다.

FileReader.onload()
load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료되었을 때마다 발생한다.
 -->