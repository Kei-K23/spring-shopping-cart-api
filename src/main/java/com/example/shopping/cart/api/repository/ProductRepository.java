package com.example.shopping.cart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.cart.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
