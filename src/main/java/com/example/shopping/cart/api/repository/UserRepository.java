package com.example.shopping.cart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.shopping.cart.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
