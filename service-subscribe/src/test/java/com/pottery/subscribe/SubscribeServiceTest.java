package com.pottery.subscribe;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
class SubscribeServiceTest {

    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private SubscribeRepository subscribeRepository;

    @BeforeEach
    void setUp() {
        subscribeRepository.deleteAll();
    }

    @Nested
    @DisplayName("create method tests")
    class create {

        @Test
        void shouldCreateSubscribe() {
            //given
            String email = "example@test.com";
            //when
            subscribeService.create(email);
            //then
            Optional<Subscribe> subscribe = subscribeRepository.findById(email);
            assertTrue(subscribe.isPresent(), "Subscribe not found");
        }

        @Test
        void shouldDoNothingIfEmailAlreadyExist() {
            //given
            String email = "example@test.com";
            Subscribe oldSubscribe = subscribeRepository.save(new Subscribe(email, UUID.randomUUID()));
            //when
            subscribeService.create(email);
            //then
            Optional<Subscribe> subscribe = subscribeRepository.findById(email);
            assertTrue(subscribe.isPresent(), "Subscribe not found");
            assertEquals(0, subscribe.get().getToken().compareTo(oldSubscribe.getToken()), "Subscribe changed token");
            assertEquals(1, subscribeRepository.count(), "Subscribes are more than one in db");
        }
    }

    @Nested
    @DisplayName("delete method tests")
    class delete {

        @Test
        void shouldDeleteSubscribe() {
            //given
            Subscribe subscribe = subscribeRepository.save(new Subscribe("example@test.com", UUID.randomUUID()));
            //when
            subscribeService.delete(subscribe.getToken());
            //then
            assertTrue(subscribeRepository.findById(subscribe.getEmail()).isEmpty(), "Subscribe not deleted");
            assertEquals(0, subscribeRepository.count(), "Subscribes are not empty in db");
        }

        @Test
        void shouldDoNothingWhenTokenIsInvalid() {
            //given
            UUID token = UUID.randomUUID();
            subscribeRepository.save(new Subscribe("example@test.com", token));
            UUID invalidToken = UUID.randomUUID();
            //when
            subscribeService.delete(invalidToken);
            //then
            assertEquals(1, subscribeRepository.count(), "Subscribes are empty in db");
            assertTrue(subscribeRepository.findById("example@test.com").isPresent(), "Subscribe not found");
        }
    }
}
