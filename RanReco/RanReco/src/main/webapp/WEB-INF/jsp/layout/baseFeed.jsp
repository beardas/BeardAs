<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/top.jsp"></jsp:include>
	<meta charset="UTF-8">
	<title>오늘 뭐하지? | 맛집 피드</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<section class="content">
		<tiles:insertAttribute name="headerFeed"/>
		<tiles:insertAttribute name="body"/>
	</section>
</body>
<jsp:include page="../common/foot.jsp"></jsp:include>
</html>