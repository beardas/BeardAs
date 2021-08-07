<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
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
	<h1>��ǰ ����Ʈ</h1>
	<div>
		<br>
		<div>
		<button type="button" onclick="javascript:fn_reg();">���</button>
		<button type="button" onclick="javascript:fn_update();">����</button>
		<button type="button" onclick="javascript:fn_delete();">����</button>
		</div>
		
		<br>
		<button type="button" onclick="javascript:fn_home();">�������� ���ư���</button>
		
		<table border="1">
			<thead>
				<tr>
					<th><input type="checkbox" onclick="javascript:fn_checkboxAllCheck(this);"/></th>
					<th>��ȣ</th>
					<th>ǰ��</th>
					<th>��ǰ��</th>
					<th>ī�װ�</th>
					<th>����</th>
					<th>����</th>
					<th>�������</th>
					<th>�����</th>
				</tr>
			</thead>
			
			<tbody>
					<c:if test="${empty list}">
							<tr>
								<td colspan="10" class="text-center">��ϵ� ��ǰ�� �����ϴ�.</td>
							</tr>
					</c:if>
					<c:if test="${not empty list}">
					<c:forEach items="${list}" var="list" varStatus="status">
						<tr>
						<td><input type="checkbox" name="chooseRecord"/></td>
						<td class="idx" id="idx_${status.index}">${status.count}
						<input type="hidden" id="idx_${status.index}"  name="idx" value="${list.idx}" />
						</td>
						<td>${list.uuid}</td>
						<td>${list.productName}</td>
						
						<c:choose>
         				<c:when test = "${list.category eq 'skin'}">
           					<td>��Ų�ɾ�</td>
         				</c:when>
				        <c:when test = "${list.category eq 'cleansing'}">
				        	<td>Ŭ��¡/�ʸ�</td>
				        </c:when>
				        <c:when test = "${list.category eq 'mask'}">
				        	<td>����ũ/��</td>
				        </c:when>
				        <c:when test = "${list.category eq 'sun'}">
				        	<td>���ɾ�</td>
				        </c:when>
				        <c:when test = "${list.category eq 'base'}">
				        	<td>���̽� ����ũ��</td>
				        </c:when>
				        <c:when test = "${list.category eq 'eye'}">
				        	<td>���� ����ũ��</td>
				        </c:when>
				        <c:when test = "${list.category eq 'lip'}">
				        	<td>�� ����ũ��</td>
				        </c:when>
				        <c:when test = "${list.category eq 'body'}">
				        	<td>�ٵ�</td>
				        </c:when>
				        <c:when test = "${list.category eq 'hair'}">
				        	<td>���</td>
				        </c:when>
				        <c:when test = "${list.category eq 'nail'}">
				        	<td>����</td>
				        </c:when>
				        <c:when test = "${list.category eq 'perfume'}">
				        	<td>���</td>
				        </c:when>
				        <c:otherwise>
				            <td>��Ÿ</td>
				        </c:otherwise>
				        </c:choose>
						
						<td>${list.price}</td>
						<td>${list.cnt}</td>
						<td>${list.regDate}</td>
						<td>${list.writer}</td>
					</tr>
					</c:forEach>
					</c:if>
			</tbody>
		</table>
	</div>
	
	<!-- ����¡ -->
	<c:if test="${not empty list}">
	<c:forEach var="i" begin="1" end="${total / 10 + 1}" >
		<a href="http://localhost:9994/productList?pageNum=${i}">[${i}]</a>
	</c:forEach>
	</c:if>
	
	
	<form id="detailForm" name="detailForm" method="post">
	<input type="hidden" id="idxs" name="idxs" value="" />
	</form>
</body>


<script type="text/javascript">
function fn_home() {
	location.href = "/";
}

function fn_reg() {
	location.href = "/productReg";
}


<%-- üũ�ڽ� ��缱�� --%>
function fn_checkboxAllCheck(obj){
	var chooseRecords = document.getElementsByName("chooseRecord");
	for(var i=0; i<chooseRecords.length; i++){
		chooseRecords[i].checked = obj.checked;
	}
}

<%-- ���� --%>
function fn_delete(){
	var boardIdxs = []; <%--�迭--%>
	var chooseRecordCount = 0; <%--üũ�� ���� �ʱⰪ 0 --%>
	var chooseRecords = document.getElementsByName("chooseRecord");  <%--üũ�ڽ�--%>
	
	for(var i=0; i<chooseRecords.length; i++){ <%--üũ�ڽ� �� ��ŭ �ݺ�--%> <%--��) 10�� -> 10�� �ݺ� --%> 
		if(chooseRecords[i].checked){ <%--���߿� üũ �Ȱ� ������--%> <%--��) 0, 3, 4��° üũ�ڽ��� üũ �ƴ�ġ�� --%> 
			chooseRecordCount++; <%--üũ�� ��ŭ üũ �� ����--%> <%-- ��) 3���� --%>
			boardIdxs.push(document.getElementsByName("idx")[i].value); <%--${result.memberIdx}�� �迭�� �߰��� ����--%>
																				<%--��) 0, 3, 4��° ȸ����ȣ�� �迭�� ����--%>
		}
	}

	if(chooseRecordCount < 1){  <%-- ��) 3�̴� �˸����� ���� ���� --%>
		alert("������ ��ǰ�� üũ�ϼ���.");
		return;
	}
	
	var f = document.detailForm;
	f.idxs.value = boardIdxs; <%--�迭�� ����--%>
	f.action = "/productDel";	<%--BoardController�� delete �޼ҵ� ����--%>
	f.submit();
}

<%-- ���������� �̵� --%>
function fn_update(){
	var chooseRecordCount = 0;
	var chooseRecords = document.getElementsByName("chooseRecord");
	
	for(var i=0; i<chooseRecords.length; i++){
		if(chooseRecords[i].checked){
			chooseRecordCount++;
		}
	}

	if(chooseRecordCount < 1){
		alert("������ ��ǰ�� üũ�ϼ���.");
		return;
		
	}

	var boardIdx = 0;
	var boardIdxs = document.getElementsByName("idx");
	if(chooseRecordCount > 1){
		alert("2�� �̻� üũ�ϼż� üũ�� ��ǰ �� ù��° ��ǰ ������ �̵��մϴ�.");
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

	location.href = "/productUpd?idx="+boardIdxs[boardIdx].value;
}

</script>

</html>

