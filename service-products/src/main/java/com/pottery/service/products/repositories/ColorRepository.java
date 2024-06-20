package com.pottery.service.products.repositories;

import com.pottery.service.products.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
