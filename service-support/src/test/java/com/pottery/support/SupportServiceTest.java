package com.pottery.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
class SupportServiceTest {

    @Autowired
    private SupportService supportService;
    @Autowired
    private SupportRepository supportRepository;

    @BeforeEach
    void setUp() {
        supportRepository.deleteAll();
    }

    @Nested
    @DisplayName("save method tests")

    class save {

        @Test
        void shouldSaveSupportRequest() {
            //given
            SupportRequest request = new SupportRequest();
            //when
            SupportRequest savedRequest = supportService.create(request);
            //then
            assertNotNull(savedRequest.getToken(), "Token is null");
            assertEquals(1, supportRepository.count(), "Support request not saved");
        }
    }


}
