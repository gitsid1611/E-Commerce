package com.example.demo.service;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.exception.LoginException;

public interface LoginService {

    public String loginAdmin(AdminDto admin) throws LoginException;
	
	public String logoutAdmin(String key) throws LoginException;
	
	
	public String loginCustomer(LoginDto customer) throws LoginException;
	
	public String logoutCustomer(String key) throws LoginException;
	
	
	
	
}
