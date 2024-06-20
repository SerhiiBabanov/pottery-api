package com.pottery.service.products.controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
public class BaseIntegrationDocumentationTest {

    @Value("${dev-server.host}")
    protected String devServerHost;
    @Value("${dev-server.scheme}")
    protected String devServerScheme;

    @LocalServerPort
    protected Integer port;

    protected RequestSpecification documentationSpec;

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

    protected RestDocumentationFilter getModifyUrlFilter(String documentationName) {
        return document(documentationName, preprocessRequest(
                modifyUris()
                        .scheme(devServerScheme)
                        .host(devServerHost)
                        .removePort()));
    }
}
