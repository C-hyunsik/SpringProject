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
	<form method="post" enctype="multipart/form-data" action="/spring/user/ncpUpload">
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
			<%--
			<tr>
				<td colspan="2">
					<input type="file" name="img"/>
				</td>
			</tr>
			
			<!-- 다중 업로드할 때는 name 속성의 이름이 같아야 한다. => 서버가 배열로 받기 떄문 -->
			<tr>
				<td colspan="2">
					<input type="file" name="img"/>
				</td>
			</tr>
			 --%>
			 <!-- 한 번에 1개 또는 여러개(드래그)를 선택 => 서버에서 List로 받는다-->
			 <tr>
				<td colspan="2">
					<input type="file" name="img" multiple="multiple"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="uploadBtn" value="이미지 업로드"/>
					<input type="reset" value="취소"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>