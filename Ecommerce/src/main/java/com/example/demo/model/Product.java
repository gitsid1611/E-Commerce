package com.example.demo.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	
	@Size(min =3, max = 25, message = "Product name should contain min 3 charater")
	private String productName;
	
	@Min(value = 1, message = "Please Enter the valid  price")
	private Double price;
	
	
	private String color;
	
	
	private String dimension;
	
	
	private String manufacture;
	
	
	@Min(value = 1, message = "Please Enter the valid Quantity")
	private Integer quantity;
	
	@Embedded
	private Category category;
	

}
