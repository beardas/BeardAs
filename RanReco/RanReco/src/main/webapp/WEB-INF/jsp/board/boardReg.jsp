<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.input-group{
		margin-top: 25px;
	}
</style>
<div class="container">
	<div>
		<form id="insertForm" name="insertForm" method="post" enctype="multipart/form-data">
		<div style="">
		<div class="input-group text-center" style="float: left;">
				<label class="btn btn-default">
				<img class="rg_i Q4LuWd" id="locationImage" src="/images/seoul.png" data-atf="true" width="150" height="150" onerror="this.src='/images/seoul.png'">
				    지역 사진 선택 <input type="file" id="locationImageAttachFile" name="locationImageAttachFile" hidden onchange="setThumbnail_location(event);" />
				</label>
			</div>
			<div class="input-group text-center" style="float: left;">
				<label class="btn btn-default">
				<img class="rg_i Q4LuWd" id="foodImage" src="/images/food.png" data-atf="true" width="150" height="150" onerror="this.src='/images/food.png'">
				    음식 사진 선택 <input type="file" id="foodImageAttachFile" name="foodImageAttachFile" hidden onchange="setThumbnail_food(event);" />
				</label>
			</div>
			<div class="input-group text-center" style="float: left;">
				<label class="btn btn-default">
				<img class="rg_i Q4LuWd" id="fashionImage" src="/images/fashion.png" data-atf="true" width="150" height="150" onerror="this.src='/images/fashion.png'">
				    옷차림 사진 선택 <input type="file" id="fashionImageAttachFile" name="fashionImageAttachFile" hidden onchange="setThumbnail_fashion(event);" />
				</label>
			</div>
		</div>
			<br>
			<div class="input-group" style="float: left;">
				<span class="input-group-addon" id="basic-addon1">제목</span>
				<input type="text" id="title" name="title" class="form-control" placeholder="제목를 작성하세요." aria-describedby="basic-addon1">
			</div>
			
			<div class="input-group" style="float: left;">
				<span class="input-group-addon" id="basic-addon1">내용</span>
				<textarea  style="height: 300px" id="content" name="content" class="form-control" placeholder="내용을 작성하세요." aria-describedby="basic-addon1"></textarea>
			</div>
			<div class="input-group" style="float: left;">
            <span class="input-group-addon" id="basic-addon1">태그</span>
            <input type="text" id="tag" name="tag" class="form-control" placeholder="#태그를 작성하세요." aria-describedby="basic-addon1">
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
		
		var f = document.insertForm;
		f.action = "/insertBoard.do";
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
		else if(document.getElementById("locationImageAttachFile").value == "") {
			alert("지역사진을 추가하세요.");
			document.getElementById("locationImageAttachFile").focus();
			return true;
		}
		else if(document.getElementById("foodImageAttachFile").value == "") {
			alert("음식사진을 추가하세요.");
			document.getElementById("foodImageAttachFile").focus();
			return true;
		}else if(document.getElementById("fashionImageAttachFile").value == "") {
			alert("옷차림사진을 추가하세요.");
			document.getElementById("fashionImageAttachFile").focus();
			return true;
		}
		
		return false;
	}
	
	<%-- 이미지 업로드시 이미지 미리보기 --%>
	function setThumbnail_location(event) { 
		var reader = new FileReader();

		reader.onload = function(event) { 
			document.getElementById("locationImage").setAttribute("src", event.target.result);
		};

		reader.readAsDataURL(event.target.files[0]); 
	}
	
	function setThumbnail_food(event) { 
		var reader = new FileReader();

		reader.onload = function(event) { 
			document.getElementById("foodImage").setAttribute("src", event.target.result);
		};

		reader.readAsDataURL(event.target.files[0]); 
	}
	
	function setThumbnail_fashion(event) { 
		var reader = new FileReader();

		reader.onload = function(event) { 
			document.getElementById("fashionImage").setAttribute("src", event.target.result);
		};

		reader.readAsDataURL(event.target.files[0]); 
	}

</script>
