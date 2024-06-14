package com.pottery.service.products.services;

import com.pottery.service.products.dtos.ProductShortDto;
import com.pottery.service.products.entities.Product;
import com.pottery.service.products.exceptions.AppException;
import com.pottery.service.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductShortDto> getByFilters(List<Long> categoryIds,
                                              List<Long> colorIds,
                                              List<Long> collectionIds,
                                              BigDecimal minPrice, BigDecimal maxPrice,
                                              Boolean isAvailable, Pageable pageable) {
        return productRepository.getProductsByFilter(categoryIds, colorIds, collectionIds,
                minPrice, maxPrice, isAvailable, pageable);
    }

    public Product getById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> {
            log.error("Product with id {} not found", productId);
            return new AppException("Not found product with id: " + productId, HttpStatus.NOT_FOUND);
        });
    }

    public List<ProductShortDto> getRecommendedProducts(Long productId) {
        Product product = getById(productId);
        return productRepository.getRecommendedProducts(product);
    }
}
