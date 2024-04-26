package com.example.shopping.cart.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.cart.api.model.Cart;
import com.example.shopping.cart.api.model.CartProduct;
import com.example.shopping.cart.api.service.CartProductService;
import com.example.shopping.cart.api.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart-product")
public class CartProductController {
    private final CartProductService cartProductService;
    private final CartService cartService;

    @Autowired
    public CartProductController(CartProductService cartProductService, CartService cartService) {
        this.cartProductService = cartProductService;
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartProduct> getAllCartProducts() {
        return cartProductService.getAllCartProducts();
    }

    @GetMapping("/{id}")
    public Optional<CartProduct> getCartProductById(@PathVariable Long id) {
        return cartProductService.getCartProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartProduct saveCart(@Valid @RequestBody CartProduct cartProduct) {
        CartProduct cp = cartProductService.saveCartProduct(cartProduct);

        // update the quantity of cart
        Cart cart = cartService.getCartById(cp.getCartId()).get();
        if (cart.getQuantity() == null) {
            cart.setQuantity(0);
        }
        cart.setQuantity(cart.getQuantity() + cartProduct.getQuantity());
        cartService.saveCart(cart);

        return cp;
    }

    @PutMapping("/{id}")
    public CartProduct updateCartProduct(@PathVariable Long id, @Valid @RequestBody CartProduct cartProduct) {
        cartProduct.setId(id);
        return cartProductService.saveCartProduct(cartProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCartById(@PathVariable Long id) {
        cartProductService.removeCartProductById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllCartProducts() {
        cartProductService.removeAllCartProducts();
    }
}
