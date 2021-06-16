<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="context"><%=request.getContextPath()%></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오늘 뭐하지?</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script src='{% static "js/jquery-1.11.3.min.js" %}'></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="https://ddo7jzca0m2vt.cloudfront.net/unify/css/custom.css?version=20210111">




<script>

var now = new Date();
var hours = now.getHours();


$(function(){
   rain = $('#rain').val();
   sky = $('#sky').val();
   
   switch (rain) {
   case "비":
      $('#rain_img').attr('src','/images/weather/rainy.png');
      $('#rain_img_pc').attr('src','/images/weather/rainy.png');
      break;
   case "비/눈":
      $('#rain_img').attr('src','/images/weather/sleety.png');
      $('#rain_img_pc').attr('src','/images/weather/sleety.png');
      break;
   case "눈":
      $('#rain_img').attr('src','/images/weather/snowy.png');
      $('#rain_img_pc').attr('src','/images/weather/snowy.png');
      break;
   case "소나기":
      $('#rain_img').attr('src','/images/weather/shower.png');
      $('#rain_img_pc').attr('src','/images/weather/shower.png');
      break;
   default:
      $('#rain_img').attr('src','');
      $('#rain_img_pc').attr('src','');
      break;
   }
   
   if($('#rain_img').attr('src') == ""){
      
      if(hours > 6 && hours < 20) {
         switch (sky) {
         case "맑음":
            $('#sky_img').attr('src','/images/weather/sunny_day.png');
            $('#sky_img_pc').attr('src','/images/weather/sunny_day.png');
            break;
         case "구름 조금":
            $('#sky_img').attr('src','/images/weather/little_day.png');
            $('#sky_img_pc').attr('src','/images/weather/little_day.png');
            break;
         case "구름 많음":
            $('#sky_img').attr('src','/images/weather/many_day.png');
            $('#sky_img_pc').attr('src','/images/weather/many_day.png');
            break;
         case "흐림":
            $('#sky_img').attr('src','/images/weather/cloudy.png');
            $('#sky_img_pc').attr('src','/images/weather/cloudy.png');
            break;
         default:
            $('#sky_img').attr('src','');
            $('#sky_img_pc').attr('src','');
            break;
         }
      } else {
         switch (sky) {
         case "맑음":
            $('#sky_img').attr('src','/images/weather/sunny_night.png');
            $('#sky_img_pc').attr('src','/images/weather/sunny_night.png');
            break;
         case "구름 조금":
            $('#sky_img').attr('src','/images/weather/little_night.png');
            $('#sky_img_pc').attr('src','/images/weather/little_night.png');
            break;
         case "구름 많음":
            $('#sky_img').attr('src','/images/weather/many_night.png');
            $('#sky_img_pc').attr('src','/images/weather/many_night.png');
            break;
         case "흐림":
            $('#sky_img').attr('src','/images/weather/cloudy.png');
            $('#sky_img_pc').attr('src','/images/weather/cloudy.png');
            break;
         default:
            $('#sky_img').attr('src','');
            $('#sky_img_pc').attr('src','');
            break;
         }
      }
   }
});


$(window).resize(function() {

   var width=window.innerWidth;

   if (width < 767){ // mobile모드
      $('#icon_brand').attr('style','top: 0px; height: 33px; width:  35px; display:inline; margin-top: -5% ')
      
   } else { // pc모드
      $('#icon_brand').attr('style','top: 0px; height: 33px; width:  35px; display:block;')
   }

}).resize();
</script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://ddo7jzca0m2vt.cloudfront.net/unify/css/style.css?version=20210111">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,700|Open+Sans:400,400i,700,700i|Source+Code+Pro&amp;subset=korean" rel="stylesheet">


<style type="text/css">
li{
   cursor: pointer;
}
a{
   text-decoration:none !important;
}
body, input, button, select, textarea, h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, .purchase span {
    font-family: 'Open Sans', 'Apple SD Gothic Neo', 'Noto Sans CJK KR', 'Noto Sans KR', '나눔바른고딕', '나눔고딕', '맑은고딕', 'Helvetica Neue', 'Helvetica', 'Arial', sans-serif !important;
}

.navbar-default {
    color: #333;
    font-size: 13px;
    line-height: 1.6;
    
}

@media (min-width: 768px) {
 .navbar-header {float: left;}
 #weather_mobile {display: none;}
}
@media (max-width: 767px) {
 .navbar-header {float: left;}
 #weather_pc {display: none;}
}
 



</style>
</head>



<body>
<c:set var="admin" value="관리자" />
<c:set var="provider" value="Kakao" />
<div class="header no-print">
<div class="topbar">
<div class="container">
<ul class="loginbar pull-right">
<c:if test="${null eq userName}">
<li><a href="fwdMemberReg.do">회원가입</a></li>
<li class="topbar-devider"></li><li>
<a href="login.do">로그인</a>
</c:if>
<c:if test="${null ne userName}">
<c:if test="${provider eq userProvider}">
<a href="kakaoLogout.do">로그아웃</a>
</c:if>
<c:if test="${provider ne userProvider}">
<a href="logout.do">로그아웃</a>
</c:if>
</c:if>
</ul></div></div>

<div class="navbar navbar-default mega-menu"  role="navigation">
   <div class="container">
   <div class="navbar-header">
   <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
   <span class="sr-only">Toggle navigation</span><span class="fa fa-bars"></span></button>
   <a class="navbar-brand" href="/"><img id="logo-header" src="/images/로고.png" alt="Logo" data-retina=""></a>
   <input type="hidden" id="rain" value="${resultRain}">
   <input type="hidden" id="sky" value="${resultSky}">
   <span id="weather_mobile">
   <c:if test="${resultLand eq userLoc}">
   <p id="weather_text" style="font-size: 13px; text-align: center;"><br>${resultLand} ${resultTem}℃ ${resultSky} ${resultRain}
   <img id="rain_img" width="25px;" src="">
   <img id="sky_img" width="25px;" src=""></p>
   </c:if>
   <c:if test="${resultLand ne userLoc}">
   <p id="weather_text" style="font-size: 13px; text-align: center;"><br>서울 ${resultTem}℃  ${resultSky} ${resultRain}
   <img id="rain_img" width="25px;" src="">
   <img id="sky_img" width="25px;" src=""></p>
   </c:if>
   </span>
      </div>
   <div class="collapse navbar-collapse navbar-responsive-collapse">
   <ul class="nav navbar-nav">
   <li class="dropdown mega-menu-fullwidth"><a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">오늘 뭐하지?</a>
   <ul class="dropdown-menu" style="margin-top: -20px;">
   <li>
   <div class="mega-menu-content" >
   <div class="container" >
   <div class="row equal-height">
   <div class="col-md-3 equal-height-in">
   <ul class="list-unstyled equal-height-list" >
   <li><h3>오늘 뭐하지?</h3></li>
      <li><a href="selectLocation.do">오늘 어디가지?</a></li>
      <li><a href="selectFoodList.do">오늘 뭐먹지?</a></li>
      <li><a href="selectFashionList.do">오늘 뭐입지?</a></li>
      </ul>
      </div>
   <div class="col-md-3 equal-height-in">
   <ul class="list-unstyled equal-height-list">
   <li><h3>명예의 전당</h3></li>
      <li><a href="selectBoardList.do">top3</a></li>
      <li><a href="fwdBoardReg.do">나도 자랑하기</a></li>
      </ul></div>
   <div class="col-md-3 equal-height-in"><ul class="list-unstyled equal-height-list">
   <li><h3>공지사항</h3></li>
      <li><a href="selectNoticeList.do">공지사항</a></li></ul></div>
   <div class="col-md-3 equal-height-in">
   <c:if test="${null ne userName}">
   <ul class="list-unstyled equal-height-list">
   <li><h3>마이페이지</h3></li>
      <li><a href="selectMyResult.do?idx=${userIdx}/${today}">오늘 뭐하지 결과</a></li>
      <li><a href="fwdMemberUpt.do?idx=${userIdx}">정보 수정</a></li>
      </ul>
   </c:if>
   <c:if test="${null eq userName}">
   <ul class="list-unstyled equal-height-list">
      <li><h3>회원가입</h3></li>   
      <li><a href="fwdMemberReg.do">회원가입</a></li>
   </ul>
   </c:if>
      </div></div></div></div></li></ul></li>
   <li><a href="selectFashionList.do">오늘 뭐입지</a></li>
   <li><a href="selectLocation.do">오늘 어디가지</a></li>
   <li><a href="selectFoodList.do">오늘 뭐먹지</a></li>
   <li><a href="selectNoticeList.do">공지사항</a></li>
   <c:if test="${null ne userName}">
   <li><a href="selectMyResult.do?idx=${userIdx}/${today}">오늘 뭐하지 결과</a></li>
   </c:if>
   <li><a href="/"><img width="25px;" src="data:image/svg+xml;base64,PHN2ZyBpZD0iQ2FwYV8xIiBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCA1MTIgNTEyIiBoZWlnaHQ9IjUxMiIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHdpZHRoPSI1MTIiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGc+PGc+PHBhdGggZD0ibTQ1OC42MSA1MDQuNWgtNTMuNTFsLTE0Ni40Mi00Ny44NTYtMTUxLjc4IDQ3Ljg1NmgtNTMuNTFjLTI1LjM0NCAwLTQ1Ljg5LTIwLjU0NS00NS44OS00NS44OXYtNTMuNTFsNjMuMDUzLTE1MC44ODUtNjMuMDUzLTE0Ny4zMTV2LTUzLjUxYzAtMjUuMzQ1IDIwLjU0NS00NS44OSA0NS44OS00NS44OWg1My41MWwxNTUuNzcgNDQuNzc1IDE0Mi40My00NC43NzVoNTMuNTFjMjUuMzQ0IDAgNDUuODkgMjAuNTQ1IDQ1Ljg5IDQ1Ljg5djUzLjUxbC00NC45NjUgMTUwLjIyIDQ0Ljk2NSAxNDcuOTh2NTMuNTFjMCAyNS4zNDUtMjAuNTQ1IDQ1Ljg5LTQ1Ljg5IDQ1Ljg5eiIgZmlsbD0iI2Y2ZjlmOSIvPjxwYXRoIGQ9Im00MDUuMSAyMDYuMyA1NC40MzUtMjMuNjY5IDQ0Ljk2NSAyMy42Njl2OTkuNGwtNDcuNjI2IDI3LjIzOS01MS43NzQtMjcuMjM5LTI2LjcwNS01Mi45MzN6IiBmaWxsPSIjOTRhZmU1Ii8+PHBhdGggZD0ibTQwNS4xIDEwNi45aDk5LjR2OTkuNGgtOTkuNGwtMjYuNzA1LTUyLjkzM3oiIGZpbGw9IiNhZGM5ZmEiLz48cGF0aCBkPSJtNDA1LjEgMzA1LjdoOTkuNHY5OS40aC05OS40bC0yNi43MDUtNTIuOTMzeiIgZmlsbD0iI2FkYzlmYSIvPjxwYXRoIGQ9Im0xMDYuOSAzMDUuNy01NC40MzUgMjMuNjY5LTQ0Ljk2NS0yMy42Njl2LTk5LjRsNDcuNjI2LTI3LjIzOSA1MS43NzQgMjcuMjM5IDI2LjcwNSA1Mi45MzN6IiBmaWxsPSIjYmFhYmViIi8+PHBhdGggZD0ibTEwNi45IDQwNS4xaC05OS40di05OS40aDk5LjRsMjYuNzA1IDUyLjkzM3oiIGZpbGw9IiNjY2JhZWIiLz48cGF0aCBkPSJtMTA2LjkgMjA2LjNoLTk5LjR2LTk5LjRoOTkuNGwyNi43MDUgNTIuOTMzeiIgZmlsbD0iI2NjYmFlYiIvPjxwYXRoIGQ9Im0zMDUuNyA0MDUuMSAyMy42NjkgNTQuNDM1LTIzLjY2OSA0NC45NjVoLTk5LjRsLTI3LjIzOS00Ny42MjYgMjcuMjM5LTUxLjc3NCA1Mi45MzMtMjYuNzA1eiIgZmlsbD0iI2M4NjQ2ZCIvPjxwYXRoIGQ9Im00MDUuMSA0MDUuMXY5OS40aC05OS40di05OS40bDUyLjkzMy0yNi43MDV6IiBmaWxsPSIjZTI3ZjgzIi8+PHBhdGggZD0ibTIwNi4zIDQwNS4xdjk5LjRoLTk5LjR2LTk5LjRsNTIuOTMzLTI2LjcwNXoiIGZpbGw9IiNlMjdmODMiLz48cGF0aCBkPSJtMjA2LjMgMTA2LjktMjMuNjY5LTU0LjQzNSAyMy42NjktNDQuOTY1aDk5LjRsMjcuMjM5IDQ3LjYyNi0yNy4yMzkgNTEuNzc0LTUyLjkzMyAyNi43MDV6IiBmaWxsPSIjZjFjZDZiIi8+PHBhdGggZD0ibTEwNi45IDEwNi45di05OS40aDk5LjR2OTkuNGwtNTIuOTMzIDI2LjcwNXoiIGZpbGw9IiNmNWRkODEiLz48cGF0aCBkPSJtMzA1LjcgMTA2Ljl2LTk5LjRoOTkuNHY5OS40bC01Mi45MzMgMjYuNzA1eiIgZmlsbD0iI2Y1ZGQ4MSIvPjxwYXRoIGQ9Im0xMDYuOSAxMDYuOWgyOTguMnYyOTguMmgtMjk4LjJ6IiBmaWxsPSIjYmZlMTlmIiB0cmFuc2Zvcm09Im1hdHJpeCgwIDEgLTEgMCA1MTIgMCkiLz48Zz48Zz48cGF0aCBkPSJtMjIzLjI1IDI1Ni4xMDRoLTUwLjg4OWMtMTMuNDk2IDAtMjQuNDM3LTEwLjk0MS0yNC40MzctMjQuNDM3di01MC44ODljMC0xMy40OTYgMTAuOTQxLTI0LjQzNyAyNC40MzctMjQuNDM3aDUwLjg4OWMxMy40OTYgMCAyNC40MzcgMTAuOTQxIDI0LjQzNyAyNC40Mzd2NTAuODg5Yy0uMDAxIDEzLjQ5Ny0xMC45NDEgMjQuNDM3LTI0LjQzNyAyNC40Mzd6IiBmaWxsPSIjZjZmOWY5Ii8+PHBhdGggZD0ibTE4Ny42NTQgMjU2LjEwOWgtMTUuM2MtMTMuNDkgMC0yNC40My0xMC45NC0yNC40My0yNC40NHYtNTAuODljMC0xMy40OSAxMC45NC0yNC40MyAyNC40My0yNC40M2gxNS4zYy0xMy41IDAtMjQuNDQgMTAuOTQtMjQuNDQgMjQuNDN2NTAuODljMCAxMy41IDEwLjk0IDI0LjQ0IDI0LjQ0IDI0LjQ0eiIgZmlsbD0iI2RkZGFlYyIvPjwvZz48Zz48cGF0aCBkPSJtMzM5LjYzOSAzNTUuNjU0aC01MC44ODljLTEzLjQ5NiAwLTI0LjQzNy0xMC45NDEtMjQuNDM3LTI0LjQzN3YtNTAuODg5YzAtMTMuNDk2IDEwLjk0MS0yNC40MzcgMjQuNDM3LTI0LjQzN2g1MC44ODljMTMuNDk2IDAgMjQuNDM3IDEwLjk0MSAyNC40MzcgMjQuNDM3djUwLjg4OWMwIDEzLjQ5Ni0xMC45NDEgMjQuNDM3LTI0LjQzNyAyNC40Mzd6IiBmaWxsPSIjZjZmOWY5Ii8+PHBhdGggZD0ibTMwNC4wNDQgMzU1LjY1OGgtMTUuM2MtMTMuNDkgMC0yNC40My0xMC45NC0yNC40My0yNC40NHYtNTAuODljMC0xMy40OSAxMC45NC0yNC40MyAyNC40My0yNC40M2gxNS4zYy0xMy41IDAtMjQuNDQgMTAuOTQtMjQuNDQgMjQuNDN2NTAuODljMCAxMy41IDEwLjk0IDI0LjQ0IDI0LjQ0IDI0LjQ0eiIgZmlsbD0iI2RkZGFlYyIvPjwvZz48L2c+PC9nPjxnPjxwYXRoIGQ9Im00NTguNjEgMGgtNDA1LjIyYy0yOS40MzkgMC01My4zOSAyMy45NTEtNTMuMzkgNTMuMzl2NDA1LjIyYzAgMjkuNDM5IDIzLjk1MSA1My4zOSA1My4zOSA1My4zOWg0MDUuMjJjMjkuNDM5IDAgNTMuMzktMjMuOTUxIDUzLjM5LTUzLjM5di03Ny40MmMwLTQuMTQyLTMuMzU4LTcuNS03LjUtNy41cy03LjUgMy4zNTgtNy41IDcuNXYxNi40MWgtODQuNHYtODQuNGg4NC40djM3Ljk5YzAgNC4xNDIgMy4zNTggNy41IDcuNSA3LjVzNy41LTMuMzU4IDcuNS03LjV2LTI5Ny44YzAtMjkuNDM5LTIzLjk1MS01My4zOS01My4zOS01My4zOXptLTI0NC44MSA5OS40di04NC40aDg0LjR2ODQuNHptLTk5LjQgMHYtODQuNGg4NC40djg0LjR6bTE4My44IDMxMy4ydjg0LjRoLTg0LjR2LTg0LjR6bTk5LjQgMHY4NC40aC04NC40di04NC40em0tMTk4LjggODQuNGgtODQuNHYtODQuNGg4NC40em0tMTgzLjgtOTkuNHYtODQuNGg4NC40djg0LjR6bTg0LjQtMTk4LjhoLTIzLjkxYy00LjE0MiAwLTcuNSAzLjM1OC03LjUgNy41czMuMzU4IDcuNSA3LjUgNy41aDIzLjkxdjg0LjRoLTg0LjR2LTg0LjRoMzAuNDljNC4xNDIgMCA3LjUtMy4zNTggNy41LTcuNXMtMy4zNTgtNy41LTcuNS03LjVoLTMwLjQ5di04NC40aDg0LjR6bTIxMy44LTE4My44aDg0LjR2ODQuNGgtODQuNHptOTkuNCA5OS40aDg0LjR2ODQuNGgtODQuNHptODQuNC02MS4wMXY0Ni4wMWgtODQuNHYtODQuNGg0Ni4wMWMyMS4xNjggMCAzOC4zOSAxNy4yMjIgMzguMzkgMzguMzl6bS00NDMuNjEtMzguMzloNDYuMDF2ODQuNGgtODQuNHYtNDYuMDFjMC0yMS4xNjggMTcuMjIyLTM4LjM5IDM4LjM5LTM4LjM5em0tMzguMzkgNDQzLjYxdi00Ni4wMWg4NC40djg0LjRoLTQ2LjAxYy0yMS4xNjggMC0zOC4zOS0xNy4yMjItMzguMzktMzguMzl6bTQ4MiAwYzAgMjEuMTY4LTE3LjIyMiAzOC4zOS0zOC4zOSAzOC4zOWgtNDYuMDF2LTg0LjRoODQuNHptLTM4Mi42LTYxLjAxdi0yODMuMmgyODMuMnYyODMuMnptMjk4LjItOTkuNHYtODQuNGg4NC40djg0LjR6Ii8+PHBhdGggZD0ibTIyMy4yNSAxNDguODQyaC01MC44ODljLTE3LjYxIDAtMzEuOTM3IDE0LjMyNy0zMS45MzcgMzEuOTM3djUwLjg4OWMwIDE3LjYxIDE0LjMyNyAzMS45MzcgMzEuOTM3IDMxLjkzN2g1MC44ODljMTcuNjEgMCAzMS45MzctMTQuMzI3IDMxLjkzNy0zMS45Mzd2LTUwLjg4OWMwLTE3LjYxLTE0LjMyNy0zMS45MzctMzEuOTM3LTMxLjkzN3ptMTYuOTM3IDgyLjgyNmMwIDkuMzM5LTcuNTk4IDE2LjkzNy0xNi45MzcgMTYuOTM3aC01MC44ODljLTkuMzM5IDAtMTYuOTM3LTcuNTk4LTE2LjkzNy0xNi45Mzd2LTUwLjg4OWMwLTkuMzM5IDcuNTk4LTE2LjkzNyAxNi45MzctMTYuOTM3aDUwLjg4OWM5LjMzOSAwIDE2LjkzNyA3LjU5OCAxNi45MzcgMTYuOTM3eiIvPjxwYXRoIGQ9Im0xOTcuODA1IDE5OC4yMjNjLTQuMTQyIDAtNy41IDMuMzU4LTcuNSA3LjV2LjVjMCA0LjE0MiAzLjM1OCA3LjUgNy41IDcuNXM3LjUtMy4zNTggNy41LTcuNXYtLjVjMC00LjE0Mi0zLjM1OC03LjUtNy41LTcuNXoiLz48cGF0aCBkPSJtMjIxLjA0MiAxNzQuOTg3Yy00LjE0MiAwLTcuNSAzLjM1OC03LjUgNy41di41YzAgNC4xNDIgMy4zNTggNy41IDcuNSA3LjVzNy41LTMuMzU4IDcuNS03LjV2LS41YzAtNC4xNDItMy4zNTgtNy41LTcuNS03LjV6Ii8+PHBhdGggZD0ibTE3NC41NjkgMjIxLjQ2Yy00LjE0MiAwLTcuNSAzLjM1OC03LjUgNy41di41YzAgNC4xNDIgMy4zNTggNy41IDcuNSA3LjVzNy41LTMuMzU4IDcuNS03LjV2LS41YzAtNC4xNDItMy4zNTgtNy41LTcuNS03LjV6Ii8+PHBhdGggZD0ibTMzOS42MzkgMjQ4LjM5MWgtNTAuODg5Yy0xNy42MSAwLTMxLjkzNyAxNC4zMjctMzEuOTM3IDMxLjkzN3Y1MC44ODljMCAxNy42MSAxNC4zMjcgMzEuOTM3IDMxLjkzNyAzMS45MzdoNTAuODg5YzE3LjYxIDAgMzEuOTM3LTE0LjMyNyAzMS45MzctMzEuOTM3di01MC44ODljMC0xNy42MS0xNC4zMjctMzEuOTM3LTMxLjkzNy0zMS45Mzd6bTE2LjkzNyA4Mi44MjZjMCA5LjMzOS03LjU5OCAxNi45MzctMTYuOTM3IDE2LjkzN2gtNTAuODg5Yy05LjMzOSAwLTE2LjkzNy03LjU5OC0xNi45MzctMTYuOTM3di01MC44ODljMC05LjMzOSA3LjU5OC0xNi45MzcgMTYuOTM3LTE2LjkzN2g1MC44ODljOS4zMzkgMCAxNi45MzcgNy41OTggMTYuOTM3IDE2LjkzN3oiLz48cGF0aCBkPSJtMzE0LjE5NSAyOTcuNzcyYy00LjE0MiAwLTcuNSAzLjM1OC03LjUgNy41di41YzAgNC4xNDIgMy4zNTggNy41IDcuNSA3LjVzNy41LTMuMzU4IDcuNS03LjV2LS41YzAtNC4xNDItMy4zNTgtNy41LTcuNS03LjV6Ii8+PHBhdGggZD0ibTMzNy40MzEgMjkwLjAzNmM0LjE0MiAwIDcuNS0zLjM1OCA3LjUtNy41di0uNWMwLTQuMTQyLTMuMzU4LTcuNS03LjUtNy41cy03LjUgMy4zNTgtNy41IDcuNXYuNWMwIDQuMTQyIDMuMzU4IDcuNSA3LjUgNy41eiIvPjxwYXRoIGQ9Im0yOTAuOTU4IDMyMS4wMDljLTQuMTQyIDAtNy41IDMuMzU4LTcuNSA3LjV2LjVjMCA0LjE0MiAzLjM1OCA3LjUgNy41IDcuNXM3LjUtMy4zNTggNy41LTcuNXYtLjVjMC00LjE0Mi0zLjM1OC03LjUtNy41LTcuNXoiLz48cGF0aCBkPSJtMzM3LjY4MSAzMjEuMjU5aC0uNWMtNC4xNDIgMC03LjUgMy4zNTgtNy41IDcuNXMzLjM1OCA3LjUgNy41IDcuNWguNWM0LjE0MiAwIDcuNS0zLjM1OCA3LjUtNy41cy0zLjM1OC03LjUtNy41LTcuNXoiLz48cGF0aCBkPSJtMjkxLjIwOCAyNzQuNzg2aC0uNWMtNC4xNDIgMC03LjUgMy4zNTgtNy41IDcuNXMzLjM1OCA3LjUgNy41IDcuNWguNWM0LjE0MiAwIDcuNS0zLjM1OCA3LjUtNy41cy0zLjM1OC03LjUtNy41LTcuNXoiLz48cGF0aCBkPSJtNDM4LjI1MiA4NS4wNDhoMzEuOTI0YzQuMTQyIDAgNy41LTMuMzU4IDcuNS03LjVzLTMuMzU4LTcuNS03LjUtNy41aC0xMy44MTdsMjAuNTktMjAuNTljMi45MjktMi45MjkgMi45MjktNy42NzggMC0xMC42MDYtMi45MjktMi45MjktNy42NzgtMi45MjktMTAuNjA2IDBsLTIwLjU5IDIwLjU5di0xMy44MThjMC00LjE0Mi0zLjM1OC03LjUtNy41LTcuNXMtNy41IDMuMzU4LTcuNSA3LjV2MzEuOTI0Yy0uMDAxIDQuMTQyIDMuMzU3IDcuNSA3LjQ5OSA3LjV6Ii8+PHBhdGggZD0ibTQwLjUzMyA1NC40NTRoMzAuNTk0YzQuMTQyIDAgNy41LTMuMzU4IDcuNS03LjVzLTMuMzU4LTcuNS03LjUtNy41aC0zMC41OTRjLTQuMTQyIDAtNy41IDMuMzU4LTcuNSA3LjVzMy4zNTggNy41IDcuNSA3LjV6Ii8+PHBhdGggZD0ibTQwLjUzMyA3NS43MzZoMzAuNTk0YzQuMTQyIDAgNy41LTMuMzU4IDcuNS03LjVzLTMuMzU4LTcuNS03LjUtNy41aC0zMC41OTRjLTQuMTQyIDAtNy41IDMuMzU4LTcuNSA3LjVzMy4zNTggNy41IDcuNSA3LjV6Ii8+PHBhdGggZD0ibTQ1Mi4yMjQgNDgwLjc2N2MxNS4xMzcgMCAyNy40NTMtMTIuMzE1IDI3LjQ1My0yNy40NTNzLTEyLjMxNS0yNy40NTItMjcuNDUzLTI3LjQ1Mi0yNy40NTMgMTIuMzE1LTI3LjQ1MyAyNy40NTIgMTIuMzE1IDI3LjQ1MyAyNy40NTMgMjcuNDUzem0wLTM5LjkwNWM2Ljg2NiAwIDEyLjQ1MyA1LjU4NiAxMi40NTMgMTIuNDUycy01LjU4NiAxMi40NTMtMTIuNDUzIDEyLjQ1My0xMi40NTMtNS41ODYtMTIuNDUzLTEyLjQ1MyA1LjU4Ni0xMi40NTIgMTIuNDUzLTEyLjQ1MnoiLz48cGF0aCBkPSJtODQuNDMzIDQ1Ni45MDRoLTYuNDY3di03Ljg0OWg2LjQ2N2M0LjE0MiAwIDcuNS0zLjM1OCA3LjUtNy41cy0zLjM1OC03LjUtNy41LTcuNWgtNi40Njd2LTYuNDY3YzAtNC4xNDItMy4zNTgtNy41LTcuNS03LjVzLTcuNSAzLjM1OC03LjUgNy41djYuNDY3aC03Ljg0OXYtNi40NjdjMC00LjE0Mi0zLjM1OC03LjUtNy41LTcuNXMtNy41IDMuMzU4LTcuNSA3LjV2Ni40NjdoLTYuNDY3Yy00LjE0MiAwLTcuNSAzLjM1OC03LjUgNy41czMuMzU4IDcuNSA3LjUgNy41aDYuNDY3djcuODQ5aC02LjQ2N2MtNC4xNDIgMC03LjUgMy4zNTgtNy41IDcuNXMzLjM1OCA3LjUgNy41IDcuNWg2LjQ2N3Y2LjQ2N2MwIDQuMTQyIDMuMzU4IDcuNSA3LjUgNy41czcuNS0zLjM1OCA3LjUtNy41di02LjQ2N2g3Ljg0OXY2LjQ2N2MwIDQuMTQyIDMuMzU4IDcuNSA3LjUgNy41czcuNS0zLjM1OCA3LjUtNy41di02LjQ2N2g2LjQ2N2M0LjE0MiAwIDcuNS0zLjM1OCA3LjUtNy41cy0zLjM1OC03LjUtNy41LTcuNXptLTI5LjMxNiAwdi03Ljg0OWg3Ljg0OXY3Ljg0OXoiLz48L2c+PC9nPjwvc3ZnPg=="/></a></li>
   
   <li id="weather_pc">
   <input type="hidden" id="rain" value="${resultRain}">
   <input type="hidden" id="sky" value="${resultSky}">
   <c:if test="${resultLand eq userLoc}">
   <p id="weather_text" style="font-size: 13px; text-align: right;">${resultLand} ${resultTem}℃ ${resultSky} ${resultRain}<br>
   <img id="rain_img_pc" width="25px;" src="">
   <img id="sky_img_pc" width="25px;" src=""></p>
   </c:if>
   <c:if test="${resultLand ne userLoc}">
   <p id="weather_text" style="font-size: 13px; text-align: right;">서울 ${resultTem}℃  ${resultSky} ${resultRain}<br>
   <img id="rain_img_pc" width="25px;" src="">
   <img id="sky_img_pc" width="25px;" src=""></p>
   </c:if></li>
   </ul></div></div></div></div>


</body>
</html>