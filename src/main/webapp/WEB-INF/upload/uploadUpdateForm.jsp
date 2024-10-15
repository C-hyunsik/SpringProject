<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 업데이트</title>
<link rel="stylesheet" href="../css/uploadForm.css">
</head>
<body>
	<form id="updateAJaxForm">
		<table>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" name="imageName" size="35" placeholder="상품명을 입력하세요" value="${userUploadDTO.imageName }">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="imageContent" rows="10" cols="50" placeholder="상품 설명을 입력하세요">${userUploadDTO.imageContent }</textarea>
				</td>
			</tr>
			 <!-- 한 번에 1개 또는 여러개(드래그)를 선택 => 서버에서 List로 받는다-->
			 <tr>
				<td colspan="2">
					<img src="../image/camera.png" alt="카메라" id="camera" width="50" height="50"/>
					<span id="showImageList">
					<img src = "https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-145/storage/${userUploadDTO.imageFileName}"
                		 alt = "${userUploadDTO.imageName }" width="70" height="70"/>
					</span>
					<input type="file" name="img" id="img" style="visibility:hidden"/>
				</td>
			</tr>
			<tr>
				 <td colspan="2">
					 <div  class="button-group">
						<input type="button" id="updateBtn" value="글 수정"/>
						<input type="button" id="listBtn" value="목록" onclick="location.href='/spring/user/uploadList'"/>
						<input type="button" id="resetBtn" value="취소" onclick="location.reload()"/>
					</div>
    			</td>
			</tr>
		</table>
		<input type="hidden" id="seq" name="seq" value="${userUploadDTO.seq }"/>
		<input type="hidden" id="existFile" name="existFile" value="${userUploadDTO.imageFileName}"/>
	</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/uploadUpdateForm.js"></script>
</body>
</html>