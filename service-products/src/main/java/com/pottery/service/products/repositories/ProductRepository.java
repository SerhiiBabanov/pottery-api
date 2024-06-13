package com.pottery.service.products.repositories;

import com.pottery.service.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslProductRepository {

}
