<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="board" value="${requestScope.board}" />
	<c:set var="files" value="${requestScope.files}" />
	<c:set var="replyList" value="${requestScope.replyList}" />
	<c:if test="${session_id eq null}">
		<script>
			alert("로그인 후 이용하세요!");
			location
					.replace("${pageContext.request.contextPath}/member/MemberLogin.me");
		</script>
	</c:if>
	<div align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="900px">
			<tr align="right" valign="middle">
				<td>${session_id.getUserid()}님환영합니다.<a
					href="${pageContext.request.contextPath}/member/MemberLogout.me">로그아웃</a>
			</tr>
		</table>
		<br>
		<hr>
		<table width="900px" border="0" cellpading="0" cellspacing="0">
			<tr align="center" valign="middle">
				<td><h3>MVC 게시판</h3></td>
			</tr>
		</table>
		<table border="1" cellspacing="0" cellpadding="0" width="900px">
			<tr>
				<td>제목</td>
				<td>${board.getBoardtitle()}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${board.getUserid()}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${board.getBoardcontents()}</td>
			</tr>
			<c:choose>
				<c:when test="${files.size() != 0 and files!=null}">
					<!-- c:forEach는 begin=0 ~ end=0 이면 한번 반복을 한다. -->
					<!-- for(int i=0;i<=0;i++) -->
					<c:forEach var="i" begin="0" end="${files.size()-1}">
						<tr>
							<td>첨부파일${i+1}</td>
							<td><a
								href="${pageContext.request.contextPath}/board/FileDownload.bo?
							filename=${files.get(i).getFilename()}&realname=${files.get(i).getRealname()}">
									${files.get(i).getRealname()} <!-- button.png -->
							</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2">첨부 파일이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<table>
			<tr align="center" valign="middle">
				<c:if test="${board.getUserid() == session_id.getUserid()}">
					<td><a href="javascript:modifyBoard();">[수정]</a>
					<td><a href="javascript:deleteBoard();">[삭제]</a>
				</c:if>
				<td><a
					href="${pageContext.request.contextPath}/board/BoardList.bo">[목록]</a>
				</td>
			</tr>
		</table>
	</div>
	<!-- 댓글부분 -->
	<div align="center">
		<form
			action="${pageContext.request.contextPath}/board/BoardReplyOk.bo"
			method="post" name="replyForm">
			<input type="hidden" name="num" value="${board.getBoardnum()}">
			<table>
				<tr>
					<td>댓 글</td>
					<td><textarea style="resize: none; width: 700px; height: 70px"
							name="replycontents"></textarea></td>
					<td><a href="javascript:replySubmit()">[등록]</a></td>

				</tr>
			</table>
			<table>
				<c:choose>
					<c:when test="${replyList != null and fn:length(replyList)>0}">
						<c:set var="i" value="${0}"/>
						<c:forEach var="reply" items="${replyList}">
							<c:set var="i" value="${i+1}"/>
							<tr>
								<td>${reply.getUserid()}</td>
								<td><textarea name="reply${i}" id="${i}" readonly
										style="resize: none; width: 700px; height: 70px">${reply.getReplycontents()}</textarea></td>
								<td><c:if
										test="${reply.getUserid() eq session_id.getUserid()}">
										<a id="ready${i}" href="javascript:updateReply(${i})">[수정]</a> &nbsp;&nbsp;
										<a id="ok${i}" href="javascript:replyUpdateOk(${i},${board.getBoardnum()},${reply.getReplynum()})" style="display:none">[수정완료]</a>
										<a href="${pageContext.request.contextPath}/board/BoardReplyDeleteOk.bo?replynum=${reply.getReplynum()}&boardnum=${board.getBoardnum()}">[삭제]</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="3">댓글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</form>
	</div>



	<form action="" name="boardForm">
		<input type="hidden" name="num" value="${board.getBoardnum()}">
	</form>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	check = false;
	function replySubmit() {
		var textarea = $("textarea[name='replycontents']");
		if (textarea.val() == "") {
			alert("내용을 작성해 주세요.")
			textarea.focus()
			return false;
		}
		replyForm.submit();
	}
	function deleteBoard() {
		$("form[name='boardForm']").attr('action',
				"${pageContext.request.contextPath}/board/BoardDelete.bo")
		document.boardForm.submit();
	}

	function modifyBoard() {
		$("form[name='boardForm']").attr('action',
				"${pageContext.request.contextPath}/board/BoardModify.bo")
		document.boardForm.submit();
	}
	function updateReply(num){
		var textarea = $("textarea#"+num);
		var ready_a = $("a#ready"+num);
		var ok_a = $("a#ok"+num);
		
		if(!check){
			textarea.removeAttr("readonly");
			ok_a.css("display","inline");
			ready_a.css("display","none");
			check=true;
		}else{
			alert("수정중인 댓글이 있습니다.")
		}
	}
	function replyUpdateOk(num,boardnum,replynum){
		$("form[name='replyForm']").attr("action","${pageContext.request.contextPath}/board/BoardReplyModifyOk.bo?"
				+"replynum="+replynum+"&boardnum="+boardnum+"&num="+num);
		replyForm.submit();
	}
	
</script>
</html>







