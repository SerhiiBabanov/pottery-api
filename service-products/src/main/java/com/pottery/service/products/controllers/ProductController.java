package com.pottery.service.products.controllers;

import com.pottery.service.products.dtos.ProductDto;
import com.pottery.service.products.dtos.ProductShortDto;
import com.pottery.service.products.mappers.ProductMapper;
import com.pottery.service.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public Page<ProductShortDto> getProducts(@RequestParam(required = false) List<Long> categoryIds,
                                             @RequestParam(required = false) List<Long> colorIds,
                                             @RequestParam(required = false) List<Long> collectionIds,
                                             @RequestParam(required = false) BigDecimal minPrice,
                                             @RequestParam(required = false) BigDecimal maxPrice,
                                             @RequestParam(required = false) boolean isAvailable,
                                             Pageable pageable
                                             ) {
        return productService.getByFilters(categoryIds, colorIds, collectionIds, minPrice, maxPrice,
                isAvailable, pageable);
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @GetMapping("{id}/recommended")
    public List<ProductShortDto> getRecommendedProducts(@PathVariable Long id) {
        return productService.getRecommendedProducts(id);
    }
}
