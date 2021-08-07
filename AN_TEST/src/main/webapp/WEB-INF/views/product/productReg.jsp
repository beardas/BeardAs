<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<title>List</title>
	
	  <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/assets/css/custom/menu.css" rel="stylesheet">
  <!-- bxSilder -->
  <link href="/resources/assets/css/jquery.bxslider.css" rel="stylesheet">

  <!-- Library -->
  <script src="/resources/lib/js/jquery-3.4.1.min.js"></script>
  <script src="/resources/lib/js/bootstrap.bundle.min.js"></script>
  <!-- https://github.com/muzammilkm/jq-router -->
  <script src="/resources/lib/js/jq-router.min.js"></script>
  <!-- https://github.com/stevenwanderski/bxslider-4 -->
  <script src="/resources/lib/js/jquery.bxslider.min.js"></script>
	
	
  
</head>
	
<body>
	<div class="container">
	<div>

		<form id="insertForm" name="insertForm" method="post">
		<!-- <form id="insertForm" name="insertForm" method="post" enctype="multipart/form-data"> -->
			<!-- <div class="input-group text-center">
				<img class="rg_i Q4LuWd" id="userImage" src="./defaultpic.png" data-atf="true" width="150" height="150" onerror="this.src='images/defaultpic.png'">
				<label class="btn btn-default">
				    사진 선택 <input type="file" id="userImageAttachFile" name="userImageAttachFile" hidden onchange="setThumbnail(event);" />
				</label>
			</div> -->
			
			<h2>상품 정보 등록</h2>
			<br>
			
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">카테고리</span>
				<select id="category" name="category" style="padding: .4em">
				<option value="skin">스킨케어</option>
				<option value="cleansing">클렌징/필링</option>
				<option value="mask">마스크/팩</option>
				<option value="sun">선케어</option>
				<option value="base">베이스 메이크업</option>
				<option value="eye">아이 메이크업</option>
				<option value="lip">립 메이크업</option>
				<option value="body">바디</option>
				<option value="hair">헤어</option>
				<option value="nail">네일</option>
				<option value="perfume">향수</option>
				<option value="others">기타</option>
				</select>
			</div>
			<small><p style="color: gray;">카테고리를 선택하세요.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">상품코드</span>
				<input type="text" id="uuid" name="uuid" class="form-control" placeholder="상품코드를 입력하세요." aria-describedby="basic-addon1" maxlength='3'>
			</div>
			<small><p style="color: gray;">영문 3자만 입력가능합니다.</p></small>
				
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">상품명</span>
				<input type="text" id="productName" name="productName" class="form-control" placeholder="상품명을 입력하세요." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">상품명을 입력하세요.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">가격</span>
				<input type="text" id="price" name="price" class="form-control" placeholder="가격을 입력하세요." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">숫자만 입력가능합니다.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">수량</span>
				<input type="text" id="cnt" name="cnt" class="form-control" placeholder="수량을 입력하세요." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">숫자만 입력가능합니다.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">작성자</span>
				<input type="text" id="writer" name="writer" class="form-control" placeholder="작성자를 입력하세요." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">작성자를 입력하세요.</p></small>
			
		</form>
	</div>

	<div style="margin-top:15px;">
		<div>
			<button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:history.go(-1);">뒤로가기</button>
			<button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_submit();">등록</button>
			<!-- <button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_test();">확인</button> -->
		</div>
	</div>
		
</div>

</body>

<script>
	<%-- 등록 --%>
	function fn_submit(){
		if(fn_validation()){
			return;
		}
		
		var f = document.insertForm;
		f.action = "/insertProduct";
		f.submit();
	}
	
	<%-- function fn_test(){
		if(fn_validation()){
			return;
		}
		
		console.log($('select[name=category] option:selected').val());
		console.log($('input[name=uuid]').val());
		console.log($('input[name=productName]').val());
		console.log($('input[name=price]').val());
		console.log($('input[name=cnt]').val());
		console.log($('input[name=writer]').val());
	} --%>
	

	<%-- 유효성 검사 --%>
	function fn_validation(){
		
		var UuidReg = /^[A-Za-z]{3}$/;
		var NumberReg = /^[0-9]+$/;
		
		document.getElementById("uuid").value = $('#uuid').val().replace(/ /gi, '').toUpperCase();
		document.getElementById("writer").value = $('#writer').val().replace(/ /gi, '');
		document.getElementById("price").value = $('#price').val().replace(/ /gi, '');
		document.getElementById("cnt").value = $('#cnt').val().replace(/ /gi, '');
		document.getElementById("productName").value = $('#productName').val().trim();
		
		if(document.getElementById("uuid").value.length < 1) {
			alert("품번을 입력하세요.");
			document.getElementById("uuid").focus();
			return true;
		}else if(!UuidReg.test(document.getElementById("uuid").value)){
			alert("상품코드는 영문3자만 입력 가능합니다.");
			document.getElementById("uuid").focus();
			return true;
		}else if(document.getElementById("productName").value.length < 1){
			alert("상품명을 입력하세요.");
			return true;
		}else if(!NumberReg.test(document.getElementById("price").value)){
			alert("가격은 숫자만 입력 가능합니다.");
			document.getElementById("price").focus();
			return true;
		}else if(!NumberReg.test(document.getElementById("cnt").value)){
			alert("수량은 숫자만 입력 가능합니다.");
			document.getElementById("cnt").focus();
			return true;
		}else if(document.getElementById("writer").value.length < 1) {
			alert("작성자를 입력하세요.");
			document.getElementById("writer").focus();
			return true;
		}
		
		return false;
	}

	<%-- 이미지 업로드시 이미지 미리보기 --%>
	/* function setThumbnail(event) { 
		var reader = new FileReader();

		reader.onload = function(event) { 
			document.getElementById("userImage").setAttribute("src", event.target.result);
		};

		reader.readAsDataURL(event.target.files[0]); 
	}
	
	$( function() {
	    $( "#birth" ).datepicker();
	} ); */
</script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">




</html>

