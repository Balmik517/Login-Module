package com.balmik.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balmik.learning.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	User findByEmail(String email);
}
