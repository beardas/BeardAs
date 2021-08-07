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
				    ���� ���� <input type="file" id="userImageAttachFile" name="userImageAttachFile" hidden onchange="setThumbnail(event);" />
				</label>
			</div> -->
			
			<h2>��ǰ ���� ���</h2>
			<br>
			
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">ī�װ�</span>
				<select id="category" name="category" style="padding: .4em">
				<option value="skin">��Ų�ɾ�</option>
				<option value="cleansing">Ŭ��¡/�ʸ�</option>
				<option value="mask">����ũ/��</option>
				<option value="sun">���ɾ�</option>
				<option value="base">���̽� ����ũ��</option>
				<option value="eye">���� ����ũ��</option>
				<option value="lip">�� ����ũ��</option>
				<option value="body">�ٵ�</option>
				<option value="hair">���</option>
				<option value="nail">����</option>
				<option value="perfume">���</option>
				<option value="others">��Ÿ</option>
				</select>
			</div>
			<small><p style="color: gray;">ī�װ��� �����ϼ���.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">��ǰ�ڵ�</span>
				<input type="text" id="uuid" name="uuid" class="form-control" placeholder="��ǰ�ڵ带 �Է��ϼ���." aria-describedby="basic-addon1" maxlength='3'>
			</div>
			<small><p style="color: gray;">���� 3�ڸ� �Է°����մϴ�.</p></small>
				
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">��ǰ��</span>
				<input type="text" id="productName" name="productName" class="form-control" placeholder="��ǰ���� �Է��ϼ���." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">��ǰ���� �Է��ϼ���.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">����</span>
				<input type="text" id="price" name="price" class="form-control" placeholder="������ �Է��ϼ���." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">���ڸ� �Է°����մϴ�.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">����</span>
				<input type="text" id="cnt" name="cnt" class="form-control" placeholder="������ �Է��ϼ���." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">���ڸ� �Է°����մϴ�.</p></small>
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">�ۼ���</span>
				<input type="text" id="writer" name="writer" class="form-control" placeholder="�ۼ��ڸ� �Է��ϼ���." aria-describedby="basic-addon1">
			</div>
			<small><p style="color: gray;">�ۼ��ڸ� �Է��ϼ���.</p></small>
			
		</form>
	</div>

	<div style="margin-top:15px;">
		<div>
			<button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:history.go(-1);">�ڷΰ���</button>
			<button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_submit();">���</button>
			<!-- <button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_test();">Ȯ��</button> -->
		</div>
	</div>
		
</div>

</body>

<script>
	<%-- ��� --%>
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
	

	<%-- ��ȿ�� �˻� --%>
	function fn_validation(){
		
		var UuidReg = /^[A-Za-z]{3}$/;
		var NumberReg = /^[0-9]+$/;
		
		document.getElementById("uuid").value = $('#uuid').val().replace(/ /gi, '').toUpperCase();
		document.getElementById("writer").value = $('#writer').val().replace(/ /gi, '');
		document.getElementById("price").value = $('#price').val().replace(/ /gi, '');
		document.getElementById("cnt").value = $('#cnt').val().replace(/ /gi, '');
		document.getElementById("productName").value = $('#productName').val().trim();
		
		if(document.getElementById("uuid").value.length < 1) {
			alert("ǰ���� �Է��ϼ���.");
			document.getElementById("uuid").focus();
			return true;
		}else if(!UuidReg.test(document.getElementById("uuid").value)){
			alert("��ǰ�ڵ�� ����3�ڸ� �Է� �����մϴ�.");
			document.getElementById("uuid").focus();
			return true;
		}else if(document.getElementById("productName").value.length < 1){
			alert("��ǰ���� �Է��ϼ���.");
			return true;
		}else if(!NumberReg.test(document.getElementById("price").value)){
			alert("������ ���ڸ� �Է� �����մϴ�.");
			document.getElementById("price").focus();
			return true;
		}else if(!NumberReg.test(document.getElementById("cnt").value)){
			alert("������ ���ڸ� �Է� �����մϴ�.");
			document.getElementById("cnt").focus();
			return true;
		}else if(document.getElementById("writer").value.length < 1) {
			alert("�ۼ��ڸ� �Է��ϼ���.");
			document.getElementById("writer").focus();
			return true;
		}
		
		return false;
	}

	<%-- �̹��� ���ε�� �̹��� �̸����� --%>
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

