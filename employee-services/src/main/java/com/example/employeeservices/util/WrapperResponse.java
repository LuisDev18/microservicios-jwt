package com.example.employeeservices.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse <T>{

    private T data;
    private String status;
    private Instant timestamp;
    private String message;

    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}
