<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">

<style>

.fontSize{
	font-size: 28px;
	font-family: 'Black Han Sans', sans-serif;
	color: black;
}

</style>




		<c:set var="admin" value="������" />
		<c:if test='${admin eq userAdmin}'>
		<h4 align="center">&laquo;&laquo;�����ڸ��&raquo;&raquo;</h4>
		</c:if>
		<c:if test="${!empty userName}"><h3 align="center" >${userName}��, ȯ���մϴ�!</h3>
		<br>
		<div class="size" align="center" style="font-size: 100%" >
		<span class="fontSize">������?!</span> <br>
		&nbsp;<a href="selectFashionList.do">
		<input class="in" type="text" disabled="disabled" value="${resultVO.look}"  placeholder="?"
		style=" width: 130px;height: 30px; border-style: none;text-align: center; font-size: 25px;"/>
		<span class="fontSize" >�� �԰�</span></a>
		&nbsp;&nbsp;&nbsp;
		<a href="selectLocation.do">
		<input class="in" type="text" disabled="disabled" value="${resultVO.memberLocation}" placeholder="?"
		style=" width: 130px;height: 30px; border-style: none;text-align: center; font-size: 25px;"/>
		<span class="fontSize">�� ����</span></a>
		&nbsp;&nbsp;&nbsp;
		<a href="selectFoodList.do">
		<input class="in" type="text" disabled="disabled" value="${resultVO.memberFood}"  placeholder="?" 
		style=" width: 130px;height: 30px; border-style: none;text-align: center; font-size: 25px;"/>
		<span class="fontSize">�� �Դ´�</span></a>
		</div>
		</c:if>
		<br>
		<div align="center" >
		<a style="width:100%;height:100%;display:block;" target="_self" href="/">
		<span style="width:100%;height:100%;display:block"></span></a>
		<img class="Ȩ" style="width:80%" src="/images/Ȩ3.jpg"/></div>





<!-- Channel Plugin Scripts -->
<script>
  (function() {
    var w = window;
    if (w.ChannelIO) {
      return (window.console.error || window.console.log || function(){})('ChannelIO script included twice.');
    }
    var ch = function() {
      ch.c(arguments);
    };
    ch.q = [];
    ch.c = function(args) {
      ch.q.push(args);
    };
    w.ChannelIO = ch;
    function l() {
      if (w.ChannelIOInitialized) {
        return;
      }
      w.ChannelIOInitialized = true;
      var s = document.createElement('script');
      s.type = 'text/javascript';
      s.async = true;
      s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
      s.charset = 'UTF-8';
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(s, x);
    }
    if (document.readyState === 'complete') {
      l();
    } else if (window.attachEvent) {
      window.attachEvent('onload', l);
    } else {
      window.addEventListener('DOMContentLoaded', l, false);
      window.addEventListener('load', l, false);
    }
  })();
  ChannelIO('boot', {
    "pluginKey": "339b8bbc-420c-4679-ae83-58de7fccd8ac"
  });
</script>
<!-- End Channel Plugin -->