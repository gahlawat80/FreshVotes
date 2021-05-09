package com.freshvotes.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.freshvotes.springboot.app.dao.UserRepo;
import com.freshvotes.springboot.app.entity.Authority;
import com.freshvotes.springboot.app.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User save(User user){
		System.out.println(user);
		String encodedPassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user);
		
		user.getAuthorities().add(authority);
		
		System.out.println(user);
		System.out.println(authority);
		
		return userRepo.save(user);
	}
	
}
