package com.abc.user.entity;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    
    
    private String email;
    private String mobile;
	
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
	
	
	public Admin( String email, String mobile) {
		super();
		
		this.email = email;
		this.mobile = mobile;
	}
    
    
}
