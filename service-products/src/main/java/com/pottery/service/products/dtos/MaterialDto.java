package com.pottery.service.products.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.pottery.service.products.entities.Material}
 */
public record MaterialDto(Long id, String name) implements Serializable {
}
