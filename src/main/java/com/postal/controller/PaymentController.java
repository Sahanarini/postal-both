package com.postal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postal.model.Payment;
import com.postal.serviceimplementation.PaymentServiceImp;

@RestController
@CrossOrigin("*")
public class PaymentController {
	
	@Autowired
	private PaymentServiceImp service;
	
	@PostMapping("/addPayment")
	public String paymentAdd(@RequestBody Payment payment) {
		service.addPayment(payment);
		return "added";

	}

}
