package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.CartException;
import com.example.demo.exception.LoginException;
import com.example.demo.exception.ProductException;
import com.example.demo.model.Cart;
import com.example.demo.model.ProductCart;

public interface CartService {

	
    public Cart addProductToCart(Integer productId, Integer quantity, String key) throws CartException,LoginException,ProductException;
		
	public List<ProductCart> removeProductfromCart(Integer productId, String key) throws CartException,LoginException,ProductException;	
	
	
	
	public List<ProductCart> viewAllProducts(String key) throws CartException,LoginException;
	
}
