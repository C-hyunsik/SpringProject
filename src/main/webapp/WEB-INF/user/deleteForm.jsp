<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/updateForm.css">
</head>
<body>
	<div id="imgWrap">
	<a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg"/></a>
	</div>
	<form id="userUpdateForm">
		<h2>삭제</h2>
		<input type="hidden" id="pg" value="${param.pg}">
		<div id="idWrap">
			<label for="id">아이디</label>
			<input type="text" id="id" name="id" value="${list[0].id }" readonly="readonly"/>
			<div id="idDiv"></div>
		</div>
		<div id="nameWrap">
			<label for="name">이름</label>
			<input type="text" id="name" name="name" value="${list[0].name }" readonly="readonly"/>
			<div id="nameDiv"></div>
		</div>
		<div id="pwdWrap">
			<label for="pwd">비밀번호</label>
			<input type="hidden" id="checkPwd" name="checkPwd" value="${list[0].pwd }">
			<input type="text" id="pwd" name="pwd"/>
			<div id="pwdDiv"></div>
		</div>
		<div>
			<input type="button" id="listBtn" value="목록" onclick="location.href='/spring/user/listForm?pg=${param.pg}'">
			<input type="button" id="deleteBtn" value="삭제하기" onclick="location.href=/spring/user/deleteForm?id=${list[0].id }">
			<input type="button" id="backBtn" value="뒤로가기" onclick="history.go(-1)"/>
		</div>
	</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/delete.js"></script>
</body>
</html>