package com.kasenov.libpro.simplelibrary.dto.dtoMapper;

import com.kasenov.libpro.simplelibrary.dto.AbstractDto;
import com.kasenov.libpro.simplelibrary.model.AbstractEntity;

public interface ComplexDtoMapper<E extends AbstractEntity, D extends AbstractDto> {

    D toDto(E entity);

    E toEntity(D dto);
 }
