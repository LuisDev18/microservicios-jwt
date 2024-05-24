package com.example.employeeservices.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter <E, D> {

    public abstract D toDto(E entity);

    public abstract E toEntity(D dto);

    public List<E> toEntity(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<D> toDto(List<E> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

}
