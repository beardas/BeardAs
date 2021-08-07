// 기본 Page 패턴
var SubPage = function() {
  this.constructor();
};

SubPage.prototype = {
  sendData: null,
  // 생성자
  constructor: function() {
    // 이전 페이지에서 전달된 변수를 바인딩한다.
    SubPage.prototype.sendData = App.Router.getSendData();

    this.buildControl();
    this.buildEvent();
  },
  // 컨트롤 빌드
  buildControl: function() {
    // slider build
    $('#divImageSlider').bxSlider({
      auto: true,
      autoControls: false,
      stopAutoOnClick: false,
      pager: false,
      adaptiveHeight: true,
      slideWidth: 600,
    });
  },
  // 이벤트 빌드
  buildEvent: function() {

  },      
}

$(document).ready(function () {
  var subPage = new SubPage();
  console.log("sub page load");
});