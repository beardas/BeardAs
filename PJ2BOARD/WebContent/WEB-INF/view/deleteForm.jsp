<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 삭제</title>
</head>
<body>
<form action="delete.do" method="post">
<input type="hidden" name="no" value="${delReq.articleNumber}">
게시글을 삭제하시려면 암호를 입력하세요 :
<p>
암호 : <input type="password" name="password" value="${delReq.password}">
<c:if test="${errors.PwNotMatch}">암호가 일치하지 않습니다.</c:if>
<c:if test="${errors.password}">암호를 입력하세요.</c:if>
</p>
	<input type="submit" value="게시글 삭제하기">
	<a href="list.do">[목록으로 돌아가기]</a>
</form>
</body>
</html>