package com.pottery.service.products.repositories;

import com.pottery.service.products.entities.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesRepository extends JpaRepository<Properties, Long> {
}
