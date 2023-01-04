package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
	

	public List<Feedback> findByCustomerId(Integer customerid);
	
}
