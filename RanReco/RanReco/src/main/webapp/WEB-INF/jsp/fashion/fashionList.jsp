<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

   <style>

        
.toggle3 input[type=radio]{
    display: none;
}
.toggle3 input[type=radio] + label {
    color: #e0e0e0;
    font-size: x-large;
    margin-right: 25px;
    margin-left: 25px;
}
.toggle3 input[type=radio]:checked + label {
    color: #000;
}
#choose{
	list-style: none
	
}
 @media (max-width:1200px){ 
 #randomImg{width:60%;} } 
 @media (max-width:768px){ 
 #randomImg{width:60%;} } 
 @media (max-width:560px){ 
 #randomImg{width:80%;} } 
 @media (max-width:480px){ 
 #randomImg{width:90%; } }
    
   </style>


     <div id="reloading" align="center" style="margin-bottom: 200px; " >
     <c:if test="${!not empty resultFashion}">
            <form id="selectLook" name="selectLook" method="post" >
		    <div class="toggle3">
		    <h4>성별</h4>
		    <input type="radio" id="toggle1-1" name="gender" value="여자">
		    <label for="toggle1-1">여자</label>
		    <input type="radio" id="toggle1-2" name="gender" value="남자">
		    <label for="toggle1-2">남자</label>
			</div>   
		    <div class="toggle3">
		    <h4>계절</h4>
		    <input type="radio" id="toggle2-1" name="season" value="봄">
		    <label for="toggle2-1">봄</label>
		    <input type="radio" id="toggle2-2" name="season" value="여름">
		    <label for="toggle2-2">여름</label>
		    <input type="radio" id="toggle2-3" name="season" value="가을">
		    <label for="toggle2-3">가을</label>
		    <input type="radio" id="toggle2-4" name="season" value="겨울">
		    <label for="toggle2-4">겨울</label>
			</div>   
		    <div class="toggle3">
		    <h4>룩</h4>
		    <input type="radio" id="toggle3-1" name="look" value="데이트룩">
		    <label for="toggle3-1">데이트룩</label>
		    <input type="radio" id="toggle3-2" name="look" value="캠퍼스룩">
		    <label for="toggle3-2">캠퍼스룩</label>
		    <input type="radio" id="toggle3-3" name="look" value="데일리룩">
		    <label for="toggle3-3">데일리룩</label>
		    <input type="radio" id="toggle3-4" name="look" value="오피스룩">
		    <label for="toggle3-4">오피스룩</label>
		    <input type="radio" id="toggle3-5" name="look" value="하객룩">
		    <label for="toggle3-5">하객룩</label>
			</div>  
			<br><br>
			<button type="button" class="btn btn-lg btn-success " onclick="javascript:fn_selectLook();" >옷 고르러가기</button>
			<br><br>
            <input type="hidden" id="memberIdx" name="memberIdx" value="${userIdx}">
            </form>
            </c:if>
    	        <c:if test="${not empty resultFashion}">
        	    <c:forEach items="${resultFashion}" var="resultFashion" varStatus="status">
                         <input type="hidden" id="idx_${status.index}"  name="idx" value="${resultFashion.idx}" />
               </c:forEach>
				<form name="fashionForm"  >
                         <c:set var="fullURL" value="${pageContext.request.requestURL}"></c:set>
                 		 <c:set var="pathURI" value="${pageContext.request.requestURI}"></c:set>
                		 <c:set var="baseURL" value="${fn:replace(fullURL, pathURI, '')}"></c:set>
                         <input type="hidden" id="base"  name="base" value="${fn:replace(fullURL, pathURI, '')}" />
	            <ul class="gallery" >
               	<li id="choose">
    	        		<button type="button" class="btn btn-lg btn-success"   onclick="javascript:fn_moveImg();" style="margin-left: -30px;" >랜덤으로 고르기</button><br>
            	      	<img id="loading" src="" width="200px;"  style="margin-left: -30px;"><br>
            	      	<img id="loading2" src="" width="50px;"  style="margin-left: -30px;">
						<p id="weathercomment"></p>                        
                        <img class="file" id="randomImg" src="" width="500px"  style="margin-left: -30px;"/>
                </li>
                </ul>
                        <input type="hidden" id="memberFashionFile" name="memberFashionFile" value="" />
                        <input type="hidden" id="memberIdx" name="memberIdx" value="${userIdx}">
						<input type="hidden" id="rain" value="${resultRain}">
  					    <input type="hidden" id="sky" value="${resultSky}">
  					    <input type="hidden" id="tem" value="${resultTem}">

                	<div class="weatherLi">
		   			</div>
		   			
                </form>
			    </c:if>
               </div>
     <!-- 왼쪽 사이드 메뉴 스크립트 -->
<script>

 var img = false
 

function fn_moveImg(){
   if(img==false){
      img=true
      var timerId = setInterval(() => {document.getElementById('loading').src="/images/white.png";}, 200);
      var timerId1 = setInterval(() => {document.getElementById('loading').src="/images/로딩.jpg";}, 300);
      
      
   setTimeout(() => {fn_random()}, 3500); 
   setTimeout(() => clearInterval(timerId), 3500);
   setTimeout(() => clearInterval(timerId1), 3500);
   setTimeout(() => {fn_weathercomment();}, 3500);
   }
}
	
 function fn_weathercomment(){
     rain = $('#rain').val();
     sky = $('#sky').val();
     tem = $("#tem").val();
     
        if(tem < 4){
              if(rain == "비" || rain == "비/눈" || rain == "소나기"){
                document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  오늘 비가와요! 우산챙기세요~";
              document.getElementById('loading2').src="/images/comment/우산.png";   
              }
              else if(rain == "눈"){
              document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  오늘 눈이와요! 따뜻하게 입고가세요~";
              document.getElementById('loading2').src="/images/comment/눈.png";
              }
              else {
              document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃ !  오늘 날씨가 추워요 따뜻하게 입고가세요~";
              document.getElementById('loading2').src="/images/comment/한파.png";
              }
        }else if(tem >= 4 && tem < 11){
              if(rain == "비" || rain == "비/눈" || rain == "소나기"){
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃ !  오늘 비가와요! 우산챙기세요~";
                 document.getElementById('loading2').src="/images/comment/우산.png";   
                 }
                 else if (rain == "눈"){
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  오늘 눈이와요! 따뜻하게 입고가세요~";
                 document.getElementById('loading2').src="/images/comment/눈.png";
                 }
                 else {
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃ !  오늘 추워요!  따뜻하게 입고가세요~";
                 document.getElementById('loading2').src="/images/comment/추워요.png";
                 }
        }else if(tem >= 11 && tem < 18){
              if(rain == "비" || rain == "비/눈" || rain == "소나기"){
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  오늘 비가와요! 우산챙기세요~";
                 document.getElementById('loading2').src="/images/comment/우산.png";   
                 }
               else if (rain == "눈"){
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!   오늘 눈이와요! 따뜻하게 입고가세요~";
                 document.getElementById('loading2').src="/images/comment/눈.png";
                 }
               else {
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  쌀쌀한 날씨에요! ";
                 document.getElementById('loading2').src="/images/comment/쌀쌀.png";
                 }
        }
        else if(tem >= 18 && tem < 23){
              if(rain == "비" || rain == "비/눈" || rain == "소나기"){
                document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  오늘 비가와요! 우산챙기세요~";
                 document.getElementById('loading2').src="/images/comment/우산.png";   
                 }
              else{
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!   오늘은 따뜻한날씨에요~  ";
                 document.getElementById('loading2').src="/images/comment/봄.png";
                 }
        }
        else if(tem >= 23 && tem < 33){
              if(rain == "비" || rain == "비/눈" || rain == "소나기"){
                document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  오늘 비가와요! 우산챙기세요~";
                 document.getElementById('loading2').src="/images/comment/우산.png";   
                 }
              else{
                document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!   날씨가 더워요 ";
                 document.getElementById('loading2').src="/images/comment/더워요.png";
                 }
        }
        else if(tem >= 33 ){
              if(rain == "비" || rain == "비/눈" || rain == "소나기"){
                document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!  오늘 비가와요! 우산챙기세요~";
                 document.getElementById('loading2').src="/images/comment/우산.png";   
                 }
              else{
                 document.getElementById('weathercomment').innerHTML="오늘의 온도는"+tem+"℃!   폭염주의! ";
                 document.getElementById('loading2').src="/images/comment/폭염.png";
                 }
        }
  
  
  
  }
 
 
	
function fn_random() {
	   var num = Math.floor(Math.random() * 5);
	   
	   var imgArray = new Array();
	   imgArray[0] = document.getElementById('idx_0').value;
	   imgArray[1] = document.getElementById('idx_1').value;
	   imgArray[2] = document.getElementById('idx_2').value;
	   imgArray[3] = document.getElementById('idx_3').value;
	   imgArray[4] = document.getElementById('idx_4').value;
	   
	   
	  var randomNum= imgArray[num];
	   $("#idx").val(randomNum);
	   var randomImgStr =
		document.getElementById('base').value+ "/" + randomNum + ".png"
	   document.getElementById('randomImg').src= randomImgStr;
	  
	   document.getElementById('memberFashionFile').value=randomImgStr;
	   
	   if(document.getElementById('memberIdx').value != 0){
		   setTimeout(function() {
			 var returnValue = confirm("이 옷으로 결정하시겠습니까?");
			 if(returnValue){
				 var f = document.fashionForm;
					f.action = "/memberFashionPopup.do";
					f.submit();
					
			}
			else {
				alert("다시 선택해주세요!")
				history.back()
			}
			}, 1000);
	  }else{
		setTimeout(function(){
			alert("오늘 코디는 이걸로 결정! ");
			},1000);
		}
	   var location = document.querySelector("#randomImg").offsetTop;
	   window.scrollTo({top:location, behavior:'smooth'});
	} 
 
<%-- 로그인 --%>
function fn_selectLook(){
		var f = document.selectLook;
	 if(document.getElementById('memberIdx').value != 0){
		f.action = "/selectLookList.do";
		f.submit();
	 }else{
		f.action = "/noselectLookList.do";
		f.submit();
	 }
}
	 



</script>