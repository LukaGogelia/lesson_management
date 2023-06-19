package com.lesson_mgmt.servlets;

import java.io.IOException;

import com.lesson_mgmt.dao.DaoClass;
import com.lesson_mgmt.helpers.ConnectionProvider;
import com.lesson_mgmt.helpers.ServerMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RemoveLesson extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.sendRedirect("removeLesson.jsp");
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ServerMessage sm = null;
		HttpSession s = request.getSession();
		try {
			int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));			
			ConnectionProvider conn = new ConnectionProvider();	        
	        DaoClass dao = new DaoClass(conn.getConnection());
	        boolean removeLesson = dao.removeLesson(lesson_id);	        
	        if(removeLesson) {
	        	sm = new ServerMessage("success", "Lesson Removed Successfull", "alert-success");
	        }else {
	        	sm = new ServerMessage("error", "Lesson Removed Fail", "alert-danger");
	        }
			
		} catch (Exception e) {
			sm = new ServerMessage("error", "Invalid Lesson Id", "alert-danger");
		}
		
		s.setAttribute("serverMsg", sm);
		
		response.sendRedirect("removeLesson.jsp");
    }
	
}
