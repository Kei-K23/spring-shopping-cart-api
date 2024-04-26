package com.example.shopping.cart.api.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartProductKey implements Serializable {
    private Long cartId;
    private Long productId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
