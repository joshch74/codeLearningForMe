<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<!--
	Scalar by Pixelarity
	pixelarity.com | hello@pixelarity.com
	License: pixelarity.com/license
-->
<html>
<head>
<title>Untitled</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
</head>
<body>
	<c:if test="${session_id eq null}">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace("${pageContext.request.contextPath}/member/MemberLogin.me");
		</script>
	</c:if>
	<c:if test="${param.result == false }">
		<script>
			alert('게시글 등록 실패');
		</script>
	</c:if>
	<c:set var="list" value="${requestScope.boardList}" />
	<c:set var="nowPage" value="${requestScope.nowPage}" />
	<c:set var="startPage" value="${requestScope.startPage }" />
	<c:set var="endPage" value="${requestScope.endPage }" />
	<c:set var="totalCnt" value="${requestScope.totalCnt }" />
	<c:set var="totalPage" value="${requestScope.totalPage }" />
	<!-- 로그인 정보 -->
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="right" valign="middle">
			<td>${session_id.getUserid()}님환영합니다.<a
				href="${pageContext.request.contextPath}/member/MemberLogout.me">로그아웃</a>
		</tr>
	</table>

	<!-- 게시판 리스트 -->
	<table width="900px" border="0" cellpading="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td><h3>MVC 게시판</h3></td>
		</tr>
		<tr align="right">
			<td>전체 글 개수 : ${totalCnt}</td>
		</tr>
	</table>

	<div id="page-wrapper">
		<div id="main" class="wrapper style3">
			<div class="container">
				<section>

					<div class="table-wrapper">

						<table border="1" cellspacing="0" cellpadding="0" width="900px">
							<tr align="center">
								<td width="7%">번호</td>
								<td width="50%">제목</td>
								<td width="17%">작성자</td>
								<td width="19%">날짜</td>
								<td width="7%">조회수</td>
							</tr>
							<c:choose>
								<c:when test="${list!=null and fn:length(list)>0 }">
									<c:forEach var="board" items="${list}">
										<tr align="center" valign="middle"
											onmouseover="this.style.backgroundColor='#e0f7fa'"
											onmouseout="this.style.backgroundColor=''">
											<td height="24px">${board.getBoardnum() }</td>
											<td><a
												href="${pageContext.request.contextPath}/board/BoardView.bo?num=${board.getBoardnum()}">
													${board.getBoardtitle() } </a></td>
											<td>${board.getUserid() }</td>
											<td>${board.getBoarddate() }</td>
											<td>${board.getBoardreadcount() }</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr hegiht="50px" align="center">
										<td colspan="5">등록된 게시물이 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
				</section>
			</div>
		</div>
	</div>
	<br>
	<script>
		console.log("totalPage"+'<%=request.getParameter("totalPage")%>');
		console.log("startPage"+'<%=request.getParameter("startPage")%>');
		console.log("endPage"+'<%=request.getParameter("endPage")%>');
		console.log("nowPage"+'<%=request.getParameter("nowPage")%>');
		console.log("totalCnt"+'<%=request.getParameter("totalCnt")%>');
		console.log("list"+'<%=request.getParameter("boardList")%>
		');
	</script>
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="center" valign="middle">
			<td><c:choose>
					<c:when test="${nowPage>1}">
						<a
							href="${pageContext.request.contextPath}/board/BoardList.bo?page=${nowPage-1}">[이전]</a>
					</c:when>
				</c:choose> <c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:choose>
						<c:when test="${i == nowPage }">
								[${i}]
							</c:when>
						<c:otherwise>
							<a
								href="${pageContext.request.contextPath}/board/BoardList.bo?page=${i}">[${i}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach> <c:if test="${nowPage<totalPage }">
					<a
						href="${pageContext.request.contextPath}/board/BoardList.bo?page=${nowPage+1}">[다음]</a>
				</c:if></td>
		</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="right" valign="middle">
			<td><a
				href="${pageContext.request.contextPath}/board/BoardWrite.bo">[글쓰기]</a>
			</td>
		</tr>
	</table>
	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.dropotron.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/browser.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/breakpoints.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

</body>
</html>