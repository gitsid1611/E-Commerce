package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.exception.LoginException;
import com.example.demo.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	

	 
	 
	 @PostMapping("/adminLogin")
		public ResponseEntity<String> loginAdminHandler(@Valid @RequestBody AdminDto admin) throws LoginException {

			String res = loginService.loginAdmin(admin);

			return new ResponseEntity<String>(res, HttpStatus.OK);

		}
	 
	 @PostMapping("/adminLogout")
		public ResponseEntity<String> logoutAdminHandler(@RequestParam("key") String key) throws LoginException {

			String res = loginService.logoutAdmin(key);

			return new ResponseEntity<String>(res, HttpStatus.OK);

		}
	 
	 
	 
//	 ///////////////////////
	 
	 
	 @PostMapping("/customerLogin")
		public ResponseEntity<String> loginCustomerHandler(@Valid @RequestBody LoginDto admin) throws LoginException {

			String res = loginService.loginCustomer(admin);

			return new ResponseEntity<String>(res, HttpStatus.OK);

		}
	 
	 @PostMapping("/customerLogout")
		public ResponseEntity<String> logoutCustomerHandler(@RequestParam("key") String key) throws LoginException {

			String res = loginService.logoutCustomer(key);

			return new ResponseEntity<String>(res, HttpStatus.OK);

		}
	 
	 
	 
	
}
