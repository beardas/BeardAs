<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
   .input-group{
      margin-top: 25px;
   }
</style>
<div class="container">
   <div>
      <form id="updateForm" name="updateForm" method="post" enctype="multipart/form-data">
         <input type="hidden" id="idx" name="idx" value="${resultVO.idx}" />
         <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">제목</span>
            <input type="text" id="title" name="title"  value="${resultVO.title}" class="form-control" placeholder="제목를 작성하세요." aria-describedby="basic-addon1">
         </div>
         <div class="input-group">
			<span class="input-group-addon" id="basic-addon1">작성자</span>
			<input type="text" id="writer" name="writer" class="form-control" value="${resultVO.writer}" disabled="disabled" aria-describedby="basic-addon1">
			<input type="hidden" id="writer" name="writer" class="form-control" value="${resultVO.writer}" />
		</div>
         <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">내용</span>
            <textarea  style="height: 300px" id="content" name="content" class="form-control" placeholder="내용을 작성하세요." aria-describedby="basic-addon1">${resultVO.content}</textarea>
         </div>
         <div class="input-group" style="float: left;">
            <span class="input-group-addon" id="basic-addon1">태그</span>
            <input type="text" id="tag" name="tag" value="${resultVO.tag}" class="form-control" placeholder="#태그를 작성하세요." aria-describedby="basic-addon1">
         </div>
      </form>
   </div>

   <div style="margin-top:15px;">
      <div>
         <button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:fn_list();">목록</button>
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
      f.action = "/updateBoard.do";
      f.submit();
   }
   
   function fn_list(){
		var f = document.insertForm;
		f.action = "/selectBoardList.do?size=10";
		f.submit();
	}


   <%-- 유효성 검사 --%>
   function fn_validation(){

      if(document.getElementById("title").value.length < 1) {
         alert("제목를 작성하세요.");
         document.getElementById("title").focus();
         return true;
      }else if(document.getElementById("content").value.length < 1) {
         alert("내용을 작성하세요.");
         document.getElementById("content").focus();
         return true;
      }
      
      return false;
   }

</script>