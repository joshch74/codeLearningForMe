<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.flabel{
	cursor:pointer;
	border:1px solid black;
	}
	.finput{
	position: absolute;
	width:1px;
	height:1px;
	padding:0;
	margin:-1px;
	}
</style>
</head>
<body>
	<div align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="900px">
			<tr align="right" valign="middle">
				<td>${session_id.getUserid()}님환영합니다.<a
					href="${pageContext.request.contextPath}/member/MemberLogout.me">로그아웃</a>
			</tr>
		</table>
		<br>
		<hr>
		<c:set var="board" value="${requestScope.board}" />
		<c:set var="files" value="${requestScope.files}"/>
		<form enctype="multipart/form-data"
			action="${pageContext.request.contextPath}/board/BoardModifyOk.bo"
			method="post" name="boardform">
			<input type="hidden" name="boardnum" value="${board.getBoardnum()}">
			<table width="900px" border="0" cellspacing="0" cellpadding="0">
				<tr align="center" valign="middle">
					<td><h3>MVC 게시판</h3></td>
				</tr>
			</table>

			<table border="1" cellspacing="0" cellpadding="0" width="900px">
				<tr>
					<td>제목</td>
					<td><input value="${board.getBoardtitle()}" type="text"
						name="boardtitle" size="50" maxlength="100"></td>
				</tr>

				<tr>
					<td>글쓴이</td>
					<td><input readonly name="userid" type="text" size="10"
						value="${session_id.getUserid()}" maxlength="10"></td>
				</tr>

				<tr>
					<td>내용</td>
					<td><textarea name="boardcontents"
							style="width: 700px; height: 200px; resize: none;">${board.getBoardcontents()}</textarea>
					</td>
				</tr>
				<c:choose>
					<c:when test="${files != null }">
						<!-- c:forEach는 begin=0 ~ end=0 이면 한번 반복을 한다. -->
						<!-- for(int i=0;i<=0;i++) -->
						<c:forEach var="i" begin="0" end="1">
							<tr>
								<td>첨부파일${i+1}</td>
								<td>
								<c:choose>
									<%--${i<files.size()} : 이 게시글에 등록된 파일이 존재할 때 --%>
									<%-- files.size() : 현재 게시글에 등록된 파일의 개수 --%>
									<c:when test="${i<files.size()}">
										<p class="filename" name="filename${i+1}" id="file${i+1}">${files.get(i).getRealname()}</p>
										<input type="hidden" name="filename" value="${files.get(i).getRealname()}">
									</c:when>
									<c:otherwise>
										<p class="filename" name="filename${i+1}" id="file${i+1}"></p>
										<input type="hidden" name="filename">
									</c:otherwise>
								</c:choose>
								<!-- 라벨 클릭시 for 속성에 주어진 id 요소가 클릭된다. -->
								<label class="flabel" for="fileInput${i+1}">첨부하기</label>
								<!-- input file 요소는 스타일을 통해 숨겨놓은 상태 -->
								<input class="finput" name="file${i+1}" id="fileInput${i+1}" type="file">
								</td>
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
					<td><a href="javascript:sendit()">[수정완료]</a> <a
						href="${pageContext.request.contextPath}/board/BoardList.bo">[목록]</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
	<script>
		function sendit() {
			//검사하기(제목 비어있는지, 내용 비어있는지 등등)
			document.boardform.submit();
		}
		
		$(document).ready(function() {
			/* 클래스 명이 finput인것이 변화가 생겼다면 */
			$('.finput').change(function(e){
				/* $(this) : 변화가 생긴 그 객체(클래스 명이 finput인 어떤 요소) - 방금 파일을 첨부한 input file 요소 */
				/* prev()을 세번 썼으므로 변화 생긴 file 요소의 세번째 전 요소이다. */
				/* 그 요소의 text값을 안에 있는 것으로 바꾸어준다. */
				/* e.target.files[0].name : 선택한 파일의 파일명 추출 */
				$(this).prev().prev().prev().text(e.target.files[0].name);
				/* 
				선택한 파일의 이름을 Action에 보내주기 위해서 input hidden요소를 만들었고
				그 요소의 값도 파일명으로 설정해 준다.
				*/
				$(this).prev().prev().val(e.target.files[0].name);
			});
		});
		
		function cancleFile(filename) {
			if ($.browser.msie) {
				// input[name='   file1    ']     input[name='file1']
				$("input[name='" + filename + "']").replaceWith(
						("input[name='" + filename + "']").clone(true));
			} else {
				$("input[name='" + filename + "']").val("");
			}
		}
	</script>
</body>
</html>












