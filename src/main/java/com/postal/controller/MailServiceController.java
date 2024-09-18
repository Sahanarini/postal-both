package com.postal.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postal.hibrepoimplementation.AddressRepoImp;
import com.postal.model.Address;
import com.postal.model.Employee;
import com.postal.model.Mail;
import com.postal.model.PostOfficeHead;
import com.postal.model.User;
import com.postal.serviceimplementation.MailServiceImp;

@RestController
@CrossOrigin("*")
public class MailServiceController {

	@Autowired
	private MailServiceImp service;

	@Autowired
	private AddressRepoImp Addservice;

	@GetMapping("/pincode/{mId}")
	public ResponseEntity<?> getUserPincodeByMailId(@PathVariable("mId") int mId) {
		Optional<Integer> pincodeOptional = service.findUserPincodeByMailId(mId);

		if (pincodeOptional.isPresent()) {
			// Return pincode as a JSON object with a key for consistency
			return ResponseEntity.ok(Collections.singletonMap("pincode", pincodeOptional.get()));
		} else {
			// Return error message in a consistent JSON format
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("error", "Pincode not found for the given Mail ID."));
		}
	}

	@GetMapping("/topincode/{mId}")
	public ResponseEntity<?> getUsertoPincodeByMailId(@PathVariable("mId") int mId) {
		Optional<Integer> pincodeOptional = service.findToPincodeByMailId(mId);

		if (pincodeOptional.isPresent()) {
			// Return pincode as a JSON object with a key for consistency
			return ResponseEntity.ok(Collections.singletonMap("pincode", pincodeOptional.get()));
		} else {
			// Return error message in a consistent JSON format
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("error", "Pincode not found for the given Mail ID."));
		}
	}

//	 Address address = mail.getAddress();
//     if (address.getId() == 0 || !addressRepository.existsById(address.getId())) {
//         // Address does not exist or has no ID; save it
//         addressRepository.save(address);
//     }
//     // Save the Mail entity
//     mailRepository.save(mail);
// }

//	@PostMapping("/addMail")
//	public ResponseEntity<String> addMail(@RequestBody Mail mail) {
//		try {
//			Address address = mail.getAddress();
////			if (address.getId() == 0) {
////				// Address does not exist or has no ID; save it
////				Addservice.addAddress(address);
////				
////
////			}
//			
//			service.addMail(mail);
//			return ResponseEntity.ok("Mail added successfully");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding Mail");
//		}
//	}

//	@PostMapping("/addMail")
//	public ResponseEntity<String> addMail(@RequestBody Mail mail) {
//	    try {
//	        // Fetch and set User if provided
//	        User user = mail.getUser();
//	        if (user != null && user.getUserId() > 0) {
//	            // Fetch user by ID
//	            User existingUser = service.findById(user.getUserId());
//	            if (existingUser != null) {
//	                mail.setUser(existingUser);
//	            } else {
//	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
//	            }
//	        }
//	        
//	        // Fetch and set Address if provided
//	        Address address = mail.getAddress();
//	        if (address != null && address.getId() > 0) {
//	            // Fetch address by ID
//	            Address existingAddress = service.findByaddId(address.getId());
//	            if (existingAddress != null) {
//	                mail.setAddress(existingAddress);
//	            } else {
//	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address not found");
//	            }
//	        }
//	        
//	        service.addMail(mail);
//	        return ResponseEntity.ok("Mail added successfully");
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding Mail: " + e.getMessage());
//	    }
//	}

	@PostMapping("/addMail")
	public ResponseEntity<String> addMail(@RequestBody Mail mail) {
		try {
			// Fetch and set User if provided
			User user = mail.getUser();
			if (user != null && user.getUserId() > 0) {
				// Fetch user by ID
				User existingUser = service.findById(user.getUserId());
				if (existingUser != null) {
					mail.setUser(existingUser);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
				}
			}

			// Fetch and set Address if provided
			Address address = mail.getAddress();
			if (address != null && address.getId() > 0) {
				// Fetch address by ID
				Address existingAddress = service.findByaddId(address.getId());
				if (existingAddress != null) {
					mail.setAddress(existingAddress);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address not found");
				}
			}

			service.addMail(mail);
			return ResponseEntity.ok("Mail added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding Mail: " + e.getMessage());
		}
	}

	@GetMapping("/getAllMail")
	public List<Mail> getAll() {
		return service.getAllMail();

	}

	@PutMapping("/UpdateMail")
	public String updatePostoffice(@RequestBody Mail mail) {
		service.updateMail(mail);
		return "postoffice Updated";

	}

	@GetMapping("/{mId}/address")
	public ResponseEntity<?> getAddressByMailId(@PathVariable("mId") int mId) {
		Optional<Address> address = service.getAddressByMailId(mId);
		if (address.isPresent()) {
			return ResponseEntity.ok(address.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/mails/{pincode}")
	public ResponseEntity<List<Mail>> getMailsByPinCode(@PathVariable String pincode) {
		List<Mail> mails = service.getMailsByPinCode(pincode);
		return ResponseEntity.ok(mails);
	}

	@GetMapping("/tomails/{toPincode}")
	public ResponseEntity<List<Mail>> findToMailsByPinCode(@PathVariable String toPincode) {
		List<Mail> mails = service.findToMailsByPinCode(toPincode);
		return ResponseEntity.ok(mails);
	}

//	@PostMapping("/mails/assign/{mId}")
//	public ResponseEntity<String> assignEmployeeToMail(@PathVariable("mId") int mId, @RequestBody Map<String, Integer> requestBody) {
//	    try {
//	        Integer employeeId = requestBody.get("empId");
//	        if (employeeId == null) {
//	            throw new RuntimeException("Employee ID is missing");
//	        }
//	        service.assignEmployeeToMail(mId, employeeId);
//	        return ResponseEntity.ok("Employee assigned successfully");
//	    } catch (RuntimeException e) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//	    }
//	}

	@PostMapping("/mails/assign/{mId}")
	public ResponseEntity<String> assignEmployeeToMail(@PathVariable("mId") int mId,
			@RequestBody Map<String, Integer> requestBody) {
		try {
			Integer employeeId = requestBody.get("empId");
			if (employeeId == null) {
				throw new RuntimeException("Employee ID is missing");
			}
			service.assignEmployeeToMail(mId, employeeId);
			return ResponseEntity.ok("Employee assigned successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/frommailsofemployee/{empId}")
	public ResponseEntity<List<Mail>> getfromMailsofEmployee(@PathVariable int empId) {
		List<Mail> mails = service.getFromMailforEmployee(empId);
		return ResponseEntity.ok(mails);
	}
	
	@GetMapping("/tomailsofemployee/{empId}")
	public ResponseEntity<List<Mail>> gettoMailsofEmployee(@PathVariable int empId) {
		List<Mail> mails = service.getToMailforEmployee(empId);
		return ResponseEntity.ok(mails);
	}
	
	@PostMapping("/updateMailStatus")
	public ResponseEntity<String> updateStatus(@RequestBody Map<String, Object> payload) {
		try {
			int mId = (Integer) payload.get("mId");
			String status = (String) payload.get("status");
 
			boolean result = service.updatestatus(mId, status);
			if (result) {
				return ResponseEntity.ok("SUCCESS");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Approval not found");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception for debugging
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILURE");
		}
	}
	

}