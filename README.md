# PRO3 – Slaughterhouse System

## Description

This project is a slaughterhouse system developed in Java using Spring Boot, PostgreSQL, REST and gRPC.

The system has three stations:

1. **Animal Registration:** Registers animals and stores their information.
2. **Cutting:** Registers animal parts and puts them into trays.
3. **Packaging:** Creates products and connects them with trays.

The system also allows us to track which animals were used in a product and which products contain parts from a specific animal.

## Technologies

- Java and Spring Boot
- PostgreSQL
- REST API
- gRPC
- Maven
- Postman for testing

## Database

The system uses PostgreSQL to store information about animals, animal parts, trays and products.

The database tables are created using the `database.sql` file.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Create and configure the PostgreSQL database.
3. Run the `database.sql` file.
4. Check the database connection in `application.properties`.
5. Run the Spring Boot application.
6. Use Postman to test the system.

REST runs on port `8080` and gRPC on port `9090`.

## Testing

I tested the system using Postman.

The following tests were successful:

- Register animal parts.
- Create trays.
- Add parts to trays.
- Check that the tray maximum weight is not exceeded.
- Check that different meat types cannot be mixed in one tray.
- Create products.
- Add trays to products.
- Get animals by product using gRPC.
- Get products by animal using gRPC.

For example, I created a product called **Beef Package** using meat from animal 1001.

Using gRPC, I could find animal 1001 from the product ID and also find the product using the animal registration number.

## Architecture

The architecture is documented using three C4 diagrams:

- C1 – System Context
- C2 – Container
- C3 – Component

## Conclusion

The three stations work together to register animals, manage meat parts and trays, and create products.

The system uses REST for the station operations and gRPC for traceability.

The main functionalities were tested successfully using Postman.