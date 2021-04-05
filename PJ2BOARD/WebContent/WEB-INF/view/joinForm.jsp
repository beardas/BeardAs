<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<form action="join.do" method="post">
<p>
	아이디 : <input type="text" name="id" value="${param.id}">
	<c:if test="${errors.id}">아이디를 입력하세요.</c:if>
	<c:if test="${errors.duplicateId}">중복된 아이디입니다.</c:if>
</p>
<p>
	이름 : <input type="text" name="name" value="${param.name}">
	<c:if test="${errors.name}">이름을 입력하세요.</c:if>
</p>
<p>
	암호 : <input type="password" name="password" value="${param.password}">
	<c:if test="${errors.password}">암호를 입력하세요.</c:if>
</p>
<p>
	확인 : <input type="password" name="password2" value="${param.password2}">
	<c:if test="${errors.password2}">암호를 입력하세요.</c:if>
	<c:if test="${errors.pwNotMatch}">암호가 일치하지 않습니다.</c:if>
</p>

<input type="submit" value="회원가입">
<a href="../index.jsp">메인화면으로 돌아가기</a>

</form>
</body>
</html>