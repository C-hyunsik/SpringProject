<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Uploaded Images</title>
<style>
/* 레이아웃 및 스타일 */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}
.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}
h1 {
    text-align: center;
    margin-bottom: 40px;
    color: #333;
}
.gallery {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 20px;
}
.gallery-item {
    position: relative; /* 상대 위치 지정 */
    background-color: #fff;
    padding: 15px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    transition: transform 0.3s ease;
    text-align: center;
}
.gallery-item:hover {
    transform: scale(1.05);
}
.gallery-item img {
    max-width: 100%;
    height: auto;
    border-radius: 8px;
    margin: 0 auto;
}
.image-info {
    margin-top: 10px;
    text-align: center;
}
.image-info h3 {
    font-size: 18px;
    color: #555;
    margin-bottom: 5px;
}
.image-info p {
    font-size: 14px;
    color: #777;
}
.button-container {
    margin-top: 40px;
    text-align: center;
}
.button-container input[type="button"] {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}
.button-container input[type="button"]:hover {
    background-color: #45a049;
}
.checkbox-container {
    text-align: center;
    margin-bottom: 20px;
}
.checkbox-container input[type="checkbox"] {
    margin-right: 10px;
}
.seq_check {
    position: absolute; /* 절대 위치 지정 */
    top: 10px; /* 상단 여백 */
    left: 10px; /* 왼쪽 여백 */
    width: 20px; /* 체크박스 크기 */
    height: 20px; /* 체크박스 크기 */
    cursor: pointer; /* 포인터로 변경 */
}
#deleteBtn{
    background-color: #f44336; /* 빨간색 배경 */
}

#deleteBtn:hover {
    background-color: #e53935; /* 빨간색 버튼 hover 효과 */
}
</style>
</head>
<body>
    <div class="container">
        <h1>이미지 목록</h1>
        
        <div class="checkbox-container">
            <input type="checkbox" id="all_check"/>
            <label for="all_check">전체 선택</label>
        </div>

        <div class="gallery">
            <!-- DB에서 가져온 이미지 리스트를 순회하며 출력 -->
            <c:forEach var="image" items="${list}">
                <div class="gallery-item">
                    <input type="checkbox" class="seq_check" value="${image.seq}">
                    <a href="/spring/user/uploadView?seq=${image.seq }">
                    	<img src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-145/storage/${image.imageFileName}" 
                    	alt="${image.imageName}" width="160" height="200">
                    </a>
                    <div class="image-info">
                        <!-- 이미지 이름과 설명 -->
                        <h3>${image.imageName}</h3>
                        <p>${image.imageContent}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="button-container">
            <input type="button" value="사진등록" onclick="location.href='/spring/user/uploadAJaxForm'"/>
            <input type="button" value="메인으로" onclick="location.href='/spring/'"/>
            <input type="button" id="deleteBtn" value="삭제"/>
        </div>
    </div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#all_check').change(function(){
		let isChk = $(this).is(':checked')
		//$('.hobby_check').attr('checked',isChk)
		$('.seq_check').prop('checked',isChk)
	});
	
	$('.seq_check').click(function(){
		let checkVal = $('input[class = "seq_check"]:checked').length;
		let totalCheck = $('input[class = "seq_check"]').length
		if(checkVal == totalCheck)
			$('#all_check').prop('checked',true);
		else
			$('#all_check').prop('checked',false);
	});
	$('#deleteBtn').click(function(){
		// 체크된 seq 값을 수집
        let selectedSeqs = [];
        $('input[class=seq_check]:checked').each(function() {
            selectedSeqs.push($(this).val());
        });

        // 삭제할 seq 값이 없으면 알림
        if (selectedSeqs.length === 0) {
            alert('삭제할 항목을 선택하세요.');
            return;
        }

        // AJAX 요청으로 삭제
        $.ajax({
            type: 'post',
            url: '/spring/user/checkDelete',
            data: {'selectedSeqs[]':selectedSeqs }, // seq 값을 콤마로 연결하여 전송
            dataType: 'text',
            success: function(data) {
                let result = data.trim();
                alert(result + '개의 항목을 삭제하였습니다.');
                location.href = "/spring/user/uploadList";
            },
            error: function(e) {
                console.log(e);
            }
        });
	});
});
</script>
</body>
</html>