package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이동할 위치 선별 
		String action = request.getParameter("action");
		
		//Dao 인스턴스 생성
		UserDao ud;
		
		//세션 인스턴스 생성
		HttpSession session;
		
		if("joinForm".equals(action)) {
			
			WebUtil.forward("/WEB-INF/views/user/joinForm.jsp", request, response);
			
		}
		
		else if("join".equals(action)) {
			
			String id = request.getParameter("uid");
			String pw = request.getParameter("pw");
			String name = request.getParameter("uname");
			String gender = request.getParameter("gender");

			UserVo uv = new UserVo(id, pw, name, gender);

			//Dao에 insert
			ud = new UserDao();
			ud.insert(uv);
			
			WebUtil.forward("/WEB-INF/views/user/joinOk.jsp", request, response);
			
			
			
		}
		
		
		else if("loginForm".equals(action)) {
			//***************************************************************
			//집중하자 forward는 주소값 그대로 파일로 보낸다 속성값 넣을 필요가 없다!!!!!!!!!!!!!!!!!!!!!!
				WebUtil.forward("/WEB-INF/views/user/loginForm.jsp", request, response);
			//***************************************************************
		}
		
		
		else if("login".equals(action)) {
			
			String id = request.getParameter("uid");
			String password = request.getParameter("pw");
			
			ud = new UserDao();
			UserVo authUser = ud.getUser(id, password);
			
				if(authUser==null) {
					
					WebUtil.redirect("/mysite2/user?action=loginForm&result=fail", response);
				}
				
				else {
					
					
					//세션 객체 가져와서 session 변수에 담기
					session = request.getSession();
					
					
					//가져온 세션에 user 정보 담기
					session.setAttribute("authUser", authUser);				
					
					//아이디, 비번이 맞았으니 메인으로 리다이렉트
					WebUtil.redirect("/mysite2/main", response);
					
				}
				
			
			
			
		}
		
		else if("logout".equals(action)) {
			
			session = request.getSession();
			
			session.removeAttribute("authUser");
			session.invalidate();

			WebUtil.redirect("/mysite2/main", response);	
			
		}
		
		
		
		
		
		else if("modifyForm".equals(action)) {
			
			session = request.getSession();
			
			//로그인 한 회원의 세션에서 회원 no 가져오기
			UserVo uv = (UserVo)session.getAttribute("authUser");
			
			//회원의 no를 통해서 value에 넣을 값들 받아오기 value에 값들을 넣어줘야 한다면 이 방법 선택 몰라서 전 방법 선택
			//modiForm에 뿌릴 회원 정보 담은 user get
			ud = new UserDao();
			UserVo authUser = ud.getUser(uv.getNo());
			
			request.setAttribute("authUser", authUser);
			
			WebUtil.forward("/WEB-INF/views/user/modifyForm.jsp", request, response);
		}
		
		else if("modify".equals(action)) {
			
			//세션에서 바로 getAttribute으로 정보 불러와 no값을 가져올 수 있지 않을까 생각 그러나 
			//이미 modiform에서 불렀기 때문에 form에서 no 넘겨주는 것으로 일단 선택
			//시간 된다면 바로 getAttribute으로 불러서 사용해보는것 시도
			int no = Integer.parseInt(request.getParameter("no"));
			//----------------------------------------------------
			String password = request.getParameter("password");
			String name = request.getParameter("uname");
			String gender = request.getParameter("gender");

			UserVo uv = new UserVo(no, password, name, gender);
			ud = new UserDao();
			int result = ud.update(uv);
			
			if(result==1) {
				
				session = request.getSession();
								
				UserVo modiUser = (UserVo)session.getAttribute("authUser");
				
				
				//****************************
				modiUser.setName(name);
						
				//세션에서 불러온 Vo 수정해주면 세션의 Vo 정보만 바뀐다.
				//다시 속성 불러와서 설정해줄 필요가 없다.
				//****************************
				
				WebUtil.redirect("/mysite2/main", response);
				
			}
			
			else {
				
				WebUtil.redirect("/mysite2/user?action=modifyForm", response);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}


	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
