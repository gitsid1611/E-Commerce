package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CartException;
import com.example.demo.exception.LoginException;
import com.example.demo.exception.ProductException;
import com.example.demo.model.Cart;
import com.example.demo.model.CurrentUserSession;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.model.ProductCart;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.CurrentUserRepo;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductCartRepo;
import com.example.demo.repository.ProductRepo;


@Service
public class CartServiceImpl implements CartService {
	

		@Autowired
		  private CartRepo cartdao;
		   
		  @Autowired
		  private ProductRepo pdo;
		  
		  @Autowired
		  private ProductCartRepo pdao;
		  
		  @Autowired
		  private CurrentUserRepo sdo;
		  
		   @Autowired
		  private CustomerRepository cus;
		
	
	

	@Override
	public Cart addProductToCart(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {
		 CurrentUserSession currentCustomer  = sdo.findByUuid(key);
		   
		   
		   if(currentCustomer == null) {
				throw new LoginException("Please provide a valid key to update a customer");
			}
		   else
		   {
			
			   Optional<Product> optProduct = pdo.findById(productId) ;
				
				if(optProduct.isEmpty()) {
					throw new ProductException("No product available with id :"+ productId) ;
				}
				Integer s = currentCustomer.getUserId();
				
				Optional<com.example.demo.model.Customer> fin = cus.findById(s);
				
				
				Customer or = fin.get();
				Product currentProduct = optProduct.get();
				
				if(currentProduct.getQuantity() < quantity) {
					throw new ProductException("Product quantity not available or Out of stock") ;
				}
				
				Cart customerCart = cartdao.getCart(or.getCustomerId());
				
				if(customerCart == null) { // user is adding first time in the cart 
					
					customerCart = new Cart();
					
				
					customerCart.setCustomer(or);
					
					List<ProductCart> list = customerCart.getProducts();
					
					ProductCart productDto = new ProductCart( currentProduct.getProductId(),
															currentProduct.getProductName(),
															currentProduct.getPrice(), 
															currentProduct.getColor(), 
															currentProduct.getDimension(),
															currentProduct.getManufacture(),
															quantity);
					
					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					
					list.add(productDto);
					
					
					cartdao.save(customerCart) ;
					pdo.save(currentProduct);
					
					return customerCart;
						
				}
				else {
					
					List<ProductCart> list = customerCart.getProducts();
					
					ProductCart productDto = new ProductCart( currentProduct.getProductId(),
															currentProduct.getProductName(),
															currentProduct.getPrice(), 
															currentProduct.getColor(), 
															currentProduct.getDimension(),
															currentProduct.getManufacture(), 
															quantity);
					
					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					
					list.add(productDto);
					
					
					cartdao.save(customerCart) ;
					pdo.save(currentProduct);
					 
					return customerCart;
			   
				}
	}
	}

	@Override
	public List<ProductCart> removeProductfromCart(Integer productId, String key)
			throws CartException, LoginException, ProductException {

		
		CurrentUserSession currentCustomer = sdo.findByUuid(key);
		
		if(currentCustomer==null)
		{
			throw new LoginException("You are Not Authorized to Delete the Product");
		}
          Optional<ProductCart> optProduct = pdao.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		else
		{
			Integer s = currentCustomer.getUserId();
			
			Optional<Customer> fin = cus.findById(s);
			
			
			Customer or = fin.get();
			
			
			ProductCart currentProduct = optProduct.get();
			
			Cart customerCart = cartdao.getCart(or.getCustomerId());
			
			
			if(customerCart==null)
			{
				throw new CartException("No product found for this ");
			}
			else
			{
				cartdao.deleteById(productId);
				
				cartdao.save(customerCart);
				List<ProductCart> list = customerCart.getProducts();
				
				
				return list;
				
			}
		}
	}

	@Override
	public List<ProductCart> viewAllProducts(String key) throws CartException, LoginException {
		
	    CurrentUserSession ss  = sdo.findByUuid(key);
	    
	     if(ss==null)
	     {
	    	 throw new LoginException("You are not Authorized to Check Cart");
	     }
	     else
	     {
	    	 Integer s = ss.getUserId();
	 		
	 		Optional<Customer> fin = cus.findById(s);
	 		
	 		
	 		Customer or = fin.get();
	 		
	 		Cart customerCart = cartdao.getCart(or.getCustomerId());	    	 
	 		
	 		if(customerCart == null) {
	 			throw new CartException("You dont have anything in your cart");
	 		}
	 		
	 		return customerCart.getProducts();
	     }
		
	}

}
