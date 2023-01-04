package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AdminException;
import com.example.demo.exception.CustomerException;
import com.example.demo.exception.LoginException;
import com.example.demo.model.CurrentUserSession;
import com.example.demo.model.Customer;
import com.example.demo.repository.CurrentUserRepo;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	  private CurrentUserRepo sdo;
	  
	   @Autowired
	  private CustomerRepository cus;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		
		Customer existing = cus.findByEmail(customer.getEmail());
		
		if(existing != null)
		
			throw new CustomerException("Email already exists " + customer.getEmail());
		
		
		return cus.save(customer);
	}

//	////////////////////Update Customer
	
	@Override
	public Customer updateCust(Customer customer, String key) throws CustomerException, LoginException {

		CurrentUserSession s = sdo.findByUuid(key);
		
		if(s==null)
		{
			throw new LoginException("You are not authoridzed to update this ");
		}
      else if( customer.getMobilenumber().toCharArray().length != 10 ){
			
			throw new CustomerException("Mobile Number can only be of 10 digit");
		}
		
		if(customer.getCustomerId() ==s.getUserId()) {
			return cus.save(customer) ;
		}
		else {
			throw new CustomerException("Can't change UserID!") ;
		}
	}

//	////////////////////////  Deleting account
	
	
	@Override
	public Customer removeCustomer(Customer customer, String key) throws CustomerException, LoginException {

		CurrentUserSession s = sdo.findByUuid(key);
		if(s==null)
		{
			throw new LoginException("You are not authoridzed Delete this ");
		}
		else
		{
			Optional<Customer> ss = cus.findById(customer.getCustomerId());
			
			 Customer sss = ss.get();
			 
			   cus.deleteById(customer.getCustomerId());
			   
			return sss;
		}
		
	}

	
//	/////////////////////////////////  customer service by admin
	
	@Override
	public Customer viemCustomer(Integer cusInteger) throws CustomerException {
		Optional<Customer> ss = cus.findById(cusInteger);
		
		 if(ss.isEmpty())
		 {
			 throw new CustomerException("Id Was In-Correct");
		 }
		 else {
			 return ss.get();
		 }
	}

	@Override
	public List<Customer> viewAllCustomer() throws CustomerException {
		// TODO Auto-generated method stub
		List<Customer> allcus = cus.findAll();

		if(allcus.isEmpty()) {
			throw new CustomerException("No Customer In The Detabase");
		}
		else {
			return allcus;
		}
	}

}
