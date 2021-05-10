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
		User localUser = user;
		String encodedPassword=passwordEncoder.encode(localUser.getPassword());
		user.setPassword(encodedPassword);
		
		Authority auth = new Authority();
		auth.setAuthority("ROLE_USER");
		auth.setUser(localUser);
		//System.out.println(authority);
		
		localUser.getAuthorities().add(auth);
				
		return userRepo.save(localUser);
	}
	
}
