package com.abc.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.user.entity.Customer;

public interface UserRepository extends JpaRepository<Customer,Integer> {

}
