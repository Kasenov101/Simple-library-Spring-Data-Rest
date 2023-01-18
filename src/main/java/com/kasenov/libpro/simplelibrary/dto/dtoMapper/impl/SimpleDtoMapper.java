package com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.stream.Collectors;

public interface SimpleDtoMapper {

    static final ModelMapper modelMapper = new ModelMapper();

    static <S,D> D map(S source, Class<D> outClass) {
        return modelMapper.map(source,outClass);
    }

    static  <S,D> Collection<D> mapAll(Collection<S> entities, Class<D> outClass) {
        return entities.stream()
                .map(i -> map(i,outClass))
                .collect(Collectors.toList());
    }
}
