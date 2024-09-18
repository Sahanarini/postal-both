package com.postal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postal.model.Address;
import com.postal.model.User;
import com.postal.serviceimplementation.AddressServiceImp;

@RestController
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressServiceImp service;

//	@PostMapping("/addAddress")
//	public String userAddress(@RequestBody Address address) {
//		service.addAddress(address);
//		return "added";
//
//	}
	
	
	@PostMapping("/addAddress")
	public ResponseEntity<Address> userAddress(@RequestBody Address address) {
		Address savedAddress = service.addAddress(address);
		if (savedAddress != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/addressUpdate")
	public String addressUpdate(@RequestBody Address address) {
		service.updateAddress(address);
		return "User Object Updated";
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAddressById(@PathVariable("id") int id) {
		try {
			Address address = service.findByaddId(id);
			if (address != null) {
				return ResponseEntity.ok(address);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			// Log the exception (optional)
			System.err.println("Error fetching address: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching address");
		}
	}

}
