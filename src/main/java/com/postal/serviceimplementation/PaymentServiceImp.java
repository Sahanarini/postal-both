package com.postal.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postal.hibrepoimplementation.PaymentRepoImp;
import com.postal.model.Payment;
import com.postal.service.PaymentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {

	@Autowired
	private PaymentRepoImp repo;
	
	@Override
	public void addPayment(Payment payment) {

		repo.addPayment(payment);
	}

}
