package com.example.shopping.cart.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shopping.cart.api.model.Product;
import com.example.shopping.cart.api.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void removeAllProducts() {
        productRepository.deleteAll();
    }

    public void removeProductById(Long id) {
        productRepository.deleteById(id);
    }
}
