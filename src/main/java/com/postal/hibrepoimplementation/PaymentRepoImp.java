package com.postal.hibrepoimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postal.model.Payment;
import com.postal.repository.PaymentRepo;

import jakarta.persistence.EntityManager;

@Repository

public class PaymentRepoImp implements PaymentRepo{

	
	@Autowired
	EntityManager em;

	
	@Override
	public void addPayment(Payment payment) {
		em.merge(payment);
	}

}
