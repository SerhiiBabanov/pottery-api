# Pottery API

This is the API for the Pottery Store project. It's built with Java, Spring Boot, Kotlin, Gradle, and SQL.

## Services

The project consists of several services:

- `service-products`: Handles all operations related to products.
- `docs`: Handles the generation and serving of documentation.

## Running the Project

To run the project, use the following command:

```bash
docker compose up -d

This will start all the services. You can access the API through the backend-gateway service at http://localhost:8080.  

Documentation
The API documentation is generated using Spring REST Docs and served by the docs service. You can access it at http://localhost:8080/docs/index.html.
