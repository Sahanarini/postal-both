package com.postal.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postal.hibrepoimplementation.AddressRepoImp;
import com.postal.hibrepoimplementation.UserRepoImp;
import com.postal.model.Address;
import com.postal.service.AddressService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImp implements AddressService {

	
	@Autowired
	private AddressRepoImp repo;
	
	public Address addAddress(Address address) {
        // Save address and return the saved address (which includes any generated fields like ID)
        return repo.addAddress(address);
    }

	@Override
	public void updateAddress(Address address) {

		repo.updateAddress(address);
	}

	@Override
	public Address findByaddId(int id) {
		// TODO Auto-generated method stub
		return repo.findByaddId(id) ;
	}

}
