package com.abc.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.user.entity.Customer;
import com.abc.user.exception.ResourceNotFoundException;
import com.abc.user.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return userRepository.save(customer);
    }

    @Override
	public Customer getCustomerById(int userId) {
		Optional<Customer> optionalCustomer = userRepository.findById(userId);
        if(optionalCustomer.isEmpty()) {
        	throw new ResourceNotFoundException("Customer not existing with id: "+userId);
        }
        Customer customer = optionalCustomer.get();
        return customer;
	}

    @Override
    public List<Customer> getAllCustomers() {
        return userRepository.findAll();
    }

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> optionalCustomer = userRepository.findById(customer.getUserId());
        if(optionalCustomer.isEmpty()) {
        	throw new ResourceNotFoundException("Customer not existing with id: "+customer.getUserId());
        }
        userRepository.save(customer);
        return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		Optional<Customer> optionalCustomer = userRepository.findById(id);
        if(optionalCustomer.isEmpty()) {
        	throw new ResourceNotFoundException("Medicine not existing with id: "+id);
        }
        Customer customer = optionalCustomer.get();
        userRepository.delete(customer);
	}

}