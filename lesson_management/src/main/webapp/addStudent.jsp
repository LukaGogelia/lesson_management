<!DOCTYPE html>
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
			<form action="add_student" method="POST">

				<h1 class=" text-center">Add Student</h1>

				<%
	        	ServerMessage sm = (ServerMessage) session.getAttribute("serverMsg");
	        	if(sm != null){
	        		%>
	        		
	        		<div class="alert <%=sm.getCss() %> alert-dismissible fade show">	        		
	        			<%=sm.getMessage() %>
	        		</div>        		
	        		
	        		<% 
	        	}
	        	
	        	session.removeAttribute("serverMsg");
            	%>


				<div class="form-group">
					<label for="email">Student Name:</label> <input type="text"
						class="form-control" placeholder="Student Name" required="required"
						name="studentName">
				</div>		
				<div class="form-group">
					<label for="email">Student Phone:</label> <input type="text" required="required"
						class="form-control" placeholder="Student Phone"
						name="studentPhone">
				</div>

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