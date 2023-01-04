package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.AddressException;
import com.example.demo.model.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

	@Query("from Address where state = ?1")
	public Address viewAllAddress(String id) throws AddressException;
}
