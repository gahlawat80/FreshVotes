package com.freshvotes.springboot.app.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.freshvotes.springboot.app.dao.ProductRepo;
import com.freshvotes.springboot.app.entity.Product;
import com.freshvotes.springboot.app.entity.User;
import com.freshvotes.springboot.app.security.CustomSecurityUser;
import com.freshvotes.springboot.app.services.UserService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/products")
	public String product(ModelMap model){
		return "product";
	}
	
	@GetMapping("/products/{productId}")
	public String product(@PathVariable Long productId, ModelMap map, HttpServletResponse http) throws IOException{
		Optional<Product> prodOptional = productRepo.findById(productId);
		
		if(prodOptional.isPresent()){
			Product prod = prodOptional.get();
			map.put("product", prod);			
		} else{
			http.sendError(HttpStatus.NOT_FOUND.value(), "Product with ID: "+productId+" not found in db.");
			return "product";
		}
		return "product";
	}
	
	@PostMapping("/product")
	public String product(){
		//To get hold of current logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomSecurityUser customUser = (CustomSecurityUser) auth.getPrincipal();
		String username = customUser.getUsername();
		
		User savedUser = userService.findByUsername(username);
		Product product = new Product();		
		product.setPublished(false);
		product.setUser(savedUser);
		//System.out.println("User:==="+savedUser);
		Product savedProduct = productRepo.save(product);
		
		return "redirect:/products/"+savedProduct.getId();
	}

}
