package com.ecommerce.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.bookstore.model.Login;
import com.ecommerce.bookstore.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepository repo;
	  
	  public Login login(String username, String password) {
	  Login user = repo.findByUsernameAndPassword(username, password);
	   return user;
	  }
}
