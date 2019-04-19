package com.to;

import java.util.Date;

public class Player {

	private String id;
	private String name;
	private Date dob;
	private String email;
	private String gender;
	private String teamname;
	private long contact;
	
	
	
	public Player(String name, Date dob, String email, String gender, String teamname, long contact) {
		super();
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.teamname = teamname;
		this.contact = contact;
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public Player(String id, String name, Date dob, String email, String gender, String teamname, long contact) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.teamname = teamname;
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", dob=" + dob + ", email=" + email + ", gender=" + gender
				+ ", teamname=" + teamname + ", contact=" + contact + "]";
	}
	
	
}
