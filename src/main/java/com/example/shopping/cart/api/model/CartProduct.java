package com.example.shopping.cart.api.model;

import jakarta.persistence.*;

@Entity
public class CartProduct {

    @EmbeddedId
    private CartProductKey id;

    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "id", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    private Integer quantity;

    public CartProductKey getId() {
        return id;
    }

    public void setId(CartProductKey id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
