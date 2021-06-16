<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<style>
	.input-group{
		margin-top: 25px;
	}
</style>

<div class="container">
	<div>
		<div>
		<table class="table table-hover">
				<tr>
					<th style="width: 20%; ">글 번호</th>
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
				<tr style="height: 200px">
					<th style="vertical-align: top;">내용</th>
					<td>${resultView.content}</td>
				</tr>
				</table>
				
				<table>
				<tr>
					<c:set var="fullURL" value="${pageContext.request.requestURL}"></c:set>
					<c:set var="pathURI" value="${pageContext.request.requestURI}"></c:set>
					<c:set var="baseURL" value="${fn:replace(fullURL, pathURI, '')}"></c:set>
					<td width="200px;">
					<img src="${baseURL}/${resultView.locationFile}" width="150" height="150" onerror="this.src='images/defaultpic.png'"/>
					</td>
					<td width="200px;">
					<img src="${baseURL}/${resultView.foodFile}" width="150" height="150" onerror="this.src='images/defaultpic.png'"/>
					</td>
					<td  width="200px;">
					<img src="${baseURL}/${resultView.fashionFile}" width="150" height="150" onerror="this.src='images/defaultpic.png'"/>
					</td>
				</tr>
				</table>
				
				<br>
				<table>
				<tr>
					<th>조회수</th>
							<td><i class="fa fa-eye eye"></i>${resultView.cnt}&nbsp;&nbsp;&nbsp;</td>
					<th>좋아요</th>
							<td>
							<c:set var="boardInfo" value="${resultView.idx},${userIdx},${resultView.boardLike}" />
									<c:choose>
									<c:when test="${empty resultView.boardLike || resultView.boardLike == null}">
										<button type="button" style="float:left; color: transparent; text-shadow: 0 0 2px rgba(255,255,255,.7), 0 0 0 #000;" class="btn_boardLike" id="btn_boardLike" value="${boardInfo }">
										❤&nbsp;${resultView.likes}
										</button>
									</c:when>
									<c:when test="${resultView.boardLike eq 'T'}">
										<button type="button" style="float:left; color: #f00; text-shadow: 0;" class="btn_boardLike" id="btn_boardLike" value="${boardInfo}">
										❤&nbsp;${resultView.likes}
										</button>
									</c:when>
									<c:otherwise>
										<button type="button" style="float:left; color: transparent; text-shadow: 0 0 2px rgba(255,255,255,.7), 0 0 0 #000;" class="btn_boardLike" id="btn_boardLike" value="${boardInfo}">
										❤&nbsp;${resultView.likes}
										</button>
									</c:otherwise>
									</c:choose>
							
							
							
							</td>
					<th>&nbsp;&nbsp;&nbsp;싫어요</th>
							<td>
							
							<c:choose>
									<c:when test="${empty resultView.boardLike || resultView.boardLike == null}">
										<button type="button" style="float:left; color: transparent; text-shadow: 0 0 2px rgba(255,255,255,.7), 0 0 0 #000;" class="btn_boardHate" id="btn_boardHate" value="${boardInfo }">
										❌&nbsp;${resultView.hates}
										</button>
									</c:when>
									<c:when test="${resultView.boardLike eq 'T'}">
										<button type="button" style="float:left; color: transparent; text-shadow: 0 0 2px rgba(255,255,255,.7), 0 0 0 #000;" class="btn_boardHate" id="btn_boardHate" value="${boardInfo }">
										❌&nbsp;${resultView.hates}
										</button>
									</c:when>
									<c:otherwise>
										<button type="button" style="float:left; color: #f00; text-shadow: 0;" class="btn_boardHate" id="btn_boardHate" value="${boardInfo}">
										❌&nbsp;${resultView.hates}
										</button>
									</c:otherwise>
									</c:choose>
							
							
							</td>
					<th>&nbsp;&nbsp;&nbsp;&nbsp;작성일자</th>
							<td>${resultView.regDate}</td>
				</tr>
		</table>	
	</div>
	</div>

	<div style="margin-top:15px;">
		<div>
		<c:set var="board_writer" value="${resultView.writer}" />
		<c:if test='${board_writer eq userName}'>
			<button type="button" class="btn btn-lg btn-danger pull-right" onclick="javascript:fn_delete();">삭제</button>
			<button type="button" class="btn btn-lg btn-success pull-right" onclick="javascript:fn_update();">수정</button>
		</c:if>
			<button type="button" class="btn btn-lg btn-warning pull-right" onclick="javascript:fn_list();">목록</button>
		</div>
	</div>
	<!-- 댓글 -->
	<br>
	<br>
	<br>
<hr>
<!-- 게시글 하단부(댓글) -->

<div class="panel panel-default">
			<div class="panel-footer">댓글 리스트</div>
			<c:if test="${empty replyList}">
			<ul id="reply-box" class="list-group">
				<li id="reply" class="list-group-item text-justify">
					<div>댓글이 없습니다.</div>
				</li>
			</ul>
			</c:if>
			<c:if test="${!empty replyList}">
			<ul id="reply-box" class="list-group">
				<c:forEach var="reply" items="${replyList}">
				<li id="reply" class="list-group-item text-justify">
			<form action="deleteReply.do" method="post">
				<input type="hidden" name="idx" value="${reply.idx}">
				<input type="hidden" name="boardIdx" value="${reply.boardIdx}">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div>작성자 : ${reply.writer} &nbsp; ${reply.regDate} &nbsp; 
						<c:set var="reply_writer_idx" value="${reply.memberIdx}" />
						<c:if test="${reply_writer_idx eq userIdx}">
						<button type="submit" class="badge">삭제</button>
						</c:if>
						
				
					
					
					<c:set var="replyInfo" value="${reply.idx},${reply.boardIdx},${userIdx},${reply.reply_like}" />
									<c:choose>
									<c:when test="${empty reply.reply_like || reply.reply_like == null}">
										<button type="button" style="float: right;" class="btn_hate" id="like_hate_btn" value="${replyInfo }">
										<img alt="싫어요" style="width: 25px;" src="images/hate_btn_normal.png" id="like_hate_img">&nbsp;${reply.hatesCnt}
										</button>
										<button type="button" style="float: right;" class="btn_like" id="like_hate_btn" value="${replyInfo }">
										<img alt="좋아요" style="width: 25px;" src="images/like_btn_normal.png" id="like_hate_img">&nbsp;${reply.likesCnt}
										</button>
									</c:when>
									<c:when test="${reply.reply_like eq 'T'}">
										<button type="button" style="float: right;" class="btn_hate" id="like_hate_btn" value="${replyInfo }">
										<img alt="싫어요" style="width: 25px;" src="images/hate_btn_normal.png" id="like_hate_img">&nbsp;${reply.hatesCnt}
										</button>
										<button type="button" style="float: right;" class="btn_like" id="like_hate_btn" value="${replyInfo }">
										<img alt="좋아요" style="width: 25px;" src="images/like_btn_light.png" id="like_hate_img">&nbsp;${reply.likesCnt}
										</button>
									</c:when>
									<c:otherwise>
										<button type="button" style="float: right;" class="btn_hate" id="like_hate_btn" value="${replyInfo}">
										<img alt="싫어요" style="width: 25px;" src="images/hate_btn_light.png" id="like_hate_img">&nbsp;${reply.hatesCnt}
										</button>
										<button type="button" style="float: right;" class="btn_like" id="like_hate_btn" value="${replyInfo}">
										<img alt="좋아요" style="width: 25px;" src="images/like_btn_normal.png" id="like_hate_img">&nbsp;${reply.likesCnt}
										</button>
									</c:otherwise>
									</c:choose>
									</div>
									</div>
				</form>
					</li>
				</c:forEach>
			</ul>
			</c:if>
			
		</div>
 
									
						
			<form action="writeReply.do" method="post">
		<div class="panel panel-default">
			<input type="hidden" name="boardIdx" value="${resultView.idx}">
			<input type="hidden" name="memberIdx" value="${userIdx}">
			<input type="hidden" name="writer" value="${userName}">
			<div class="panel-body"><textarea class="form-control" row="1" name="content" placeholder="댓글을 입력하세요."></textarea></div>
			<div class="panel-footer" ><button type="submit" class="btn btn-primary">등록</button>
			<a href="#top" style="text-align: right;"><button style="float: right;"  type="button" class="btn btn-outline-dark">▲ TOP</button></a>
			</div>
		</div>
		</form>
		
		
	</div>


<script type="text/javascript">
var board_idx = '${resultView.idx}';
function fn_delete(){
	location.href = "/deleteBoard.do?idx="+board_idx;
}

function fn_update(){
	location.href = "/fwdBoardUpt.do?idx="+board_idx;
}

function fn_list(){
	location.href = "/selectBoardList.do?size=10";
}


$(function(){
	$(".btn_like").click(function(){
		var reply = $(this).attr('value');
		var infoArray = reply.split(",");
		var reply_idx = infoArray[0];
		var board_idx = infoArray[1];
		var member_idx = infoArray[2];
		var reply_like = infoArray[3];
		
		var replyInfo = {"reply_idx" : reply_idx, "board_idx" : board_idx, "member_idx" : member_idx, "reply_like" : reply_like};
		
		$.ajax({
			type:"post",
			async:false,
			data : replyInfo,
			dataType : "text",
			url:"replyLike.do",
			success:function (data){
				if(data == "N"){
					alert("로그인을 해주세요.");
				}else{
					window.location.reload();
				}
			},
			error:function(request, status, error){
				alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n"+"error : " + error);
			},
			complete:function(data){
				console.log("완료! : " + data);
			}
		});
		
	});
	
	$(".btn_hate").click(function(){
		var reply = $(this).attr('value');
		var infoArray = reply.split(",");
		var reply_idx = infoArray[0];
		var board_idx = infoArray[1];
		var member_idx = infoArray[2];
		var reply_like = infoArray[3];
		
		var replyInfo = {"reply_idx" : reply_idx, "board_idx" : board_idx, "member_idx" : member_idx, "reply_like" : reply_like};
		
		$.ajax({
			type:"post",
			async:false,
			data : replyInfo,
			dataType : "text",
			url:"replyHate.do",
			success:function (data){
				if(data == "N"){
					alert("로그인을 해주세요.");
				}else{
					window.location.reload();
				}
			},
			error:function(request, status, error){
				alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n"+"error : " + error);
			},
			complete:function(data){
				console.log("완료! : " + data);
			}
		});
		
	});
});


$(function(){
	$(".btn_boardLike").click(function(){
		var board = $(this).attr('value');
		var infoArray = board.split(",");
		var board_idx = infoArray[0];
		var member_idx = infoArray[1];
		var board_like = infoArray[2];
		
		var boardInfo = {"board_idx" : board_idx, "member_idx" : member_idx, "boardLike" : board_like};
		
		$.ajax({
			type:"post",
			async:false,
			data : boardInfo,
			dataType : "text",
			url:"boardLike.do",
			success:function (data){
				if(data == "N"){
					alert("로그인을 해주세요.");
				}else{
					window.location.reload();
				}
			},
			error:function(request, status, error){
				alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n"+"error : " + error);
			},
			complete:function(data){
				console.log("완료! : " + data);
			}
		});
		
	});
	
	$(".btn_boardHate").click(function(){
		var board = $(this).attr('value');
		var infoArray = board.split(",");
		var board_idx = infoArray[0];
		var member_idx = infoArray[1];
		var board_like = infoArray[2];
		
		var boardInfo = {"board_idx" : board_idx, "member_idx" : member_idx, "boardLike" : board_like};
		
		$.ajax({
			type:"post",
			async:false,
			data : boardInfo,
			dataType : "text",
			url:"boardHate.do",
			success:function (data){
				if(data == "N"){
					alert("로그인을 해주세요.");
				}else{
					window.location.reload();
				}
			},
			error:function(request, status, error){
				alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n"+"error : " + error);
			},
			complete:function(data){
				console.log("완료! : " + data);
			}
		});
		
	});
});



</script>



<script src="//code.jquery.com/jquery.min.js"></script>
