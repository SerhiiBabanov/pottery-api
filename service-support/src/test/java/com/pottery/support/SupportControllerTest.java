package com.pottery.support;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
class SupportControllerTest {

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

    @Test
    void saveSupportRequest() {

        SupportRequestDto request = new SupportRequestDto();
        request.setEmail("test@example.com");
        request.setName("test");
        request.setPhone("123456789");
        request.setMessage("test");

        given()
                .spec(documentationSpec)
                .filter(getModifyUrlFilter("support"))
                .filter(document("support", requestFields(
                        fieldWithPath("email").description("Email of the user"),
                        fieldWithPath("name").description("Name of the user"),
                        fieldWithPath("phone").description("Phone number of the user"),
                        fieldWithPath("message").description("Message of the user"))))
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/support")
                .then()
                .statusCode(201);
    }

    protected RestDocumentationFilter getModifyUrlFilter(String documentationName) {
        return document(documentationName, preprocessRequest(
                modifyUris()
                        .scheme(documentationServerScheme)
                        .host(documentationServerHost)
                        .removePort()));
    }
}
