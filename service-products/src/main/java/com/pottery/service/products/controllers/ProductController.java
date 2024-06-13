package com.pottery.service.products.controllers;

import com.pottery.service.products.dtos.ProductDto;
import com.pottery.service.products.dtos.ProductShortDto;
import com.pottery.service.products.exceptions.AppException;
import com.pottery.service.products.mappers.ProductMapper;
import com.pottery.service.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
                                             @RequestParam(required = false) String sort,
                                             Pageable pageable
                                             ) {
        return productService.getByFilters(categoryIds, colorIds, collectionIds, minPrice, maxPrice,
                isAvailable, sort, pageable);
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getById(id).map(productMapper::toDto)
                .orElseThrow(() -> {
                    log.error("Product with id {} not found", id);
                    return new AppException("Not found product with id: " + id, HttpStatus.NOT_FOUND);
                });
    }
}
