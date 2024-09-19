package com.postal.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;
import java.util.HashMap;
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
import com.postal.model.Mail;
import com.postal.model.User;
import com.postal.serviceimplementation.MailServiceImp;

@RestController
@CrossOrigin("*")
public class MailServiceController {

	@Autowired
	private MailServiceImp service;

	@GetMapping("/pincode/{mId}")
	public ResponseEntity<?> getUserPincodeByMailId(@PathVariable("mId") int mId) {
		Optional<Integer> pincodeOptional = service.findUserPincodeByMailId(mId);

		if (pincodeOptional.isPresent()) {
			return ResponseEntity.ok(Collections.singletonMap("pincode", pincodeOptional.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("error", "Pincode not found for the given Mail ID."));
		}
	}

	@GetMapping("/topincode/{mId}")
	public ResponseEntity<?> getUsertoPincodeByMailId(@PathVariable("mId") int mId) {
		Optional<Integer> pincodeOptional = service.findToPincodeByMailId(mId);

		if (pincodeOptional.isPresent()) {
			return ResponseEntity.ok(Collections.singletonMap("pincode", pincodeOptional.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("error", "Pincode not found for the given Mail ID."));
		}
	}

	@PostMapping("/addMail")
	public ResponseEntity<Map<String, Object>> addMal(@RequestBody Mail mail) {
		Map<String, Object> response = new HashMap<>();
		try {
			User user = mail.getUser();
			if (user != null && user.getUserId() > 0) {
				User existingUser = service.findById(user.getUserId());
				if (existingUser != null) {
					mail.setUser(existingUser);
				} else {
					response.put("error", "User not found");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
			}

			Address address = mail.getAddress();
			if (address != null && address.getId() > 0) {
				Address existingAddress = service.findByaddId(address.getId());
				if (existingAddress != null) {
					mail.setAddress(existingAddress);
				} else {
					response.put("error", "Address not found");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
			}

			Integer savedMailId = service.addMal(mail.getService(), mail.getArticleType(), mail.getArticlecontent(),
					mail.getCreatedAt(), mail.getPrice(), mail.getWeight(), mail.getLength(), mail.getHeight(),
					mail.getWidth(), mail.getValue(), mail.getCollectiondate(), mail.getTime(), mail.getStatus(),
					mail.getUser().getUserId(), mail.getAddress().getId());

			response.put("mId", savedMailId);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("error", "Error adding Mail: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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