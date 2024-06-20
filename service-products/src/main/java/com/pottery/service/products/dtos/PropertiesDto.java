package com.pottery.service.products.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.pottery.service.products.entities.Properties}
 */
public record PropertiesDto(Long id, String dimensions, MaterialDto material, ColorDto color, BigDecimal defaultPrice,
                            BigDecimal discountPrice, Integer quantity, Integer sold) implements Serializable {
}
