package com.example.authservice.controller;

import com.example.authservice.converter.UserConverter;
import com.example.authservice.dto.AuthRequest;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserCredentialRepository;
import com.example.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody AuthRequest authRequest) {
         User credential = service.saveUser(authRequest);
         return new ResponseEntity<String>("Usuario creado exitosamente",HttpStatus.OK);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            var usuario = userCredentialRepository.findByEmail(authRequest.getUsername()).orElseThrow();
            var jwtToken = service.generateToken(usuario);
            return jwtToken;
        } catch (AuthenticationException e) {
          return e.getMessage();
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
