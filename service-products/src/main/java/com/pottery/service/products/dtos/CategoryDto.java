package com.pottery.service.products.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.pottery.service.products.entities.Category}
 */
public record CategoryDto(Long id, String name) implements Serializable {
}
