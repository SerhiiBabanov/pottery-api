package com.pottery.service.products.repositories;

import com.pottery.service.products.dtos.ProductShortDto;
import com.pottery.service.products.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface QueryDslProductRepository {
    Page<ProductShortDto> getProductsByFilter(List<Long> categoryIds, List<Long> colorIds, List<Long> collectionIds,
                                              BigDecimal minPrice, BigDecimal maxPrice, Boolean isAvailable,
                                              Pageable pageable);
    List<ProductShortDto> getRecommendedProducts(Product product);
}
