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
class ProductControllerIntegrationTest {

    @LocalServerPort
    protected Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @DisplayName("GET /api/products")
    void get_products_should_return_all_products_with_page_size_12_by_default() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("content", hasSize(12))
                .body("size", equalTo(12))
                .body("totalPages", equalTo(2))
                .body("totalElements", equalTo(15));
    }

    @Test
    @DisplayName("GET /api/products/1")
    void get_products_with_id_should_return_correct_product_dto_with_id_1() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("A Gold Metal Vase"))
                .body("description", equalTo("Description"))
                .body("careGuide", equalTo("Care Guide"))
                .body("catalogPrice", equalTo(60.0f))
                .body("discountCatalogPrice", nullValue())
                .body("$", hasKey("properties"))
                .body("$", hasKey("images"))
                .body("$", hasKey("category"))
                .body("$", hasKey("collections"));
    }

    @Test
    @DisplayName("GET /api/products/1/recommended")
    void get_products_id_recommended_should_return_recommended_products() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/1/recommended")
                .then()
                .statusCode(200)
                .body(".", hasSize(4))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("images"))
                .body("[0]", hasKey("catalogPrice"))
                .body("[0]", hasKey("discountCatalogPrice"));
    }
}
