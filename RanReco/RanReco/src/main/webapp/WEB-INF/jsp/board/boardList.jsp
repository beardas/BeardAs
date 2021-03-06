<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
html,body {width:100%;margin:0px;padding:0px;font-family:sans-serif;}
 ul, li{margin:0px;padding:0px; list-style:none;} 
 /* .container{width:100%;max-width:1400px;margin:0 auto;overflow:hidden;}  */
 .table{width:100%;max-width:1400px;margin:0 auto;overflow:hidden;} 
 .gallery {margin:50px -20px;box-sizing:border-box;width: 250px;} 
 .gallery:after {content:"";display:block;clear:both;visibility: hidden;overflow:hidden;}
 .container .gallery #rankIdx_0{position: relative; top:0px; float:left;box-sizing:border-box;padding:0;}
 .container .gallery #rankIdx_1{position: relative; top:100px; float:left;box-sizing:border-box;padding:0;margin-left: -150px;}
 .container .gallery #rankIdx_2{position: relative; top:-230px; float:left;box-sizing:border-box;padding:0;margin-left: 150px;}
 .container .gallery li img{width: 250px; }
 .container .gallery #rankIdx_0 .crown img{width: 120px;}
 @media (max-width:768px){ 
 .gallery {margin:50px -20px;box-sizing:border-box;width: 200px;} 
 .container .gallery #rankIdx_0{position: relative; top:opx; float:left;box-sizing:border-box;padding:0;}  
 .container .gallery li img{width: 200px;}
 .container .gallery #rankIdx_0 .crown img{width: 80px; }
 .container .gallery #rankIdx_1{position: relative; top:100px; float:left;box-sizing:border-box;padding:0;margin-left: -120px;}  
 .container .gallery #rankIdx_2{position: relative; top:-180px; float:left;box-sizing:border-box;padding:0;margin-left: 120px;}
 .mobile {display: none;}  
 }



</style>




<div class="container" style="margin-top:25px;">
<c:set var="admin" value="admin" />
   
   <div>
   <h2>명예의 전당!!</h2>
   
            <c:if test="${empty rankBoard}">
            <table class="table table-hover">
               <tr>
                  <td colspan="10" class="text-center">게시글이 없습니다.</td>
               </tr>
            </table>
            </c:if>
            
            
            
            <c:if test="${not empty rankBoard}">
         <div class="container" align="center">
                 <ul class="gallery">
               <c:forEach items="${rankBoard}" var="rankBoard" varStatus="status">
                     <p class="thmb" >
                        <li id="rankIdx_${status.index}" style="background-color: black; color: white;">
                        <input type="hidden" name="idx" id="rankIdx_${status.index}" value="${rankBoard.idx}" />
                        <div class="photo u-default-box">
                  <header class="photo__header">
                        <div class="img"><a class="idx">
                        <c:if test="${0 eq status.index}">
                        <div class="crown">
                        <img src="/images/crown.png"/>
                        </div>
                        </c:if>

                      <c:set var="fullURL" value="${pageContext.request.requestURL}"></c:set>
                 <c:set var="pathURI" value="${pageContext.request.requestURI}"></c:set>
                 <c:set var="baseURL" value="${fn:replace(fullURL, pathURI, '')}"></c:set>
                    <img id="main" src="${baseURL}/${rankBoard.foodFile}" onerror="this.src='images/defaultpic.png'" /></a></div>
                   <div style="padding-top: 10px"></div>
                   <div class="photo_user_info">
                     </div>
                   </header>
                   <div class="photo_post_image">
                  </div>
                   <div class="photo__info">
                  <div class="photo__actions">
                  <span class="photo__action"> 
                   
                   <!-- 좋아요(하트) 이미지 -->
                        <i class="fa fa-heart heart heart-clicked"></i>  ${rankBoard.likes} &nbsp;
                        <i class="fa fa-thumbs-down thumbs-down"></i>  ${rankBoard.hates} &nbsp;
                        <i class="fa fa-comment comment"></i>  ${rankBoard.replyCnt}
                  </span>
                  </div>
                  
               <div class="photo_caption">
               <span>
                     ${rankBoard.tag}
                  </span>
                  <div class="photo__username">
                     ${rankBoard.writer} &nbsp; <fmt:formatDate value="${rankBoard.regDate}" pattern="YYYY-MM-dd"/>
                  </div>
               </div>
               
               </div>
               </div>
               <div style="padding-top: 10px"></div>
                  </li>
               </c:forEach>
               </ul>
               </div>
            </c:if>
            
          
   <hr>
               
   </div>
   
   
   
   

   
   
    
   
   
   <br><br><br>
   
   <div id="boardSearchDiv" class="text-center" style="display:none;float:right;">
      <form id="searchForm" name="searchForm" method="post" class="navbar-form navbar-left" role="search">
         <input type="hidden" id="page" name="page" value="" />
         <input type="hidden" id="size" name="size" value="10" />
         <div class="form-group">
            <input type="text" id="title" name="title" class="form-control" placeholder="Search">
         </div>
         <button type="submit" class="btn btn-default" onclick="javascript:fn_searchSubmit();">Search</button>
      </form>
   </div>
   
   
   
   <div>
      <table class="table table-hover">
         <thead>
            <tr>
            <c:if test='${admin eq userAdmin}'>
               <th><input type="checkbox" onclick="javascript:fn_checkboxAllCheck(this);"/></th>
            </c:if>
               <th>번호</th>
               <th>사진</th>
               <th>제목</th>
               <th>작성자</th>
               <th class="mobile">작성일자<th>
            </tr>
         </thead>
         <tbody>
            <c:if test="${empty resultBoardList}">
               <tr>
                  <td colspan="10" class="text-center">게시글이 없습니다.</td>
               </tr>
            </c:if>
            <c:if test="${not empty resultBoardList}">
               <c:forEach items="${resultBoardList}" var="resultBoard" varStatus="status">
                     <tr>
                     <c:if test='${admin eq userAdmin}'>
                     <td><input type="checkbox" name="chooseRecord"/></td>
                     </c:if>
                     <td class="idx" id="idx_${status.index}" >${status.count}
                        <input type="hidden" id="idx_${status.index}" name="idx" value="${resultBoard.idx}" />
                     </td>
                     <td class="idx" id="idx_${status.index}">
                        <c:set var="fullURL" value="${pageContext.request.requestURL}"></c:set>
                        <c:set var="pathURI" value="${pageContext.request.requestURI}"></c:set>
                        <c:set var="baseURL" value="${fn:replace(fullURL, pathURI, '')}"></c:set>
                        <img src="${baseURL}/${resultBoard.foodFile}" width="150" height="150" onerror="this.src='images/defaultpic.png'"/>
                     </td>
                     <td class="idx" id="idx_${status.index}" >${resultBoard.title}&nbsp;<c:if test="${0 ne resultBoard.replyCnt}"><span class="badge">${resultBoard.replyCnt}</span></c:if>
                        <br><i class="fa fa-heart heart heart-clicked"></i>${resultBoard.likes}&nbsp; <i class="fa fa-thumbs-down thumbs-down"></i>${resultBoard.hates} &nbsp; <i class="fa fa-eye eye"></i>${resultBoard.cnt}  
                     
                     
                     </td>
                     <td class="idx" id="idx_${status.index}" >${resultBoard.writer}</td>
                     <td  class="mobile" id="idx_${status.index}" ><fmt:formatDate value="${resultBoard.regDate}" pattern="YYYY-MM-dd"/></td>
                     </tr>
               </c:forEach>
            </c:if>
         </tbody>
      </table>   
   </div>
   
   <c:if test="${not empty resultBoardList}">
   <div class="text-center">
      <c:set var="startPage" value="${pagingVO.startPage}" />
      <c:set var="endPage" value="${pagingVO.endPage}" />
      <c:set var="totalPage" value="${pagingVO.totalPage}" />
      <nav>
         <ul class="pagination">
         <c:if test="${resultBoard.number >= startPage}">
               <li>
                  <a href="javascript:fn_goPage('${resultBoard.number}');" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                  </li>
            </c:if>
               <c:forEach var="i" begin="${startPage}" end="${endPage}">
                  <c:if test="${i-1 eq resultBoard.number}">
                  <li class="active"><a href="javascript:fn_goPage('${i}');">${i}</a></li>
                  </c:if>
                  <c:if test="${i-1 ne resultBoard.number}">
                  <li><a href="javascript:fn_goPage('${i}');">${i}</a></li>
                  </c:if>
            </c:forEach>
            <c:if test="${resultBoard.number != totalPage-1}">
               <li>
                  <a href="javascript:fn_goPage('${resultBoard.number + 2}');" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
               </li>
            </c:if>
         </ul>
      </nav>
   </div>
   </c:if>
   
   <div>
   
      <c:if test='${admin eq userAdmin}'>
      <button type="button" class="btn btn-lg btn-danger pull-right" onclick="javascript:fn_delete();">삭제</button>
      </c:if>
      <button type="button" class="btn btn-lg btn-info pull-right" onclick="javascript:fn_search();">검색</button>
      <button type="button" class="btn btn-lg btn-primary pull-right" onclick="javascript:fn_reg();">등록</button>
   </div>

</div>

<form id="detailForm" name="detailForm" method="post">
   <input type="hidden" id="idxs" name="idxs" value="" />
</form>
<script>

   var boardIdx=null;
   // 얘랑 2시간 싸움...
   $("td[id^='idx_']").click(function(){
      boardIdx = $(this).attr("id");
      boardResult = $("input[id='"+boardIdx+"']").val();
   location.href = "/viewBoard.do?idx="+boardResult;
   });
   
   $("li[id^='rankIdx_']").click(function(){
      rankIdx = $(this).attr("id");
      rankResult = $("input[id='"+rankIdx+"']").val();
   location.href = "/viewBoard.do?idx="+rankResult;
   });
   

   <%-- 페이징_페이지 이동 --%>
   function fn_goPage(page){
      var f = document.searchForm;
      f.page.value = page-1;
      f.action = "/selectBoardList.do?size=10";
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
      location.href = "/fwdBoardReg.do";
   }

   <%-- 검색창 --%>
   function fn_search(){
      var boardSearchDiv = document.getElementById("boardSearchDiv");
      
      if(boardSearchDiv.style.display == "none"){
         boardSearchDiv.style.display = "block";
      }else{
         boardSearchDiv.style.display = "none";
      }
      
   }

   <%-- 검색 --%>
   function fn_searchSubmit(){
      var f = document.searchForm;
      f.action = "/selectBoardList.do";
      f.submit();
   }

   <%-- 삭제 --%>
   function fn_delete(){
      var boardIdxs = []; <%--배열--%>
      var chooseRecordCount = 0; <%--체크된 갯수 초기값 0 --%>
      var chooseRecords = document.getElementsByName("chooseRecord");  <%--체크박스--%>
      
      for(var i=0; i<chooseRecords.length; i++){ <%--체크박스 수 만큼 반복--%> <%--예) 10개 -> 10번 반복 --%> 
         if(chooseRecords[i].checked){ <%--그중에 체크 된게 있으면--%> <%--예) 0, 3, 4번째 체크박스가 체크 됐다치면 --%> 
            chooseRecordCount++; <%--체크된 만큼 체크 수 증가--%> <%-- 예) 3증가 --%>
            boardIdxs.push(document.getElementsByName("idx")[i].value); <%--${result.memberIdx}를 배열에 추가로 삽입--%>
                                                               <%--예) 0, 3, 4번째 회원번호를 배열로 받음--%>
         }
      }

      if(chooseRecordCount < 1){  <%-- 예) 3이니 알림문은 뜨지 않음 --%>
         alert("데이터를 선택하세요.");
         return;
      }
      
      var f = document.detailForm;
      f.idxs.value = boardIdxs; <%--배열을 리턴--%>
      f.action = "/deleteBoardByAdmin.do";   <%--BoardController의 delete 메소드 실행--%>
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

      var boardIdx = 0;
      var boardIdxs = document.getElementsByName("idx");
      if(chooseRecordCount > 1){
         alert("2개 이상 선택이기 때문에 첫번째 데이터값으로 이동합니다.");
         for(var i=0; i<chooseRecords.length; i++){
            chooseRecords[i].checked = false;
         }
         chooseRecords[0].checked = true;
         boardIdx = 0;
      }else{
         for(var i=0; i<chooseRecords.length; i++){
            if(chooseRecords[i].checked){
               boardIdx = i;
               break;
            }
         }
      }

      location.href = "/fwdBoardUpt.do?idx="+boardIdxs[boardIdx].value;
   }
</script>