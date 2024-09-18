package com.postal.service;

import com.postal.model.Address;

public interface AddressService {
	public Address addAddress(Address address);
	public void updateAddress(Address address);
	public Address findByaddId(int id);
	

}
