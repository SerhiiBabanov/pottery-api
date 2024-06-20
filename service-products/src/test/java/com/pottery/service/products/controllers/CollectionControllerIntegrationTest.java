package com.pottery.service.products.controllers;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

class CollectionControllerIntegrationTest extends BaseIntegrationDocumentationTest {

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        setDocumentationSpec(restDocumentation);
    }

    @Test
    @DisplayName("GET /api/collections")
    void get_collections_should_return_all_collections() {
        given(this.documentationSpec)
                .contentType(ContentType.JSON)
                .filter(getModifyUrlFilter("collections"))
                .filter(document("collections", documentArrayResponseFields()))
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
        Long id = 1L;
        given(this.documentationSpec)
                .contentType(ContentType.JSON)
                .filter(getModifyUrlFilter("collection"))
                .filter(document("collection", documentResponseFields()))
                .filter(document("collection", pathParameters(
                        parameterWithName("id").description("Id of the collection to retrieve")
                )))
                .when()
                .get("/api/collections/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Home collection"))
                .body("$", hasKey("description"));
    }

    private ResponseFieldsSnippet documentArrayResponseFields() {
        return responseFields(
                subsectionWithPath("[]").description("An array of collections"),
                fieldWithPath("[].id").description("Id of the collection that is bigger than 0"),
                fieldWithPath("[].name").description("Name of collection"),
                fieldWithPath("[].description").description("Description of collection")
        );
    }

    private ResponseFieldsSnippet documentResponseFields() {
        return responseFields(
                fieldWithPath("id").description("Id of the collection that is bigger than 0"),
                fieldWithPath("name").description("Name of collection"),
                fieldWithPath("description").description("Description of collection")
        );
    }
}
