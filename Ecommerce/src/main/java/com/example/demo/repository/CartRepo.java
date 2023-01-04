package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.exception.CustomerException;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;

public interface CartRepo extends JpaRepository<Cart, Integer> {
	
     public Cart findByCustomer(Customer customer) throws CustomerException;
	
	
	@Query("select c from Cart c where c.customer.customerId=?1")
	public Cart getCart(Integer custId);

}
