package com.pottery.service.products.repositories;

import com.pottery.service.products.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
