Delivery Service User Management
Project description
This project is a system for managing user accounts for a delivery service. Users can register, view their accounts and delete their accounts according to GDPR requirements. The system is built with Spring Boot and uses MySQL as a database to store user information. The project follows a client-server model where a Java console application is used to interact with the server via HTTP requests.

Functions
Create account: Users can register with username, email address and password.
View Account: Users can view their account information based on the username.
Delete Account: Users can delete their account, which deletes their data from the database.
Password security: Passwords are hashed before being saved in the database to ensure data security.
Technologies
Java 21
Spring Boot
Spring Data JPA
MySQL
JPA/Hibernate for database management
Maven for project building and dependency management
System architecture
Server-side application: Spring Boot is used to create a REST API that handles user registration, display and deletion.
Console Client Application: A Java console application that sends HTTP requests to the server to manage user data.
