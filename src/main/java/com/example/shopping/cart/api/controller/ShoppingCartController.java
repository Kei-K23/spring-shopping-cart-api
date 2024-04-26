package com.example.shopping.cart.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shopping-cart")
public class ShoppingCartController {

    @GetMapping
    public String getAllShoppingCart() {
        return  "get all shopping cart";
    }
}
