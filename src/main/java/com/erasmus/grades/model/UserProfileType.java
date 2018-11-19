package com.erasmus.grades.model;

public enum UserProfileType {
	STUDENT("STUDENT"),
	TEACHER("TEACHER"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
