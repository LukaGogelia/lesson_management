package com.lesson_mgmt.helpers;

public class ServerMessage {

	private String type;
	private String message;
	private String css;	
	
	
	public ServerMessage(String type, String message, String css) {
		super();
		this.type = type;
		this.message = message;
		this.css = css;
	}
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}

	
	
}
