package com.pottery.service.products.services;

import com.pottery.service.products.entities.Color;
import com.pottery.service.products.repositories.ColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ColorService {

    private final ColorRepository colorRepository;

    public List<Color> getAll() {
        return colorRepository.findAll();
    }
}
