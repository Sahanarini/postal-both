package com.postal.service;

import java.util.List;
import java.util.Optional;

import com.postal.model.PostOfficeHead;

public interface PostOfficeService {
	public void addPo(PostOfficeHead po);
	public void delPo(int pincode);
	public void updatePo(PostOfficeHead po);
	public List<PostOfficeHead> getAllPos();
	public PostOfficeHead findByPincode(int pincode);
	public Optional<PostOfficeHead> findByCity(String city);
	public Optional<PostOfficeHead> findByState(String state);
	public PostOfficeHead Login(int pincode, String password);
}
