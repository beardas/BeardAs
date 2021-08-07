<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>BeardAs 프로젝트</title>

  <!-- Bootstrap core CSS -->
  <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/assets/css/custom/menu.css" rel="stylesheet">
  <!-- bxSilder -->
  <link href="/resources/assets/css/jquery.bxslider.css" rel="stylesheet">

  <!-- Library -->
  <script src="/resources/lib/js/jquery-3.4.1.min.js"></script>
  <script src="/resources/lib/js/bootstrap.bundle.min.js"></script>
  <!-- https://github.com/muzammilkm/jq-router -->
  <script src="/resources/lib/js/jq-router.min.js"></script>
  <!-- https://github.com/stevenwanderski/bxslider-4 -->
  <script src="/resources/lib/js/jquery.bxslider.min.js"></script>

  <!-- 공통 소스 바인딩 (전역에서 사용하는 자바스크립트) -->
  <!-- <script src="/resources/src/handler/Router.js"></script>
  <script src="/resources/src/pages/Index.js"></script>
  <script src="/resources/src/App.js"></script>
 -->  
  
</head>

<body>
  <!-- Header 영역-->
  <div id="divHeader" class="container-fluid fixed-top bg-white">
    <!-- 검색 창 -->
    <div class="row">
      <div class="col-12">
        <nav class="navbar navbar-expand-lg">
          <a id="headerLogo" class="navbar-brand">안성수 - TEST</a>
          <form class="form-inline ml-auto">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>
          </form>
        </nav>
      </div>
    </div>
    <!-- 메뉴 영역 -->
    <div class="row">
      <div class="col-12">
        <nav class="navbar navbar-expand-sm navbar-light bg-light">
          <div>
            <ul id="ulNavMenu" class="navbar-nav mr-auto">
              <li class="nav-item dropdown">
                <a id="dropDownItem1" class="nav-link dropdown-toggle" role="button" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  메뉴1
                </a>
                <div class="dropdown-menu" aria-labelledby="dropDownItem1">
                  <a class="dropdown-item" router="SubPage">Sub page</a>
                  <a class="dropdown-item" href="#">Another action</a>
                  <a class="dropdown-item" href="#">Something else here</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a id="dropDownItem2" class="nav-link dropdown-toggle" role="button" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  메뉴2
                </a>
                <div class="dropdown-menu" aria-labelledby="dropDownItem2">
                  <a class="dropdown-item">Action2</a>
                  <a class="dropdown-item">Another action</a>
                  <a class="dropdown-item">Something else here</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a id="dropDownItem3" class="nav-link dropdown-toggle" role="button" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  상품관리
                </a>
                <div class="dropdown-menu" aria-labelledby="dropDownItem3">
                  <a class="dropdown-item" href="/productList">상품리스트</a>
                  <a class="dropdown-item">Another action</a>
                  <a class="dropdown-item">Something else here</a>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  </div>

  <!-- Main 영역 -->
  <div id="divMain">
  
  <!-- <br> 
<div class="container-fluid">
  <div class="mx-auto" style="max-width: 600px;">
    <div id="divImageSlider" class="bxslider center-block">
      <div><img src="/resources/assets/images/coffee1.jpg"></div>
      <div><img src="/resources/assets/images/coffee2.jpg"></div>
      <div><img src="/resources/assets/images/coffee3.jpg"></div>
    </div>     
  </div>
</div> -->
  
  
  
  
  </div>
</body>

</html>
