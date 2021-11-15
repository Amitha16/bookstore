package com.ecommerce.bookstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.bookstore.dto.UserRegistrationDto;
import com.ecommerce.bookstore.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
