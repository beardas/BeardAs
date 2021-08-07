// 페이지 이동을 담당하는 클래스
var Router = function(options) {
  this.constructor(options)
}

Router.prototype = {
  containerId: null,
  routerConfig: null,

  constructor: function (options) {
    // 생성자 변수 삽입
    Router.prototype.containerId = options.containerId;
    Router.prototype.routerConfig = options.routerConfig;

    this.initRouter();
  },
  
  // router 초기화
  // 메인 페이지 초기화
  initRouter: function() {
    $.router
      .setData(Router.prototype.routerConfig)
      .run(Router.prototype.containerId, 'MainPage');
  },

  // 페이지 이동
  movePage: function(path, sendData) {
    $.router.go(path, sendData);
  },

  // 전송된 Paramater를 전달 받는다.
  getSendData: function() {
    return $.router.getCurrentParams()
  }
}