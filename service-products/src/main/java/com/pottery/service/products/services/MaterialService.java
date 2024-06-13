package com.pottery.service.products.services;

import com.pottery.service.products.entities.Material;
import com.pottery.service.products.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialService {

    private final MaterialRepository materialRepository;

    public List<Material> getAll() {
        return materialRepository.findAll();
    }
}
