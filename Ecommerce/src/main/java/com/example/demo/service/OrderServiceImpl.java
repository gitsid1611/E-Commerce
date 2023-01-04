package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddressDto;
import com.example.demo.exception.CartException;
import com.example.demo.exception.CustomerException;
import com.example.demo.exception.LoginException;
import com.example.demo.exception.OrderException;
import com.example.demo.model.Address;
import com.example.demo.model.Cart;
import com.example.demo.model.CurrentUserSession;
import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.model.ProductCart;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.CurrentUserRepo;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	  @Autowired
	  private CurrentUserRepo sdo;
	
	 @Autowired
	 private OrderRepo od;
	
	 @Autowired
	 private CustomerRepository cus;
	
	 
	 @Autowired
	 private CartRepo cartdao;
	
	
	
	

	@Override
	public Orders addOrder(Orders order, String key) throws LoginException, CartException, OrderException, CustomerException {
		
		
		CurrentUserSession ke = sdo.findByUuid(key);
	    
	    if(ke==null)
	    {
	    	throw new LoginException("You are not Authorized");
	    }
	    else
	    {
	    	
	    	
	    	 Integer customerId =  ke.getUserId();
	    	 
	    	 Optional<Customer>  s = cus.findById(customerId);
	    	 
	    	 Address ss = s.get().getAddress();
	    	 
	    	 Orders curr  = new Orders();
	    	 
	    	 curr.setOrderDate(LocalDate.now());
             curr.setAddress( new AddressDto(ss.getStreetNo(), ss.getBulidingName(), ss.getCity(), ss.getState(), ss.getCountry(), ss.getPincode()));
             curr.setCustomer(s.get());
             curr.setOrderStatus("Order Confrimed");
             
             
             List<ProductCart> list = cartdao.findByCustomer(s.get()).getProducts();
             
             if( list.size() < 1) {
				 throw new CartException("Add product to the cart first...");
			 }
             
             
             
             List<ProductCart> product = new ArrayList<ProductCart>();
             
             Double total = 0.0;
//             for(ProductDto s: )
	    	
             
             for(ProductCart proDto : list) {
				 
				 product.add(proDto);
				 
				 total += (proDto.getPrice() * proDto.getQuantity()) ;
				 
			 }
             
             curr.setTotal(total);	
			 curr.setPoder(product);
			 
			 
			 
           Integer customerI =  ke.getUserId();
	    	 
	    	 Optional<Customer>  t = cus.findById(customerI);
			 
			 
			 Cart customerCart = cartdao.findByCustomer(t.get()) ;
			 
			 customerCart.setProducts(new ArrayList<>());
			 
			 cartdao.save(customerCart);
			 
			 return od.save(curr);
             
             
	    }
		
		
		
	}

	@Override
	public Orders updateCustomer(Orders order, String key) throws LoginException, CartException, OrderException {
		
		
		CurrentUserSession s = sdo.findByUuid(key);
		  
		  if(s==null)
		  {
			  throw new LoginException("Sorry you are not Authorized");
		  }
		  else
		  {
			  Optional<Orders> opt=  od.findById(order.getOrderId());
			  
			  
			  if(opt.isPresent()) {
					return od.save(order);
				}
				else {
					throw new OrderException("Order does not exist");
				}
			  
		  }
		
	}

	@Override
	public Orders removeOrder(Integer id, String key) throws LoginException, CartException, OrderException {
        
		CurrentUserSession s = sdo.findByUuid(key);
		
		if(s==null)
			{
			       throw new LoginException("You are not Authorized");
			}
		else
			{
			   Optional<Orders> ss = od.findById(id);
			   
			    if(ss.isEmpty())
			    {
			    	throw new  OrderException("You Don't have orders prodcut");
			    }
			    else
			    {
			    	Orders se = ss.get();
			    	od.deleteById(id);
			    	
			    	return se;
			    }
			     
			}
	}

	@Override
	public Orders viewOrder(Integer id) throws CartException, OrderException {
      Optional<Orders> s = od.findById(id);
		
		Orders ss = s.get();
		
		
		return ss;
	}

	@Override
	public List<Orders> viewallOrderByDate(LocalDate date) throws OrderException {
		 List<Orders> s = od.findByorderDate(date);
			
			
			return s;
	}

}
