package com.postal.serviceimplementation;

import java.security.SecureRandom;

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
	
	private String generateTrackingId() {
        SecureRandom random = new SecureRandom();
        long trackingId = 100000000000L + (long)(random.nextDouble() * 900000000000L);
        return String.valueOf(trackingId);
    }
	
	@Override
    public void addPayment(Payment payment) {
        payment.setTrackingid(generateTrackingId());
        repo.addPayment(payment);
    }

}
