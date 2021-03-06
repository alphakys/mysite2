<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>


<html>


	<head>
	
	<meta charset="UTF-8">
	
	<title>LoginForm</title>
	
	<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="/mysite2/assets/css/user.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	
		function fail(){
			
			alert("아이디 또는 비밀번호가 틀렸습니다. \n다시 입력해주세요");
			
		}
	
	</script>
	
	
		<c:if test="${param.result eq 'fail'}">
		
			<script>
				
				fail();
				
			</script>
		
		</c:if>
	
	
	</head>
	
	
	<body>
	
	
		<div id="wrap">
	
			<!-- header and navi -->
			
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->
	
			<div id="content">
				
				<div id="content-head">
	            	<h3>로그인</h3>
	            	<div id="location">
	            		<ul>
	            			<li>홈</li>
	            			<li>회원</li>
	            			<li class="last">로그인</li>
	            		</ul>
	            	</div>
	                <div class="clear"></div>
	            </div>
	             <!-- //content-head -->
	
				<div id="user">
				
					<div id="loginForm">
					
						<form action="/mysite2/user" method="post">
	
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="uid" value="" placeholder="아이디를 입력하세요">
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">비밀번호</label> 
								<input type="password" id="input-pass" name="pw" value="" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							
							<!-- 버튼영역 -->
			                <div class="button-area">
			                    <button type="submit" id="btn-submit">로그인</button>
			                </div>
			                
							<input type="hidden" name="action" value="login">
							
						</form>
						
					</div>
					<!-- //loginForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
			<div class="clear"></div>
	
			<!-- //footer -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	
		</div>
		<!-- //wrap -->
	
	</body>
	
</html>