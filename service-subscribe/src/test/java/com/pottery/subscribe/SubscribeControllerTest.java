package com.pottery.subscribe;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
class SubscribeControllerTest {

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Value("${dev-server.host}")
    private String documentationServerHost;
    @Value("${dev-server.scheme}")
    private String documentationServerScheme;

    @LocalServerPort
    private Integer port;

    private RequestSpecification documentationSpec;

    @BeforeEach
    protected void setDocumentationSpec(RestDocumentationContextProvider restDocumentation) {
        RestAssured.baseURI = "http://localhost:" + port;
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }

    @Nested
    @DisplayName("subscribe method tests")
    class subscribe {

        @Test
        void subscribe_with_correct_email() {
            // given
            String email = "username@example.com";
            given()
                    .spec(documentationSpec)
                    .filter(getModifyUrlFilter("subscribe"))
                    .filter(document("subscribe", queryParameters(
                            parameterWithName("email").description("Email to subscribe to the service"))))
                    .when()
                    .post("/api/subscribe?email={email}", email)
                    .then()
                    .statusCode(201);
        }

        @Test
        void subscribe_with_incorrect_email() {
            // given
            String email = "username";
            given()
                    .param("email", email)
                    .when()
                    .post("/api/subscribe")
                    .then()
                    .statusCode(400);
        }
    }

    @Nested
    @DisplayName("unsubscribe method tests")
    class unsubscribe {

        @Test
        void unsubscribe_with_correct_token() {
            // given
            Subscribe subscribe = subscribeRepository.save(Subscribe.builder()
                    .email("example@test.com")
                    .token(UUID.randomUUID())
                    .build());
            given()
                    .spec(documentationSpec)
                    .filter(getModifyUrlFilter("unsubscribe"))
                    .filter(document("unsubscribe", queryParameters(
                            parameterWithName("token").description("Token to unsubscribe from the service"))))
                    .when()
                    .post("/api/unsubscribe?token={token}", subscribe.getToken())
                    .then()
                    .statusCode(204);
        }

        @Test
        void unsubscribe_with_incorrect_token_with_correct_UUID() {
            // given
            UUID token = UUID.randomUUID();
            given()
                    .param("token", token)
                    .when()
                    .post("/api/unsubscribe")
                    .then()
                    .statusCode(204);
        }

        @Test
        void unsubscribe_with_incorrect_token() {
            // given
            String token = "incorrect_token";
            given()
                    .param("token", token)
                    .when()
                    .post("/api/unsubscribe")
                    .then()
                    .statusCode(400);
        }
    }

    protected RestDocumentationFilter getModifyUrlFilter(String documentationName) {
        return document(documentationName, preprocessRequest(
                modifyUris()
                        .scheme(documentationServerScheme)
                        .host(documentationServerHost)
                        .removePort()));
    }
}
