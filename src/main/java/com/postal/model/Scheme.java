package com.postal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Scheme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	private String schemeName;
	private String description;
	@Lob
	@Column(columnDefinition = "LONGBLOB", length = 1000000)
	private byte[] doc;
	
	@ManyToOne
	private User user;
	
	public Scheme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Scheme(int sId, String schemeName, String description, byte[] doc) {
		super();
		this.sId = sId;
		this.schemeName = schemeName;
		this.description = description;
		this.doc = doc;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getDoc() {
		return doc;
	}
	public void setDoc(byte[] doc) {
		this.doc = doc;
	}
	
	

	

}
