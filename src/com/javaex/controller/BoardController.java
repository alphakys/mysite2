package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@WebServlet("/board")
public class BoardController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		HttpSession session;
		BoardDao bd;
		
		
		if("writeForm".equals(action)) {
			
			
			WebUtil.forward("/WEB-INF/views/board/writeForm.jsp", request, response);
			
			
			
		}
		
		else if("insert".equals(action)) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			try {
				String name = authUser.getName();
				
				BoardVo bv = new BoardVo(name, title, content);
				
				bd = new BoardDao();
			} catch (NullPointerException e) {
				
				WebUtil.redirect("/mysite2/board?action=writeForm&result=login", response);
			}
			
			
			WebUtil.redirect("/mysite2/board", response);
		}
		
		else if("read".equals(action)) {
			
			
			WebUtil.forward("/WEB-INF/views/board/read.jsp", request, response);
			
			
			
		}
		
		
		else if("modifyForm".equals(action)) {
			
			
			WebUtil.forward("/WEB-INF/views/board/modifyForm.jsp", request, response);			
			
			
		}
		
		else {
			
			bd = new BoardDao();
			
			request.setAttribute("bList", bd.getList());
			WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
			
			
		}
	
		
		
	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	}

	
	
}
