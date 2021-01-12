package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/guest")
public class GuestController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		GuestDao gd = new GuestDao();
		
		//int no = Integer.parseInt(request.getParameter("no"));
		//String name = request.getParameter("name");
		//String password = request.getParameter("pw");
		//String content = request.getParameter("content");

		
		String action = request.getParameter("action");
		
		if("add".equals(action)) {
			
			String name = request.getParameter("name");
			String password = request.getParameter("pw");
			String content = request.getParameter("content");
			
			GuestVo gv = new GuestVo(name, password, content);
			
			gd.insert(gv);
			
			WebUtil.redirect("/mysite2/guest", response);
			
		}
		
		else if("deleteForm".equals(action)) {
			
			WebUtil.forward("/WEB-INF/views/guestbook/deleteForm.jsp", request, response);
			
			
		}
		
		else if("delete".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			int result = gd.delete(no, password);
			
			System.out.println(result);
		}
		
		else {
			//방명록에 출력할 리스트 얻어오기
			
			request.setAttribute("guestList", gd.getList());
			
			WebUtil.forward("/WEB-INF/views/guestbook/addList.jsp", request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
