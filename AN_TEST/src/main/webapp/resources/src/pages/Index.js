var Index = function() {
  this.constructor();
  console.log("index Page load");
};

Index.prototype = {
  // 멤버변수
  menuHeaderId: '#divHeader',
  containerId: '#divMain',
  
  // 생성자
  constructor: function() {
    this.buildControl();
    this.buildEvent();
  },
  
  // 컨트롤 빌드
  buildControl: function() {
    this.onResizeHeader();
  },

  // 이벤트 빌드
  buildEvent: function() {
    // 메뉴 클릭 버튼 처리
    $(Index.prototype.menuHeaderId).find('.dropdown-item').click(this.onClickMenu);
    // 로고 클릭 이벤트
    $('#headerLogo').click(this.onClickLogo);
    // 헤더 리사이즈 이벤트 처리
    $(window).resize(this.onResizeHeader);
  },

  // 메뉴 클릭 이벤트
  onClickMenu: function(e) {
    e.preventDefault();
    var url = $(this).attr('router');
    App.Router.movePage(url);
  },

  // 로고 클릭 이벤트
  onClickLogo: function(e) {
    e.preventDefault();
    App.Router.movePage('MainPage');
  },

  // 헤더 리사이즈 이벤트
  onResizeHeader: function(e) {
    var menuHeaderId = Index.prototype.menuHeaderId;
    var containerId = Index.prototype.containerId;

    var headerHeight = `${$(menuHeaderId).height()}px`;
    $(containerId).css('padding-top', headerHeight);
  }  
}