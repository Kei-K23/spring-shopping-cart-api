package com.example.shopping.cart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.cart.api.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
