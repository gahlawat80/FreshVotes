package com.freshvotes.springboot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshvotes.springboot.app.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
