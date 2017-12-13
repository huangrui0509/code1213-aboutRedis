package com.example.domain;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * Description:
	 * @author lenovo
	 */
	private static final long serialVersionUID = 1L;

	private  String userName;
	private String email;
	private int id;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String userName, String email, int id) {
		super();
		this.userName = userName;
		this.email = email;
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", id=" + id + "]";
	}
	
	
	
	
}
