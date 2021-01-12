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
			
			
			
			WebUtil.forward("/WEB-INF/views/user/loginForm.jsp", request, response);

		}
		
		
		else if("login".equals(action)) {
			
			UserVo uv = new UserVo(request.getParameter("uid"), request.getParameter("pw"));
			
				if(ud.getUser(uv)==null) {
					
					WebUtil.forward("/WEB-INF/views/user/loginForm.jsp", request, response);
				}
				
				else {
					//세션 객체 가져와서 session 변수에 담기
					session = request.getSession();
					
					
					//가져온 세션에 user 정보 담기
					session.setAttribute("id", uv.getId());
					
					session.setAttribute("password", uv.getPw());
					
					//아이디, 비번이 맞았으니 메인으로 리다이렉트
					WebUtil.redirect("/mysite2/main", response);
					
				}
				
			
			
			
		}
		
		else if("modifyForm".equals(action)) {
		
			WebUtil.forward("/WEB-INF/views/user/modifyForm.jsp", request, response);
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
