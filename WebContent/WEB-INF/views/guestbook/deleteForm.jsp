<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>


<html>

	<head>
		<meta charset="UTF-8">
		
		<title>DeleteForm</title>
		
		<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="/mysite2/assets/css/guestbook.css" rel="stylesheet" type="text/css">

		<script>
		
			function wrong(){
				alert("비밀번호가 틀렸습니다.");
			}
		
		</script>
		
		
		
		<c:if test="${param.result eq 'fail'}">
		
			<script>
				wrong();
			
			</script>
			
		</c:if>		
	
	
		
		
	</head>

	<body>
		
		
		<div id="wrap">
	
	
	
			<!-- header and navi -->
			
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
	
	
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->
	
			<div id="content">
				
				<div id="content-head">
	            	<h3>일반방명록</h3>
	            	<div id="location">
	            		<ul>
	            			<li>홈</li>
	            			<li>방명록</li>
	            			<li class="last">일반방명록</li>
	            		</ul>
	            	</div>
	                <div class="clear"></div>
	            </div>
	            <!-- //content-head -->
	
				<div id="guestbook">
				
					<form action="/mysite2/guest" method="get">
					
						<table id="guestDelete">
						
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
							</colgroup>
							
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="password"></td>
								<td class="text-left"><button type="submit">삭제</button></td>
								<td><a href="/guestbook2/gbc">[메인으로 돌아가기]</a></td>
							</tr>
							
						</table>
						
						<input type='hidden' name="action" value="delete">
						<input type='hidden' name="no" value="${param.no}">
						
					</form>
					
				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->
			<div class="clear"></div>
			
			<!-- //footer -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	
		</div>
		<!-- //wrap -->
	
	</body>

</html>