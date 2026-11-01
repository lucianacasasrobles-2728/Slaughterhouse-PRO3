# Slaughterhouse – PRO3 Assignments 2 & 3

## Project Description

This project is a simulation of a slaughterhouse developed for the PRO3 course at VIA University College.

The purpose of Assignment 2 is to implement a gRPC service that retrieves traceability information about animals and products from a PostgreSQL database.

The service supports two operations:
- **GetAnimalsByProduct:** retrieves the registration numbers of all animals involved in a specific product.
- **GetProductsByAnimal:** retrieves all products associated with a specific animal.

Assignment 3 extends the project by adding a RESTful web service to register animals and retrieve their information.

## Technologies

- Java
- Spring Boot
- Maven
- gRPC and Protocol Buffers
- REST API
- PostgreSQL
- Spring JDBC
- BloomRPC and Postman for testing
- JUnit 5 and Mockito

## Project Structure

The application is organized into the following packages:

- **model:** contains the Animal and Product classes.
- **repository:** contains the database queries.
- **service:** contains the application logic.
- **grpc:** handles gRPC requests and responses.
- **controller:** handles REST API requests.

The `slaughterhouse.proto` file defines the gRPC service and its messages.

## Database

The project uses PostgreSQL and three tables:

- `animal`
- `product`
- `animal_product`

The `animal_product` table connects animals and products.

For Assignment 3, the `animal` table also stores the arrival date and origin (farm).

The `database.sql` file contains SQL statements for database setup and sample data.

## How to Run

1. Create a PostgreSQL database.
2. Execute the `database.sql` script and ensure the `animal` table includes the `date` and `origin` columns.
3. Configure the database connection in `application.properties`.
4. Set the `DB_PASSWORD` environment variable with your Postgre