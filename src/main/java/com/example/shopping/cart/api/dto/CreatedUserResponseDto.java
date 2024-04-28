package com.example.shopping.cart.api.dto;

import com.example.shopping.cart.api.enums.UserRole;

public record CreatedUserResponseDto(
                Long id,
                String username,
                String email,
                UserRole role) {

}
