package com.abc.user.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer extends User {
	
	@Pattern(regexp = "^[a-zA-Z]{4,12}$", message = "Only Alphabhet are allowed here")
    private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z]{4,12}$", message = "Only Alphabhet are allowed here")
    private String lastName;
	
    private String address;
    
    private String email;
    
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid Mobile Number")
    private String mobile;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Customer() {
        // Default constructor is needed for JPA
    }

	public Customer(String firstName, String lastName, String address, String email, String mobile) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
	}
    
    
}
