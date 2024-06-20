package com.pottery.service.products.repositories;

import com.pottery.service.products.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
