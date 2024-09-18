package com.postal.repository;

import com.postal.model.Address;

public interface AddressRepo {
	
	public Address addAddress(Address address);
	
	public void updateAddress(Address address);
	
	public Address findByaddId(int id);

	
	


}
