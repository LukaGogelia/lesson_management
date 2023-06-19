<!DOCTYPE html>
<%@page import="com.lesson_mgmt.entities.Student"%>
<%@page import="com.lesson_mgmt.helpers.ServerMessage"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lesson_mgmt.entities.Lesson"%>
<%@page import="java.util.List"%>
<%@page import="com.lesson_mgmt.dao.DaoClass"%>
<%@page import="com.lesson_mgmt.helpers.ConnectionProvider"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add lesson</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">


<style>
* {
	box-sizing: border-box;
}

.main {
	background-color: rgb(232, 231, 234);
	padding: 50px 30px;
}

.all-lessons {
	margin-top: 20px;
	width: 100%;
}

.lesson {
	color: white;
	background-color: rgb(2, 151, 135);
	padding: 10px;
	margin: 10px;
	display: inline-block;
}

.lesson:hover {
	color: white;
}
</style>

</head>

<body>
	<div class="container">
		<div class="main my-5 rounded col-6 mx-auto ">
			<form action="add_lesson" method="POST">

				<h1 class=" text-center">Add Lesson</h1>

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


				<div class="form-group">
					<label for="email">Lesson Name:</label> <input type="text"
						class="form-control" placeholder="Lesson Name"
						name="lessonName">
				</div>
				<div class="form-group">
					 <label for="lessonDate">Date of
						Lesson:</label> <input type="date" id="lessonDate" name="lessonDate"
						required><br>
					<br> <label for="lessonTime">Time of Lesson:</label> <input
						type="time" id="lessonTime" name="lessonTime" required><br>
					<br>

				</div>
				
				
				<!-- select Students for class -->
				<%
            
		            String lesson_id_string = request.getParameter("lesson_id");
		            
		            ConnectionProvider conn = new ConnectionProvider();
			        DaoClass dao = new DaoClass(conn.getConnection());
			        
			        List<Student> students = dao.getAllStudents();
			        if(students == null){
			        	response.sendRedirect("home");
			        	return ;
			        }            
	            %>
				<table class="table table-dark">
                    <thead>
                        <tr>
                            <th>Student</th>
                            <th>Select</th>
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
                        </tr>
                        	<% 
	        	        }
                    %>                        
                    </tbody>
                </table>
				
				
				
				
				<input type="submit" class="btn btn-primary w-100" value="Add Lesson">
				<a href="home" class="btn btn-success w-100 my-2">Go to Home</a>
			</form>
		</div>
	</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>