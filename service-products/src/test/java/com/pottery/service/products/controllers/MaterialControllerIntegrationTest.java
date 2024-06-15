package com.pottery.service.products.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
class MaterialControllerIntegrationTest {

    @LocalServerPort
    protected Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @DisplayName("GET /api/materials")
    void get_materials_should_return_all_materials() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/materials")
                .then()
                .statusCode(200)
                .body(".", hasSize(3))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"));
    }
}
