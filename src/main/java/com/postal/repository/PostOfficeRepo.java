package com.postal.repository;

import java.util.List;
import java.util.Optional;

import com.postal.model.PostOfficeHead;


public interface PostOfficeRepo {
	public void addPo(PostOfficeHead po);

	public void delPo(int pincode);

	public void updatePo(PostOfficeHead po);

	public List<PostOfficeHead> getAllPos();

	public PostOfficeHead findByPincode(int pincode);

	Optional<PostOfficeHead> findByCity(String city);
	Optional<PostOfficeHead> findByState(String state);

	public PostOfficeHead Login(int pincode, String password);

}
