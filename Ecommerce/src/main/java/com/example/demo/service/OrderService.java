package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.exception.CartException;
import com.example.demo.exception.CustomerException;
import com.example.demo.exception.LoginException;
import com.example.demo.exception.OrderException;
import com.example.demo.model.Orders;

public interface OrderService {

    public  Orders addOrder(Orders order,String key) throws LoginException, CartException, OrderException,CustomerException;
	
	public  Orders updateCustomer(Orders order,String key) throws LoginException, CartException, OrderException;
	
	public  Orders removeOrder(Integer id,String key) throws LoginException, CartException, OrderException;

	public  Orders viewOrder(Integer id) throws CartException, OrderException;
	
	public List<Orders> viewallOrderByDate(LocalDate date) throws OrderException;
}
