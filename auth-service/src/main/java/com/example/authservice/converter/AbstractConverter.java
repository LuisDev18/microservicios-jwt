package com.example.authservice.converter;

public abstract class AbstractConverter <E,D>{
    public abstract D fromEntity(E entity);
    public abstract E fromDto(D dto);
}
