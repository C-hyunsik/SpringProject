<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Details</title>
<style>
/* 전체 레이아웃 */
body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

/* 테이블 레이아웃 */
table {
    border-collapse: collapse;
    width: 60%;
    max-width: 800px;
    background-color: #fff;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    overflow: hidden;
}

td {
    padding: 15px;
    text-align: left; /* 기본적으로 왼쪽 정렬 */
    vertical-align: middle;
    border: 1px solid #ddd;
}

td img {
    max-width: 100%;
    height: auto;
    border-radius: 5px;
}

/* 상품명, 파일명 등의 텍스트 스타일 */
td {
    font-size: 16px;
    color: #333;
}

/* 이미지 셀 스타일 */
td[rowspan="3"] {
    text-align: center;
    vertical-align: middle;
}

/* 상품명과 파일명 강조 */
.label {
    font-weight: bold;
    color: #555;
}

/* 버튼 스타일 */
td[colspan="2"] {
    text-align: center;
    padding-top: 20px;
}

input[type="button"] {
    background-color: #4CAF50; /* 녹색 배경 */
    color: white; /* 흰색 텍스트 */
    border: none;
    padding: 10px 20px;
    margin: 5px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

input[type="button"]:hover {
    background-color: #45a049; /* 버튼에 마우스를 올렸을 때 더 진한 녹색 */
}

input[type="button"]:nth-child(2) {
    background-color: #f44336; /* 빨간색 배경 */
}

input[type="button"]:nth-child(2):hover {
    background-color: #e53935; /* 빨간색 버튼 hover 효과 */
}

input[type="button"]:nth-child(3) {
    background-color: #008CBA; /* 파란색 배경 */
}

input[type="button"]:nth-child(3):hover {
    background-color: #007bb5; /* 파란색 버튼 hover 효과 */
}
</style>
</head>
<body>
	<table border="1">
	    <tr>
	        <td rowspan="3">
	            <img src = "https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-145/storage/${userUploadDTO.imageFileName}"
	                 alt = "${userUploadDTO.imageName }"/>
	        </td>
	        <td>번호 : ${userUploadDTO.seq }</td>
	    </tr>
	    <tr>
	        <td>상품명 : ${userUploadDTO.imageName }</td>
	    </tr>
	    <tr>
	        <td>파일명 : ${userUploadDTO.imageOriginalFileName }</td>
	    </tr>
	    <tr>
	        <td colspan="2" height="150" style="text-align: left;">
	            <pre>${userUploadDTO.imageContent }</pre>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	            <input type="button" value="글 수정" onclick="location.href='/spring/user/uploadUpdateForm?seq=${userUploadDTO.seq }'">
	            <input type="button" id="deleteBtn" value="글 삭제">
	            <input type="button" value="목록" onclick="location.href='/spring/user/uploadList'">
	        </td>
	    </tr>
	</table>
	<input type="hidden" id="seq" value="${userUploadDTO.seq }"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#deleteBtn').click(function(){
		exist = confirm('줭뫌로 삭제하시겠습니까?');
		if(exist){
			seq = $('#seq').val();
			alert(seq);
			$.ajax({
				type:'post',
				url:'/spring/user/ncpDelete',
				data:{'seq':seq},
				success:function(){
					alert('글을 삭제하였습니다.');
					location.href='/spring/user/uploadList'
				},
				error:function(e){
					console.log(e);
				}
			});
		}
	});
});
</script>
</body>
</html>