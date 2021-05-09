package com.freshvotes.springboot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshvotes.springboot.app.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
