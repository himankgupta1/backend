# Loan Application Backend (Spring Boot 3.5.4 + PostgreSQL 13.3)

This project implements a backend for your multi-step loan application form.
It exposes REST APIs to create/read/update Applicants, upload documents, and manage nested data like employment, loans, references, and declarations.

## Tech
- Java 17, Spring Boot 3.5.4, Maven 3.9.9
- Spring Web, Spring Data JPA, Bean Validation
- PostgreSQL 13.x
- OpenAPI/Swagger UI (for quick testing)
- Local filesystem storage for uploaded files; DB stores metadata + file path

## Run Locally

1. Create database:
   ```sql
   CREATE DATABASE loanapp;
   ```
2. Update `src/main/resources/application.properties` with your DB username/password.
3. Build & run:
   ```bash
   mvn clean spring-boot:run
   ```
4. Swagger UI: `http://localhost:8080/swagger-ui/index.html`
5. Health check: `GET http://localhost:8080/actuator/health` (if you add actuator)

## Postman / cURL Examples

- Create Applicant (without documents yet):
  ```bash
  curl -X POST http://localhost:8080/api/applicants \
    -H "Content-Type: application/json" \
    -d @sample/applicant-create.json
  ```

- Upload a document:
  ```bash
  curl -X POST "http://localhost:8080/api/applicants/{applicantId}/documents/upload?type=ADHAAR" \
    -F file=@/path/to/aadhaar.pdf
  ```

- Get Applicant by ID:
  ```bash
  curl http://localhost:8080/api/applicants/{id}
  ```

## Notes
- The schema is created via `spring.jpa.hibernate.ddl-auto=update` initially for convenience.
- Switch to Flyway or Liquibase when stabilised.
- Lombok is used to reduce boilerplate. In your IDE enable annotation processing.
