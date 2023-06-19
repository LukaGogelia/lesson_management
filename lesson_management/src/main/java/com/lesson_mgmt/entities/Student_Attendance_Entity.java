package com.lesson_mgmt.entities;

import java.sql.Timestamp;

public class Student_Attendance_Entity {

	private Lesson lesson;
	private Student student;
	private boolean attendance;
	private int latency;
	private Timestamp time;
	
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp attendance_time) {
		this.time = attendance_time;
	}

	public Student_Attendance_Entity() {
		// TODO Auto-generated constructor stub
	}	
	
	
	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isAttendance() {
		return attendance;
	}
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
	public int getLatency() {
		return latency;
	}
	public void setLatency(int latency) {
		this.latency = latency;
	}
	
	
	
}
