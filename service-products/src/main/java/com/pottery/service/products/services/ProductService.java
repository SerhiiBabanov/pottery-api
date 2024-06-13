package com.pottery.service.products.services;

import com.pottery.service.products.dtos.ProductShortDto;
import com.pottery.service.products.entities.Product;
import com.pottery.service.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public Page<ProductShortDto> getByFilters(List<Long> categoryIds,
                                              List<Long> colorIds,
                                              List<Long> collectionIds,
                                              BigDecimal minPrice, BigDecimal maxPrice,
                                              Boolean isAvailable, String sort, Pageable pageable) {
        return productRepository.getProductViews(categoryIds, colorIds, collectionIds,
                minPrice, maxPrice, isAvailable, sort, pageable);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }
}
