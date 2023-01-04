package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AdminException;
import com.example.demo.exception.CustomerException;
import com.example.demo.exception.ProductException;
import com.example.demo.model.Admin;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.service.AdminService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	private AdminService userService;
	
	@Autowired
	private CustomerService ps;
	
	@Autowired
	private ProductService prode;

	@PostMapping("/addAdmins")
	public ResponseEntity<Admin> addAdminHandler(@Valid @RequestBody Admin user) throws AdminException {

		Admin savedUser = userService.saveUser(user);

		return new ResponseEntity<Admin>(savedUser, HttpStatus.OK);

	}
	
	

	
	
//	/////////////////////////////////////  Customer view
	
	@GetMapping("/viewCustomerById/{id}")
	public ResponseEntity<Customer> findbyid(@PathVariable("id") int id)throws CustomerException{

		Customer byid = ps.viemCustomer(id);

		return new ResponseEntity<Customer>(byid,HttpStatus.ACCEPTED);

	}
	
	
	@GetMapping("/viewAllcustomer")
	public ResponseEntity<List<Customer>> viewAll()throws CustomerException{

		List<Customer> find = ps.viewAllCustomer();

		return new ResponseEntity<List<Customer>>(find,HttpStatus.ACCEPTED);

	}
	
	
//	////////////////////////  Admin - Product 
	
	
	
	@PostMapping("/addproduct")
	public ResponseEntity<Product> addproductHandler(@Valid @RequestBody Product prod) throws ProductException
	{
		   Product s = prode.addProduct(prod);
		   
		   return new ResponseEntity<Product>(s, HttpStatus.OK);
	}
	
	
	@GetMapping("/allproduct")
	public ResponseEntity<List<Product>> getallprod()throws ProductException
	{
		List<Product> allpr = prode.viewAllproduct();
		
		return new ResponseEntity<List<Product>>(allpr,HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteByProductId/{pid}")
	public ResponseEntity<Product> deletProdouct(@PathVariable("pid")Integer pr) throws ProductException
	{
		Product s = prode.removeProduct(pr);
		
		return new ResponseEntity<Product>(s, HttpStatus.OK);
	}
	
}
