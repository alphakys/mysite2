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
		
		//<글쓰기>
		if("writeForm".equals(action)) {
			
			
			WebUtil.forward("/WEB-INF/views/board/writeForm.jsp", request, response);
			
			
			
		}
		
		//<게시글 등록>
		else if("insert".equals(action)) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
				int userNo = authUser.getNo();
			
				BoardVo bv = new BoardVo(userNo, title, content);
				
				bd = new BoardDao();
			
				bd.insert(bv);
				
			
			WebUtil.redirect("/mysite2/board", response);
		}
		
		
		//<게시글 읽기>
		else if("read".equals(action)) {
			
			//게시물 번호를 받아오고 setAttri에 담을 해당 데이터 가져오기
			int no = Integer.parseInt(request.getParameter("no"));
			
			bd = new BoardDao();
			
			BoardVo post = bd.getPost(no);
			
			int hit = post.getHit();
			hit+= 1;
			
			post.setHit(hit);
			bd.updateHit(no, hit);
			
			request.setAttribute("post", post);
			
			WebUtil.forward("/WEB-INF/views/board/read.jsp", request, response);
			
			
			
		}
		
		
		else if("modifyForm".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			bd = new BoardDao();
			
			BoardVo post = bd.getPost(no);
			
			request.setAttribute("post", post);
			
			WebUtil.forward("/WEB-INF/views/board/modifyForm.jsp", request, response);			
			
			
		}
		
		else if("modify".equals(action)) {
			
			
			
			
			
		}
		
		else if("delete".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			bd = new BoardDao();
			bd.delete(no);
			
			WebUtil.redirect("/mysite2/board", response);
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
