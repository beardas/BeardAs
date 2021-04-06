<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
<form action="deleteMember.do" method="post">
<c:if test="${errors.idOrPwNotMatch}">아이디 또는 비밀번호가 일치하지 않습니다.</c:if>

<p>
	아이디 : <br><input type="text" name="id" value="${param.id}">
	<c:if test="${errors.id}">아이디를 입력하세요.</c:if>
</p>

<p>
	암호 : <br><input type="password" name="password" >
	<c:if test="${errors.password}">암호를 입력하세요.</c:if>
</p>

<input type="submit" value="회원탈퇴">
<a href="index.jsp">[메인화면으로 돌아가기]</a>

</form>
</body>
</html>