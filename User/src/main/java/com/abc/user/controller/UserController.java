package com.abc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.user.entity.Customer;
import com.abc.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/save")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
		userService.saveCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> fetchUserDetails(@PathVariable("id") int userId) {
		Customer customer = userService.getCustomerById(userId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = userService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> editCustomer(@Valid @RequestBody Customer customer) {
		userService.updateCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int userId) {
		userService.deleteCustomer(userId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

	
	/*
	 * @PostMapping("/admin") public Admin createAdmin(@RequestBody Admin admin) {
	 * return (Admin) customerService.saveCustomer(admin);
	 * 
	 * }
	 */
	 

}