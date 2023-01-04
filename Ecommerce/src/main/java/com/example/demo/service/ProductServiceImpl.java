package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo dd;

	@Override
	public Product addProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
		return dd.save(product);
	}

	@Override
	public List<Product> viewAllproduct() throws ProductException {
		// TODO Auto-generated method stub
		List<Product> p = dd.findAll();
		   
	    if(p.isEmpty())
	    {
	    	throw new ProductException("There is no product ");
	    	
	    }
	
	 return p;
	 
	 
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub

        Product s = dd.save(product);
         return s;
	}

	
	@Override
	public Product viewProduct(Integer id) throws ProductException {
		// TODO Auto-generated method stub
		 Optional<Product> p = dd.findById(id);
		  
		  
		  if(p.isEmpty())
		  {
			  throw new ProductException("No Product found");
		  }
		  else
		  {
			  Product ss = p.get();
			  return ss;
		  }
	}

	
	@Override
	public List<Product> viewProudctbyCategory(String cname) throws ProductException {
		// TODO Auto-generated method stub
       List<Product> s = dd.viewbyCategoryName(cname);
		
		return s;
	}

	
	@Override
	public Product removeProduct(Integer pid) throws ProductException {
		// TODO Auto-generated method stub
		 Optional<Product> ss = dd.findById(pid);
		 
		 
		 if(ss.isEmpty())
		 {
			 throw new ProductException("Product not found");
		 }
		 else
		 {
			 Product s = ss.get();
			 return s;
		 }
	}
	
	

}
