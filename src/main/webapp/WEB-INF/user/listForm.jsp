<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
}
#imgWrap {
    margin-bottom: 20px;
}
table {
    width: 800px;
    border-collapse: collapse;
    margin: 0 auto;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
th, td {
    padding: 15px;
    border-bottom: 1px solid #ddd;
    text-align: center;
}
th {
    background-color: #4CAF50;
    color: white;
}
tr:hover {
    background-color: #f1f1f1;
}
#pagingWrap {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}
#pagingWrap #paging, #pagingWrap #currentPaging {
    margin: 0 5px;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
}
#pagingWrap #paging {
    background-color: #4CAF50;
    color: white;
}
#pagingWrap #paging:hover {
    background-color: #45a049;
    transform: scale(1.05);
}
#pagingWrap #currentPaging {
    background-color: #FFC107;
    color: white;
}
#movepaging {
    font-weight: bold;
}
a:link, a:visited, a:active, a:hover {
    color: black;
    text-decoration: none;
}
</style>
</head>
<body>
    <div class="container">
        <div id="imgWrap">
            <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg"/></a>
        </div>
        <table>
            <tr>
                <th>이름</th>
                <th>아이디</th>
                <th>비밀번호</th>
            </tr>
            <c:forEach var="userDTO" items="${map2.list}">
                <tr>
                    <td>${userDTO.name}</td>
                    <td><a href="/spring/user/updateForm?id=${userDTO.id}&pg=${map2.pg}">${userDTO.id}</a></td>
                    <td>${userDTO.pwd}</td>
                </tr>
            </c:forEach>
        </table>
        <div id="pagingWrap">
            ${map2.userPaging.pagingHTML }
        </div>
    </div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
function userPaging(pg){
    location.href = '/spring/user/listForm?pg=' + pg;
}
</script>
</body>
</html>