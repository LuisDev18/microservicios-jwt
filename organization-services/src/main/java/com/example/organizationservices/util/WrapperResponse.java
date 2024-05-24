package com.example.organizationservices.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse <T>{

    private T data;

    private String status;

    private String message;


  public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

}
