<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
</head>
<body>
<form action="list.do" method="post">
<table border="1">

	<tr>
		<td colspan="6"><a href="write.do">[게시글쓰기]</a></td>
	</tr>
	
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
		<td>좋아요</td>
		<td>추천</td>
	</tr>
	
<c:if test="${articlePage.hasNoArticles()}">
	<tr>
		<td colspan="6">게시글이 없습니다.</td>
	</tr>
</c:if>
	
<c:forEach var="article" items="${articlePage.content}">
	<tr>
		<td>${article.number}</td>
		<td><a href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
		<c:out value="${article.title}" /></a></td>
		<td>${article.writer.name}</td>
		<td>${article.readCount}</td>
		<td>${article.likeCount}</td>
		<td>
		<c:if test="${!empty user}">
			<c:if test="${!LikeArticleService.likes}"><input type="submit" value="좋아요" name="true"></c:if>
			<c:if test="${LikeArticleService.likes}"><input type="submit" value="취소" name="false"></c:if>
		</c:if>
		<c:if test="${empty user}">
			<a href="../login.do">[로그인]</a>
		</c:if>
		</td>
	</tr>
</c:forEach>

<c:if test="${articlePage.hasArticles()}">
	<tr>
		<td colspan="6">
		
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
<a href="../index.jsp">[메인화면으로 돌아가기]</a>
</form>
</body>
</html>