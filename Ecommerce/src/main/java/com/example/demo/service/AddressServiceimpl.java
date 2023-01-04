package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AddressException;
import com.example.demo.exception.CustomerException;
import com.example.demo.exception.LoginException;
import com.example.demo.model.Address;
import com.example.demo.model.CurrentUserSession;
import com.example.demo.model.Customer;
import com.example.demo.repository.AddressDao;
import com.example.demo.repository.CurrentUserRepo;
import com.example.demo.repository.CustomerRepository;

@Service
public class AddressServiceimpl implements AddressService {
	
	@Autowired
	private AddressDao ads;
	
	
	@Autowired
	private  CustomerRepository cust;
	
	@Autowired
	private  CurrentUserRepo ses;

	@Override
	public Address addAddress(Address add, String key) throws AddressException, LoginException {
		 CurrentUserSession loggedInUser= ses.findByUuid(key);
			
			if(loggedInUser == null)
			{
				throw new LoginException("Pleas login First!");
			}
			else
			{
			    
				Optional<Customer> s = cust.findById(loggedInUser.getUserId());
				
				
				  Customer  ss = s.get();
				  
				  Address ad = ads.save(add);
				  
				   ss.setAddress(ad);
				   
				   
				   cust.save(ss);
				   
				   return ad;
			}		   
				  
	}

	@Override
	public Address updateAddress(Address add, String key) throws AddressException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentUserSession loggedInUser= ses.findByUuid(key);
		
		if(loggedInUser == null)
		{
			throw new LoginException("Oops Pleas login and Update the Address");
		}
		else
		{
		    
			Optional<Customer> s = cust.findById(loggedInUser.getUserId());
			
			
			  Customer  ss = s.get();
			  
			  Address ad = ads.save(add);
			  
			   ss.setAddress(ad);
			   
			   
			   cust.save(ss);
			   
			   return ad;
			  
			
			   
		}
	
	}

	@Override
	public String removeAddress(Integer addressId, String key) throws AddressException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentUserSession loggedInUser= ses.findByUuid(key);
		
		if(loggedInUser == null)
		{
			throw new LoginException("Please Provide Valid Username or pass");
		}
		
		else
		{
		       ads.deleteById(addressId);
		       
		       return "Address Deleted";
		}
		
	}

	@Override
	public List<Address> viewAlladdress() throws AddressException {
		// TODO Auto-generated method stub
				
		     List<Address> ad = ads.findAll();
		     
		     if(ad.isEmpty()) {
					throw new AddressException("No Address In The Detabase");
				}
				else {
					return ad;
				}
		
		     
		
		
		
		
	}

	

}
