package com.example.shopping.cart.api.dto;

import com.example.shopping.cart.api.enums.UserRole;

public record SignUpDto(String username,
        String password,
        UserRole role,
        String email) {
}
