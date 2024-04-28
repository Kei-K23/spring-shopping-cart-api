package com.example.shopping.cart.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.cart.api.model.Cart;
import com.example.shopping.cart.api.model.CartProduct;
import com.example.shopping.cart.api.service.CartProductService;
import com.example.shopping.cart.api.service.CartService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart-product")
@CrossOrigin
public class CartProductController {
    private final CartProductService cartProductService;
    private final CartService cartService;

    public CartProductController(CartProductService cartProductService, CartService cartService) {
        this.cartProductService = cartProductService;
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartProduct> getAllCartProducts() {
        return cartProductService.getAllCartProducts();
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CartProduct.class))
    })
    public Optional<CartProduct> getCartProductById(@PathVariable Long id) {
        return cartProductService.getCartProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CartProduct.class))
    })
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
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CartProduct.class))
    })
    public CartProduct updateCartProduct(@PathVariable Long id, @Valid @RequestBody CartProduct cartProduct) {
        cartProduct.setId(id);
        return cartProductService.saveCartProduct(cartProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")
    })
    public void removeCartById(@PathVariable Long id) {
        cartProductService.removeCartProductById(id);
    }

    @DeleteMapping
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllCartProducts() {
        cartProductService.removeAllCartProducts();
    }
}
