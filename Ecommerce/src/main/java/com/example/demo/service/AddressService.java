package com.example.demo.service;

import com.example.demo.exception.AddressException;
import com.example.demo.exception.LoginException;
import com.example.demo.model.Address;

import antlr.collections.List;
//import java.util.List;

public interface AddressService {

    public Address addAddress(Address add , String key) throws AddressException, LoginException;
	
	public Address updateAddress(Address add, String key) throws AddressException , LoginException;
	
	public String removeAddress(Integer addressId,String key) throws AddressException, LoginException;
	
	public java.util.List<Address> viewAlladdress()throws AddressException;
	
    
	
}
