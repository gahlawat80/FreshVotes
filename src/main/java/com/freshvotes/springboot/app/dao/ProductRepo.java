package com.freshvotes.springboot.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freshvotes.springboot.app.entity.Product;
import com.freshvotes.springboot.app.entity.User;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	List<Product> findByUser(User user);
	
	@Query("select p from Product p  join fetch p.user where p.id = :id")
	Optional<Product> findByIdWithUser(@Param("id") Long id);
}
