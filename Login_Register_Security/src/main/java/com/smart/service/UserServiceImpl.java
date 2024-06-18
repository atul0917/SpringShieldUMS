package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.smart.entity.User;
import com.smart.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public User saveUser(User user) {
	
	String password=	passwordEncoder.encode(user.getPassword());
	user.setPassword(password);
	user.setRole("ROLE_USER");
	User newuser =	userRepo.save(user);
		return newuser;
	}

}
