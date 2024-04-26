package com.example.shopping.cart.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.cart.api.model.Cart;
import com.example.shopping.cart.api.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart saveCart(@Valid @RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @Valid @RequestBody Cart cart) {
        cart.setId(id);
        return cartService.saveCart(cart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCartById(@PathVariable Long id) {
        cartService.removeCartById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllCarts() {
        cartService.removeAllCarts();
    }
}
