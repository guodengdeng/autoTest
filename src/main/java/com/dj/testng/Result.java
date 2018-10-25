package com.dj.testng;

public class Result {
	
	private int httpStatus;
	private String content;
	
	public Result(int httpStatus, String content) {
		super();
		this.httpStatus = httpStatus;
		this.content = content;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
