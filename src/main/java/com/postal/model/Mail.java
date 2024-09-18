package com.postal.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Mail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mId;
	
	private String service;
	
	private String articleType;

	private String articlecontent;

	private LocalDate createdAt;

	private int price;

	private int weight;

	private int length;

	private int height;

	private int width;

	private int value;

	private String collectiondate;

	private String time;

	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL) // Added cascade type
	@JoinColumn(name = "add_id")
	private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_id") // Foreign key in Employee table
    private List<Employee> employees = new ArrayList<>();

	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mail(int mId, String service, String articleType, String articlecontent, LocalDate createdAt, int price,
			int weight, int length, int height, int width, int value, String collectiondate, String time, String status,
			User user, Address address, List<Employee> employees) {
		super();
		this.mId = mId;
		this.service = service;
		this.articleType = articleType;
		this.articlecontent = articlecontent;
		this.createdAt = createdAt;
		this.price = price;
		this.weight = weight;
		this.length = length;
		this.height = height;
		this.width = width;
		this.value = value;
		this.collectiondate = collectiondate;
		this.time = time;
		this.status = status;
		this.user = user;
		this.address = address;
		this.employees = employees;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getArticlecontent() {
		return articlecontent;
	}

	public void setArticlecontent(String articlecontent) {
		this.articlecontent = articlecontent;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getCollectiondate() {
		return collectiondate;
	}

	public void setCollectiondate(String collectiondate) {
		this.collectiondate = collectiondate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	}
