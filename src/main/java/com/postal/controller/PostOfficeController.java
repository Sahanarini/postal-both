package com.postal.controller;

import java.util.List;
import java.util.Optional;

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

import com.postal.model.PostOfficeHead;
import com.postal.serviceimplementation.PostOfficeServiceImp;

@RestController
@CrossOrigin("*")
public class PostOfficeController {
	
	@Autowired
	private PostOfficeServiceImp service;
	
	@PostMapping("/addPo")
	public String AddPostoffice(@RequestBody PostOfficeHead po) {
		service.addPo(po);
		return "added";

	}
	
//	@PostMapping("/addPostOfficeHead")
//    public ResponseEntity<String> addPostOfficeHead(@RequestBody PostOfficeHead po) {
//        try {
//            // Save the PostOfficeHead and its employees in one transaction
//            service.addPo(po);
//            return ResponseEntity.ok("PostOfficeHead added");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding PostOfficeHead");
//        }
//    }

	@GetMapping("/getAllPostoffice")
	public List<PostOfficeHead> getAll() {
		return service.getAllPos();

	}

	@PutMapping("/UpdatePostoffice")
	public String updatePostoffice(@RequestBody PostOfficeHead po) {
		service.updatePo(po);
		return "postoffice Updated";

	}
	
	@GetMapping("/PoLogin/{pincode}/{password}")
	public ResponseEntity<?> validateLogin(@PathVariable("pincode")int pincode,
			@PathVariable("password") String password) {
		try {
			PostOfficeHead po = service.Login(pincode,password);
			if (po != null) {
				return ResponseEntity.ok(po);
			}
		} catch (Exception e) {
			System.out.println("Error user login");

		}

		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}
	
	
	@GetMapping("/postoffice/{pincode}")
	public ResponseEntity<PostOfficeHead> getPostOfficeByPincode(@PathVariable int pincode) {
	    PostOfficeHead postOfficeHead = service.findByPincode(pincode);
	    if (postOfficeHead != null) {
	        return ResponseEntity.ok(postOfficeHead);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


    // Endpoint to find PostOfficeHead by city
    @GetMapping("/city/{city}")
    public ResponseEntity<Optional<PostOfficeHead>> getPostOfficeByCity(@PathVariable String city) {
        Optional<PostOfficeHead> postOfficeHead = service.findByCity(city);
        if (postOfficeHead.isPresent()) {
            return ResponseEntity.ok(postOfficeHead);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to find PostOfficeHead by state
    @GetMapping("/state/{state}")
    public ResponseEntity<Optional<PostOfficeHead>> getPostOfficeByState(@PathVariable String state) {
        Optional<PostOfficeHead> postOfficeHead = service.findByState(state);
        if (postOfficeHead.isPresent()) {
            return ResponseEntity.ok(postOfficeHead);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	

}
