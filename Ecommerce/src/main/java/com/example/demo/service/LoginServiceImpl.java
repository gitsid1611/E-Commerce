package com.example.demo.service;

import java.time.LocalDateTime;
//import java.util.Random;
//import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.exception.LoginException;
import com.example.demo.model.Admin;
import com.example.demo.model.CurrentUserSession;
import com.example.demo.model.Customer;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CurrentUserRepo;
import com.example.demo.repository.CustomerRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {


	@Autowired
	private AdminRepository admindao;

//	@Autowired
////	private CustomerDao customerRepo;

	@Autowired
	private CurrentUserRepo currUserSession;
	
	@Autowired
	private CustomerRepository cst;
	
	
	
	@Override
	public String loginAdmin(AdminDto admin) throws LoginException {
		Admin existingUser = admindao.findByAdminUsername(admin.getAdminUsername());
		
			if (existingUser == null)
				throw new LoginException("Invalid credentials. Username does not exist " + admin.getAdminUsername());
		
			java.util.Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingUser.getAdminId());
		
			if (validCustomerSessionOpt.isPresent()) {
		
				throw new LoginException("User already Logged In with this username");
		
			}
		
			if (existingUser.getAdminPassword().equals(admin.getAdminPassword())) {
		
				String key = RandomString.make(6);
		
				Boolean isAdmin = true;
		
				CurrentUserSession cs = new CurrentUserSession(existingUser.getAdminId(), key, isAdmin, LocalDateTime.now());
	
				currUserSession.save(cs);
		
				return cs.toString();
			} else
				throw new LoginException("Please Enter a valid password");
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {
		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Logged Out !";
	}

	
//	////////////  Customer login & logout
	
	@Override
	public String loginCustomer(LoginDto customer) throws LoginException {
		Customer existingUser = cst.findByEmail(customer.getEmail());
		
		if (existingUser == null)
			throw new LoginException("Invalid credentials. Email does not exist " + customer.getEmail());
	
		java.util.Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingUser.getCustomerId());
	
		if (validCustomerSessionOpt.isPresent()) {
	
			throw new LoginException("User already Logged In with this username");
	
		}
	
		if (existingUser.getPassword().equals(customer.getPassword())) {
	
			String key = RandomString.make(6);
	
			Boolean isAdmin = false;
	
			CurrentUserSession cs = new CurrentUserSession(existingUser.getCustomerId(), key, isAdmin, LocalDateTime.now());

			currUserSession.save(cs);
	
			return cs.toString();
		} else
			throw new LoginException("Please Enter a valid password");
	}

	@Override
	public String logoutCustomer(String key) throws LoginException {
		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) 
		{
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Logged Out !";
	}

}
