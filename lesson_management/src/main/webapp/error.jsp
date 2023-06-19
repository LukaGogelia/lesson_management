<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error login! Something went wrong.</title>


</head>
<body>

	
	<img alt="Error" src="assets/img/error.png" style="height:100vh; width: auto; margin: 0 auto; display: block">	

	<div class="container">
	
		<h2 style="color: royalblue"> error is : <%= exception%></h2>
		<h2 style="color: royalblue"> error is : <%= exception.getMessage() %></h2>
	</div>

</body>
</html>