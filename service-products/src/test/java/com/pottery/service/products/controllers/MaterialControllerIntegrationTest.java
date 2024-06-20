package com.pottery.service.products.controllers;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

class MaterialControllerIntegrationTest extends BaseIntegrationDocumentationTest {

    @Test
    @DisplayName("GET /api/materials")
    void get_materials_should_return_all_materials() {
        given(this.documentationSpec)
                .contentType(ContentType.JSON)
                .filter(getModifyUrlFilter("materials"))
                .filter(document("materials", documentResponseFields()))
                .when()
                .get("/api/materials")
                .then()
                .statusCode(200)
                .body(".", hasSize(3))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"));
    }

    private ResponseFieldsSnippet documentResponseFields() {
        return responseFields(
                subsectionWithPath("[]").description("An array of materials"),
                fieldWithPath("[].id").description("Id of the material"),
                fieldWithPath("[].name").description("Name of material")
        );
    }
}
