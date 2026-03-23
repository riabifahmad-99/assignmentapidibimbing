Product Management REST API

📌 Project Overview

This project is a Product Management REST API built using Spring Boot. The application allows managing product data such as adding products, editing products, updating stock, and deleting products.

In this project, Spring Data JPA is integrated with MySQL to handle data persistence. The main focus of this assignment is to understand backend architecture, data validation, and database integration using a clean layered approach.

🛠️ Tech Stack

Java
Spring Boot
Spring Web (API)
Spring Data JPA
MySQL
📦 Dependencies (pom.xml)

This project includes several important dependencies:

MySQL Connector = Used to connect the application with the MySQL database.
Spring Data JPA = Used to access and manage data in the database using ORM.
Spring Web = Used to build RESTful APIs and handle HTTP requests.
These dependencies enable the application to expose APIs and interact with the database properly.

🧱 Project Architecture

Controller → Service → DTO → Entity → Repository → Database

📂 Layer Explanation

🔹 Controller Layer

Handles incoming HTTP requests and outgoing responses. Controllers only act as API endpoints and do not contain business logic.

🔹 Service Layer

The service layer focuses on:

Validating incoming data
Mapping data from DTO to Entity
Handling business rules
Managing error handling using ResponseStatusException
All validation and logic are centralized in this layer.

🔹 DTO Layer

DTOs are used to receive and transfer data from client requests. DTOs in this project: ProductDTO = Used for adding and editing product data. UpdateProductDTO = Used to update product stock, containing the sold variable.

DTOs ensure that only required data is processed by the service layer.

🔹 Entity Layer

Represents the database structure and acts as a mapping between Java objects and database tables.

🔹 Repository Layer

Handles database operations using Spring Data JPA. Acts as a bridge between the service layer and the database.

🔹 Custom Exception Layer

Handles application errors consistently across all endpoints.

CustomException Implemented using @RestControllerAdvice to handle:

400 Bad Request 500 Internal Server Error
