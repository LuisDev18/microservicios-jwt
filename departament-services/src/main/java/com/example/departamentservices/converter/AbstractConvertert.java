package com.example.departamentservices.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConvertert <E, D> {

    public abstract E convertToEntity(D dto);

    public abstract D convertToDto(E entity);

    public List<E> convertToEntity(List<D> dtoList){
        return dtoList.stream().map((dto)->convertToEntity(dto)).collect(Collectors.toList());
    }

    public List<D> convertToDto(List<E> entityList){
        return entityList.stream().map((entity)->convertToDto(entity)).collect(Collectors.toList());
    }
}
