package com.pottery.service.products.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * DTO for {@link com.pottery.service.products.entities.Product}
 */
public record ProductDto(Long id, String name, String description, String careGuide, List<PropertiesDto> properties,
                         Map<Integer, String> images, Set<CollectionDto> collections, CategoryDto category,
                         BigDecimal catalogPrice, BigDecimal discountCatalogPrice) implements Serializable {
}
