package com.pottery.service.products.services;

import com.pottery.service.products.entities.Collection;
import com.pottery.service.products.repositories.CollectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public List<Collection> getAll() {
        return collectionRepository.findAll();
    }

    public Optional<Collection> get(Long id) {
        return collectionRepository.findById(id);
    }
}
