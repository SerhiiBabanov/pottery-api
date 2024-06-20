package com.pottery.subscribe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    public void create(String email) {
        if (subscribeRepository.existsById(email)) {
            return;
        }
        subscribeRepository.save(new Subscribe(email, UUID.randomUUID()));
        log.info("Subscribed");
        //TODO: send email to confirm subscription
    }

    @Transactional
    public void delete(UUID token) {
        subscribeRepository.deleteByToken(token);
        log.info("Unsubscribed");
    }
}
