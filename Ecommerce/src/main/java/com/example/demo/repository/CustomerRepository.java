package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.CustomerException;
import com.example.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByEmail(String email);
	
	public Customer findByemail(String email);
	
	public Optional<Customer> findByMobilenumber(String mobileNumber) throws CustomerException;

}
