package com.stone.springmvc.member.common;

import java.sql.Date;

public class Member {

	String id;
	String password;
	String name;
	String email;
	int state;
	Date regDate;
	int no;
	
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
	
	public void setNo(int no) {
		this.no = no;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		
		return id + " " + password +" "+ name;
	}
}
