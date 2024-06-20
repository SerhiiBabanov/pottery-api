package com.pottery.service.products.controllers;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;


class ProductControllerIntegrationTest extends BaseIntegrationDocumentationTest {

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
    @DisplayName("GET /api/products")
    void get_products_should_return_all_products_with_filters() {
        Integer categoryId = 1;
        Integer colorId = 1;
        Integer collectionId = 1;
        BigDecimal minPrice = BigDecimal.valueOf(60);
        BigDecimal maxPrice = BigDecimal.valueOf(200);
        Boolean isAvailable = true;
        Integer size = 12;
        Integer page = 0;
        String sort = "catalogPrice,desc";
        given(this.documentationSpec)
                .contentType(ContentType.JSON)
                .filter(getModifyUrlFilter("products"))
                .filter(document("products", queryParameters(
                        parameterWithName("categoryIds").description("List of category ids to filter products, e.g. 1,2,3"),
                        parameterWithName("colorIds").description("List of color ids to filter products"),
                        parameterWithName("collectionIds").description("List of collection ids to filter products"),
                        parameterWithName("minPrice").description("Minimum price to filter products, e.g. 60.00"),
                        parameterWithName("maxPrice").description("Maximum price to filter products"),
                        parameterWithName("isAvailable").description("Filter products by availability, e.g. true or false"),
                        parameterWithName("size").description("Number of products per page, max 24"),
                        parameterWithName("page").description("Page number"),
                        parameterWithName("sort").description("Sort products by field and direction - field,direction (asc or desc) e.g. catalogPrice,desc")
                )))
                .when()
                .get("/api/products?" +
                                "categoryIds={categoryId}&" +
                                "colorIds={colorId}&" +
                                "collectionIds={collectionId}&" +
                                "minPrice={minPrice}&" +
                                "maxPrice={maxPrice}&" +
                                "isAvailable={isAvailable}&" +
                                "size={size}&" +
                                "page={page}&" +
                                "sort={sort}",
                        categoryId, colorId, collectionId, minPrice, maxPrice, isAvailable, size, page, sort)
                .then()
                .statusCode(200)
                .body("content", hasSize(5))
                .body("size", equalTo(12))
                .body("totalPages", equalTo(1))
                .body("totalElements", equalTo(5));
    }

    @Test
    @DisplayName("GET /api/products/1")
    void get_products_with_id_should_return_correct_product_dto_with_id_1() {
        Long id = 1L;
        given(this.documentationSpec)
                .contentType(ContentType.JSON)
                .filter(getModifyUrlFilter("product"))
                .filter(document("product", pathParameters(
                        parameterWithName("id").description("Id of the product to retrieve")
                )))
                .when()
                .get("/api/products/{id}", id)
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
        Long id = 1L;
        given(this.documentationSpec)
                .contentType(ContentType.JSON)
                .filter(document("recommended", pathParameters(
                        parameterWithName("id").description("Id of the product to retrieve recommended products")
                )))
                .filter(getModifyUrlFilter("recommended"))
                .when()
                .get("/api/products/{id}/recommended", id)
                .then()
                .statusCode(200)
                .body(".", hasSize(4))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("images"))
                .body("[0]", hasKey("catalogPrice"))
                .body("[0]", hasKey("discountCatalogPrice"))
                .body("[0].discountCatalogPrice", nullValue());
    }

}
