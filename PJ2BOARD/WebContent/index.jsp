<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 게시판</title>
</head>
<body>

<c:if test="${empty user}">
	<a href="join.do">[회원가입하기]</a>
	<a href="login.do">[로그인하기]</a>
</c:if>

<c:if test="${!empty user}">
	${user.name}님, 안녕하세요.
	<a href="logout.do">[로그아웃하기]</a>
	<a href="article/list.do">[게시판목록]</a>
	<a href="changePwd.do">[암호변경하기]</a>
</c:if>

<br>

</body>
</html>