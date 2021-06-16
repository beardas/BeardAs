<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
   .input-group{
      margin-top: 25px;
   }
</style>
<div class="container">
   <div>
      <form id="updateForm" name="updateForm" method="post" enctype="multipart/form-data">
         <div class="input-group">
         <input type="hidden" id="idx" name="idx" value="${resultVO.idx}" />
            <span class="input-group-addon" id="basic-addon1">제목</span>
            <input type="text" id="storeName" name="storeName"  value="${resultVO.storeName}" class="form-control" placeholder="제목를 작성하세요." aria-describedby="basic-addon1">
         </div>
			<input type="hidden" id="location" name="location" value="${resultVO.location}" />
         <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">내용</span>
            <textarea  style="height: 300px" id="address" name="address" class="form-control" placeholder="내용을 작성하세요." aria-describedby="basic-addon1">${resultVO.address}</textarea>
         </div>
      </form>
   </div>

   <div style="margin-top:15px;">
      <div>
         <button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:history.go(-1);">뒤로가기</button>
         <button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_submit();">저장</button>
      </div>
   </div>
      
</div>

<script>
   <%-- 등록 --%>
   function fn_submit(){
      if(fn_validation()){
         return;
      }
      
      var f = document.updateForm;
      f.action = "/updateFood.do";
      f.submit();
   }


   <%-- 유효성 검사 --%>
   function fn_validation(){

      if(document.getElementById("storeName").value.length < 1) {
         alert("제목를 작성하세요.");
         document.getElementById("storeName").focus();
         return true;
      }else if(document.getElementById("address").value.length < 1) {
         alert("내용을 작성하세요.");
         document.getElementById("address").focus();
         return true;
      }
      
      return false;
   }

</script>