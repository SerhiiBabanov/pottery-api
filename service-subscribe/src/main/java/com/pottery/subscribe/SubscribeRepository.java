package com.pottery.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscribeRepository extends JpaRepository<Subscribe, String> {
    void deleteByToken(UUID token);
}
