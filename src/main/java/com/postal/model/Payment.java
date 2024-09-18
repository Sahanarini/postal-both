package com.postal.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	
	
	private String trackingid;
	
	private int price;
	
	@OneToOne
	private Mail mail;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	




	public Payment(int pId, String trackingid, int price, Mail mail) {
		super();
		this.pId = pId;
		
		this.trackingid = trackingid;
		this.price = price;
		this.mail = mail;
	}



	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	
	

	public String getTrackingid() {
		return trackingid;
	}



	public void setTrackingid(String trackingid) {
		this.trackingid = trackingid;
	}
	

}
