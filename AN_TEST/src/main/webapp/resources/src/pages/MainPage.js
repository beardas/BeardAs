// 기본 Page 패턴
var MainPage = function() {
  this.constructor();
};

MainPage.prototype = {
  sendData: null,
  // 생성자
  constructor: function() {
    // 이전 페이지에서 전달된 변수를 바인딩한다.
    MainPage.prototype.sendData = App.Router.getSendData();

    this.buildControl();
    this.buildEvent();
  },
  // 컨트롤 빌드
  buildControl: function() {
    
  },
  // 이벤트 빌드
  buildEvent: function() {
  },
}

$(document).ready(function () {
  var mainPage = new MainPage();
  console.log("main page load");
});