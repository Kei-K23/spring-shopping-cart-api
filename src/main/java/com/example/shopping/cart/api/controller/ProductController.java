package com.example.shopping.cart.api.controller;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.cart.api.model.Product;
import com.example.shopping.cart.api.service.ProductService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final Bucket bucket;

    public ProductController(ProductService productService) {
        // rate limit with bucket
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder().addLimit(limit).build();
        this.productService = productService;
    }

    @GetMapping
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "price", required = false) String price,
            @RequestParam(name = "quantity", required = false) String quantity) {

        if (bucket.tryConsume(1)) {
            Pageable pageable;
            Integer size = 10;
            // pagination here
            if (price != null) {
                // sorting here
                Sort sort = price.equals("asc") ? Sort.by("price").ascending() : Sort.by("price").descending();
                pageable = PageRequest.of(page, size, sort);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productService.getAllProducts(pageable));
            } else if (quantity != null) {
                Sort sort = quantity.equals("asc") ? Sort.by("quantity").ascending() : Sort.by("quantity").descending();
                pageable = PageRequest.of(page, size, sort);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productService.getAllProducts(pageable));
            } else {
                pageable = PageRequest.of(page, size);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productService.getAllProducts(pageable));
            }
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    public Product saveProduct(@Valid @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        product.setId(id);
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")
    })
    public void removeProductById(@PathVariable Long id) {
        productService.removeProductById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")
    })
    public void removeAllProducts() {
        productService.removeAllProducts();
    }
}
