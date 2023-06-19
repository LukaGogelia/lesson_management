package com.lesson_mgmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lesson_mgmt.entities.Lesson;
import com.lesson_mgmt.entities.Student;
import com.lesson_mgmt.entities.Student_Attendance_Entity;





public class DaoClass {

	private Connection conn;

	public DaoClass(Connection conn) {
		this.conn = conn;
	}

	public void addAttendence(int lesson_id, int student_id, int attendence, int latency) {
		try {
			String query = "INSERT INTO `lesson_attendence`(`lesson_id`, `student_id`, `attendance`, `latency`, `attendance_time`) VALUES (?,?,?,?,?)";			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setInt(1, lesson_id);
			pstmt.setInt(2, student_id);
			pstmt.setInt(3, attendence);
			pstmt.setInt(4, latency);			
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pstmt.execute();
			System.out.println("Added attendence");
		} catch (Exception e) {
			System.out.println("Adding new Attendence fail" + e);
		}
	}
	
	
	//get lesson by id
	
	
	//get lesson_attendance by lesson id
	public List<Student_Attendance_Entity> getLessonAttendanceByLessonId(int lesson_id) {
		List<Student_Attendance_Entity> student_Attendance = new ArrayList<>();		
		try {
			String q = "SELECT * FROM `lesson_attendence` WHERE lesson_id='"+lesson_id+"' ORDER BY attendance_time DESC";
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(q);
			
			while(set.next()) {
				int id = set.getInt("id");
				int student_id = set.getInt("student_id");
				int attendance_int =  set.getInt("attendance");
				boolean attendance = false;
				if(attendance_int == 1){
					attendance = true;
				}
				int latency = set.getInt("latency");				
				Timestamp attendance_time = set.getTimestamp("attendance_time");
				
				Student student = getStudentById(student_id);				
				Student_Attendance_Entity stu = new Student_Attendance_Entity();
				stu.setStudent(student);
				stu.setTime(attendance_time);
				stu.setLatency(latency);
				stu.setAttendance(attendance);
				
				
				student_Attendance.add(stu);
				
			}
			
		} catch (Exception e) {
			System.out.println("Error fetching data xxx "+ e);
		}
		
		return student_Attendance;
	}
	
	
	
	//add lesson attendance
	public void addLessonAttendance(int lesson_id, int student_id) {
		try {
			String query = "INSERT INTO `lesson_student`(`lesson_id`, `student_id`) VALUES (?,?)";			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setInt(1, lesson_id);
			pstmt.setInt(2, student_id);
			pstmt.execute();
			System.out.println("Student added for lesson");
		} catch (Exception e) {
			System.out.println("Student added for lesson" + e);
		}
	}
	
	//get all lessons
	public List<Lesson> getAllLessons() {
		List<Lesson> lessons = new ArrayList<>();		
		try {
			String q = "select * from lessons ORDER BY schedule DESC";
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(q);
			
			while(set.next()) {
				int id = set.getInt("id");
				String lesson_name = set.getString("lesson_name");
				Timestamp schedule = set.getTimestamp("schedule");
				
				Lesson l = new Lesson(id, lesson_name, schedule);
				lessons.add(l);				
			}
			
		} catch (Exception e) {
			System.out.println("Error fetching data "+ e);
		}
		
		return lessons;
	}
	
	
	//get lesson by name
	public Lesson getLessonByName(String lesson_name) {
		Lesson lesson = null;		
		try {			
			String q = "select * from lessons WHERE lesson_name = '"+lesson_name+"'";
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(q);	
			
			if(set.next()) {
				int id = set.getInt("id");
				lesson_name = set.getString("lesson_name");
				Timestamp schedule = set.getTimestamp("schedule");
				lesson = new Lesson(id, lesson_name, schedule);		
			}
			
		} catch (Exception e) {
			System.out.println("Error fetching data "+ e);
		}
		
		return lesson;
	}
	
	
	
	public Student getStudentById(int student_id) {
		Student student = null;		
		try {
			String q = "select * from students where id ='"+student_id+"'";
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(q);
			
			if(set.next()) {
				int id = set.getInt("id");
				String name = set.getString("name");
				String phone = set.getString("phone");					
				student = new Student(id, name, phone);				
			}
			
		} catch (Exception e) {
			System.out.println("Error fetching data "+ e);
		}
		return student ;	
	}
	
	
	
	public List<Student> getAllStudents(){
		List<Student> students = new ArrayList<>();		
		try {
			String q = "select * from students";
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(q);
			
			while(set.next()) {
				int id = set.getInt("id");
				String name = set.getString("name");
				String phone = set.getString("phone");
					
				Student s = new Student(id, name, phone);
				students.add(s);
			}
			
		} catch (Exception e) {
			System.out.println("Error fetching data "+ e);
		}
		return students ;		
	}		
	
	
	
	//get all students for specific lesson
	public List<Student> getAllStudentsForLesson(int lesson_id){
		List<Student> students = new ArrayList<>();		
		try {
			
			String query1 = "select * from lesson_student where lesson_id= '"+lesson_id+"' ";
			Statement stmt1 = conn.createStatement();
			ResultSet set1 = stmt1.executeQuery(query1);
			
			while(set1.next()) {
				int l_id = set1.getInt("id");
				int student_id = set1.getInt("student_id");
				
				String q = "select * from students where id='"+student_id+"'";
				Statement stmt = conn.createStatement();
				ResultSet set = stmt.executeQuery(q);
				
				if(set.next()) {
					int id = set.getInt("id");
					String name = set.getString("name");
					String phone = set.getString("phone");
						
					Student s = new Student(id, name, phone);
					students.add(s);
				}				
			}
			
			

		} catch (Exception e) {
			System.out.println("Error fetching data "+ e);
		}
		return students ;		
	}	
	
	
	public boolean addLesson(Lesson lesson) {
		try {
			String query = "INSERT INTO `lessons`( `lesson_name`, `schedule`) VALUES (?,?)";			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, lesson.getLesson_name());
			pstmt.setTimestamp(2, lesson.getSchedule());			
			pstmt.execute();
			System.out.println("Added Lesson");
			return true;
		} catch (Exception e) {
			System.out.println("Adding new Lesson fail" + e);
			return false;
		}
	}

	
	public boolean removeLesson(int lesson_id) {
		try {
			String query = "DELETE FROM `lessons` WHERE id = ?";			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setInt(1, lesson_id);
						
			pstmt.execute();
			System.out.println("removed Lesson");
			return true;
		} catch (Exception e) {
			System.out.println("Adding new Lesson fail" + e);
			return false;
		}
	}

	
	
	public boolean addStudent(Student student) {
		try {
			String query = "INSERT INTO `students`(`name`, `phone`) VALUES (?,?)";			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getPhone());			
			pstmt.execute();
			System.out.println("Added Student");
			return true;
		} catch (Exception e) {
			System.out.println("Adding new Lesson fail" + e);
			return false;
		}
	}
	
	
	
}
