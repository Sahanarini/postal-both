package com.postal.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postal.hibrepoimplementation.PostOfficeRepoImp;
import com.postal.model.PostOfficeHead;
import com.postal.service.PostOfficeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostOfficeServiceImp implements PostOfficeService {

	@Autowired
	private PostOfficeRepoImp repo;
	
	@Override
	public void addPo(PostOfficeHead po) {
		repo.addPo(po);

		
	}

	@Override
	public void delPo(int pincode) {
		repo.delPo(pincode);
	}

	@Override
	public void updatePo(PostOfficeHead po) {
		// TODO Auto-generated method stub
		repo.updatePo(po);
		
	}

	@Override
	public List<PostOfficeHead> getAllPos() {
		// TODO Auto-generated method stub
		return repo.getAllPos() ;
	}

	@Override
	public PostOfficeHead findByPincode(int pincode) {
		// TODO Auto-generated method stub
		return repo.findByPincode(pincode) ;
	}

	@Override
	public Optional<PostOfficeHead> findByCity(String city) {
		// TODO Auto-generated method stub
		return repo.findByCity(city) ;
	}

	@Override
	public Optional<PostOfficeHead> findByState(String state) {
		// TODO Auto-generated method stub
		return repo.findByState(state);
	}

	@Override
	public PostOfficeHead Login(int pincode, String password) {
		PostOfficeHead po = null;
		try {
			po = repo.Login(pincode, password);
		}catch(Exception e) {
			po = null;
		}
		return po;	
	}

}
