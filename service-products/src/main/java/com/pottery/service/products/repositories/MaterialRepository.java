package com.pottery.service.products.repositories;

import com.pottery.service.products.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
