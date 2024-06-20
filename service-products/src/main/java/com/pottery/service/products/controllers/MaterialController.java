package com.pottery.service.products.controllers;

import com.pottery.service.products.dtos.MaterialDto;
import com.pottery.service.products.mappers.MaterialMapper;
import com.pottery.service.products.services.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
@Slf4j
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialMapper materialMapper;

    @GetMapping
    public List<MaterialDto> getMaterials() {
        return materialService.getAll().stream()
                .map(materialMapper::toDto)
                .collect(Collectors.toList());
    }
}
