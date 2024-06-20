package com.pottery.service.products.controllers;

import com.pottery.service.products.dtos.CategoryDto;
import com.pottery.service.products.mappers.CategoryMapper;
import com.pottery.service.products.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.getAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
