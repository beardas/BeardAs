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

    <title>메인화면</title>

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
<jsp:include page="common/top.jsp"></jsp:include> 
<div style="text-align: center;">
프로젝트 2팀
<br>
<c:if test="${!empty user}">
	${user.name}님, 안녕하세요.
</c:if>
<br>
<img id="pic" style="margin-left: 15px;" height="480px" width="760px" src="backgroundImage/project.png"><br/>
</div>
<br>
<jsp:include page="common/foot.jsp"></jsp:include> 
</body>
</html>