# Slaughterhouse – PRO3 Assignment 2

## Project Description

This project is a simulation of a slaughterhouse developed for the PRO3 course at VIA University College.

The purpose of Assignment 2 is to implement a gRPC service that retrieves traceability information about animals and products from a PostgreSQL database.

The service supports two operations:
- **GetAnimalsByProduct:** retrieves the registration numbers of all animals involved in a specific product.
- **GetProductsByAnimal:** retrieves all products associated with a specific animal.

## Technologies

- Java
- Spring Boot
- Maven
- gRPC and Protocol Buffers
- PostgreSQL
- Spring JDBC
- BloomRPC for testing

## Project Structure

The application is organized into the following packages:

- **model:** contains the Animal and Product classes.
- **repository:** contains the database queries.
- **service:** contains the application logic.
- **grpc:** handles gRPC requests and responses.

The `slaughterhouse.proto` file defines the gRPC service and its messages.

## Database

The project uses PostgreSQL and three tables:

- `animal`
- `product`
- `animal_product`

The `animal_product` table connects animals and products.

The `database.sql` file contains the SQL statements needed to create the tables and insert sample data.

## How to Run

1. Create a PostgreSQL database.
2. Execute the `database.sql` script.
3. Configure the database connection in `application.properties`.
4. Set the `DB_PASSWORD` environment variable with your PostgreSQL password.
5. Run `SlaughterhouseApplication.java` in IntelliJ IDEA.

The gRPC server runs on port `9090`.

## Testing

Both gRPC operations were tested using BloomRPC.

**Test 1 – GetAnimalsByProduct**

Request: `product_id = 501`

Expected result: animal registration numbers `1001` and `1002`.

**Test 2 – GetProductsByAnimal**

Request: `registration_number = 1001`

Expected result: products `501` (Beef Package) and `502` (Mixed Package).

Screenshots of the successful tests are available in the `tests` folder.

## Architecture

The `diagrams` folder contains the following architecture diagrams:

- **C1 – System Context Diagram**
- **C2 – Container Diagram**
- **C3 – Component Diagram**

These diagrams describe the system, its main containers and the internal structure of the traceability service.
