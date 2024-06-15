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
class CollectionControllerIntegrationTest {

    @LocalServerPort
    protected Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @DisplayName("GET /api/collections")
    void get_collections_should_return_all_collections() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/collections")
                .then()
                .statusCode(200)
                .body(".", hasSize(2))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("description"));
    }

    @Test
    @DisplayName("GET /api/collections/1")
    void get_collection_with_id_should_return_correct_collection() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/collections/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Home collection"))
                .body("$", hasKey("description"));
    }
}
