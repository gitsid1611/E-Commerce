package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AddressException;
import com.example.demo.exception.CartException;
import com.example.demo.exception.CustomerException;
import com.example.demo.exception.LoginException;
import com.example.demo.exception.OrderException;
import com.example.demo.exception.ProductException;
import com.example.demo.model.Address;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.model.Product;
import com.example.demo.model.ProductCart;
import com.example.demo.service.AddressService;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerService;

import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/user")
public class CustomerController {


	@Autowired
	private CustomerService cus;
	
	
	@Autowired
	private AddressService ads;
	
	@Autowired
	private CartService car;
	
	@Autowired
	private OrderService odd;
	
	@Autowired
	private ProductService prod;
	
	 
	@PostMapping("/Register")
	 public ResponseEntity<Customer> addcustomer(@Valid @RequestBody Customer customer) throws  CustomerException
	 {
		 Customer addCustomer = cus.addCustomer(customer);
		 
		 return new ResponseEntity<Customer>(addCustomer, HttpStatus.OK );
	 }
	
	
	@PutMapping("/updacustomer/{key}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("key") String key, @RequestBody Customer customer) throws LoginException,CustomerException
	{
		Customer update = cus.updateCust(customer, key);
		
		
		return new ResponseEntity<Customer>(update, HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/delAccount{key}")
	public ResponseEntity<Customer> delAccount(@PathVariable("key") String key, @RequestBody Customer customer) throws LoginException, CustomerException
	{
		
		Customer del = cus.removeCustomer(customer, key);
		
		return new ResponseEntity<Customer>(del, HttpStatus.OK);
	}
	
	
	
//	////////  Address service by customer
	

	@PostMapping("/address")
    public ResponseEntity<Address> addaddress(@RequestBody Address address, @RequestParam String key) throws AddressException, LoginException
    {
		Address s = ads.addAddress(address, key);
		
		return new ResponseEntity<Address>(s, HttpStatus.OK);
    }
	
	
	
	@PutMapping("/address")
    public ResponseEntity<Address> updateAdd(@RequestBody Address address, @RequestParam String key) throws AddressException, LoginException
    {
		Address s = ads.updateAddress(address, key);
		
		return new ResponseEntity<Address>(s, HttpStatus.OK);
    }
	
	@DeleteMapping("/address{delid}")
	public ResponseEntity<String> deladdres(@PathVariable("delid") Integer de, @RequestParam String key) throws LoginException ,CustomerException, AddressException
	{
		String s = ads.removeAddress(de, key);
		
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}
	
	
//	////////////////////// Customer - Product 
	
	
	@GetMapping("/allproduct")
	public ResponseEntity<List<Product>> getallprod()throws ProductException
	{
		List<Product> allpr = prod.viewAllproduct();
		
		return new ResponseEntity<List<Product>>(allpr,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/p/c/{catname}")
	public ResponseEntity<List<Product>> GetprodtByCat(@PathVariable("catname") String cat) throws ProductException
	{
		List<Product> s = prod.viewProudctbyCategory(cat);
		
		return new ResponseEntity<List<Product>>(s,HttpStatus.OK);
	}
	
	
	
//	///////////////////////////////// Customer -cart
	
	
	@PostMapping("/addtocart")
	public ResponseEntity<Cart> addtoCart(@RequestParam Integer productId, @RequestParam Integer quantity, @RequestParam String key) throws CartException,LoginException,ProductException
	{
		
		Cart cartIteam = car.addProductToCart(productId, quantity, key);
		
		return new ResponseEntity<Cart>(cartIteam, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/cart/remove")
	public ResponseEntity<List<ProductCart>> removefrom(@RequestParam Integer productid,@RequestParam  String key) throws CartException, LoginException ,ProductException
	{
		List<ProductCart> list = car.removeProductfromCart(productid, key);
		
		return new ResponseEntity<List<ProductCart>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/getprod")
	public ResponseEntity<List<ProductCart>> getallcart(@RequestParam String key) throws CartException,LoginException
	{
		List<ProductCart> s = car.viewAllProducts(key);
		
		return new ResponseEntity<List<ProductCart>>(s, HttpStatus.OK);
	}
	
	
//	///////////////////////////////// Customer_orders
	
	

	@PostMapping("/addorders")
	public ResponseEntity<Orders> addOrder(@RequestBody Orders order, @RequestParam String key) throws LoginException, CartException, OrderException,CustomerException{
		Orders added = odd.addOrder(order, key);
		
		return new ResponseEntity<Orders>(added,HttpStatus.OK);
	}
	
	@PutMapping("/orderupdate")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order, @RequestParam String key) throws LoginException, CartException, OrderException{
		
		Orders s = odd.updateCustomer(order, key);
		
		return new ResponseEntity<Orders>(s,HttpStatus.OK);
		
	}
	
	
	
	@DeleteMapping("/cancele{orderid}")
	public ResponseEntity<Orders> removeorder(@PathVariable("orderid") Integer orderId, @RequestParam String uuid) throws LoginException, CartException, OrderException
	{
		Orders delt = odd.removeOrder(orderId, uuid);
		
		return new ResponseEntity<Orders>(delt, HttpStatus.OK);
	}
	
	@GetMapping("/allorder/{date}")
	public ResponseEntity<List<Orders>> georderbydate(@PathVariable("data") LocalDate date) throws OrderException
	{
		
		   List<Orders> s= odd.viewallOrderByDate(date);
		   
		   return new ResponseEntity<List<Orders>>(s, HttpStatus.OK);
		   
	}
	
	@GetMapping("/vieworder")
     public ResponseEntity<Orders> viewOrder(@RequestParam Integer orderId) throws CartException, OrderException
     
     {
		Orders s = odd.viewOrder(orderId);
		
		return new ResponseEntity<Orders>(s,HttpStatus.OK);
     }
	
	
}
