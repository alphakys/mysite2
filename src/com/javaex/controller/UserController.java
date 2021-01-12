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
		UserDao ud = new UserDao();
		
		//세션 인스턴스 생성
		HttpSession session = null;
		
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

			ud.insert(uv);
			
			WebUtil.forward("/WEB-INF/views/user/joinOk.jsp", request, response);
			
			
			
		}
		
		
		else if("loginForm".equals(action)) {
			
			//로그인 실패시	
			String result = request.getParameter("result");
			
			if("fail".equals(result)) {
				request.setAttribute("result", result);
				WebUtil.forward("/WEB-INF/views/user/loginForm.jsp", request, response);
				
			}
			
			//로그인  상태에서 로그인 시도시
			else if(session.
					!=null) {
			
				WebUtil.redirect("/mysite2/main", response);
			}
			
			//로그인 시도시
			else {
				
				WebUtil.forward("/WEB-INF/views/user/loginForm.jsp", request, response);

			}
			

		}
		
		
		else if("login".equals(action)) {
			
			String id = request.getParameter("uid");
			String password = request.getParameter("pw");
				
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
			
			
			session.removeAttribute("authUser");
			session.invalidate();

			WebUtil.redirect("/mysite2/main", response);	
			
		}
		
		
		
		
		
		else if("modifyForm".equals(action)) {
			
			WebUtil.forward("/WEB-INF/views/user/modifyForm.jsp", request, response);
		}
		
		else if("modify".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			String name = request.getParameter("uname");
			String gender = request.getParameter("gender");

			UserVo uv = new UserVo(no, password, name, gender);
			
			int result = ud.update(uv);
			
			if(result==1) {
				
				session = request.getSession();
				
				//기존 속성 지우기
				session.removeAttribute("authUser");				
				
				//변경된 유저 가져오기 (회원 번호를 파라미터로 가진 메소드 오버로딩?해서 위에 받아온 정보를 가지고 기존의 메소드 이용할까 했지만
				//이미 정보가 변경됐기 때문에 불가능하다 판단 오버로딩 하는데 오랜 시간 안걸릴듯 해서 그냥 메소드 생성)
				
				UserVo newUser = ud.getUser(no);
				
				//새로 변경 된 Vo는 new로 명명
				session.setAttribute("authUser",newUser);
				
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
