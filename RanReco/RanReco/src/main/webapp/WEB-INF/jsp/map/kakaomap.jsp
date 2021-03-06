<%@page import="com.RanReco.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	9f393215a67756aa45074cc052574c5e&libraries=services"></script>
    <style>
	#page{
		margin-top: 25px;
	}
</style>
 <div align="center">
  <h1>???? ??õ????</h1>
 </div>  
 <hr>
    <style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'????',sans-serif;font-size:12px; }
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
<form id="foodInsert" name="foodInsert" method="post" enctype="multipart/form-data">

<div id="page" align="center">
<label for="location">????</label>
<input type="text" name="location" id="location" >
<div id="page"  align="center">
<label for="storeName" ></label>
<input type="text" name="storeName" id="storeName" >
</div>
<div id="page"  align="center">
<label for="address" >?ּ?</label>
<textarea name="address" id="address" cols="30" rows="7" style="padding:20px; width: 60%; height: 30%;" ></textarea>

</div>
<div align="center" id="page" >
		<img class="rg_i Q4LuWd" id="userImage" src="./defaultpic.png" data-atf="true" width="150" height="150" onerror="this.src='images/defaultpic.png'">
		<label  class="btn btn-default">
		 ???? ???? <input type="file" id="userImageAttachFile" name="userImageAttachFile"  onchange="setThumbnail(event);" />
		</label>
</div>

<div id="page"  class="map_wrap" align="center">
    <div id="map" align="center" style="width:70%;height:100%;position:relative;overflow:hidden;"></div>

    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
                    Ű???? : <input type="text" value="???¿? ????" id="keyword" size="15"> 
                    <input type="button" id="searchBtn" onclick="searchPlaces()" value="?˻?">
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>
</div>

</form>
<br>
<button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_submit();">????</button>

<!--??Ŀ?? ???? ?迭?Դϴ?-->
<script>
var markers = [];

var mapContainer = document.getElementById('map'), // ?????? ǥ???? div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // ?????? ?߽???ǥ
        level: 3 // ?????? Ȯ?? ????
    };  

// ?????? ?????մϴ?    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// ???? ?˻? ??ü?? ?????մϴ?
var ps = new kakao.maps.services.Places();  

// ?˻? ???? ?????̳? ??Ŀ?? Ŭ?????? ?? ???Ҹ??? ǥ???? ?????????츦 ?????մϴ?
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// Ű?????? ???Ҹ? ?˻??մϴ?
searchPlaces();



// Ű???? ?˻??? ??û?ϴ? ?Լ??Դϴ?
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('Ű???带 ?Է????ּ???!');
        return false;
    }

    // ???Ұ˻? ??ü?? ???? Ű?????? ???Ұ˻??? ??û?մϴ?
    ps.keywordSearch( keyword, placesSearchCB); 
}

// ???Ұ˻??? ?Ϸ????? ?? ȣ???Ǵ? ?ݹ??Լ? ?Դϴ?
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // ?????????? ?˻??? ?Ϸ???????
        // ?˻? ???ϰ? ??Ŀ?? ǥ???մϴ?
        displayPlaces(data);

        // ?????? ??ȣ?? ǥ???մϴ?
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('?˻? ?????? ???????? ?ʽ??ϴ?.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('?˻? ???? ?? ?????? ?߻??߽??ϴ?.');
        return;

    }
}

  

// ?˻????? ?׸??? Element?? ??ȯ?ϴ? ?Լ??Դϴ?
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

// ??Ŀ?? ?????ϰ? ???? ???? ??Ŀ?? ǥ???ϴ? ?Լ??Դϴ?
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // ??Ŀ ?̹??? url, ????????Ʈ ?̹????? ???ϴ?
        imageSize = new kakao.maps.Size(36, 37),  // ??Ŀ ?̹????? ũ??
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // ????????Ʈ ?̹????? ũ??
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // ????????Ʈ ?̹??? ?? ?????? ?????? ?»??? ??ǥ
            offset: new kakao.maps.Point(13, 37) // ??Ŀ ??ǥ?? ??ġ??ų ?̹??? ???????? ??ǥ
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // ??Ŀ?? ??ġ
            image: markerImage 
        });

    marker.setMap(map); // ???? ???? ??Ŀ?? ǥ???մϴ?
    markers.push(marker);  // ?迭?? ?????? ??Ŀ?? ?߰??մϴ?

    return marker;
}

// ???? ???? ǥ?õǰ? ?ִ? ??Ŀ?? ???? ?????մϴ?
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// ?˻????? ???? ?ϴܿ? ????????ȣ?? ǥ?ô? ?Լ??Դϴ?
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // ?????? ?߰??? ????????ȣ?? ?????մϴ?
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

// ?˻????? ???? ?Ǵ? ??Ŀ?? Ŭ?????? ?? ȣ???Ǵ? ?Լ??Դϴ?
// ?????????쿡 ???Ҹ??? ǥ???մϴ?
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // ?˻????? ?????? ?ڽ? Element?? ?????ϴ? ?Լ??Դϴ?
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
    
    // ?˻? ???? ???Ͽ? ?߰??? ?׸????? ?????մϴ?
    removeAllChildNods(listEl);

    // ?????? ǥ?õǰ? ?ִ? ??Ŀ?? ?????մϴ?
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // ??Ŀ?? ?????ϰ? ?????? ǥ???մϴ?
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // ?˻? ???? ?׸? Element?? ?????մϴ?

        // ?˻??? ???? ??ġ?? ???????? ???? ?????? ?缳???ϱ?????
        // LatLngBounds ??ü?? ??ǥ?? ?߰??մϴ?
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
    // ?˻????? ?׸????? ?˻????? ???? Elemnet?? ?߰??մϴ?
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // ?˻??? ???? ??ġ?? ???????? ???? ?????? ?缳???մϴ?
    map.setBounds(bounds);
}
 
</script>

<script>

<%-- ???? --%>
function fn_submit(){
	if(fn_validation()){
		return;
	}
	
	var f = document.foodInsert;
	f.action = "/foodInsert.do";
	f.submit();
}


function fn_validation(){
	if(document.getElementById("location").value.length < 1){
		alert("?????? ?Է????ּ???.");
		document.getElementById("location").focus();
		return true;
	}
	else if(document.getElementById("storeName").value.length < 1){
		alert("?????? ?Է????ּ???.");
		document.getElementById("storeName").focus();
		return true;
	}
	return false;
}


<%-- ?̹??? ???ε??? ?̹??? ?̸????? --%>
function setThumbnail(event) { 
	var reader = new FileReader();

	reader.onload = function(event) { 
		document.getElementById("userImage").setAttribute("src", event.target.result);
	};

	reader.readAsDataURL(event.target.files[0]); 
}



</script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
