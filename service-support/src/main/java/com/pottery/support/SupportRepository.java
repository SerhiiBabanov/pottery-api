package com.pottery.support;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupportRepository extends JpaRepository<SupportRequest, String> {

}
