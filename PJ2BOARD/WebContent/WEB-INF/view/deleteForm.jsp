<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="login.jsp">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="">

    <title>게시글 삭제</title>

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
<form action="delete.do" method="post">
<div style="text-align: center;">
<input type="hidden" name="no" value="${delReq.articleNumber}">
게시글을 삭제하시려면 암호를 입력하세요 :
<p>
암호 : <br><input type="password" name="password" value="${delReq.password}"><br>
<c:if test="${errors.PwNotMatch}">암호가 일치하지 않습니다.</c:if>
<c:if test="${errors.password}">암호를 입력하세요.</c:if>
</p>
	<input type="submit" value="게시글 삭제하기">
	<a href="list.do">[목록으로 돌아가기]</a>
</div>
</form>
<jsp:include page="../../common/foot.jsp"></jsp:include> 
</body>
</html>