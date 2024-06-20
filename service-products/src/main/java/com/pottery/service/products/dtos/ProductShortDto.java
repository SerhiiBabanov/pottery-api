package com.pottery.service.products.dtos;

import com.querydsl.core.annotations.QueryProjection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * DTO for {@link com.pottery.service.products.entities.Product}
 */
public record ProductShortDto(Long id, String name, Map<Integer, String> images,
                              BigDecimal catalogPrice, BigDecimal discountCatalogPrice) implements Serializable {
    @QueryProjection
    public ProductShortDto {
    }
}
