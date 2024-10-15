<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../css/writeForm.css">
</head>
<body>
	<div id="imgWrap">
	<a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg"/></a>
	</div>
	<form id="userWriteForm">
		<div id="idWrap">
			<label for="id">아이디</label>
			<input type="text" id="id" name="id"/>
			<div id="idDiv"></div>
		</div>
		<div id="nameWrap">
			<label for="name">이름</label>
			<input type="text" id="name" name="name"/>
			<div id="nameDiv"></div>
		</div>
		<div id="pwdWrap">
			<label for="pwd">비밀번호</label>
			<input type="password" id="pwd" name="pwd"/>
			<div id="pwdDiv"></div>
		</div>
		<div>
			<input type="button" id="writeBtn" value="회원가입">
			<input type="reset" value="초기화">
		</div>
	</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript"src="../js/writeForm.js"></script>
</body>
</html>
