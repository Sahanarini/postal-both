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

import com.postal.serviceimplementation.UserServiceImplementation;
import com.postal.model.User;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserServiceImplementation service;

	@PostMapping("/addUser")
	public String userAdd(@RequestBody User user) {
		service.addUser(user);
		return "added";

	}

//	@GetMapping("/Login/{email}/{password}")
//	public ResponseEntity<?> validateLogin(@PathVariable("email") String email,
//			@PathVariable("password") String password) {
//		try {
//			User user = service.Login(email, password);
//			if (user != null) {
//				return ResponseEntity.ok(user);
//			}
//		} catch (Exception e) {
//			System.out.println("Error user login");
//
//		}
//
//		return (ResponseEntity<?>) ResponseEntity.badRequest();
//	}

	@GetMapping("/Login/{email}/{password}")
	public ResponseEntity<?> validateLogin(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		try {
			User user = service.Login(email, password);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				// Return 404 Not Found if user is not found
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			// Log the error and return a 500 Internal Server Error
			System.out.println("Error user login: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	@GetMapping("/getUsers")
	public List<User> getAll() {
		return service.getAllUsers();

	}

	@GetMapping("/getuser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		User user = service.findById(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/userUpdate/{id}")
	public String userUpadet(@RequestBody User user, @PathVariable("id") int id) {
		User user1 = service.findById(id);
		if (user1 != null) {
			service.updateUser(user);
			return "User Object Updated";
		}

		return "User not updated";

	}

}
