package com.kasenov.libpro.simplelibrary.repository;

import com.kasenov.libpro.simplelibrary.model.EntityImpl.OrderEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends CommonRepository<OrderEntity> {
    List<OrderEntity> findByOrderIsCompletedFalseAndReturnDateBefore(LocalDate returnDate);
}
