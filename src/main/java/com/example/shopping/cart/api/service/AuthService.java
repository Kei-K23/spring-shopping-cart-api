package com.example.shopping.cart.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.shopping.cart.api.dto.SignUpDto;
import com.example.shopping.cart.api.model.User;
import com.example.shopping.cart.api.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User signUp(SignUpDto data) throws JWTCreationException {
        if (userRepository.findByUsername(data.username()) != null) {
            throw new JWTCreationException("Username already exists: " + data.username(), null);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.username(), data.email(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }
}
