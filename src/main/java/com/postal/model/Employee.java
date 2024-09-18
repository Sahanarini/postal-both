package com.postal.model;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;

	private String empName;

	private long empMobile;

	private String area;

	@Column(nullable = true)
	private int slot;

	@Column(nullable = true)
	private LocalDate deliverydate;

	@Column(nullable = true)
	private LocalTime deliverytime;

	private String empEmail;

	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "post_office_pincode", nullable = false)
	private PostOfficeHead pincode;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int empId, String empName, long empMobile, String area, int slot, LocalDate deliverydate,
		LocalTime deliverytime, String empEmail, String password, PostOfficeHead pincode) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empMobile = empMobile;
		this.area = area;
		this.slot = slot;
		this.deliverydate = deliverydate;
		this.deliverytime = deliverytime;
		this.empEmail = empEmail;
		this.password = password;
		this.pincode = pincode;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public long getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(long empMobile) {
		this.empMobile = empMobile;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(LocalDate deliverydate) {
		this.deliverydate = deliverydate;
	}

	public LocalTime getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(LocalTime deliverytime) {
		this.deliverytime = deliverytime;
	}

	public PostOfficeHead getPincode() {
		return pincode;
	}

	public void setPincode(PostOfficeHead pincode) {
		this.pincode = pincode;
	}

}
