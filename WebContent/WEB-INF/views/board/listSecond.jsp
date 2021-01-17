<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 모든 리스트를 한 번에 다받아서 뿌려주는 방식 1번과 그렇게 되면 오랜 시간이 걸려 적절치 못할 것같다는 지적이 있을 것 같아 DB에서 원하는 리스트 개수만 불러오는 두번째 방법을 위한 리스트 jsp -->

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
			
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>


			<c:import url="/WEB-INF/views/include/asideBoard.jsp"></c:import>




			<!-- content -->

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
				
					<form action="/mysite2/board" method="post">
						<input type="hidden" name="action" value="search">
					
						<div class="form-group text-right">
							<input type="text" name="keyword">
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
							
								
					<c:forEach items="${bList}" var="bv">
						
						<tbody>
						
							<tr>
								<td>${bv.no }</td>
								<td class="text-left"><a href="/mysite2/board?action=read&no=${bv.no}">${bv.title }</a></td>
								<td>${bv.name}</td>
								<td>${bv.hit}</td>
								<td>${bv.date}</td>
								
								<td>
									<c:if test="${sessionScope.authUser.no eq bv.userNo}">
									
										<a href="/mysite2/board?action=delete&no=${bv.no}">[삭제]</a>
								
									</c:if>
								</td>
								
							</tr>
				
						</tbody>
						
					</c:forEach>
				
	
				</table>
		
					<div id="paging">
					
						<ul>
							
							<!--   ◀이전 페이지 -->
							<c:choose>
							 	<c:when test="${param.page <= 1}">
							
									<li><a href="/mysite2/board?page=${param.page eq 1}">◀</a></li>
							
								</c:when>
								
								<c:otherwise>
								
									<li><a href="/mysite2/board?page=${param.page -1}">◀</a></li>
									
								</c:otherwise>
							</c:choose>
							
							<!--◀ 이전 페이지  -->
							
							
							<!-- <<본 페이지>> --> 
							<!-- active 기능 구현 -->
							<c:choose>
								
								<c:when test="${totalPost%10 eq 0}">
									
									<c:forEach var="page" begin="1" end="${totalPost/10 }">
										
										<c:choose>
											<c:when test="${param.page eq page }">
												<li class="active">
													<a href= "/mysite2/board?page=${page }">${page}</a> 
												</li>
											</c:when>
										
											<c:otherwise>
												<li>
													<a href= "/mysite2/board?page=${page }">${page}</a> 
												</li>									
											</c:otherwise>
										</c:choose>												
													
									</c:forEach>
								
								</c:when>
								
								<c:otherwise>
									
									<c:forEach var="page" begin="1" end="${totalPost/10 +1}">
							
										<c:choose>
										
											<c:when test="${param.page eq page}">
												<li class="active">
													<a href= "/mysite2/board?page=${page}">${page}</a> 
												</li>
											</c:when>
										
											<c:otherwise>
												<li>
													<a href= "/mysite2/board?page=${page}">${page}</a> 
												</li>									
											</c:otherwise>
											
										</c:choose>			
										
									</c:forEach>
								
								</c:otherwise>
								
							</c:choose>
							
							
							<!-- <<본 페이지>> -->
							
							
							
							<!--▶ 다음 페이지 -->
							<li><a href="/mysite2/board?page=${param.page+1 }">▶</a></li>
						
						
						
						
							<!--▶ 다음 페이지 -->
			
						</ul>
						
						
						<div class="clear"></div>
					</div>
					
					
					<!-- 로그인시 글쓰기 가능 기능 -->
					<c:choose>
					
						<c:when test="${sessionScope.authUser.no eq bv.userNo}">
						
						</c:when>
							
						<c:otherwise>
							
							<a id="btn_write" href="/mysite2/board?action=writeForm">글쓰기</a>
						
						</c:otherwise>
						
					</c:choose>
				
				
				
				
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
		
		
	</div>
	<!-- //wrap -->

</body>

</html>