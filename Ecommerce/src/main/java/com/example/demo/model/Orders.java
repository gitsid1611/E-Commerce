package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import com.example.demo.dto.AddressDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    
    
    
    private LocalDate orderDate;
    
    
    private String orderStatus;
    
    
    private Double total;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="customer_order", joinColumns = @JoinColumn(name="order_id", referencedColumnName = "orderId"))
    private Customer customer;
    
    
    @ElementCollection
    @CollectionTable(name="product_order", joinColumns = @JoinColumn(name ="order_id", referencedColumnName = "orderId"))
    private List<ProductCart> poder = new ArrayList<>();
    
    
    @Embedded
    private AddressDto address;
    
    
    
    public Orders(LocalDate orderDate, String orderStatus, Double total, Customer customer, List<ProductCart> poder,
			AddressDto address) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.total = total;
		this.customer = customer;
		this.poder = poder;
		this.address = address;
	}
}
