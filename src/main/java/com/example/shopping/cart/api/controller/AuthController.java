package com.example.shopping.cart.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.cart.api.config.auth.TokenProvider;
import com.example.shopping.cart.api.dto.CreatedUserResponseDto;
import com.example.shopping.cart.api.dto.JwtDto;
import com.example.shopping.cart.api.dto.SignInDto;
import com.example.shopping.cart.api.dto.SignUpDto;
import com.example.shopping.cart.api.model.User;
import com.example.shopping.cart.api.service.AuthService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/signup")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreatedUserResponseDto.class))
    })
    public ResponseEntity<CreatedUserResponseDto> signUp(@RequestBody @Valid SignUpDto data) {
        User user = authService.signUp(data);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreatedUserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole()));
    }

    @PostMapping("/signin")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = JwtDto.class))
    })
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);

        var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());

        return ResponseEntity.ok(new JwtDto(accessToken));
    }

}
