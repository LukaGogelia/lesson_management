package com.lesson_mgmt.servlets;

import java.io.IOException;

import com.lesson_mgmt.dao.DaoClass;
import com.lesson_mgmt.entities.Student;
import com.lesson_mgmt.helpers.ConnectionProvider;
import com.lesson_mgmt.helpers.ServerMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddStudent extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("Do Get");
		response.sendRedirect("addStudent.jsp");
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String studentName = request.getParameter("studentName");
		String studentPhone = request.getParameter("studentPhone");
		
		Student student = new Student(0, studentName, studentPhone);
		
		
		ConnectionProvider conn = new ConnectionProvider();
        
        DaoClass dao = new DaoClass(conn.getConnection());
        boolean addStudent = dao.addStudent(student);
        
        ServerMessage sm = null;
        
        if(addStudent) {
        	sm = new ServerMessage("success", "Student Added Successfull", "alert-success");
        }else {
        	sm = new ServerMessage("error", "Student Added Fail", "alert-danger");
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("serverMsg", sm);  
		response.sendRedirect("addStudent.jsp");
    }
	
}
