package com.example.shopping.cart.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shopping.cart.api.model.Cart;

import com.example.shopping.cart.api.repository.CartRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void removeAllCarts() {
        cartRepository.deleteAll();
    }

    public void removeCartById(Long id) {
        cartRepository.deleteById(id);
    }
}
