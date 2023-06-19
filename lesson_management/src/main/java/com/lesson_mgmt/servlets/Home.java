package com.lesson_mgmt.servlets;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		res.sendRedirect("index.jsp");
		
		
    }
}
