<!DOCTYPE html>
<%@page import="com.lesson_mgmt.entities.Student_Attendance_Entity"%>
<%@page import="com.lesson_mgmt.helpers.ServerMessage"%>
<%@page import="com.lesson_mgmt.entities.Student"%>
<%@page import="com.lesson_mgmt.entities.Student_Attendance_Entity"%>
<%@page import="java.util.List"%>
<%@page import="com.lesson_mgmt.dao.DaoClass"%>
<%@page import="com.lesson_mgmt.helpers.ConnectionProvider"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance History</title>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

</head>

<body>

    <div class="container">

        <div class="main my-4 py-5">
            <h1 class=" text-center mb-3">Lesson History</h1>
            
            <%
	        	ServerMessage sm = (ServerMessage) session.getAttribute("serverMsg");
	        	if(sm != null){
	        		%>
	        		
	        		<div class="alert <%=sm.getCss() %> alert-dismissible fade show">	        		
	        			<%=sm.getMessage() %>
	        		</div>	        			
	        		<div class="mb-2 text-center">
	        			<a href="home" class="btn btn-success btn-lg">Go to Home Page</a>
	        		</div>	        		
	        		
	        		<% 
	        	}
	        	
	        	session.removeAttribute("serverMsg");
            %>
            
            
            
            <%             
            
            String lesson_id_string = request.getParameter("lesson_id");
            
            
            ConnectionProvider conn = new ConnectionProvider();
	        DaoClass dao = new DaoClass(conn.getConnection());
	        
	        List<Student_Attendance_Entity> students = dao.getLessonAttendanceByLessonId(Integer.parseInt(lesson_id_string));
	        
	        if(students == null){
	        	response.sendRedirect("home");
	        	return ;
	        }            
            %>

            <main >            	
            
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th>Student</th>
                            <th>Attendance</th>
                            <th>Latency (minutes)</th>
                            <th>Time</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <%
	                    for(Student_Attendance_Entity student : students){	            	        
	        	        	%>

                      	<tr>
                            <td><%=student.getStudent().getName() %></td>
                            <td><%=student.isAttendance() %></td>
                            <td><%=student.getLatency() %></td>
                            <td><%=student.getTime() %></td>
                        </tr>
                        	<% 
	        	        }
                    %>                        
                    </tbody>
                </table>
                 <a href="home" class="btn btn-success btn-lg">Go to Home Page</a>
            </main> 
        </div>
    </div>   

</body>

</html>