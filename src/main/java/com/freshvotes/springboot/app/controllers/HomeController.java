package com.freshvotes.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import com.freshvotes.springboot.app.entity.User;

@Controller
public class HomeController {
	
	@RequestMapping(value="/",method=RequestMethod.GET )
	public String home(){
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(){
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
		System.out.println(user);
		return "redirect:/register";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(){
		return "logout";
	}

}
