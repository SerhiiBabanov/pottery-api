package com.pottery.service.products.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.pottery.service.products.entities.Color}
 */
public record ColorDto(Long id, String name) implements Serializable {
}
