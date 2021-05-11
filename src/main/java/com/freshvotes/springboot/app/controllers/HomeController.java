package com.freshvotes.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freshvotes.springboot.app.dao.ProductRepo;
import com.freshvotes.springboot.app.entity.Product;
import com.freshvotes.springboot.app.entity.User;
import com.freshvotes.springboot.app.security.CustomSecurityUser;
import com.freshvotes.springboot.app.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepo productRepo;
	
	@RequestMapping(value="/",method=RequestMethod.GET )
	public String home(){
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(ModelMap model){
		//To get hold of current logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomSecurityUser customUser = (CustomSecurityUser) auth.getPrincipal();
		String username = customUser.getUsername();
		
		User user = userService.findByUsername(username);
		List<Product> products = productRepo.findByUser(user);
		
		model.put("products", products);
		return "dashboard";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model){
		model.put("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String createAccount(User user){
		System.out.println("Input user : -"+user);
		User savedUser = userService.save(user);
		System.out.println("Saved user : -"+savedUser);
		return "redirect:/register";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(){
		return "logout";
	}

}
