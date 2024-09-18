package com.postal.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class PostOfficeHead {
	@Id
	private int pincode;

	private String postOfficeName;

	private String officeType;

	private String location;
	
	private String state;
	private String city;

	private String telephone;

	private String role;

	@Column(nullable = false)
	private String password;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "post_office_id", nullable = true)
//	private List<Employee> employee = new ArrayList<>();

	public PostOfficeHead() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public PostOfficeHead(int pincode, String postOfficeName, String officeType, String location, String state, String city,
		String telephone, String role, String password) {
	super();
	this.pincode = pincode;
	this.postOfficeName = postOfficeName;
	this.officeType = officeType;
	this.location = location;
	this.state = state;
	this.city = city;
	this.telephone = telephone;
	this.role = role;
	this.password = password;
}



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getPostOfficeName() {
		return postOfficeName;
	}

	public void setPostOfficeName(String postOfficeName) {
		this.postOfficeName = postOfficeName;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
