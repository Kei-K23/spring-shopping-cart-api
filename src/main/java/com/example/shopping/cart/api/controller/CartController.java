package com.example.shopping.cart.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.cart.api.model.Cart;
import com.example.shopping.cart.api.service.CartService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carts")
@CrossOrigin
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class))
    })
    public Optional<Cart> getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class))
    })
    public Cart saveCart(@Valid @RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class))
    })
    public Cart updateCart(@PathVariable Long id, @Valid @RequestBody Cart cart) {
        cart.setId(id);
        return cartService.saveCart(cart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")
    })
    public void removeCartById(@PathVariable Long id) {
        cartService.removeCartById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")
    })
    public void removeAllCarts() {
        cartService.removeAllCarts();
    }
}
