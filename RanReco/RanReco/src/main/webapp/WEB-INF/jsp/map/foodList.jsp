<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9f393215a67756aa45074cc052574c5e&libraries=services"></script>
  <style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px; }
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px; }
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
</style>

<style>
html,body {width:100%;margin:0px;padding:0px;font-family:sans-serif;}
 ul, li{margin:0;padding:0;list-style:none} 
 .container1{width:100%;max-width:1400px;margin:0 auto;overflow:hidden;} 
 .gallery{margin:50px -20px;box-sizing:border-box;} 
 .gallery:after{content:"";display:block;clear:both;visibility: hidden;} 
 .gallery li{width:20%;float:left;box-sizing:border-box;padding:0 20px;margin:0 0 50px 0;} 
 .gallery li img{width:100%;height:100%;} 
 @media (max-width:1200px){ 
 .container .gallery li{width:25%;} } 
 @media (max-width:768px){ 
 .container .gallery li{width:35%;} } 
 @media (max-width:560px){ 
 .container .gallery li{width:42%;} } 
 @media (max-width:480px){ 
 .container .gallery li{width:45%;} }

</style>

<style>
.img{
	width:250px;
	height: 200px;
	overflow: hidden;
	text-align: center ;
}}

.main{
	min-width: 320px;
	max-height: 800px;
	padding:50px;
	margin: 0 auto;
	background: #ffffff;
}

label{
	display:inline-block;
	margin:0 0 -1px;
	padding: 15px 25px;
	font-weight: 600;
	color: #bbb;
	border:1px solid transparent;
}

label:hover{
	color: #2e9cdf;
	cursor:pointer;
}

 @media (max-width:1200px){ 
 .first{width:25%;} } 
 @media (max-width:768px){ 
 .first{width:33.33333%;} } 
 @media (max-width:560px){ 
 .first{width:50%;} } 
 @media (max-width:480px){ 
 .first{width:100%;} }

</style>

<div class="container" style="margin-top:25px;">
<c:set var="admin" value="관리자" />
 
   
         <div >
            <c:if test='${admin eq userAdmin}'>
               <th><input type="checkbox" onclick="javascript:fn_checkboxAllCheck(this);"/></th>
            </c:if>
               <br>
               <div align="center" >
               <h3><strong>랜덤으로 추천 받기</strong></h3>
               <form name="form">
               <IMG src="/images/food/한식.png" name="first" width="500px;" class="first" ><br>
               <input type="button" value="  START  " onClick="javascript:startGame()" >   
               <input type="button" value="  STOP  " onClick="javascript:end(1)" ><br><br><br>
               <div>
               <p id="pay_back" style="text-align: center; font-size: xx-large;">
               </div>
              
              		 <div id="page"  class="map_wrap" align="center">
                 	<div id="map" align="center" style="width:100%; height:100%; position:relative; overflow:hidden;"></div>
                 	<div id="menu_wrap" class="bg_white" >
                 	<div class="option">
                 	<div>
                       	키워드 : <input type="text" value="${userLoc}${memberFood}맛집" id="keyword" size="15"> 
                	 <input type="button" id="searchBtn" onclick="searchPlaces()" value="검색">
                	 </div>
                	 </div>
                	 <hr>
                	 <ul id="placesList"></ul>
                	 <div id="pagination"></div>
                 	<input type="hidden" id="location" name="location" value="${userLoc}">
                	 </div>
                	</div>
                	<input type="hidden" id="memberFood" name="memberFood" value="">
                	<input type="hidden" id="memberFoodFile" name="memberFoodFile" value="">
         		<input type="hidden" id="memberIdx" name="memberIdx" value="${userIdx}">
               </form>
               <br>
               </div>
               <br><br>
               </div>
            
               <div style="margin: 5%;">
               <h3>동네별 맛집</h3>
               <hr >
               <div class="main">
               		<label for="tab1"><a href="/selectFoodList.do?location=압구정">압구정</a></label>
               		<label for="tab2"><a href="/selectFoodList.do?location=왕십리">왕십리</a></label>
               		<label for="tab3"><a href="/selectFoodList.do?location=이태원">이태원</a></label>
               		<label for="tab4"><a href="/selectFoodList.do?location=COEX">COEX</a></label>
               		<label for="tab5"><a href="/selectFoodList.do?location=DDP">DDP</a></label>
               		<label for="tab6"><a href="/selectFoodList.do?location=홍대">홍대</a></label>
               		<label for="tab7"><a href="/selectFoodList.do?location=여의도">여의도</a></label>
               		<label for="tab8"><a href="/selectFoodList.do?location=익선동">익선동</a></label>
               		<label for="tab9"><a href="/selectFoodList.do?location=성수동">성수동</a></label>
               		<label for="tab10"><a href="/selectFoodList.do?location=서울숲">서울숲</a></label>
               		<label for="tab11"><a href="/selectFoodList.do?location=상수동">상수동</a></label>
               		<label for="tab12"><a href="/selectFoodList.do?location=문래">문래</a></label>
               		<label for="tab13"><a href="/selectFoodList.do?location=대학로">대학로</a></label>
               		<label for="tab14"><a href="/selectFoodList.do?location=노량진">노량진</a></label>
               		<label for="tab15"><a href="/selectFoodList.do?location=고속터미널">고터</a></label>
               		<label for="tab16"><a href="/selectFoodList.do?location=경복궁">경복궁</a></label>
               		<label for="tab17"><a href="/selectFoodList.do?location=강남">강남</a></label>
               		<label for="tab18"><a href="/selectFoodList.do?location=가로수길">가로수길</a></label>
               		<label for="tab19"><a href="/selectFoodList.do?location=샤로수길">샤로수길</a></label>
               </div>
               
               
                </div>
                 <div id="foodSearchDiv" class="text-center" style="display:none;float:right;">
   				   <form id="searchForm" name="searchForm" method="post" class="navbar-form navbar-left" role="search">
        			 <input type="hidden" id="page" name="page" value="" />
         			 <input type="hidden" id="size" name="size" value="10" />
        			 <div class="form-group">
            		 <input type="text" id="location" name="location" value=""  class="form-control" placeholder="Search">
         		</div>
         		<button type="submit" class="btn btn-default" onclick="javascript:fn_searchSubmit();">Search</button>
      			</form>
 			  </div>
               
            <c:if test="${empty resultFoodList}">
               <tr>
                  <td colspan="10" class="text-center">게시글이 없습니다.</td>
               </tr>
            </c:if>
                  <div class="container1">
                     <ul class="gallery">
            <c:if test="${not empty resultFoodList}">
               <c:forEach items="${resultFoodList}" var="resultFood" varStatus="status">
                     <p class="thmb" >
                        <input type="hidden" id="idx_${status.index}"  name="idx" value="${resultFood.idx}" />
                        <li><div class="img"><a class="idx" id="idx_${status.index}">
                        <c:set var="fullURL" value="${pageContext.request.requestURL}"></c:set>
                        <c:set var="pathURI" value="${pageContext.request.requestURI}"></c:set>
                        <c:set var="baseURL" value="${fn:replace(fullURL, pathURI, '')}"></c:set>
                        <img class="file"  onload="setDirection(this)" src="${baseURL}/${resultFood.foodFile}" width="150" height="150" onerror="this.src='images/defaultpic.png'"/></a></div>
                 <div class="text">
                  <p class="idx" id="idx_${status.index}">${resultFood.location}</p>
                  <p class="store"><strong>${resultFood.storeName}</strong></p>
                 <input type="hidden" id="idx_${status.index}_link" value="${resultFood.url}"/>
                  <a onclick="javascript:fn_link(${status.index});">★상세보기★</a>
                  <a class="tx_brief"><span class="idx" id="idx_${status.index}"></span></a></div>
                  </li>
               </c:forEach>
            </c:if>
               </ul>
               </div>
   </div>
   
   <c:if test="${not empty resultFoodList}">
   <div class="text-center">
      <c:set var="startPage" value="${pagingVO.startPage}" />
      <c:set var="endPage" value="${pagingVO.endPage}" />
      <c:set var="totalPage" value="${pagingVO.totalPage}" />
      <nav>
         <ul class="pagination">
         <c:if test="${resultFood.number >= startPage}">
               <li>
                  <a href="javascript:fn_goPage('${resultFood.number}');" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                  </li>
            </c:if>
               <c:forEach var="i" begin="${startPage}" end="${endPage}">
                  <c:if test="${i-1 eq resultFood.number}">
                  <li class="active"><a href="javascript:fn_goPage('${i}');">${i}</a></li>
                  </c:if>
                  <c:if test="${i-1 ne resultFood.number}">
                  <li><a href="javascript:fn_goPage('${i}');">${i}</a></li>
                  </c:if>
            </c:forEach>
            <c:if test="${resultFood.number != totalPage-1}">
               <li>
                  <a href="javascript:fn_goPage('${resultFood.number + 2}');" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
               </li>
            </c:if>
         </ul>
      </nav>
   </div>
   </c:if>
   
   <div>
   
      <c:if test='${admin eq userAdmin}'>
      <button type="button" class="btn btn-lg btn-danger pull-right" onclick="javascript:fn_delete();">삭제</button>
      <button type="button" class="btn btn-lg btn-primary pull-right" onclick="javascript:fn_reg();">등록</button>
      </c:if>
      
   </div>


<form id="detailForm" name="detailForm" method="post">
   <input type="hidden" id="idxs" name="idxs" value="" />
</form>
<script>

/*    var foodIdx=null;
   // 얘랑 2시간 싸움...
   $("a[id^='idx_']").click(function(){
      foodIdx = $(this).attr("id");
      result = $("input[id='"+foodIdx+"']").val();
   location.href = "/viewFood.do?idx="+result;
   }); */
   
   
   function fn_link(i){
       location.href = document.getElementById("idx_"+i+"_link").value;
       }
   
   

   <%-- 페이징_페이지 이동 --%>
   function fn_goPage(page){
      var f = document.searchForm;
      f.page.value = page-1;
      f.action = "/selectFoodList.do?size=10";
      f.submit();
   }

   <%-- 체크박스 모든선택 --%>
   function fn_checkboxAllCheck(obj){
      var chooseRecords = document.getElementsByName("chooseRecord");
      for(var i=0; i<chooseRecords.length; i++){
         chooseRecords[i].checked = obj.checked;
      }
   }

   <%-- 등록으로 이동 --%>
   function fn_reg(){
      location.href = "/kakaomap.do";
   }

   <%-- 검색창 --%>
   function fn_search(){
      var foodSearchDiv = document.getElementById("foodSearchDiv");
      
      if(foodSearchDiv.style.display == "none"){
         foodSearchDiv.style.display = "block";
      }else{
         foodSearchDiv.style.display = "none";
      }
      
   }

   
   
   <%-- 검색 --%>
   function fn_searchSubmit(){
      var f = document.searchForm;
      f.action = "/selectFoodList.do";
      f.submit();
   }
   function fn_search(){
	   $("#location").val(document.getElementById("tab2").value);
	   fn_searchSubmit();
   }

   <%-- 삭제 --%>
   function fn_delete(){
      var boardIdxs = []; <%--배열--%>
      var chooseRecordCount = 0; <%--체크된 갯수 초기값 0 --%>
      var chooseRecords = document.getElementsByName("chooseRecord");  <%--체크박스--%>
      
      for(var i=0; i<chooseRecords.length; i++){ <%--체크박스 수 만큼 반복--%> <%--예) 10개 -> 10번 반복 --%> 
         if(chooseRecords[i].checked){ <%--그중에 체크 된게 있으면--%> <%--예) 0, 3, 4번째 체크박스가 체크 됐다치면 --%> 
            chooseRecordCount++; <%--체크된 만큼 체크 수 증가--%> <%-- 예) 3증가 --%>
            foodIdxs.push(document.getElementsByName("idx")[i].value); <%--${result.memberIdx}를 배열에 추가로 삽입--%>
                                                               <%--예) 0, 3, 4번째 회원번호를 배열로 받음--%>
         }
      }

      if(chooseRecordCount < 1){  <%-- 예) 3이니 알림문은 뜨지 않음 --%>
         alert("데이터를 선택하세요.");
         return;
      }
      
      var f = document.detailForm;
      f.idxs.value = foodIdxs; <%--배열을 리턴--%>
      f.action = "/deleteFoodByAdmin.do";   <%--BoardController의 delete 메소드 실행--%>
      f.submit();
   }

   <%-- 수정페이지 이동 --%>
   function fn_update(){
      var chooseRecordCount = 0;
      var chooseRecords = document.getElementsByName("chooseRecord");
      
      for(var i=0; i<chooseRecords.length; i++){
         if(chooseRecords[i].checked){
            chooseRecordCount++;
         }
      }

      if(chooseRecordCount < 1){
         alert("데이터를 선택하세요.");
         return;
         
      }

      var foodIdx = 0;
      var foodIdxs = document.getElementsByName("idx");
      if(chooseRecordCount > 1){
         alert("2개 이상 선택이기 때문에 첫번째 데이터값으로 이동합니다.");
         for(var i=0; i<chooseRecords.length; i++){
            chooseRecords[i].checked = false;
         }
         chooseRecords[0].checked = true;
         foodIdx = 0;
      }else{
         for(var i=0; i<chooseRecords.length; i++){
            if(chooseRecords[i].checked){
               foodIdx = i;
               break;
            }
         }
      }

      location.href = "/FoodUpt.do?idx="+foodIdxs[foodIdx].value;
   }
   
   var markers = [];

   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
       mapOption = {
           center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
           level: 3 // 지도의 확대 레벨
};  

   // 지도를 생성합니다    
   var map = new kakao.maps.Map(mapContainer, mapOption); 

   // 장소 검색 객체를 생성합니다
   var ps = new kakao.maps.services.Places();  

   // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
   var infowindow = new kakao.maps.InfoWindow({zIndex:1});

   // 키워드로 장소를 검색합니다
   searchPlaces();



   // 키워드 검색을 요청하는 함수입니다
   function searchPlaces() {

       var keyword = document.getElementById('keyword').value;

       if (!keyword.replace(/^\s+|\s+$/g, '')) {
           alert('키워드를 입력해주세요!');
           return false;
       }

       // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
       ps.keywordSearch( keyword, placesSearchCB); 
   }

   // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
   function placesSearchCB(data, status, pagination) {
       if (status === kakao.maps.services.Status.OK) {

           // 정상적으로 검색이 완료됐으면
           // 검색 목록과 마커를 표출합니다
           displayPlaces(data);

           // 페이지 번호를 표출합니다
           displayPagination(pagination);

       } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

           alert('검색 결과가 존재하지 않습니다.');
           return;

       } else if (status === kakao.maps.services.Status.ERROR) {

           alert('검색 결과 중 오류가 발생했습니다.');
           return;

       }
   }


     

   // 검색결과 항목을 Element로 반환하는 함수입니다
   function getListItem(index, places) {

       var el = document.createElement('li'),
       itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                   '<div class="info">' +
                   '   <h5>' + places.place_name + '</h5>';

       if (places.road_address_name) {
           itemStr += '    <span>' + places.road_address_name + '</span>' +
                       '   <span class="jibun gray">' +  places.address_name  + '</span>';
       } else {
           itemStr += '    <span>' +  places.address_name  + '</span>'; 
       }
                    
         itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                   '</div>';           

       el.innerHTML = itemStr;
       el.className = 'item';

       return el;
   }

   // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
   function addMarker(position, idx, title) {
       var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
           imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
           imgOptions =  {
               spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
               spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
               offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
           },
           markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
               marker = new kakao.maps.Marker({
               position: position, // 마커의 위치
               image: markerImage 
           });

       marker.setMap(map); // 지도 위에 마커를 표출합니다
       markers.push(marker);  // 배열에 생성된 마커를 추가합니다

       return marker;
   }

   // 지도 위에 표시되고 있는 마커를 모두 제거합니다
   function removeMarker() {
       for ( var i = 0; i < markers.length; i++ ) {
           markers[i].setMap(null);
       }   
       markers = [];
   }

   // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
   function displayPagination(pagination) {
       var paginationEl = document.getElementById('pagination'),
           fragment = document.createDocumentFragment(),
           i; 

       // 기존에 추가된 페이지번호를 삭제합니다
       while (paginationEl.hasChildNodes()) {
           paginationEl.removeChild (paginationEl.lastChild);
       }

       for (i=1; i<=pagination.last; i++) {
           var el = document.createElement('a');
           el.href = "#";
           el.innerHTML = i;

           if (i===pagination.current) {
               el.className = 'on';
           } else {
               el.onclick = (function(i) {
                   return function() {
                       pagination.gotoPage(i);
                   }
               })(i);
           }

           fragment.appendChild(el);
       }
       paginationEl.appendChild(fragment);
   }

   // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
   // 인포윈도우에 장소명을 표시합니다
   function displayInfowindow(marker, title) {
       var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

       infowindow.setContent(content);
       infowindow.open(map, marker);
   }

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
   function removeAllChildNods(el) {   
       while (el.hasChildNodes()) {
           el.removeChild (el.lastChild);
       }
   }
    
    
   function displayPlaces(places) {

       var listEl = document.getElementById('placesList'), 
       menuEl = document.getElementById('menu_wrap'),
       fragment = document.createDocumentFragment(), 
       bounds = new kakao.maps.LatLngBounds(), 
       listStr = '';
       
       // 검색 결과 목록에 추가된 항목들을 제거합니다
       removeAllChildNods(listEl);

       // 지도에 표시되고 있는 마커를 제거합니다
       removeMarker();
       
       for ( var i=0; i<places.length; i++ ) {

           var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
               marker = addMarker(placePosition, i), 
               itemEl = getListItem(i, places[i]); 

           // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
           // LatLngBounds 객체에 좌표를 추가합니다
           bounds.extend(placePosition);

    
   (function (marker, title) {
      kakao.maps.event.addListener(marker, 'mouseover', function() {
           displayInfowindow(marker, title);
       });

       kakao.maps.event.addListener(marker, 'mouseout', function() {
           infowindow.close();
       });
       itemEl.onmouseover =  function () {
           displayInfowindow(marker, title);
       };
      itemEl.onmouseout =  function () {
       infowindow.close();
       };
       kakao.maps.event.addListener(marker, 'click', (function(placePosition) {
       displayInfowindow(marker, title);
       
   })(placePosition));
       
       
   })(marker, places[i].place_name);

   fragment.appendChild(itemEl);
   }
       // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
       listEl.appendChild(fragment);
       menuEl.scrollTop = 0;

       // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
       map.setBounds(bounds);
   }
    
</script>

<SCRIPT language="JavaScript1.1">

game = false
box1 = true
num = Math.floor(Math.random() * 59)



IMG = new Array();

IMG[0] = new Image(); IMG[0].src = "/images/food/한식.png";
IMG[1] = new Image(); IMG[1].src = "/images/food/갈비.png";
IMG[2] = new Image(); IMG[2].src = "/images/food/게장.png";
IMG[3] = new Image(); IMG[3].src = "/images/food/계란말이.png";
IMG[4] = new Image(); IMG[4].src = "/images/food/국밥.png";
IMG[5] = new Image(); IMG[5].src = "/images/food/규동.png";
IMG[6] = new Image(); IMG[6].src = "/images/food/그라탕.png";
IMG[7] = new Image(); IMG[7].src = "/images/food/닭도리탕.png";
IMG[8] = new Image(); IMG[8].src = "/images/food/덮밥.png";
IMG[9] = new Image(); IMG[9].src = "/images/food/도넛.png";
IMG[10] = new Image(); IMG[10].src = "/images/food/돈카츠.png";
IMG[11] = new Image(); IMG[11].src = "/images/food/동태탕.png";
IMG[12] = new Image(); IMG[12].src = "/images/food/라면.png";
IMG[13] = new Image(); IMG[13].src = "/images/food/랍스타.png";
IMG[14] = new Image(); IMG[14].src = "/images/food/리코타.png";
IMG[15] = new Image(); IMG[15].src = "/images/food/마카롱.png";
IMG[16] = new Image(); IMG[16].src = "/images/food/마파두부.png";
IMG[17] = new Image(); IMG[17].src = "/images/food/바게트.png";
IMG[18] = new Image(); IMG[18].src = "/images/food/보쌈.png";
IMG[19] = new Image(); IMG[19].src = "/images/food/불백.png";
IMG[20] = new Image(); IMG[20].src = "/images/food/비빔밥.png";
IMG[21] = new Image(); IMG[21].src = "/images/food/뼈해장국.png";
IMG[22] = new Image(); IMG[22].src = "/images/food/삼겹살.png";
IMG[23] = new Image(); IMG[23].src = "/images/food/삼계탕.png";
IMG[24] = new Image(); IMG[24].src = "/images/food/샌드위치.png";
IMG[25] = new Image(); IMG[25].src = "/images/food/샐러드.png";
IMG[26] = new Image(); IMG[26].src = "/images/food/소바.png";
IMG[27] = new Image(); IMG[27].src = "/images/food/쌀국수.png";
IMG[28] = new Image(); IMG[28].src = "/images/food/알밥.png";
IMG[29] = new Image(); IMG[29].src = "/images/food/에그타르트.png";
IMG[30] = new Image(); IMG[30].src = "/images/food/에비동.png";
IMG[31] = new Image(); IMG[31].src = "/images/food/연어.png";
IMG[32] = new Image(); IMG[32].src = "/images/food/오코노미야키.png";
IMG[33] = new Image(); IMG[33].src = "/images/food/우동.png";
IMG[34] = new Image(); IMG[34].src = "/images/food/인도커리.png";
IMG[35] = new Image(); IMG[35].src = "/images/food/일본라멘.png";
IMG[36] = new Image(); IMG[36].src = "/images/food/전골.png";
IMG[37] = new Image(); IMG[37].src = "/images/food/짜장면.png";
IMG[38] = new Image(); IMG[38].src = "/images/food/쭈꾸미.png";
IMG[39] = new Image(); IMG[39].src = "/images/food/초밥.png";
IMG[40] = new Image(); IMG[40].src = "/images/food/치킨.png";
IMG[41] = new Image(); IMG[41].src = "/images/food/카레.png";
IMG[42] = new Image(); IMG[42].src = "/images/food/칼국수.png";
IMG[43] = new Image(); IMG[43].src = "/images/food/커피.png";
IMG[44] = new Image(); IMG[44].src = "/images/food/케이크.png";
IMG[45] = new Image(); IMG[45].src = "/images/food/타르트.png";
IMG[46] = new Image(); IMG[46].src = "/images/food/타코.png";
IMG[47] = new Image(); IMG[47].src = "/images/food/파스타.png";
IMG[48] = new Image(); IMG[48].src = "/images/food/푸딩.png";
IMG[49] = new Image(); IMG[49].src = "/images/food/피자.png";
IMG[50] = new Image(); IMG[50].src = "/images/food/햄버거.png";
IMG[51] = new Image(); IMG[51].src = "/images/food/호두과자.png";
IMG[52] = new Image(); IMG[52].src = "/images/food/회.png";
IMG[53] = new Image(); IMG[53].src = "/images/food/숯불구이.png";
IMG[54] = new Image(); IMG[54].src = "/images/food/반쎄오.png";
IMG[55] = new Image(); IMG[55].src = "/images/food/퀘사디아.png";
IMG[56] = new Image(); IMG[56].src = "/images/food/만둣국.png";
IMG[57] = new Image(); IMG[57].src = "/images/food/파전.png";
IMG[58] = new Image(); IMG[58].src = "/images/food/파닭.png";




// 슬롯머신의 숫자패널을 랜덤하게 돌려준다.
function hyouji()
{       
       if (num == 59) num = 0
       if (box1) document.images["first"].src = IMG[num % 59].src
       num += 1
       tid = setTimeout("hyouji()",60)
}

function startGame()
{
   if(game == false)
   {
        game = true
        hyouji();
    }
}
function end(variable)
{
        if (game == true)
      {
                if (variable == 1) 
                   box1 = false
        }
      else
      {
         alert ('게임이 시작되지 않았습니다.')
      }

        if (box1 == false)
      {
                clearTimeout (tid)
                reset()
        }
}
food = null;
function reset()
{
   switch (document.images["first"].src) {
   case IMG[0].src: food="한식"; break; case IMG[1].src: food="갈비"; break;
   case IMG[2].src: food="게장"; break; case IMG[3].src: food="계란말이"; break;
   case IMG[4].src: food="국밥"; break; case IMG[5].src: food="규동"; break;
   case IMG[6].src: food="그라탕"; break; case IMG[7].src: food="닭도리탕"; break;
   case IMG[8].src: food="덮밥"; break; case IMG[9].src: food="도넛" ;break;
   case IMG[10].src: food="돈카츠"; break; case IMG[11].src: food="동태탕" ;break;
   case IMG[12].src: food="라면"; break; case IMG[13].src: food="랍스타" ;break;
   case IMG[14].src: food="리코타"; break; case IMG[15].src: food="마카롱" ;break;
   case IMG[16].src: food="마파두부"; break; case IMG[17].src: food="바게트"; break;
   case IMG[18].src: food="보쌈"; break; case IMG[19].src: food="불백" ;break;
   case IMG[20].src: food="비빔밥"; break; case IMG[21].src: food="뼈해장국"; break;
   case IMG[22].src: food="삼겹살"; break; case IMG[23].src: food="삼계탕"; break;
   case IMG[24].src: food="샌드위치"; break; case IMG[25].src: food="샐러드"; break;
   case IMG[26].src: food="소바" ;break; case IMG[27].src: food="쌀국수"; break;
   case IMG[28].src: food="알밥"; break; case IMG[29].src: food="에그타르트"; break;
   case IMG[30].src: food="에비동"; break; case IMG[31].src: food="연어" ;break;
   case IMG[32].src: food="오코노미야키"; break; case IMG[33].src: food="우동"; break;
   case IMG[34].src: food="인도커리"; break; case IMG[35].src: food="일본라멘"; break;
   case IMG[36].src: food="전골"; break; case IMG[37].src: food="짜장면" ;break;
   case IMG[38].src: food="쭈꾸미"; break; case IMG[39].src: food="초밥" ;break;
   case IMG[40].src: food="치킨"; break; case IMG[41].src: food="카레" ;break;
   case IMG[42].src: food="칼국수"; break; case IMG[43].src: food="커피"; break;
   case IMG[44].src: food="케이크"; break; case IMG[45].src: food="타르트"; break;
   case IMG[46].src: food="타코"; break; case IMG[47].src: food="파스타"; break;
   case IMG[48].src: food="푸딩"; break; case IMG[49].src: food="피자"; break;
   case IMG[50].src: food="햄버거"; break; case IMG[51].src: food="호두과자"; break; 
   case IMG[52].src: food="회" ;break; case IMG[53].src: food="숯불구이" ;break;
   case IMG[54].src: food="반쎄오"; break; case IMG[55].src: food="퀘사디아"; break;
   case IMG[56].src: food="만둣국"; break; case IMG[57].src: food="파전" ;break;
   case IMG[58].src: food="파닭"; break;

   default:
      break;
   }
   document.getElementById('pay_back').innerHTML="오늘 점심은 " + food+"!"
   if(document.getElementById('memberIdx').value != 0){
	   setTimeout(function() {
		var returnValue = confirm(food+"로 결정하시겠습니까?");
		if(returnValue){
			alert("오늘은 음식은 "+food+"으로 결정!");
				$("#memberFood").val(food);
				var foodLink = "/images/food/"+food+".png"
				$("#memberFoodFile").val(foodLink);
				var f = document.form;
				f.action = "/memberFoodPopup.do";
				f.submit();
		}
		else 
			alert("다시 선택해주세요!")
		}, 1000);
  }else{
	setTimeout(function(){
		alert("오늘 음식은 "+food+"으로 결정! ");
		},1000);
	}
      game = false
      box1 = true
}



document.getElementById('pay_back').innerHTML="START버튼 꾹!"


</SCRIPT><br>