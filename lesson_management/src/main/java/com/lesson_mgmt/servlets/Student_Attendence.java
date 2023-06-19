package com.lesson_mgmt.servlets;

import java.io.IOException;


import com.lesson_mgmt.dao.DaoClass;
import com.lesson_mgmt.helpers.ConnectionProvider;
import com.lesson_mgmt.helpers.ServerMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig
public class Student_Attendence extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		response.sendRedirect("student_attendance.jsp");
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		

		
	    // Retrieve the attendance, latency, and student ID values from request parameters	    
	    String[] latencyValues = request.getParameterValues("latency[]");
	    String[] studentIdValues = request.getParameterValues("studentId[]");			
	    int[] attendanceValues = new int[latencyValues.length] ;
	    String lesson_id_string = request.getParameter("lesson_id");
	    int lesson_id = 0;
	    
	    for(int i=0; i<studentIdValues.length ; i++) {
	    	String attendanceClient = request.getParameter(""+studentIdValues[i]);
	    	int attendance = 0;
	    	if(attendanceClient != null) {
	    		attendance = 1;
	    	}
	    	attendanceValues[i] = attendance;	    	
	    }	    

  
	    HttpSession session = request.getSession();
	    ServerMessage msg = null;
	    
	    //checking for get lesson id
	    if(lesson_id_string.isBlank()){
	    	msg = new ServerMessage("error", "Invalid Lesson Id", "alert-danger");	    	
	    }else {
	    	try {
	    		  lesson_id = Integer.parseInt(lesson_id_string);
	    	}catch (Exception e) {
	    		msg = new ServerMessage("error", "Invalid Lesson Id", "alert-danger");
			}
	    }
	    
	    
	    
	 // Perform operations on each student's data
	    if (msg ==null && attendanceValues != null && latencyValues != null && studentIdValues != null &&
	        attendanceValues.length == latencyValues.length && latencyValues.length == studentIdValues.length) {

	      for (int i = 0; i < attendanceValues.length; i++) {
	        int studentId = Integer.parseInt(studentIdValues[i]);
	        int attendance = attendanceValues[i];
	        String latencyForm = latencyValues[i];
	        int latency = 0;
	        
	        if(!latencyForm.isEmpty()) {
	        	latency = Integer.parseInt(latencyValues[i]);
	        }

	        // Process the student's data as needed
	        // Example: Store the data in a database or perform calculations
	        ConnectionProvider conn = new ConnectionProvider();
	        DaoClass dao = new DaoClass(conn.getConnection());	        
	        dao.addAttendence(lesson_id, studentId, attendance, latency);
	        }
	      
	      msg = new ServerMessage("success", "Submitted all Students Attendance", "alert-success");
	      
	    }		    
	    
	    session.setAttribute("serverMsg", msg);
		response.sendRedirect("student_attendance.jsp?lesson_id="+lesson_id_string);		
		
    }
	
	
}
