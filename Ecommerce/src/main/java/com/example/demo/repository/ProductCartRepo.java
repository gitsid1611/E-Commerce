package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductCart;

@Repository
public interface ProductCartRepo extends JpaRepository<ProductCart, Integer> {

}
