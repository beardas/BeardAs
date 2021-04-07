<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="context"><%=request.getContextPath()%></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트2팀 게시판 프로그램</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css" rel="stylesheet" >
<link href="${context}/css/common.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="${context}/js/jquery.form.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script src="${context}/js/common.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#navbar").find("*").css({
			"color" :"black"
			});
	    $(".navbar-brand").css({
	    	"color" :"black"
	    })
	});

	function fn_checkThreeC(){
		$.ajax({
   			url:"${context}/work/three/checkThree.do",
   			success:function(result){
   				if(result == "noexist"){
					location.href = "${context}/work/three/createThree.do";
				
   				}else{
   					alert("이미 특강신청서를 제출하셨습니다");
   				}
   			}
   		});
	}
	
	function fn_checkThreeU(){
		$.ajax({
   			url:"${context}/work/three/checkThree.do",
   			success:function(result){
   				if(result == "exist"){
					location.href = "${context}/work/three/createThree.do";
				
   				}else{
   					alert("특강신청서를 작성하세요");
   				}
   			}
   		});
	}

	function fn_checkPass(){
		$.ajax({
   			url:"${context}/work/three/checkThree.do",
   			success:function(result){
   				if(result == "noexist"){
   					alert("특강신청 정보가 존재하지 않습니다.");
   				}else{
   					location.href = "${context}/work/three/retrieveResult.do";
   				}
   			}
   		});
	}
</script>


<style type="text/css">
li{
	cursor: pointer;
}
a{
	text-decoration:none !important;
}

</style>
</head>


<c:set var="homeUrl">${context}/index.jsp</c:set>
<c:set var="loginUrl">${context}/login.do</c:set>

<body>
<div class="navbar-wrapper">
      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <c:if test="${!empty user}">
            <a class="navbar-brand" href="${homeUrl}">프로젝트2팀</a>
            </c:if>
          </div>


                <div id="navbar" class="navbar-collapse collapse">

                    <ul class="nav navbar-nav">
                    <c:if test="${!empty user}">
                        <li class="dropdown">
							<a href="${context}/article/list.do"><strong>게시글 목록</strong></a>
						</li>
						<li class="dropdown">
							<a href="${context}/changePwd.do"><strong>암호 변경</strong></a>
						</li>
						<li class="dropdown">
							<a href="${context}/deleteMember.do"><strong>회원 탈퇴</strong></a>
						</li>
                    </c:if>
                    </ul>
                    <ul class="nav navbar-nav pull-right">
                    	<c:if test="${empty user}">
                    	<li class="dropdown">
							<a href="${context}/join.do"><strong>회원가입</strong></a>
						</li>
						</c:if>
 						<c:if test="${!empty user}">
						<li class="dropdown">
							<a href="${context}/update.do"><strong>나의 정보 수정</strong></a>
                       	</li>
                        </c:if>
                    	<li>
						<c:if test="${empty user}">
							<a href="${context}/login.do"><strong>LOGIN</strong></a>
						</c:if>
						<c:if test="${!empty user}">
							<a href="${context}/logout.do"><strong>LOGOUT</strong></a>
						</c:if>
						</li>
						<li> 
							<a href="${context}/index.jsp"><span class="glyphicon glyphicon-home"></span></a>
						</li>
                    </ul>
                </div>
			</div>

        </nav>
    </div>

</body>
</html>