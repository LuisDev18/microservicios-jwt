package com.example.authservice.converter;

import com.example.authservice.entity.User;
import org.springframework.stereotype.Component;
import com.example.authservice.dto.AuthRequest;

@Component
public class UserConverter extends AbstractConverter<User,AuthRequest> {

    @Override
    public AuthRequest fromEntity(User entity) {
        if (entity == null) {
            return null;
        } else {
            return AuthRequest.builder()
                    .username(entity.getEmail())
                    .password(entity.getPassword())
                    .build();
        }
    }

    @Override
    public User fromDto(AuthRequest dto) {
        if(dto == null){
            return null;
        } else {
            return User.builder()
                    .email(dto.getUsername())
                    .password(dto.getPassword())
                    .build();
        }
    }
}
