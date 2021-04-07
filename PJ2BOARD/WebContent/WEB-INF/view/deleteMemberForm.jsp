<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="login.jsp">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="">

    <title>회원 탈퇴</title>

    <link href="${context}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="${context}/css/sb-admin-2.css" rel="stylesheet">
    <link href="${context}/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- jQuery -->
    <script src="${context}/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${context}/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${context}/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${context}/js/sb-admin-2.js"></script>
<meta charset="UTF-8">
</head>
<body>
<jsp:include page="../../common/top.jsp"></jsp:include>
<form action="deleteMember.do" method="post">
<div style="text-align: center;">
<c:if test="${errors.idOrPwNotMatch}">아이디 또는 비밀번호가 일치하지 않습니다.</c:if>

<p>
	아이디 : <br><input type="text" name="id" value="${param.id}">
	<br>
	<c:if test="${errors.id}">아이디를 입력하세요.</c:if>
</p>

<p>
	암호 : <br><input type="password" name="password" >
	<br>
	<c:if test="${errors.password}">암호를 입력하세요.</c:if>
</p>

<input type="submit" value="회원탈퇴">
</div>
</form>
<jsp:include page="../../common/foot.jsp"></jsp:include> 
</body>
</html>