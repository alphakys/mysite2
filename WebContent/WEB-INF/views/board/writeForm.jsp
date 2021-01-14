<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>


<html>


	<head>
	
		<meta charset="UTF-8">
		
		<title>Write</title>
		
		<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="/mysite2/assets/css/board.css" rel="stylesheet" type="text/css">
		
		
		<c:if test="${param.result } == fail">
					
		
		
		</c:if>
		
		
	
	</head>


<body>


	<div id="wrap">

			<!-- header and navi -->
			
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
			
			
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
				<div id="writeForm">
				
					<form action="/mysite2/board" method="get">
						<input type="hidden" name="action" value="insert">
						
						<!-- 제목 -->
						<div class="form-group">
							<label class="form-text" for="txt-title">제목</label>
							<input type="text" id="txt-title" name="title" value="" placeholder="제목을 입력해 주세요">
						</div>
					
						<!-- 내용 -->
						<div class="form-group">
							<textarea id="txt-content" name="content"></textarea>
						</div>
						
						<a id="btn_cancel" href="/mysite2/board">취소</a>
						<button id="btn_add" type="submit" >등록</button>
						
					</form>
	                <!-- //form -->
				</div>
				<!-- //writeForm -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->

</body>

</html>