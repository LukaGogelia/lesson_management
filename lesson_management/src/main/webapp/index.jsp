<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lesson_mgmt.entities.Lesson"%>
<%@page import="java.util.List"%>
<%@page import="com.lesson_mgmt.dao.DaoClass"%>
<%@page import="com.lesson_mgmt.helpers.ConnectionProvider"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">


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
        
        .lesson:hover{
        
        	color: white;
        
        }
    </style>

</head>

<body>
    <div class="container">
        <div class="main my-5 rounded">
            <h1 class="text-center">School Name</h1>
            <h2 class="text-center">Teacher Name</h2>

            <div class=" my-5 d-flex justify-content-center align-items-center">
                <a href="add_lesson" class="mx-3 btn btn-success">Add Lesson</a>
                <a href="add_student" class="btn btn-success mr-3">Add new Students</a>                
                <a href="remove_lesson" class="btn btn-danger">Remove Lesson</a>
            </div>




            <!-- all lessons -->
            <div class="all-lessons row">
            
            			<%
				ConnectionProvider conn = new ConnectionProvider();

				DaoClass dao = new DaoClass(conn.getConnection());

				List<Lesson> allLessons = dao.getAllLessons();
				
				if(allLessons !=null)
				
				for(Lesson lesson : allLessons){	
					
			        // Create a SimpleDateFormat object with the desired format
			        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a';' dd'th' MMMM yyyy");

			        // Format the timestamp as a string
			        String formattedTimestamp = dateFormat.format(lesson.getSchedule());
				%>          
            
                <div class="col-4">
                    <a href="student_attendance.jsp?lesson_id=<%=lesson.getId() %>" class="lesson">
                        <%=lesson.getLesson_name() %> - <%=formattedTimestamp %>
                    </a>
                </div>
                <%
				}
				%>                
            </div>
        </div>
    </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>