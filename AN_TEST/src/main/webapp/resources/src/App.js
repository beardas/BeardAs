var App = {};

// router 기본 설정
// 페이지가 늘어날 때마다 해당 값을 추가한다.
App.Routes = {
  MainPage: { url: '#/main', templateUrl: '/index.jsp'},
  ProductListPage: { url: '/productList', templateUrl: '/productList.jsp'},
};

// main 함수
$(document).ready(function () {
  // App에 공통적으로 사용할 라우터 바인딩
  App.Router = new Router({
    containerId: '#divMain',
    routerConfig: App.Routes
  });

  // 메인 페이지를 구동
  var indexPage = new Index();
});