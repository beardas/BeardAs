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
	<h1>상품 리스트</h1>
	<div>
		<br>
		<div>
		<button type="button" onclick="javascript:fn_reg();">등록</button>
		<button type="button" onclick="javascript:fn_update();">수정</button>
		<button type="button" onclick="javascript:fn_delete();">삭제</button>
		</div>
		
		<br>
		<button type="button" onclick="javascript:fn_home();">메인으로 돌아가기</button>
		
		<table border="1">
			<thead>
				<tr>
					<th><input type="checkbox" onclick="javascript:fn_checkboxAllCheck(this);"/></th>
					<th>번호</th>
					<th>품번</th>
					<th>상품명</th>
					<th>카테고리</th>
					<th>가격</th>
					<th>수량</th>
					<th>등록일자</th>
					<th>등록자</th>
				</tr>
			</thead>
			
			<tbody>
					<c:if test="${empty list}">
							<tr>
								<td colspan="10" class="text-center">등록된 상품이 없습니다.</td>
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
           					<td>스킨케어</td>
         				</c:when>
				        <c:when test = "${list.category eq 'cleansing'}">
				        	<td>클렌징/필링</td>
				        </c:when>
				        <c:when test = "${list.category eq 'mask'}">
				        	<td>마스크/팩</td>
				        </c:when>
				        <c:when test = "${list.category eq 'sun'}">
				        	<td>선케어</td>
				        </c:when>
				        <c:when test = "${list.category eq 'base'}">
				        	<td>베이스 메이크업</td>
				        </c:when>
				        <c:when test = "${list.category eq 'eye'}">
				        	<td>아이 메이크업</td>
				        </c:when>
				        <c:when test = "${list.category eq 'lip'}">
				        	<td>립 메이크업</td>
				        </c:when>
				        <c:when test = "${list.category eq 'body'}">
				        	<td>바디</td>
				        </c:when>
				        <c:when test = "${list.category eq 'hair'}">
				        	<td>헤어</td>
				        </c:when>
				        <c:when test = "${list.category eq 'nail'}">
				        	<td>네일</td>
				        </c:when>
				        <c:when test = "${list.category eq 'perfume'}">
				        	<td>향수</td>
				        </c:when>
				        <c:otherwise>
				            <td>기타</td>
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
	
	<!-- 페이징 -->
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


<%-- 체크박스 모든선택 --%>
function fn_checkboxAllCheck(obj){
	var chooseRecords = document.getElementsByName("chooseRecord");
	for(var i=0; i<chooseRecords.length; i++){
		chooseRecords[i].checked = obj.checked;
	}
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
		alert("삭제할 상품을 체크하세요.");
		return;
	}
	
	var f = document.detailForm;
	f.idxs.value = boardIdxs; <%--배열을 리턴--%>
	f.action = "/productDel";	<%--BoardController의 delete 메소드 실행--%>
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
		alert("수정할 상품을 체크하세요.");
		return;
		
	}

	var boardIdx = 0;
	var boardIdxs = document.getElementsByName("idx");
	if(chooseRecordCount > 1){
		alert("2개 이상 체크하셔서 체크한 상품 중 첫번째 상품 정보로 이동합니다.");
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

