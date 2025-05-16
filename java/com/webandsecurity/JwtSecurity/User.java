package com.webandsecurity.StandardBank;

public class User {
	String username ,password;
	
	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	

}
