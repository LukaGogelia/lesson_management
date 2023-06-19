<!DOCTYPE html>
<%@page import="com.lesson_mgmt.helpers.ServerMessage"%>
<%@page import="com.lesson_mgmt.entities.Student"%>
<%@page import="java.util.List"%>
<%@page import="com.lesson_mgmt.dao.DaoClass"%>
<%@page import="com.lesson_mgmt.helpers.ConnectionProvider"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

</head>

<body>

    <div class="container">

        <div class="main my-4 py-5">
            <h1 class=" text-center mb-3">Lesson Attendance</h1>
            
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
	        
	        List<Student> students = dao.getAllStudentsForLesson(Integer.parseInt(lesson_id_string));
	        if(students == null){
	        	response.sendRedirect("home");
	        	return ;
	        }            
            %>

            <form action="student_attendance" id="attendanceForm" method="post" enctype="multipart/form-data">
            	<input hidden name="lesson_id" value="<%=lesson_id_string%>">
            
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th>Student</th>
                            <th>Attendance</th>
                            <th>latency (minutes)</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <%
	                    for(Student student : students){	            	        
	        	        	%>

                      	<tr>
                            <td><%=student.getName() %></td>
                            <input type="hidden" name="studentId[]" value="<%=student.getId()%>">
                            <td><input name="<%=student.getId()%>" type="checkbox"></td>
                            <td><input name="latency[]" type="number" placeholder="latency"></td>
                        </tr>
                        	<% 
	        	        }
                    %>                        
                    </tbody>
                </table>
					
				<a href="attendanceHistory.jsp?lesson_id=<%=lesson_id_string%>" class="btn btn-info btn-lg">Attendance History</a>
                 <a href="home" class="btn btn-success btn-lg">Go to Home Page</a>
                 <input type="submit" class="btn btn-lg btn-dark px-5">
            </form> 
        </div>
    </div>
    
   
    
    

</body>

</html>