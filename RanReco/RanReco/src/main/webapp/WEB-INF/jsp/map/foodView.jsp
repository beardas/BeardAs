<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9f393215a67756aa45074cc052574c5e"></script>


<style>
   .input-group{
      margin-top: 25px;
   }
</style>

<style>
.btn-like {
  color: transparent;
  text-shadow: 0 0 2px rgba(255,255,255,.7), 0 0 0 #000;
}
.btn-like:hover {
  text-shadow: 0 0 0 #ea0;
}
.btn-like.done {
  color: #f00;
  text-shadow: 0;
}
.btn-like.done:hover {
  color: transparent;
  text-shadow: 0 0 0 #777;
}


.btn-hate {
  color: transparent;
  text-shadow: 0 0 2px rgba(255,255,255,.7), 0 0 0 #000;
}
.btn-hate:hover {
  text-shadow: 0 0 0 #ea0;
}
.btn-hate.done {
  color: #f00;
  text-shadow: 0;
}
.btn-hate.done:hover {
  color: transparent;
  text-shadow: 0 0 0 #777;
}
</style>



<div class="container">
   <div>
      <div>
      <br>
      <span>no.${resultView.idx}</span>
      <br>
         <strong><h2>${resultView.storeName}</h2></strong>
         <br>
         <small>평점 : ${resultView.star}</small>
      <hr>
      <br>   
      <div>
               <c:set var="fullURL" value="${pageContext.request.requestURL}"></c:set>
               <c:set var="pathURI" value="${pageContext.request.requestURI}"></c:set>
               <c:set var="baseURL" value="${fn:replace(fullURL, pathURI, '')}"></c:set>
               <img src="${baseURL}/${resultView.fileName}" width="150" height="150" onerror="this.src='images/defaultpic.png'"/>
      </div>
      <br>
         <div style="height: 200px">
         <div style="vertical-align: top;"></div>
         <div>${resultView.address}</div>
         </div>
         <p align="center"><strong>음식점 위치</strong></p>
         <br>
         <div align="center">
      <div id="map" style="width:50%;height:350px;"></div>
         </div>
         <br><br>
         
               
   </div>
   </div>

   <div style="margin-top:15px;">
      <div>
      <c:set var="admin" value="${userAdmin}" />
      <c:if test='${admin eq "관리자"}'>
         <button type="button" class="btn btn-lg btn-danger pull-right" onclick="javascript:fn_delete();">삭제</button>
         <button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_update();">수정</button>
      </c:if>
         <button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:fn_list();">목록</button>
      </div>
   </div>
   <input type="hidden" id="address" name="address" value="${resultView.address}">
   <!-- 댓글 -->
   <br>
   <br>
   <br>
<hr>
  
</div>


<div class="container">
   <div class="replyList"></div>
</div>
<script type="text/javascript">
var food_idx = '${resultView.idx}';
function fn_delete(){
   location.href = "/deleteFood.do?idx="+food_idx;
}

function fn_update(){
   location.href = "/FoodUpt.do?idx="+food_idx;
}

function fn_list(){
   location.href = "/selectFoodList.do";
}

/* function fn_insertReply() {
   location.href = "/insertReply.do?boardIdx="+board_idx;
} */


</script>

