package com.lesson_mgmt.helpers;

import java.sql.*;

public class ConnectionProvider {
	
	private static Connection conn;	
	public static Connection getConnection() {		
		try {
			
			//if i don't use the 'if' condition here, it will load the drive every time when calling conn variable
			if(conn == null) {			
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				String link = "jdbc:mysql://localhost:3306/lesson_mgmt";
				String user = "root";
				String pass = "Jerremy3#";
				
				conn = DriverManager.getConnection(link, user, pass);
				
				if(conn.isClosed()) {
					 System.out.println("Connection is closed");
				}else {
					System.out.println("Connection established");
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}		
		return conn;
	}
	
//	public static void main(String[] args) {
//		Connection con = ConnectionProvider.getConnection();
//		System.out.println("From servlet : "+con);
//	}	
	
}
