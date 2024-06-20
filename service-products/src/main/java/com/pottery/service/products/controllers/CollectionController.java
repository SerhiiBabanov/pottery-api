package com.pottery.service.products.controllers;

import com.pottery.service.products.dtos.CollectionDto;
import com.pottery.service.products.exceptions.AppException;
import com.pottery.service.products.mappers.CollectionMapper;
import com.pottery.service.products.services.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
@Slf4j
public class CollectionController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    @GetMapping
    public List<CollectionDto> getCollections() {
        return collectionService.getAll().stream()
                .map(collectionMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CollectionDto getCollection(@PathVariable Long id) {
        return collectionService.get(id).map(collectionMapper::toDto)
                .orElseThrow(() -> {
                    log.error("Collection with id {} not found", id);
                    return new AppException("Not found Collection with id: " + id, HttpStatus.NOT_FOUND);
                });
    }
}
