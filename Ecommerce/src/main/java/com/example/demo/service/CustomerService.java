package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.CustomerException;
import com.example.demo.exception.LoginException;
import com.example.demo.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCust(Customer customer, String key) throws CustomerException, LoginException;
	
	public Customer removeCustomer(Customer customer,String key) throws CustomerException, LoginException;
	
	public Customer viemCustomer(Integer cusInteger) throws CustomerException;
	
	public List<Customer> viewAllCustomer() throws CustomerException;
	
}
