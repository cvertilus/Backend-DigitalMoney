# Backend-DigitalMoney
```markdown
# Backend-DigitalMoney

## Description
Backend-DigitalMoney is a microservices-based project developed in Java using Spring Boot. It provides functionality for managing accounts, activities, cards, user authentication, and more. The system uses Keycloak for user authentication and token management, and Docker Compose for container orchestration.

## Architecture
The project consists of the following services:
- **Account Service**: Manages user accounts.
- **Activity Service**: Tracks account activities.
- **Cards Service**: Handles card-related operations.
- **Login Service**: Manages user authentication and login.
- **User Service**: Manages user information.
- **Eureka Server**: Service discovery for microservices.
- **Config Server**: Centralized configuration management.
- **API Gateway**: Routes requests to the appropriate services.

## Features
- User authentication and token management using Keycloak.
- Account creation, updates, and transfers.
- Activity tracking for accounts.
- Card management.
- Centralized configuration and service discovery.
- API Gateway for routing and load balancing.

## Technologies Used
- **Language**: Java
- **Framework**: Spring Boot
- **Authentication**: Keycloak
- **Containerization**: Docker, Docker Compose
- **Service Discovery**: Eureka Server
- **Configuration Management**: Spring Cloud Config Server
- **API Gateway**: Spring Cloud Gateway
- **Testing**: JUnit 5, RestAssured
- **Build Tool**: Maven

## Prerequisites
- **Java 17** or higher.
- **Maven 3.8** or higher.
- **Docker** and **Docker Compose** installed.

## Setup and Deployment
1. Clone the repository:
   ```bash
   git clone <REPOSITORY_URL>
   cd Backend-DigitalMoney
   ```

2. Configure Keycloak:
    - check the data carpet

3. Build the services:
   ```bash
   mvn clean install
   ```

4. Start the application using Docker Compose:
   ```bash
   docker-compose up --build
   ```

5. Access the services:
    - **Eureka Server**: `http://localhost:<eureka-port>`
    - **API Gateway**: `http://localhost:<gateway-port>`
    - **Keycloak**: `http://localhost:<keycloak-port>`
6. Use Postman or any API client to interact with the services.

```

## Key Endpoints
### Account Service
- **POST /accounts**: Create a new account.
- **PUT /accounts/{accountId}**: Update an account.
- **POST /accounts/transfer**: Transfer money between accounts.

### User Service
- **POST /users**: Create a new user.
- **GET /users/{userId}**: Retrieve user details.

### Cards Service
- **POST /cards**: Create a new card.
- **GET /cards/{cardId}**: Retrieve card details.

## Testing
Run tests using:
```bash
mvn test
```

## Author
- **Name**: (Your Name or Team)
- **Email**: (Your Contact Email)

## License
This project is licensed under (specify license, e.g., MIT, Apache 2.0, etc.).
```