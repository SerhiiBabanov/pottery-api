package com.pottery.support;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupportService {

    private final SupportRepository supportRepository;
    @Transactional
    public SupportRequest create(SupportRequest request) {
        if (request.getToken() == null) {
            request.setToken(UUID.randomUUID());
        }
        request.setCreatedAt(Instant.now());
        //TODO: send email to confirm support request
        return supportRepository.save(request);
    }
}
