package com.lesson_mgmt.entities;

import java.sql.Timestamp;

public class Lesson {

	private int id;
	private String lesson_name;
	private Timestamp schedule;
	
	
	
	
	public Lesson() {
		super();
	}
	public Lesson(int id, String lesson_name, Timestamp schedule) {
		super();
		this.id = id;
		this.lesson_name = lesson_name;
		this.schedule = schedule;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLesson_name() {
		return lesson_name;
	}
	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}
	public Timestamp getSchedule() {
		return schedule;
	}
	public void setSchedule(Timestamp schedule) {
		this.schedule = schedule;
	}
	
	
	
	
}
