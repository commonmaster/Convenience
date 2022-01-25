package com.stone.springmvc.common;

import java.sql.Date;

public class Member {

	int no;
	String id;
	String password;
	String name;
	String email;
	int state;
	Date regDate;
	
	public Member() {}	
	
	public Member(String id, String password, String name, String email) {
		
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public Member(int no, String id, String password, String name, String email, int state,  Date regDate) {
		
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.state = state;
		this.regDate = regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getState() {
		return state;
	}

	public Date getRegDate() {
		return regDate;
	}
}
