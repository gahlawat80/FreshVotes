package com.freshvotes.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.freshvotes.springboot.app.dao.UserRepo;
import com.freshvotes.springboot.app.entity.User;
import com.freshvotes.springboot.app.security.CustomSecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if(user==null){
			throw new UsernameNotFoundException("Invalid: Username and Password!");
		}
		return new CustomSecurityUser(user);
	}

}
