package com.pottery.service.products.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.pottery.service.products.entities.Collection}
 */
public record CollectionDto(Long id, String name, String description) implements Serializable {
}
