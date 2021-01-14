<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.javaex.vo.BoardVo" %>
<%@ page import = "java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>





<!DOCTYPE html>


<html>


	<head>
		<meta charset="UTF-8">
		
		<title>BoardList</title>
		
		<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="/mysite2/assets/css/board.css" rel="stylesheet" type="text/css">
	
	</head>


	<body>


	<div id="wrap">

			<!-- header and navi -->
			
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="">일반게시판</a></li>
				<li><a href="">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				
				<h3>게시판</h3>
				
				<div id="location">
					
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
					
				</div>
				
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
			
				<div id="list">
				
					<form action="" method="">
						<div class="form-group text-right">
							<input type="text">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					
					
					<table >
					
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						
						<c:forEach items="${bList }" var="bv" varStatus="status">
						<tbody>
						
							<tr>
								<td><c:out value="${bv.no}" /></td>
								<td class="text-left"><a href="#"><c:out value="${bv.title }" /></a></td>
								<td><c:out value="${bv.name}" /></td>
								<td>1232</td>
								<td><c:out value="${bv.content }"/></td>
								<td><a href="">[삭제]</a></td>
							</tr>
							
						
						</tbody>
						</c:forEach>
						
						
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li class="active"><a href="">5</a></li>
							<li><a href="">6</a></li>
							<li><a href="">7</a></li>
							<li><a href="">8</a></li>
							<li><a href="">9</a></li>
							<li><a href="">10</a></li>
							<li><a href="">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					
					<a id="btn_write" href="/mysite2/board?action=writeForm">글쓰기</a>
				
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->
		
		
	</div>
	<!-- //wrap -->

</body>

</html>