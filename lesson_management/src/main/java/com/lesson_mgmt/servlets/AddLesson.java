package com.lesson_mgmt.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lesson_mgmt.dao.DaoClass;
import com.lesson_mgmt.entities.Lesson;
import com.lesson_mgmt.helpers.ConnectionProvider;
import com.lesson_mgmt.helpers.ServerMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddLesson extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.sendRedirect("addLesson.jsp");
    }
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		// Retrieve the form data
        String lessonName = request.getParameter("lessonName");
        String lessonDate = request.getParameter("lessonDate");
        String lessonTime = request.getParameter("lessonTime");
        Timestamp schedule = null;
        
        String[] studentIdValues = request.getParameterValues("studentId[]");		
	    int[] attendanceValues = new int[studentIdValues.length];
        
	    HashMap<Integer, Integer> student_attendance = new HashMap<Integer, Integer>();
	    
	    for(int i=0; i<studentIdValues.length ; i++) {
	    	String attendanceClient = request.getParameter(""+studentIdValues[i]);
	    	int studentId = Integer.parseInt(studentIdValues[i]);
	    	int attendance = 0;
	    	if(attendanceClient != null) {
	    		attendance = 1;
	    		student_attendance.put(studentId, attendance);
	    	}
	    	attendanceValues[i] = attendance;	    	
	    }	
	    
	    
 
	    
     // Combine date and time strings
        String dateTimeString = lessonDate + " " + lessonTime;
        

        // Create a SimpleDateFormat object to parse the date and time strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            // Parse the combined date and time string into a Date object
            java.util.Date parsedDate = dateFormat.parse(dateTimeString);

            // Create a Timestamp object from the parsed Date
             schedule = new Timestamp(parsedDate.getTime());

            // Do something with the schedule (e.g., store it in the database)
            // ...
        } catch (ParseException e) {
            // Handle parsing error
            e.printStackTrace();
        }
        
        
        
        Lesson lesson = new Lesson();
        lesson.setLesson_name(lessonName);
        lesson.setSchedule(schedule);
        
        
        
        ConnectionProvider conn = new ConnectionProvider();        
        DaoClass dao = new DaoClass(conn.getConnection());
        boolean addLesson = dao.addLesson(lesson);
        
        //getting the last lesson
        Lesson ls = dao.getLessonByName(lessonName);
//        System.out.println(ls.getId() + " " + ls.getLesson_name() + " " +ls.getSchedule() );
        
        ServerMessage sm = null;
        
        if(addLesson) {
        	sm = new ServerMessage("success", "Lesson Added Successfull", "alert-success");
        }else {
        	sm = new ServerMessage("error", "Lesson Added Fail", "alert-danger");
        }
        
        
        
  	  //Traversing Map  
	    Set set=student_attendance.entrySet();//Converting to Set so that we can traverse  
	    Iterator itr=set.iterator();  
	    while(itr.hasNext()){  	    	
	        //Converting to Map.Entry so that we can get key and value separately  
	        Map.Entry entry=(Map.Entry)itr.next();  
//	        System.out.println(entry.getKey()+" "+entry.getValue());  
	        
	        dao.addLessonAttendance(ls.getId(), (int) entry.getKey());
	    } 
        
        
        
        
        
        HttpSession session = request.getSession();
        session.setAttribute("serverMsg", sm);        
        
		response.sendRedirect("addLesson.jsp");
    }
	
}
