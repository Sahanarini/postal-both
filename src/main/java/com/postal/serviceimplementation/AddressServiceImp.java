package com.postal.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postal.hibrepoimplementation.AddressRepoImp;
import com.postal.model.Address;
import com.postal.service.AddressService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImp implements AddressService {

	
	@Autowired
	private AddressRepoImp repo;
	
	public Address addAddress(Address address) {
        return repo.addAddress(address);
    }

	@Override
	public void updateAddress(Address address) {

		repo.updateAddress(address);
	}

	@Override
	public Address findByaddId(int id) {
		return repo.findByaddId(id) ;
	}

}
