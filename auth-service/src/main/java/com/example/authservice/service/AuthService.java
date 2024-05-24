package com.example.authservice.service;

import com.example.authservice.converter.UserConverter;
import com.example.authservice.dto.AuthRequest;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserCredentialRepository;
import com.example.authservice.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserConverter userConverter;

    public User saveUser(AuthRequest authRequest) {
      User credential =  userConverter.fromDto(authRequest);
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        userCredentialRepository.save(credential);
        return credential;
    }

    public String generateToken(UserDetails userDetails) {
        return jwtService.generateToken(userDetails);
    }

    public void validateToken(String token) {
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = loadUserByUsername(username);
        jwtService.isTokenValid(token, userDetails);
    }

    public UserDetails loadUserByUsername(String username) {
        return userCredentialRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
