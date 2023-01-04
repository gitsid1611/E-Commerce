package com.example.demo.service;

import com.example.demo.exception.AdminException;
import com.example.demo.model.Admin;

public interface AdminService {

	public Admin saveUser(Admin user) throws AdminException;

	
}
