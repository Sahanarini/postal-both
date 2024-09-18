package com.postal.hibrepoimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postal.model.Address;
import com.postal.model.User;
import com.postal.repository.AddressRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class AddressRepoImp implements AddressRepo {

	@Autowired
	EntityManager em;
	
	@Override
	public Address addAddress(Address address) {
		return em.merge(address);
		
	}

	@Override
	public void updateAddress(Address address) {
		em.merge(address);
	}

	@Override
	public Address findByaddId(int id) {
		Query q = em.createQuery("from Address where id = ?1");
		q.setParameter(1, id);
		return (Address) q.getSingleResult();
	}
	

}
