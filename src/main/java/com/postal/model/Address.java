package com.postal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String toName;
	private String toAddress;
	private String toCountry;
	private String toState;
	private String toCity;
	
	private int toPincode;
	private String toEmail;
	private String toMobile;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int id, String toName, String toAddress, String toCountry, String toState, String toCity,
			int toPincode, String toEmail, String toMobile) {
		super();
		this.id = id;
		this.toName = toName;
		this.toAddress = toAddress;
		this.toCountry = toCountry;
		this.toState = toState;
		this.toCity = toCity;
		this.toPincode = toPincode;
		this.toEmail = toEmail;
		this.toMobile = toMobile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	public String getToState() {
		return toState;
	}
	public void setToState(String toState) {
		this.toState = toState;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public int getToPincode() {
		return toPincode;
	}
	public void setToPincode(int toPincode) {
		this.toPincode = toPincode;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getToMobile() {
		return toMobile;
	}
	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}
	
	

}
