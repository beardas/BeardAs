<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.input-group{
		margin-top: 25px;
	}
</style>
<div class="container">
	<div>
		<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th style="width: 20%;">글 번호</th>
					<td>${resultView.idx}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${resultView.title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${resultView.writer}</td>
				</tr>
				<tr style="height: 300px">
					<th style="vertical-align: top;">내용</th>
					<td>${resultView.content}</td>
				</tr>
				<tr>
					<td><br><br></td>
				</tr>
				<tr>
							<td>조회수 &nbsp; ${resultView.cnt}</td>
					
							<td>작성일자 &nbsp; ${resultView.regDate}</td>
				</tr>
			</thead>
		</table>	
	</div>
	</div>

	<div style="margin-top:15px;">
		<div>
		<c:set var="admin" value="관리자" />
		<c:if test='${admin eq userAdmin}'>
			<button type="button" class="btn btn-lg btn-danger pull-right" onclick="javascript:fn_delete();">삭제</button>
			<button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_update();">수정</button>
		</c:if>
			<button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:fn_list();">목록</button>
		</div>
		</div>
	</div>
		

<script type="text/javascript">
var notice_idx = '${resultView.idx}';
function fn_delete(){
	location.href = "/deleteNotice.do?idx="+notice_idx;
}

function fn_update(){
	location.href = "/fwdNoticeUpt.do?idx="+notice_idx;
}

function fn_list(){
	location.href = "/selectNoticeList.do";
}
</script>
