<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="login.jsp">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="">

    <title>게시글 목록</title>

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
<form action="list.do" method="post">

<table border="1" align="center">

	<tr>
		<td colspan="4"><a href="write.do">[게시글쓰기]</a></td>
	</tr>
	
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
	</tr>
	
<c:if test="${articlePage.hasNoArticles()}">
	<tr>
		<td colspan="4">게시글이 없습니다.</td>
	</tr>
</c:if>
	
<c:forEach var="article" items="${articlePage.content}">
	<tr>
		<td>${article.number}</td>
		<td><a href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
		<c:out value="${article.title}" /></a></td>
		<td>${article.writer.name}</td>
		<td>${article.readCount}</td>
	</tr>
</c:forEach>

<c:if test="${articlePage.hasArticles()}">
	<tr>
		<td colspan="4">
		
		<c:if test="${articlePage.startPage > 5}">
		<a href="list.do?pageNo=${articlePage.startPage - 5}">[이전]</a>
		</c:if>
		
		<c:forEach var="pNo" begin="${articlePage.startPage}" end="${articlePage.endPage}">
		<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
		</c:forEach>
		
		<c:if test="${articlePage.endPage < articlePage.totalPages}">
		<a href="list.do?pageNo=${articlePage.endPage + 5}">[다음]</a>
		</c:if>
		
		</td>
	</tr>
</c:if>
</table>
</div>
</form>
<jsp:include page="../../common/foot.jsp"></jsp:include> 
</body>
</html>