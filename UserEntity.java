package com.iktpreobuka.projectNew.entities;

import com.iktpreobuka.controllers.Roles;


public class UserEntity {
	
	protected Integer Id;
	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	protected String email;
	protected Roles userRole;
	
	public UserEntity() {}
	
	
	public UserEntity(Integer id, String firstName, String lastName, String username, String password,
			String email, Roles userRole) {
		super();
		this.Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email=email;
		this.userRole=userRole;
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Roles getUserRole() {
		return userRole;
	}


	public void setUserRole(Roles userRole) {
		this.userRole = userRole;
	}


	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	

}
