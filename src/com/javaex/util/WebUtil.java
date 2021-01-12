package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	
	//forward
	public static void forward(String path, 
								HttpServletRequest request, 
								HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		
		rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
	}
	
	
	//redirect
	public static void redirect(String url, 								 
								HttpServletResponse response) throws IOException {
		
		response.sendRedirect(url);
		
		
	}
}
