package com.example.shopping.cart.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shopping.cart.api.model.CartProduct;
import com.example.shopping.cart.api.repository.CartProductRepository;

@Service
public class CartProductService {
    private final CartProductRepository cartProductRepository;

    public CartProductService(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    public List<CartProduct> getAllCartProducts() {
        return cartProductRepository.findAll();
    }

    public Optional<CartProduct> getCartProductById(Long id) {
        return cartProductRepository.findById(id);
    }

    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

    public void removeAllCartProducts() {
        cartProductRepository.deleteAll();
    }

    public void removeCartProductById(Long id) {
        cartProductRepository.deleteById(id);
    }
}
