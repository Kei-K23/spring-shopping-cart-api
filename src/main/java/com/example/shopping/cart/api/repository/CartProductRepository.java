package com.example.shopping.cart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.cart.api.model.CartProduct;
import com.example.shopping.cart.api.model.CartProductKey;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {

}
