package com.pottery.service.products.controllers;

import com.pottery.service.products.dtos.ColorDto;
import com.pottery.service.products.mappers.ColorMapper;
import com.pottery.service.products.services.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
@Slf4j
public class ColorController {

    private final ColorService colorService;
    private final ColorMapper colorMapper;

    @GetMapping
    public List<ColorDto> getColors() {
        return colorService.getAll().stream()
                .map(colorMapper::toDto)
                .collect(Collectors.toList());
    }
}
