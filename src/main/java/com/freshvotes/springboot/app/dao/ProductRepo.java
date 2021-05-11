package com.freshvotes.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshvotes.springboot.app.entity.Product;
import com.freshvotes.springboot.app.entity.User;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	List<Product> findByUser(User user);
}
