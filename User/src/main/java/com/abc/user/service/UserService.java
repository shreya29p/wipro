package com.abc.user.service;

import java.util.List;

import com.abc.user.entity.Customer;

public interface UserService {
	
    Customer saveCustomer(Customer customer);
    
    Customer getCustomerById(int userId);
    
    List<Customer> getAllCustomers();
    
    Customer updateCustomer(Customer customer);
    
    void deleteCustomer(int id);
}
